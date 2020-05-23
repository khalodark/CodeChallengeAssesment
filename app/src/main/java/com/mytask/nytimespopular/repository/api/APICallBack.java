package com.mytask.nytimespopular.repository.api;

public interface APICallBack<T> {

    void onSuccess(T response);

    void onError(String error, int errorCode);
}
