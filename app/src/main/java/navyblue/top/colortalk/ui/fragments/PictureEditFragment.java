package navyblue.top.colortalk.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import navyblue.top.colortalk.R;
import navyblue.top.colortalk.mvp.presenter.impl.PictureEditPresenter;
import navyblue.top.colortalk.mvp.view.abs.IPictureEditView;
import navyblue.top.colortalk.ui.base.BaseFragment;

/**
 * Created by CIR on 16/3/22.
 */
public class PictureEditFragment extends BaseFragment implements IPictureEditView {

    // 步骤1. 使用Android Studio 导入TUSDK demo， 在手机上运行成功
    // 步骤2. 熟悉demo 的功能，找到我们需要的功能
    // 步骤3. 找到相关的功能代码，试着把他们放到这个类，期间可能需要拷贝类与新建类
    // 步骤4. 反复运行与测试


    private PictureEditPresenter mPresenter;

    public static PictureEditFragment newInstance() {
        return new PictureEditFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateAndBind(inflater, container, R.layout.fragment_picture_edit);

        // TODO: 在这里find views
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
