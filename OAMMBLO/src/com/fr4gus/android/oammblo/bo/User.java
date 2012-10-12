package com.fr4gus.android.oammblo.bo;

import com.google.gson.annotations.SerializedName;

public class User {
    public static final String TABLE_NAME = "user";

    public static final String[] COLUMNS = new String[] { Columns.ID, Columns.DISPLAY_NAME };

    public static interface Columns {
        String ID = "_id";

        String DISPLAY_NAME = "display_name";

    }

    private long id;

    @SerializedName("screen_name")
    private String displayName;

    public User(long id, String displayName) {
        super();
        this.id = id;
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
