package navyblue.top.colortalk.app;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.activeandroid.ActiveAndroid;
import com.umeng.socialize.PlatformConfig;

import io.rong.imkit.RongIM;
import navyblue.top.colortalk.db.beans.AccountBean;
import navyblue.top.colortalk.util.ToastUtils;
import us.pinguo.edit.sdk.PGEditImageLoader;
import us.pinguo.edit.sdk.base.PGEditSDK;

/**
 * Created by CIR on 16/1/12.
 */
public class ColorTalkApp extends MultiDexApplication {

    public static AccountBean sAccount = null;
    public static boolean postedImage;
    public static boolean logoutFlag;

    //各个平台的配置，建议放在全局Application或者程序入口
    {
        //新浪微博
        PlatformConfig.setSinaWeibo("2866580998", "0bb01987aa2965717cae207eec76b2e4");

        //微信    wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
        }
        ActiveAndroid.initialize(this);
        ToastUtils.register(this);
        PGEditImageLoader.initImageLoader(this);
        PGEditSDK.instance().initSDK(this);
    }

    public static int getUserID(){
        if(sAccount == null){
            return 0;
        }
        return sAccount.getUserID();
    }

    public static String getRongToken(){
        if(sAccount == null){
            return "";
        }
        return sAccount.token;
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
