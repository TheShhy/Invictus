package com.example.sagar.lab5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{
    public TextView txt_description;

    private ItemClickListner itemClickListener;
    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_description = itemView.findViewById(R.id.text_id);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListner itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}
class RecyclerAdapter extends RecyclerView.Adapter<RecycleViewHolder>{

    private List<String> listData = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(List<String> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout,viewGroup,false);
        return new RecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, final int i) {
        recycleViewHolder.txt_description.setText(listData.get(i));
        recycleViewHolder.setItemClickListener(new ItemClickListner() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick)
                    Toast.makeText(context,"Long Click: "+listData.get(i),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context," "+listData.get(i),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

