package com.library.libraryoccupyseat.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by weisi on 2017/7/21.
 */

public class AdminSetBean extends BmobObject implements Serializable{

    private String libraryName;
    private int libraryFloor;
    private int libraryWidth;
    private int user = 0;
    private static final long serialVersionUID = -7060210544600464481L;

    public void AdminSetBean(){
        this.setTableName("AdminSet");
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public int getLibraryFloor() {
        return libraryFloor;
    }

    public void setLibraryFloor(int libraryFloor) {
        this.libraryFloor = libraryFloor;
    }

    public int getLibraryWidth() {
        return libraryWidth;
    }

    public void setLibraryWidth(int libraryWidth) {
        this.libraryWidth = libraryWidth;
    }
}
