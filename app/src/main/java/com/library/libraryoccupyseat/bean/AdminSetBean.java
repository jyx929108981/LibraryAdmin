package com.library.libraryoccupyseat.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by weisi on 2017/7/21.
 */

public class AdminSetBean extends BmobObject {

    private String libraryName;
    private String libraryFloor;
    private String libraryWidth;

    public void AdminSetBean(){
        this.setTableName("AdminSet");
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryFloor() {
        return libraryFloor;
    }

    public void setLibraryFloor(String libraryFloor) {
        this.libraryFloor = libraryFloor;
    }

    public String getLibraryWidth() {
        return libraryWidth;
    }

    public void setLibraryWidth(String libraryWidth) {
        this.libraryWidth = libraryWidth;
    }
}
