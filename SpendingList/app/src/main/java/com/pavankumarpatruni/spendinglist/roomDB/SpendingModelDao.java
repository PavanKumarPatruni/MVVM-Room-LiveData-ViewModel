package com.pavankumarpatruni.spendinglist.roomDB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface SpendingModelDao {

    @Query("select * from SpendingModel")
    LiveData<List<SpendingModel>> getSpendingList();

    @Insert(onConflict = REPLACE)
    void addSpending(SpendingModel spendingModel);

    @Delete
    void deleteSpending(SpendingModel spendingModel);

}
