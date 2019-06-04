package com.hondorchen.mvpapp.module.home;

import android.content.Intent;

import com.hondorchen.mvpapp.R;
import com.hondorchen.mvpapp.module.base.BaseActivity;
import com.hondorchen.mvpapp.util.RxHelper;
import com.hondorchen.mvpapp.widget.SimpleButton;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;

    private boolean skipable = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews() {
        RxHelper.countcown(5)
                .compose(this.bindToLifecycle()) //管理生命周期
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        mSbSkip.setText("跳过" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        sikp();
                    }

                    @Override
                    public void onComplete() {
                        sikp();
                    }
                });
    }

    private void sikp() {
        if (!skipable) {
            skipable = true;
            finish();
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @OnClick(R.id.sb_skip)
    public void onViewClicked() {
        sikp();
    }
}
