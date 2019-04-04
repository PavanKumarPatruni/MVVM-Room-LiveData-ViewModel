package com.pavankumarpatruni.spendinglist.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pavankumarpatruni.spendinglist.R;
import com.pavankumarpatruni.spendinglist.roomDB.SpendingModel;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<SpendingModel> spendingModelList;
    private ItemClickListener itemClickListener;

    public ListAdapter(List<SpendingModel> spendingModelList) {
        this.spendingModelList = spendingModelList;
    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        final SpendingModel spendingModel = spendingModelList.get(position);

        myViewHolder.textViewSpentFor.setText(spendingModel.getSpentFor());
        myViewHolder.textViewSpentMoney.setText(String.format("₹ %d", spendingModel.getSpentMoney(), Locale.ENGLISH));
        myViewHolder.textViewDate.setText(spendingModel.getSpentDate().toLocaleString());

        myViewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onDeleteClick(spendingModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return spendingModelList.size();
    }

    public void addItems(List<SpendingModel> spendingModelList) {
        this.spendingModelList = spendingModelList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewSpentMoney)
        TextView textViewSpentMoney;

        @BindView(R.id.textViewSpentFor)
        TextView textViewSpentFor;

        @BindView(R.id.textViewDate)
        TextView textViewDate;

        @BindView(R.id.imageViewDelete)
        ImageView imageViewDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }

    interface ItemClickListener {
        void onDeleteClick(SpendingModel spendingModel);
    }

}
