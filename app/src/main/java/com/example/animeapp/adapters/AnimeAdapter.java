package com.example.animeapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animeapp.AnimeDetailsActivity;
import com.example.animeapp.R;
import com.example.animeapp.models.Anime;
import com.example.animeapp.models.Aired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    private Context context;
    private List<Anime> animeList;

    public AnimeAdapter(Context context, List<Anime> animeList) {
        this.context = context;
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    private String formatDateString(String dateString) {
        try {
            // Formato de fecha de entrada que puede incluir la hora
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
            // Formato de salida solo con la fecha
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);  // Devuelve la fecha formateada
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";  // Si hay un error en el formato, devuelve "Invalid Date"
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);
        holder.titleTextView.setText(anime.getTitle());

        // Formatear solo la fecha (sin la hora)
        if (anime.getAired() != null && anime.getAired().getFrom() != null) {
            String formattedDate = formatDateString(anime.getAired().getFrom());
            holder.airedTextView.setText("Aired: " + formattedDate);
        } else {
            holder.airedTextView.setText("Aired: N/A");  // Si no hay fecha, muestra "N/A"
        }

        // Navegar a la actividad de detalles del anime
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AnimeDetailsActivity.class);
            intent.putExtra("anime_id", anime.getMalId());  // Asegúrate de tener un getter para MalId
            context.startActivity(intent);
        });

        // Mostrar la cantidad de episodios
        holder.episodesTextView.setText("Episodes: " + anime.getEpisodes());

        // Cargar la imagen usando Glide
        Glide.with(context)
                .load(anime.getImageUrl())  // Usa el nombre del campo y su getter
                .into(holder.animeImageView);  // Cargar la imagen en el ImageView
    }

    @Override
    public int getItemCount() {
        return animeList.size();  // Retorna el tamaño de la lista de animes
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView animeImageView;
        TextView titleTextView;
        TextView airedTextView;
        TextView episodesTextView;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            animeImageView = itemView.findViewById(R.id.imageView_anime);
            titleTextView = itemView.findViewById(R.id.textView_title);
            airedTextView = itemView.findViewById(R.id.textView_aired);
            episodesTextView = itemView.findViewById(R.id.textView_episodes);
        }
    }
}