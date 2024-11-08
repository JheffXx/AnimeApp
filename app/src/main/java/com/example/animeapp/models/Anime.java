package com.example.animeapp.models;

public class Anime {
    private String title;
    private Aired aired;  // Aired es un objeto con 'from' y 'to'
    private int episodes;
    private String imageUrl;  // Se cambia 'image_url' a 'imageUrl' para usar la convención camelCase
    private int malId;  // Añadido el malId para que se pueda acceder al ID del anime

    // Getter y setters para cada campo
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Aired getAired() {
        return aired;
    }

    public void setAired(Aired aired) {
        this.aired = aired;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMalId() {
        return malId;  // Método para acceder al ID del anime
    }

    public void setMalId(int malId) {
        this.malId = malId;  // Establecer el ID del anime
    }
}