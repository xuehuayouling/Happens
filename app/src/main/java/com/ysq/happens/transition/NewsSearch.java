package com.ysq.happens.transition;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

/**
 * Created by Administrator on 2015/6/24.
 */
public class NewsSearch extends Thread {
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
        // String htmlString = getHtmlString(mUrlStr);
//		String httpContent = getHttpContent(mUrlStr);
//		Document document = Jsoup.parse(httpContent);
        Document document = null;
        try {
            document = Jsoup.connect(mUrlStr).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (NewsSearchEndListener l : mListeners) {
            l.onSearchEnd(document);
        }
        // super.run();
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

    private String getHttpContent(String urlStr) {
        String content = "";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet(urlStr);
        try {
            HttpResponse httpResponse = httpClient.execute(get);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        entity.getContent(), "gbk"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    content += line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
