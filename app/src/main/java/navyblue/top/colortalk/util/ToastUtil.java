
package navyblue.top.colortalk.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Context context, int duration, @StringRes int resId, Object... args) {
        Toast.makeText(context, context.getString(resId, args), duration).show();
    }

    public static void shorts(Context context, @StringRes int resId, Object... args) {
        show(context, Toast.LENGTH_SHORT, resId, args);
    }

    public static void longs(Context context, @StringRes int resId, Object... args) {
        show(context, Toast.LENGTH_LONG, resId, args);
    }

}
