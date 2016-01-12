package navyblue.top.colortalk.app;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by CIR on 16/1/12.
 */
public class ColorTalkApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {
        //新浪微博
        PlatformConfig.setSinaWeibo("2866580998", "0bb01987aa2965717cae207eec76b2e4");

        //微信    wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
    }
}
