package com.hondorchen.mvpapp.module.home;

import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hondorchen.mvpapp.R;
import com.hondorchen.mvpapp.module.base.BaseActivity;
import com.hondorchen.mvpapp.module.news.main.NewsMainFragment;

import androidx.core.view.GravityCompat;
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
    private int mItemId = -1;
    private SparseArray<String> mSparseTags;

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
        mSparseTags = new SparseArray<>();
        mSparseTags.put(R.id.nav_news, "news");
        mSparseTags.put(R.id.nav_news, "pi");
        mSparseTags.put(R.id.nav_news, "video");
        initDrawerLayout();
        mNavView.setCheckedItem(R.id.nav_news);
    }

    private void initDrawerLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //将侧边栏顶部延伸至status bar
            mDrawerLayout.setFitsSystemWindows(true);
            //将主页面顶部延伸至status bar
            mDrawerLayout.setClipToPadding(false);
        }
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                switch (mItemId) {
                    case R.id.nav_news:
                        replaceFragment(R.id.fl_container, new NewsMainFragment(), mSparseTags.get(R.id.nav_news));
                        break;
                   /* case R.id.nav_photos:
                        replaceFragment(R.id.fl_container, new PhotoMainFragment(), mSparseTags.get(R.id.nav_photos));
                        break;
                    case R.id.nav_videos:
                        replaceFragment(R.id.fl_container, new VideoMainFragment(), mSparseTags.get(R.id.nav_videos));
                        break;*/
                    case R.id.nav_setting:
                        // TODO: 2019/6/4 跳转到设置界面
                        break;
                }
                mItemId = -1;
            }
        });
        mNavView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.isChecked()) {
                return true;
            }
            mItemId = menuItem.getItemId();
            mDrawerLayout.closeDrawer(GravityCompat.START);//触发onDrawerClosed回调
            return true;
        });
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
