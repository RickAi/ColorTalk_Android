package navyblue.top.colortalk.mvp.models;

import com.google.gson.annotations.SerializedName;

import navyblue.top.colortalk.rest.models.UserInfo;

/**
 * Created by CIR on 16/1/16.
 */
public class Moment {

    /**
     * id : 1
     * text : fd
     * user_id : 1
     * image : {"id":1,"image_url":"7xkmui.com1.z0.glb.clouddn.com/kfjdsk","user_id":1,"type":0}
     */

    private UserInfo userInfo;
    @SerializedName("id")
    private int id;
    @SerializedName("text")
    private String text;
    @SerializedName("user_id")
    private int userId;
    /**
     * id : 1
     * image_url : 7xkmui.com1.z0.glb.clouddn.com/kfjdsk
     * user_id : 1
     * type : 0
     */

    @SerializedName("image")
    private Image image;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getUserId() {
        return userId;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                ", image=" + image +
                '}';
    }
}
