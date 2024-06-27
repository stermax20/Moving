package com.moving.moving_app.crawler.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class CgvVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int bookcnt;

    @Column(nullable = false)
    private String hash;

    public CgvVO() {}

    public CgvVO(String title, String image, String genre, int bookcnt, String hash) {
        this.title = title;
        this.image = image;
        this.genre = genre;
        this.bookcnt = bookcnt;
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getBookcnt() {
        return bookcnt;
    }

    public void setBookcnt(int bookcnt) {
        this.bookcnt = bookcnt;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
