package com.uas.wallpaperhdfiks.Model;

public class WallpaperItem {

    public String categoryid,image;

    public WallpaperItem(){
        
    }

    public WallpaperItem(String categoryid, String image) {
        this.categoryid = categoryid;
        this.image = image;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
