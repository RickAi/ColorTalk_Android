package navyblue.top.colortalk.ui.base2;

import android.support.v7.app.AppCompatActivity;

import navyblue.top.colortalk.rest.ServiceFactory;
import navyblue.top.colortalk.rest.services.ColorTalkService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by drakeet on 8/9/15.
 */
public class BaseActivity extends AppCompatActivity {

    public static final ColorTalkService sColorTalkService = ServiceFactory.getColorTalkSingleton();

    private CompositeSubscription mCompositeSubscription;

    @Override protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }


    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }


//    @Override public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.action_about:
//                startActivity(new Intent(this, AboutActivity.class));
//                return true;
//            case R.id.action_login:
//                loginGitHub();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


//    protected void loginGitHub() {
//        new Once(this).show(R.string.action_github_login, () -> {
//            ToastUtils.showLongX2(getString(R.string.tip_login_github));
//        });
//        String url = getString(R.string.url_login_github);
//        Intent intent = WebActivity.newIntent(this, url,
//                getString(R.string.action_github_login));
//        startActivity(intent);
//    }
}
