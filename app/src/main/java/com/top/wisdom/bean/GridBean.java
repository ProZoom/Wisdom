package com.top.wisdom.bean;

public class GridBean {

    private int iconResID;

    private int titleResID;


    private String icon;

    private String title;


    public GridBean(int iconResID, int titleResID) {
        this.iconResID = iconResID;
        this.titleResID = titleResID;
        this.title=title;

    }

    public GridBean(int iconResID, String title) {
        this.iconResID = iconResID;
        this.titleResID = titleResID;
        this.title=title;
    }



    public GridBean(String icon, String title) {
        this.icon = icon;
        this.title = title;
        this.titleResID = titleResID;

    }

    public int getIconResID() {
        return iconResID;
    }

    public void setIconResID(int iconResID) {
        this.iconResID = iconResID;
    }

    public int getTitleResID() {
        return titleResID;
    }

    public void setTitleResID(int titleResID) {
        this.titleResID = titleResID;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
