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
    @SerializedName("meta")
    private PageInfo pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setData(List<Moment> data) {
        this.data = data;
    }

    public List<Moment> getData() {
        return data;
    }

    public int getTotalPage(){
        return pageInfo.getPagination().getTotalPages();
    }

    public int getCurrentPage(){
        return pageInfo.getPagination().getCurrentPage();
    }

}
