package navyblue.top.colortalk.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.base.BaseFragment;

/**
 * Created by CIR on 16/5/19.
 */
public class AboutFragment extends BaseFragment {

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.mipmap.rainbow_icon)
                .addItem(new Element().setTitle("Version 1.0.0"))
                .addItem(new Element().setTitle("Advertise with us"))
                .addGroup("Connect with us")
                .addEmail("yongbiaoai@gmail.com")
                .addWebsite("http://navyblue.top/")
                .addPlayStore("com.ideashower.readitlater.pro")
                .addGitHub("YogiAi")
                .addItem(getCopyRightsElement())
                .create();
        return aboutPage;
    }

    private Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIcon(R.drawable.about_icon_copy_right);
        copyRightsElement.setColor(ContextCompat.getColor(getContext(), mehdi.sakout.aboutpage.R.color.about_item_icon_color));
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}
