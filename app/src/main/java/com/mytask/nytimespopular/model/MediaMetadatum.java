
package com.mytask.nytimespopular.model;

import com.google.gson.annotations.Expose;

public class MediaMetadatum {

    @Expose
    private String format;
    @Expose
    private int height;
    @Expose
    private String url;
    @Expose
    private int width;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
