package com.gouvealtda.gouvea.recyclerView.listItemChoose;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.model.ItemOrder;
import com.gouvealtda.gouvea.recyclerView.listItemChoose.ListItemChooseAdapter.ViewHolder;

import java.util.ArrayList;

public class ListItemChooseAdapter extends RecyclerView.Adapter<ViewHolder>{
    private ArrayList<ItemOrder> listItens;
    private CbListChooseAdapter cbListChooseAdapter;

    public ListItemChooseAdapter(ArrayList<ItemOrder> listItens, CbListChooseAdapter cbListChooseAdapter) {
        this.listItens = listItens;
        this.cbListChooseAdapter = cbListChooseAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_choose_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textViewDescription.setText(listItens.get(position).getDescription());
        holder.textViewBarcode.setText(listItens.get(position).getBarcode());
        holder.userCardContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemOrder itemOrder = listItens.get(position);
                cbListChooseAdapter.itemChoosed(itemOrder);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDescription;
        TextView textViewBarcode;
        CheckBox checkBoxChooseItem;
        LinearLayout userCardContent;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewBarcode = itemView.findViewById(R.id.textViewBarcode);
            checkBoxChooseItem = itemView.findViewById(R.id.checkBoxChooseItem);
            userCardContent = itemView.findViewById(R.id.userCardContent);

        }
    }

    public interface CbListChooseAdapter {
        void itemChoosed(ItemOrder itemOrder);
    }
}
