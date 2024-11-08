package com.example.animeapp.models;

public class Episode {
    private int episodeNumber;
    private String title;
    private String aired;

    // Constructor
    public Episode(int episodeNumber, String title, String aired) {
        this.episodeNumber = episodeNumber;
        this.title = title;
        this.aired = aired;
    }

    // Getters y setters
    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }
}