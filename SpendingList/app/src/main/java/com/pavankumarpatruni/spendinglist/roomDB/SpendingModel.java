package com.pavankumarpatruni.spendinglist.roomDB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity
public class SpendingModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String spentFor;

    private long spentMoney;

    @TypeConverters(DateConverter.class)
    private Date spentDate;

    public SpendingModel(String spentFor, long spentMoney, Date spentDate) {
        this.spentFor = spentFor;
        this.spentMoney = spentMoney;
        this.spentDate = spentDate;
    }

    public String getSpentFor() {
        return spentFor;
    }

    public long getSpentMoney() {
        return spentMoney;
    }

    public Date getSpentDate() {
        return spentDate;
    }

}
