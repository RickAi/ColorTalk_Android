package navyblue.top.colortalk.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import navyblue.top.colortalk.R;
import navyblue.top.colortalk.ui.fragments.ChatListFragment;
import navyblue.top.colortalk.ui.fragments.MainFragment;
import navyblue.top.colortalk.ui.fragments.PictureEditFragment;
import navyblue.top.colortalk.ui.fragments.PrivateGalleryFragment;
import navyblue.top.colortalk.ui.fragments.SettingFragment;
import navyblue.top.colortalk.util.PatternLockUtils;

import static navyblue.top.colortalk.util.LogUtil.logD;
import static navyblue.top.colortalk.util.LogUtil.makeLogTag;

/**
 * Created by CIR on 16/1/17.
 */
public abstract class DrawerActivity extends ToolbarActivity {

    private static final String TAG = makeLogTag(DrawerActivity.class);

    protected static final int NAV_DRAWER_ITEM_INVALID = -1;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    FloatingActionButton fab;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
        setUpFAB();
    }

    private void setUpFAB() {
        fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setVisibility(View.VISIBLE);
    }

    /**
     * Sets up the navigation drawer.
     */
    private void setupNavDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout == null) {
            // current activity does not have a drawer.
            return;
        }

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerSelectListener(navigationView);
            setSelectedItem(navigationView);
            navigationView.setCheckedItem(R.id.nav_quotes);
        }

        logD(TAG, "navigation drawer setup finished");
    }

    /**
     * Updated the checked item in the navigation drawer
     * @param navigationView the navigation view
     */
    private void setSelectedItem(NavigationView navigationView) {
        // Which navigation item should be selected?
        int selectedItem = getSelfNavDrawerItem(); // subclass has to override this method
        navigationView.setCheckedItem(selectedItem);
    }

    /**
     * Creates the item click listener.
     * @param navigationView the navigation view
     */
    private void setupDrawerSelectListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        drawerLayout.closeDrawers();
                        onNavigationItemClicked(menuItem.getItemId());
                        return true;
                    }
                });
    }

    /**
     * Handles the navigation item click.
     * @param itemId the clicked item
     */
    private void onNavigationItemClicked(final int itemId) {
        if(itemId == getSelfNavDrawerItem()) {
            // Already selected
            closeDrawer();
            return;
        }

        goToNavDrawerItem(itemId);
    }

    /**
     * Handles the navigation item click and starts the corresponding activity.
     * @param item the selected navigation item
     */
    private void goToNavDrawerItem(int item) {
        fab.setVisibility(View.GONE);

        switch (item) {
            case R.id.nav_quotes:
                loadMain();
                break;
            case R.id.nav_samples:
                loadChatList();
                break;
            case R.id.nav_settings:
                if(PatternLockUtils.hasPattern(this)){
                    PatternLockUtils.confirmPatternIfHas(this);
                } else{
                    loadPrivateGallery();
                }
                break;
            case R.id.nav_edit:
                loadSettingContent();
                break;
            case R.id.nav_picture_edit:
                loadPictureEdit();
                break;
        }
    }

    private void loadPictureEdit() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PictureEditFragment.newInstance()).commitAllowingStateLoss();
        fab.setVisibility(View.VISIBLE);
        navigationView.setCheckedItem(R.id.nav_picture_edit);
    }

    private void loadMain(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commitAllowingStateLoss();
        fab.setVisibility(View.VISIBLE);
        navigationView.setCheckedItem(R.id.nav_quotes);
    }

    private void loadChatList(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ChatListFragment.newInstance(this)).commit();
        navigationView.setCheckedItem(R.id.nav_samples);
    }

    private void loadPrivateGallery(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PrivateGalleryFragment.newInstance()).commitAllowingStateLoss();
        navigationView.setCheckedItem(R.id.nav_settings);
    }

    private void loadSettingContent(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, SettingFragment.newInstance()).commit();
        navigationView.setCheckedItem(R.id.nav_edit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PatternLockUtils.REQUEST_CODE_CONFIRM_PATTERN && resultCode != Activity.RESULT_OK) {
            loadMain();
        } else{
            loadPrivateGallery();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Returns the navigation drawer item that corresponds to this Activity. Subclasses
     * have to override this method.
     */
    protected int getSelfNavDrawerItem() {
        return NAV_DRAWER_ITEM_INVALID;
    }

    protected void openDrawer() {
        if(drawerLayout == null)
            return;

        drawerLayout.openDrawer(GravityCompat.START);
    }

    protected void closeDrawer() {
        if(drawerLayout == null)
            return;

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
