package navyblue.top.colortalk.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CIR on 16/5/18.
 */
public class UserInfo {

    /**
     * id : 1
     * nickname : Witness
     * icon_url :
     * user_id : 1
     * created_at : -0001-11-30 00:00:00
     * updated_at : -0001-11-30 00:00:00
     */

    @SerializedName("id")
    private String id;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("icon_url")
    private String iconUrl;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public void setId(String id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
