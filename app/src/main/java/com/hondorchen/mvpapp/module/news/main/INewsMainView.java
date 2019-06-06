package com.hondorchen.mvpapp.module.news.main;

import com.hondorchen.mvpapp.db.entity.NewsTypeEntity;

import java.util.List;

/**
 * Created by hondor on 2019/6/5
 */
public interface INewsMainView {
    /**
     * 显示数据
     * @param checkList     选中栏目
     */
    void loadData(List<NewsTypeEntity> checkList);
}
