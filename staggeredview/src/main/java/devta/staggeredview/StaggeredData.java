package devta.staggeredview;

import android.support.annotation.NonNull;

/**
 * author @Divyanshu Tayal
 */
public class StaggeredData {

    private int itemID;
    private String title, imageUri;

    public StaggeredData() {}

    public StaggeredData(int id, @NonNull String title, @NonNull String imageUri){
        this.itemID = id;
        this.title = title;
        this.imageUri = imageUri;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(@NonNull String imageUri) {
        this.imageUri = imageUri;
    }
}
