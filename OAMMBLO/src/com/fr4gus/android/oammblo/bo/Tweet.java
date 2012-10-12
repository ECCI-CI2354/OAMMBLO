package com.fr4gus.android.oammblo.bo;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Tweet {
    public static final String TABLE_NAME = "tweet";

    public static final String[] COLUMNS = new String[] { Columns.ID, Columns.USER, Columns.CONTENT };

    public static interface Columns {
        String ID = "_id";

        String USER = "user_id";

        String CONTENT = "content";
    }

    private long id;

    @SerializedName("user")
    private User author;

    @SerializedName("text")
    private String content;

    private Date datetime;

    private boolean reply;

    public Tweet() {
    }

    public Tweet(User author, String content, Date datetime) {
        this(author, content, datetime, false);
    }

    public Tweet(User author, String content, Date datetime, boolean isReply) {
        super();
        this.author = author;
        this.content = content;
        this.datetime = datetime;
        this.reply = isReply;
    }

    public Tweet(long id, User author, String content, Date datetime, boolean isReply) {
        super();
        this.id = id;
        this.author = author;
        this.content = content;
        this.datetime = datetime;
        this.reply = isReply;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean isReply() {
        return reply;
    }

    public void setReply(boolean reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Tweet [author=" + author + ", content=" + content + ", datetime=" + datetime + "]";
    }

}
