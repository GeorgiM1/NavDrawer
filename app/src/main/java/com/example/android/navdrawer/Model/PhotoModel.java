package com.example.android.navdrawer.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pc on 1/16/2018.
 */

public class PhotoModel implements Serializable {
    int current_page;
    int total_pages;
    int total_items;
    ArrayList<Photos> photos;
    String feature;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public ArrayList<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photos> photos) {
        this.photos = photos;
    }
}
