
package com.mytask.nytimespopular.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Medium {

    @SerializedName("approved_for_syndication")
    private int approvedForSyndication;
    @Expose
    private String caption;
    @Expose
    private String copyright;
    @SerializedName("media-metadata")
    private List<MediaMetadatum> mediaMetadata;
    @Expose
    private String subtype;
    @Expose
    private String type;

    public int getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(int approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<MediaMetadatum> getMediaMetadata() {
        if (mediaMetadata.size() > 0)
            return mediaMetadata;
        else
            return null;
    }

    public void setMediaMetadata(List<MediaMetadatum> mediaMetadata) {
        this.mediaMetadata = mediaMetadata;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
