package navyblue.top.colortalk.mvp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CIR on 16/1/16.
 */
public class Image {
    /**
     * id : 2
     * image_url : 7xkmui.com1.z0.glb.clouddn.com/kfjdsk
     * user_id : 1
     * type : 0
     */

    @SerializedName("id")
    private int id;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("type")
    private int type;

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getUserId() {
        return userId;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", userId=" + userId +
                ", type=" + type +
                '}';
    }
}
