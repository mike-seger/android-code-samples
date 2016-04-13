package com.codexpedia.heycouchbaselite;

/**
 * Created by peng on 4/10/16.
 */
public class Movie {
    private String title;
    private String overview;
    private int length;

    public Movie(String title, String overview, int length) {
        this.title = title;
        this.overview = overview;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
