package com.uas.wallpaperhdfiks.Model;

public class CategoryItem {

    public String image,name;

    public CategoryItem(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public CategoryItem(){

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
