package com.gouvealtda.gouvea.recyclerView.listItemOrder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gouvealtda.gouvea.R;
import com.gouvealtda.gouvea.model.ItemOrder;

import java.util.ArrayList;

public class ListItemOrderAdapter extends RecyclerView.Adapter<ListItemOrderAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<ItemOrder> listItens;

    public ListItemOrderAdapter(Context mContext, ArrayList<ItemOrder> listItens) {
        this.mContext = mContext;
        this.listItens = listItens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_order_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textViewDescription.setText(listItens.get(position).getDescription());
        holder.textViewQtd.setText(listItens.get(position).getQtd());
        holder.textViewBarcode.setText(listItens.get(position).getBarcode());

    }

    @Override
    public int getItemCount() {
        return listItens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewDescription;
        TextView textViewQtd;
        TextView textViewBarcode;



        public ViewHolder(View itemView) {
            super(itemView);

            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewQtd = itemView.findViewById(R.id.textViewQtd);
            textViewBarcode = itemView.findViewById(R.id.textViewBarcode);
        }
    }
}