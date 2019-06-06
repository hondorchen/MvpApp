package com.hondorchen.mvpapp.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by hondor on 2019/6/5
 * 新闻类别实体类
 */
@Entity
public class NewsTypeEntity {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String name;
    private String typeId;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String address) {
        this.typeId = address;
    }

    @Override
    public String toString() {
        return "NewsTypeEntity{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
