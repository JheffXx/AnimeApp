package com.example.animeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.animeapp.R;
import com.example.animeapp.models.Episode;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    private Context context;
    private List<Episode> episodeList;

    public EpisodeAdapter(Context context, List<Episode> episodeList) {
        this.context = context;
        this.episodeList = episodeList;
    }

    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_episode, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        holder.episodeTitle.setText(episode.getTitle());
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {

        TextView episodeTitle;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            episodeTitle = itemView.findViewById(R.id.episode_title);
        }
    }
}