package com.ysq.happens.ui;

import com.ysq.happens.database.NewsProvider;

/**
 * Created by Administrator on 2015/6/28.
 */
public class Util {
    public static final String[] NEWS_LIST_PROJECTS = {
            "_id",
            NewsProvider.NEWS_TITLE,
            NewsProvider.NEWS_SOURCE,
            NewsProvider.NEWS_AUTHOR,
            NewsProvider.NEWS_BODY,
            NewsProvider.NEWS_DATE,
            NewsProvider.NEWS_OVERVIEW
    };
}
