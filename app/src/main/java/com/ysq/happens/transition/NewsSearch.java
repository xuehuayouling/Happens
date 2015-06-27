package com.ysq.happens.transition;

/**
 * Created by Administrator on 2015/6/27.
 */
public abstract class NewsSearch {
    public interface NewsSearhchEndListener {
        public void onNeasSearchEnd(Object o);
    }
    public abstract void search();
}
