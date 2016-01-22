package navyblue.top.colortalk.db.beans;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;

import java.util.List;

import navyblue.top.colortalk.mvp.models.User;

/**
 * Created by CIR on 16/1/22.
 */
@Table(name = "account")
public class AccountBean extends Model {

    private final static int NO_ACCOUNT = 0;

    @Expose
    @Column(name = "user_id")
    public int userID;
    @Column(name = "email")
    public String email;
    @Column(name = "name")
    public String name;
    @Column(name = "gender")
    public int gender;
    @Column(name = "birthday")
    public String birthday;
    @Column(name = "is_third")
    public int isThird;
    @Column(name = "uid")
    public int uid;

    public AccountBean(){}

    public AccountBean(User user){
        this.userID = user.getId();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.gender = user.getGender();
        this.isThird = user.getIsThird();
    }

    public int getUserID(){
        return userID;
    }

    public static void cacheAccountInfo(User user){
        AccountBean accountBean = new AccountBean(user);
        accountBean.save();
    }

    public static void logoutAccount(){
        long id = getAccountID();
        if(id != NO_ACCOUNT){
            AccountBean.delete(AccountBean.class, id);
        }
    }

    public static boolean existAccountCache(){
        List<AccountBean> accounts = getAllAccounts();
        return (accounts.size() == 1);
    }

    public static AccountBean getCachedAccount(){
        List<AccountBean> accounts = getAllAccounts();
        return accounts.get(0);
    }

    public static long getAccountID(){
        if(existAccountCache()){
            List<AccountBean> accounts = getAllAccounts();
            return accounts.get(0).getId();
        }
        return NO_ACCOUNT;
    }

    public static List<AccountBean> getAllAccounts(){
        return new Select().from(AccountBean.class).execute();
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", isThird=" + isThird +
                ", uid=" + uid +
                '}';
    }
}
