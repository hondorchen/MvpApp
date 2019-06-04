package com.hondorchen.mvpapp.module.home;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hondorchen.mvpapp.R;
import com.hondorchen.mvpapp.module.base.BaseActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;

/**
 * 主界面
 */
public class HomeActivity extends BaseActivity {


    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.nav_view)
    com.google.android.material.navigation.NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private final long EXIT_INTERVAL = 2000;
    private long mLastExitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {

    }

    /**
     * 连续两次返回键退出程序
     */
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - mLastExitTime > EXIT_INTERVAL) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mLastExitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }

}
