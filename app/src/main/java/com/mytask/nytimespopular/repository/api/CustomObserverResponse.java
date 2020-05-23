package com.mytask.nytimespopular.repository.api;


import android.content.res.Resources;

import com.mytask.nytimespopular.R;
import com.mytask.nytimespopular.base.NYTestApp;
import com.mytask.nytimespopular.model.baseresponse.GeneralResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class CustomObserverResponse<T> implements Observer<Response<GeneralResponse<T>>> {

    APICallBack apiCallBack;

    public CustomObserverResponse(APICallBack apiCallBack) {
        this.apiCallBack = apiCallBack;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Response<GeneralResponse<T>> generalResponseResponse) {
        if (generalResponseResponse.code() == 200) {
            if (generalResponseResponse.body().getStatus().equals("OK")) {
                this.apiCallBack.onSuccess(generalResponseResponse.body().getResultResponseList());
            }
        } else {
            int code = generalResponseResponse.code();
            switch (code) {
                case 400:
                case 401:
                case 403:
                    try {
                        if (generalResponseResponse.errorBody() != null) {
                            this.apiCallBack.onError((String) (new JSONObject(generalResponseResponse.errorBody().string()).get("message")), code);
                        } else
                            this.apiCallBack.onError(NYTestApp.getInstance().getApplicationContext()
                                    .getResources().getString(R.string.unauthorized), code);
                    } catch (Resources.NotFoundException | IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 404:
                case 405:
                    this.apiCallBack.onError(NYTestApp.getInstance().getApplicationContext()
                            .getResources().getString(R.string.error_request_type), code);
                    break;
                case 422:
                    try {
                        if (generalResponseResponse.errorBody() != null) {
                            this.apiCallBack.onError((String)
                                    (new JSONObject(generalResponseResponse.errorBody().string()).get("message")), code);
                        } else
                            this.apiCallBack.onError(NYTestApp.getInstance().getApplicationContext()
                                    .getResources().getString(R.string.error_parsing_entity), code);
                    } catch (Resources.NotFoundException | IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    this.apiCallBack.onError(NYTestApp.getInstance().getApplicationContext()
                            .getResources().getString(R.string.server_error), code);
            }
        }
    }


    @Override
    public void onError(Throwable e) {
        this.apiCallBack.onError(NYTestApp.getContext().getApplicationContext()
                .getResources().getString(R.string.no_internet_connection), 0);
    }

    @Override
    public void onComplete() {

    }
}
