package it.handart.backend.domain.rest.response;

import it.handart.backend.domain.rest.Artwork;

import java.io.Serializable;

public class ArtworkResponse implements Serializable {

    private String id;
    private String slug;
    private String title;
    private String category;
    private String date;

    public ArtworkResponse(Artwork artwork){
        this.id = artwork.getId();
        this.slug = artwork.getSlug();
        this.title = artwork.getTitle();
        this.category = artwork.getCategory();
        this.date = artwork.getDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
