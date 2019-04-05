package com.pavankumarpatruni.spendinglist.add;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.pavankumarpatruni.spendinglist.roomDB.SpendingModel;
import com.pavankumarpatruni.spendinglist.roomDB.SpendingsRepository;

public class AddSpendingViewModel extends AndroidViewModel {

    private final SpendingsRepository spendingsRepository;

    public AddSpendingViewModel(@NonNull Application application) {
        super(application);

        spendingsRepository = new SpendingsRepository(application);

    }

    public void addSpending(SpendingModel spendingModel) {
        spendingsRepository.addSpending(spendingModel);
    }

}
