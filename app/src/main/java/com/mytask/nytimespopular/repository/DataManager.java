package com.mytask.nytimespopular.repository;

import com.mytask.nytimespopular.repository.services.AppServices;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public AppServices getServices() {
        return AppServices.getInstance();
    }
}
