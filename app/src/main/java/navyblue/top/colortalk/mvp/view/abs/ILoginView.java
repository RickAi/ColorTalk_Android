package navyblue.top.colortalk.mvp.view.abs;

/**
 * Created by CIR on 16/1/13.
 */
public interface ILoginView extends IBaseView {

    void showProcess();
    void error();
    void hideProcess();
    void gotoMainActivity();
    void registerSuccess();
    void forgetSuccess();

}
