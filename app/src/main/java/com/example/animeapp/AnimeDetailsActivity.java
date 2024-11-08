package com.example.animeapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.adapters.EpisodeAdapter;
import com.example.animeapp.models.Episode;
import com.example.animeapp.api.ApiClient;
import com.example.animeapp.api.JikanApiService;
import com.example.animeapp.models.EpisodeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimeDetailsActivity extends AppCompatActivity {

    private RecyclerView episodeRecyclerView;
    private EpisodeAdapter episodeAdapter;
    private List<Episode> episodeList = new ArrayList<>();
    private int animeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_details);

        // Obtener el ID del anime que se pasa desde MainActivity
        animeId = getIntent().getIntExtra("anime_id", -1);

        // Inicializar el RecyclerView para los episodios
        episodeRecyclerView = findViewById(R.id.recyclerView_episodes);
        episodeAdapter = new EpisodeAdapter(this, episodeList);
        episodeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        episodeRecyclerView.setAdapter(episodeAdapter);

        // Cargar los episodios del anime
        getEpisodes(animeId);
    }

    private void getEpisodes(int animeId) {
        JikanApiService apiService = ApiClient.getJikanApiService();
        Call<EpisodeResponse> call = apiService.getEpisodes(animeId, 1);  // Cargar los episodios de la primera página

        call.enqueue(new Callback<EpisodeResponse>() {
            @Override
            public void onResponse(Call<EpisodeResponse> call, Response<EpisodeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EpisodeResponse episodeResponse = response.body();
                    List<Episode> episodes = episodeResponse.getEpisodes();

                    if (episodes != null && !episodes.isEmpty()) {
                        episodeList.clear(); // Limpiar antes de agregar nuevos episodios
                        episodeList.addAll(episodes);
                        episodeAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(AnimeDetailsActivity.this, "No se encontraron episodios", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("AnimeDetailActivity", "Error en la respuesta de la API: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<EpisodeResponse> call, Throwable t) {
                Log.e("AnimeDetailActivity", "Error de conexión: " + t.getMessage());
            }
        });
    }
}