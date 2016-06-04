package navyblue.top.colortalk.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;

import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.fragments.SampleSlide;

/**
 * Created by CIR on 16/6/4.
 */
public class AppIntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setFlowAnimation();

        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro_2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        finish();
    }
}
