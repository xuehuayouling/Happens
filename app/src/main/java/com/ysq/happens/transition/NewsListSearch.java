package com.ysq.happens.transition;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public class NewsListSearch extends NewsSearch implements NewsSearchThread.NewsSearchThreadEndListener {
    private String mUrl;
    private NewsListSearchEndListener mListener;
    public interface NewsListSearchEndListener extends NewsSearhchEndListener{
        @Override
        void onNeasSearchEnd(Object list);
    }
    public NewsListSearch(String url, NewsListSearchEndListener listener) {
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
        if (document == null) return;
        List<News> list = new ArrayList<News>();
        HashMap<String, String> map = new HashMap<String, String>();
        Elements elements = document.getElementsByAttributeValueContaining("href", "news.163.com/15");
        for (Element element : elements) {
            String linkHref = element.attr("href");
            String linkText = element.text();
            if (!map.containsKey(linkHref)) {
                map.put(linkHref, linkText);
            }
        }
        for (String key : map.keySet()) {
            News news = new News();
            news.setTitle(map.get(key));
            news.setSource(key);
            list.add(news);
        }
        mListener.onNeasSearchEnd(list);
    }
}
