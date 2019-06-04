package com.hondorchen.mvpapp.module.base;

import android.os.Bundle;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initViews();
    }

    /**
     * 绑定布局文件
     * @return 布局文件id
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图
     */
    protected abstract void initViews();
}
