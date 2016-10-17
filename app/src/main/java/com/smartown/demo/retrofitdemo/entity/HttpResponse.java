package com.smartown.demo.retrofitdemo.entity;

/**
 * 作者：Tiger
 * <p/>
 * 时间：2016-10-14 16:27
 * <p/>
 * 描述：
 */
public class HttpResponse<T> {

    private int count;
    private int start;
    private int total;
    private String title;
    private T subjects;

    public boolean isSuccess() {
        return true;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }

}
