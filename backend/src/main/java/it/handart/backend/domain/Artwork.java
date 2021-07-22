package it.handart.backend.domain;

public class Artwork {

    private String id;
    private String slug;
    private String title;
    private String category;
    private String date;

    public Artwork(String id, String slug, String title, String category, String date) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.category = category;
        this.date = date;
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
