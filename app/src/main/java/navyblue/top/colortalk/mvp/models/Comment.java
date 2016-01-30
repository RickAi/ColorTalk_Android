package navyblue.top.colortalk.mvp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CIR on 16/1/30.
 */
public class Comment {

    /**
     * user_id : 1
     * moment_id : 1
     * text : fd
     * updated_at : 2016-01-30 13:06:09
     * created_at : 2016-01-30 13:06:09
     * id : 2
     */

    @SerializedName("user_id")
    private String userId;
    @SerializedName("moment_id")
    private String momentId;
    @SerializedName("text")
    private String text;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("id")
    private int id;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public String getMomentId() {
        return momentId;
    }

    public String getText() {
        return text;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
}
