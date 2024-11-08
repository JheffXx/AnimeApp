package com.example.animeapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.adapters.AnimeAdapter;
import com.example.animeapp.models.Anime;
import com.example.animeapp.api.ApiClient;
import com.example.animeapp.api.JikanApiService;
import com.example.animeapp.models.AnimeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView animeRecyclerView;
    private AnimeAdapter animeAdapter;
    private List<Anime> animeList = new ArrayList<>();
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el RecyclerView
        animeRecyclerView = findViewById(R.id.recyclerView_animes);
        animeAdapter = new AnimeAdapter(this, animeList);
        animeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        animeRecyclerView.setAdapter(animeAdapter);

        // Cargar los animes de la primera página
        getAnimes(currentPage);

        // Agregar el OnScrollListener para implementar el infinite scroll
        animeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Detectar si hemos llegado al final de la lista
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                // Si estamos en el final de la lista y no estamos cargando, cargamos más animes
                if (!recyclerView.isComputingLayout() && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    currentPage++;
                    getAnimes(currentPage);
                }
            }
        });
    }

    private void getAnimes(int page) {
        JikanApiService apiService = ApiClient.getJikanApiService();
        Call<AnimeResponse> call = apiService.getAnimes(page);

        call.enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AnimeResponse animeResponse = response.body();
                    List<Anime> animes = animeResponse.getData();

                    if (animes != null && !animes.isEmpty()) {
                        // Si es la primera página, limpiamos la lista de animes
                        if (page == 1) {
                            animeList.clear();
                        }
                        animeList.addAll(animes);

                        // Notificar al adaptador que los datos cambiaron
                        animeAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta de la API: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                Log.e("MainActivity", "Error de conexión: " + t.getMessage());
            }
        });
    }
}