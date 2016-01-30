package navyblue.top.colortalk.rest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import navyblue.top.colortalk.mvp.models.Comment;

/**
 * Created by CIR on 16/1/30.
 */
public class CommentResponse {

    @SerializedName("data")
    private List<Comment> data;
    @SerializedName("meta")
    private PageInfo pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }

    public List<Comment> getData() {
        return data;
    }

    public int getTotalPage(){
        return pageInfo.getPagination().getTotalPages();
    }

    public int getCurrentPage(){
        return pageInfo.getPagination().getCurrentPage();
    }
}
