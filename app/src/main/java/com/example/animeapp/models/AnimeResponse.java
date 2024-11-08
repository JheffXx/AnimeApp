package com.example.animeapp.models;

import java.util.List;

public class AnimeResponse {
    private List<Anime> data; // Los animes de la respuesta
    private Pagination pagination; // Información de paginación

    // Getters y setters
    public List<Anime> getData() {
        return data;
    }

    public void setData(List<Anime> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}