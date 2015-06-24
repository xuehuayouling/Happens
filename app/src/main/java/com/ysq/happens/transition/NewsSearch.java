package com.ysq.happens.transition;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

/**
 * Created by Administrator on 2015/6/24.
 */
public class NewsSearch extends Thread{
    private String mUrlStr;
    private HashSet<NewsSearchEndListener> mListeners = new HashSet<NewsSearchEndListener>();
    public interface NewsSearchEndListener {
        public void onSearchEnd(Document document);
    }
    public NewsSearch(String urlStr) {
        mUrlStr = urlStr;
    }

    public void addSearchEndListener(NewsSearchEndListener listener) {
        mListeners.add(listener);
    }
    @Override
    public void run() {
        String htmlString = getHtmlString(mUrlStr);
        Document document = Jsoup.parse(htmlString);
        for (NewsSearchEndListener l : mListeners) {
            l.onSearchEnd(document);
        }
//        super.run();
    }

    private String getHtmlString(String urlStr) {
        try {
            URL url = new URL(urlStr);
            URLConnection ucon = url.openConnection();
            InputStream instr = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(instr);
            ByteArrayBuffer baf = new ByteArrayBuffer(500);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            return EncodingUtils.getString(baf.toByteArray(), "gbk");
        } catch (Exception e) {
            return "";
        }
    }
}
