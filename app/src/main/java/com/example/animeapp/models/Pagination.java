package com.example.animeapp.models;

public class Pagination {
    private int last_visible_page; // Número de la última página visible
    private boolean has_next_page; // Si hay una página siguiente

    // Getters y setters
    public int getLastVisiblePage() {
        return last_visible_page;
    }

    public void setLastVisiblePage(int last_visible_page) {
        this.last_visible_page = last_visible_page;
    }

    public boolean hasNextPage() {
        return has_next_page;
    }

    public void setHasNextPage(boolean has_next_page) {
        this.has_next_page = has_next_page;
    }
}