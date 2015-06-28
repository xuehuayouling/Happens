package com.ysq.happens.transition;

import android.content.ContentValues;
import android.text.TextUtils;

import com.ysq.happens.database.NewsProvider;

/**
 * Created by Administrator on 2015/6/25.
 */
public class News {
    private int id;
    private String title;
    private String overview;
    private String body;
    private long date = -1;
    private String source;
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        if (!TextUtils.isEmpty(title)) {
            values.put(NewsProvider.NEWS_TITLE, title);
        }
        if (!TextUtils.isEmpty(overview)) {
            values.put(NewsProvider.NEWS_OVERVIEW, overview);
        }
        if (!TextUtils.isEmpty(body)) {
            values.put(NewsProvider.NEWS_BODY, body);
        }
        if (date != -1) {
            values.put(NewsProvider.NEWS_DATE, date);
        }
        if (!TextUtils.isEmpty(source)) {
            values.put(NewsProvider.NEWS_SOURCE, source.trim());
        }
        if (!TextUtils.isEmpty(author)) {
            values.put(NewsProvider.NEWS_AUTHOR, author);
        }
        return values;
    }
}
