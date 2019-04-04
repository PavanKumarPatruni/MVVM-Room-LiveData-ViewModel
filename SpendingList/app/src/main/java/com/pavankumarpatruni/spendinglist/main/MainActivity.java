package com.pavankumarpatruni.spendinglist.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.pavankumarpatruni.spendinglist.R;
import com.pavankumarpatruni.spendinglist.add.AddActivity;
import com.pavankumarpatruni.spendinglist.roomDB.SpendingModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ListAdapter listAdapter;

    @BindView(R.id.textViewMessage)
    TextView textViewMessage;

    private ListSpendingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddActivity.class));
            }
        });

        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ListSpendingViewModel.class);

        viewModel.getItemAndPersonList().observe(this, new Observer<List<SpendingModel>>() {
            @Override
            public void onChanged(@Nullable List<SpendingModel> spendingModelList) {
                if (spendingModelList.size() > 0) {
                    textViewMessage.setVisibility(View.GONE);
                    listAdapter.addItems(spendingModelList);
                } else {
                    textViewMessage.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        listAdapter = new ListAdapter(new ArrayList<SpendingModel>());
        listAdapter.setOnItemClickListener(new ListAdapter.ItemClickListener() {
            @Override
            public void onDeleteClick(final SpendingModel spendingModel) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle(getString(R.string.alert));
                alertDialogBuilder.setMessage(getString(R.string.alert_delete_msg));
                alertDialogBuilder.setPositiveButton(getString(R.string.button_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        viewModel.deleteItem(spendingModel);
                    }
                });
                alertDialogBuilder.setNegativeButton(getString(R.string.button_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(listAdapter);
    }

}
