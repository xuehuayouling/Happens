package com.ysq.happens.transition;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2015/6/28.
 */
public class NewsDetailsSearch extends NewsSearch implements NewsSearchThread.NewsSearchThreadEndListener {
    private static final String TAG = "NewsDetailsSearch";
    private String mUrl;
    private NewsDetailsSearchEndListener mListener;
    public interface NewsDetailsSearchEndListener extends NewsSearhchEndListener {
        @Override
        void onNeasSearchEnd(Object news);
    }

    public NewsDetailsSearch(String url, NewsDetailsSearchEndListener listener) {
        mUrl = url;
        mListener = listener;
    }

    @Override
    public void search() {
        NewsSearchThread thread = new NewsSearchThread(mUrl);
        thread.addSearchEndListener(this);
        thread.start();
    }

    @Override
    public void onSearchThreadEnd(Document document) {
        News news = new News();
        news.setSource(mUrl);
        Elements elements = document.getElementsByAttributeValue("class", "article-body");
        for (Element e : elements) {
            StringBuffer stringBuffer = new StringBuffer();
            for (Element element :e.getElementsByTag("p")) {
                if (!element.toString().contains("<img")) {
                    stringBuffer.append(element.toString() + "\n");
                }
            }
            news.setBody(stringBuffer.toString());
            Log.d(TAG, e.toString());
            break;
        }
        mListener.onNeasSearchEnd(news);
    }
}
