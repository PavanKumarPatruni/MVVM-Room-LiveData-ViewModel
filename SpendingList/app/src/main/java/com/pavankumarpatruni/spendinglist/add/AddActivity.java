package com.pavankumarpatruni.spendinglist.add;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import com.pavankumarpatruni.spendinglist.R;
import com.pavankumarpatruni.spendinglist.roomDB.SpendingModel;
import com.pavankumarpatruni.spendinglist.utils.UIUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.editTextSpentFor)
    EditText editTextSpentFor;

    @BindView(R.id.editTextSpentMoney)
    EditText editTextSpentMoney;

    @BindView(R.id.buttonSelectDate)
    Button buttonSelectDate;

    @BindView(R.id.buttonCurrentDate)
    Button buttonCurrentDate;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private String spentFor;
    private long spentMoney;
    private Date date;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private AddSpendingViewModel addSpendingViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        calendar = Calendar.getInstance();

        initViewModel();
        initDatePicker();
        initListeners();
    }

    private void initViewModel() {
        addSpendingViewModel = ViewModelProviders.of(this).get(AddSpendingViewModel.class);
    }

    private void initDatePicker() {
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                date = calendar.getTime();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void initListeners() {

        buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                datePickerDialog.show();
            }
        });

        buttonCurrentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();

                date = calendar.getTime();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spentFor = editTextSpentFor.getText().toString();

                String stringSpentMoney = editTextSpentMoney.getText().toString();
                if (!stringSpentMoney.isEmpty()) {
                    spentMoney = Long.parseLong(stringSpentMoney);
                }

                if (spentFor.isEmpty() || spentMoney == 0 || date == null) {
                    UIUtils.showSnackbar(view, getString(R.string.missing_fields));
                } else {
                    addSpendingViewModel.addSpending(new SpendingModel(
                            spentFor,
                            spentMoney,
                            date
                    ));

                    finish();
                }

            }
        });
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
