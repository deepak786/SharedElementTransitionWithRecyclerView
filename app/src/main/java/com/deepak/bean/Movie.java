package com.deepak.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author: Rockstar
 * Created by: ModelGenerator on Sep 06,2016
 */
public class Movie implements Serializable {


    private String title;
    private String image;
    private double rating;
    private int releaseYear;
    private List<String> genre;

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}