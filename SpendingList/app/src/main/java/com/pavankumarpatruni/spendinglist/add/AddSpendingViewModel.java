package com.pavankumarpatruni.spendinglist.add;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pavankumarpatruni.spendinglist.roomDB.AppDatabase;
import com.pavankumarpatruni.spendinglist.roomDB.SpendingModel;

public class AddSpendingViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AddSpendingViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

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

}
