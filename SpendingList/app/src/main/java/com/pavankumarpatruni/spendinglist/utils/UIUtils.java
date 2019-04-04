package com.pavankumarpatruni.spendinglist.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class UIUtils {

    public static void showSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

}
