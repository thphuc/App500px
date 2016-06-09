package phuc.test.app500px.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author phuc.tran
 */
public class Photo {
    private int category;
    @SerializedName("image_url")
    private String photoUrl;
    private String author;
    @SerializedName("name")
    private String title;
    private int width;
    private int height;
    private User user;

    public Photo(String photoUrl, String author, String title) {
        this.photoUrl = photoUrl;
        this.author = author;
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getAuthor() {
        if (user != null) {
            return user.getUsername();
        }
        return "";
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCategory() {
        return category;
    }
}
