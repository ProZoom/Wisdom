package com.top.wisdom.bean;

import java.util.List;

/**
 * @author azheng
 * @date 2018/6/4.
 * GitHub：https://github.com/RookieExaminer
 * Email：wei.azheng@foxmail.com
 * Description：登陆
 */
public class LoginBean {

    /**
     * collectIds : []
     * email :
     * icon :
     * id : 6300
     * password : test123321
     * type : 0
     * username : test123321
     */

    private int id;

    //邮箱
    private String email;
    //图像地址
    private String icon;
    //账号
    private String username;
    //密码
    private String password;
    //类型
    private int type;
    //？？？=
    private List<?> collectIds;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<?> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<?> collectIds) {
        this.collectIds = collectIds;
    }
}
