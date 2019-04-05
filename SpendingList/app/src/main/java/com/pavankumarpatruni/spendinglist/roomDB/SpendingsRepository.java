package com.pavankumarpatruni.spendinglist.roomDB;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class SpendingsRepository {

    private final AppDatabase appDatabase;
    private final LiveData<List<SpendingModel>> spendingModelList;

    public SpendingsRepository(Application application) {
        appDatabase = AppDatabase.getDatabase(application.getApplicationContext());

        spendingModelList = appDatabase.getSpendingModel().getSpendingList();
    }

    public void addSpending(SpendingModel spendingModel) {
        new addAsyncTask(appDatabase).execute(spendingModel);
    }

    private static class addAsyncTask extends AsyncTask<SpendingModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final SpendingModel... params) {
            db.getSpendingModel().addSpending(params[0]);
            return null;
        }
    }

    public LiveData<List<SpendingModel>> getItemAndPersonList() {
        return spendingModelList;
    }

    public void deleteItem(SpendingModel spendingModel) {
        new deleteAsyncTask(appDatabase).execute(spendingModel);
    }

    private static class deleteAsyncTask extends AsyncTask<SpendingModel, Void, Void> {

        private AppDatabase db;

        deleteAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final SpendingModel... params) {
            db.getSpendingModel().deleteSpending(params[0]);
            return null;
        }

    }
}
