package com.pavankumarpatruni.spendinglist.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.pavankumarpatruni.spendinglist.roomDB.AppDatabase;
import com.pavankumarpatruni.spendinglist.roomDB.SpendingModel;
import com.pavankumarpatruni.spendinglist.roomDB.SpendingsRepository;

import java.util.List;

public class ListSpendingViewModel extends AndroidViewModel {

    private SpendingsRepository spendingsRepository;

    public ListSpendingViewModel(@NonNull Application application) {
        super(application);

        spendingsRepository = new SpendingsRepository(application);

    }

    public LiveData<List<SpendingModel>> getItemAndPersonList() {
        return spendingsRepository.getItemAndPersonList();
    }

    public void deleteItem(SpendingModel spendingModel) {
        spendingsRepository.deleteItem(spendingModel);
    }

}
