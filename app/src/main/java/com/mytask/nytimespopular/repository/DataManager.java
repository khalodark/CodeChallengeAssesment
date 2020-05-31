package com.mytask.nytimespopular.repository;

import com.mytask.nytimespopular.repository.services.AppServices;

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    /**
     * When we use a synchronized block, internally Java uses a monitor also
     * known as monitor lock or intrinsic lock, to provide synchronization.
     * These monitors are bound to an object, thus all synchronized blocks of the
     * same object can have only one thread executing them at the same time.
     *
     * @return
     */
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
