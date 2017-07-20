package com.library.libraryoccupyseat.bean;

import cn.bmob.v3.BmobObject;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public class DreamUser extends BmobObject {

    private String dreamUser;
    private String dreamPassword;

    public String getDreamUser() {
        return dreamUser;
    }

    public void setDreamUser(String dreamUser) {
        this.dreamUser = dreamUser;
    }

    public String getDreamPassword() {
        return dreamPassword;
    }

    public void setDreamPassword(String dreamPassword) {
        this.dreamPassword = dreamPassword;
    }
}
