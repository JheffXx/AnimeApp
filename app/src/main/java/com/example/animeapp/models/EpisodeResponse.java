package com.example.animeapp.models;

import java.util.List;

public class EpisodeResponse {
    private List<Episode> data;  // Lista de episodios
    private Pagination pagination;

    public EpisodeResponse() {
    }

    public EpisodeResponse(List<Episode> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    // Se renombra 'getData' a 'getEpisodes' para que coincida con la lógica en AnimeDetailsActivity
    public List<Episode> getEpisodes() {
        return data;  // Devuelve la lista de episodios
    }

    public void setEpisodes(List<Episode> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}