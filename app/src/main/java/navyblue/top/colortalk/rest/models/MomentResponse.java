package navyblue.top.colortalk.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import navyblue.top.colortalk.mvp.models.Moment;

/**
 * Created by CIR on 16/1/16.
 */
public class MomentResponse {

    /**
     * id : 1
     * text : fd
     * user_id : 1
     * image : {"id":1,"image_url":"7xkmui.com1.z0.glb.clouddn.com/kfjdsk","user_id":1,"type":0}
     */

    @SerializedName("data")
    private List<Moment> data;

    public void setData(List<Moment> data) {
        this.data = data;
    }

    public List<Moment> getData() {
        return data;
    }

}
