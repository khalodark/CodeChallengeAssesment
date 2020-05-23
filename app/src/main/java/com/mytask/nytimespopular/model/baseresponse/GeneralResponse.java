package com.mytask.nytimespopular.model.baseresponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * General Response From Server
 *
 * @param <T> - to be determined when user knows the type of the results
 */
public class GeneralResponse<T> {
    @SerializedName("status")
    private String status;
    @SerializedName("copyright")
    private String copyright;
    @SerializedName("num_results")
    private int num_results;
    @SerializedName("results")
    private List<T> resultResponseList;

    public GeneralResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public List<T> getResultResponseList() {
        return resultResponseList;
    }

    public void setResultResponseList(List<T> resultResponseList) {
        this.resultResponseList = resultResponseList;
    }
}
