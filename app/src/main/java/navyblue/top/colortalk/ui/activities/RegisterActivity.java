package navyblue.top.colortalk.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;

import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.base.BaseLoginActivity;

// 步骤1. 参考register login的代码实例，借鉴他的xml, 替换已有的activity_register
// 步骤2. 完成注册界面，如果没有思路，可以参考LoginActivity 里的代码
// 步骤3. 优化activity_login，把界面调的好看点
// 步骤4. 替换点login 的视频文件，换成符合我们app 主题的video. video 文件是res/raw/welcome_video.mp4，替换时注意新文件名和旧文件名一样

/**
 * Created by CIR on 16/3/22.
 */
public class RegisterActivity extends BaseLoginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_register);
    }
}
