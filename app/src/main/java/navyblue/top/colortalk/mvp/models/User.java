package navyblue.top.colortalk.mvp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CIR on 16/1/16.
 */
public class User {
    /**
     * id : 1
     * name : 超级管理员
     * email : admin@admin.com
     * is_third : 0
     * gender : 0
     * birthday : 0000-00-00
     */

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("is_third")
    private int isThird;
    @SerializedName("gender")
    private int gender;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("uid")
    private String uid;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsThird(int isThird) {
        this.isThird = isThird;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getIsThird() {
        return isThird;
    }

    public int getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }
}
