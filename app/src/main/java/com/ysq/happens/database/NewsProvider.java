package com.ysq.happens.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.ysq.happens.database.DatebaseHelper;

/**
 * Created by Administrator on 2015/6/25.
 */
public class NewsProvider extends ContentProvider {
    public static final String NEWS_TITLE = "title";
    public static final String NEWS_OVERVIEW = "overview";
    public static final String NEWS_BODY = "body";
    public static final String NEWS_DATE = "date";
    public static final String NEWS_SOURCE = "source";
    public static final String NEWS_AUTHOR = "author";
    public static final Uri NEWS_CONTENT_URI = Uri.parse("content://com.ysq.haappens/news");
    private DatebaseHelper mDatebaseHelper;
    @Override
    public boolean onCreate() {
        mDatebaseHelper = new DatebaseHelper(getContext());
        return true;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        SQLiteDatabase db = mDatebaseHelper.getWritableDatabase();
        db.beginTransaction();
        for (ContentValues value : values) {
            db.insert(DatebaseHelper.NEWS_TB_NAME, null, value);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return values.length;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDatebaseHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DatebaseHelper.NEWS_TB_NAME);
        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
                null, sortOrder == null ? NEWS_SOURCE + " DESC" : sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final long rowId = mDatebaseHelper.getWritableDatabase().insert(DatebaseHelper.NEWS_TB_NAME, null, values);
        if (rowId > 0) {
            Uri insertUri = ContentUris.withAppendedId(NEWS_CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri, null);
            return insertUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


}
