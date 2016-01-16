package navyblue.top.colortalk.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import navyblue.top.colortalk.mvp.models.User;

/**
 * Created by CIR on 16/1/16.
 */
public class UserResponse {


    @SerializedName("data")
    private List<User> data;

    public void setData(List<User> data) {
        this.data = data;
    }

    public List<User> getData() {
        return data;
    }

}
