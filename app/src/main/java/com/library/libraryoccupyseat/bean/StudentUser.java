package com.library.libraryoccupyseat.bean;

import cn.bmob.v3.BmobObject;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public class StudentUser extends BmobObject {

    private String userName;
    private String userPassword;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
