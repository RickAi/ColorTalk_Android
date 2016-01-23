package navyblue.top.colortalk.mvp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CIR on 16/1/23.
 */
public class RongToken {
    /**
     * token : fdRiQQU8ZHtt6UtmBPvRVkcmAXeBgG+vum7DR8J+8Olce3YB9m6sW2+O6xvgR8V7BZ2xzVg47DBTRqea42fQ+g==
     */

    @SerializedName("token")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
