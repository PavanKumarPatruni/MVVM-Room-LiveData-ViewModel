package com.pavankumarpatruni.spendinglist.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pavankumarpatruni.spendinglist.roomDB.AppDatabase;
import com.pavankumarpatruni.spendinglist.roomDB.SpendingModel;

import java.util.List;

public class ListSpendingViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private final LiveData<List<SpendingModel>> spendingModelList;

    public ListSpendingViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        spendingModelList = appDatabase.getSpendingModel().getSpendingList();
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
