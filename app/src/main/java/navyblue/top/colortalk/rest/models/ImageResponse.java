package navyblue.top.colortalk.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import navyblue.top.colortalk.mvp.models.Image;

/**
 * Created by CIR on 16/1/16.
 */
public class ImageResponse {
    @SerializedName("data")
    private List<Image> data;

    public void setData(List<Image> data) {
        this.data = data;
    }

    public List<Image> getData() {
        return data;
    }
}
