package com.example.br.edu.ifsp.dmos.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.br.edu.ifsp.dmos.R;
import com.example.br.edu.ifsp.dmos.model.entities.Tesk;
import com.example.br.edu.ifsp.dmos.mvp.MainMVP;
import com.example.br.edu.ifsp.dmos.view.RecyclerViewItemClickListener;

import java.util.List;

public class ItemPocketRecyclerAdapter extends RecyclerView.Adapter<ItemPocketRecyclerAdapter.ViewHolder>{

    private Context context;
    private MainMVP.Presenter presenter;
    private List<Tesk> data;
    private static RecyclerViewItemClickListener clickListener;

    public ItemPocketRecyclerAdapter(Context context, List<Tesk> data, MainMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tesk tesk = data.get(position);
        holder.titleTextView.setText(tesk.getName());
        if(tesk.isUrgent()){
            holder.urgenteClickImg.setColorFilter(context.getColor(R.color.red));
        }else{
            holder.urgenteClickImg.setColorFilter(context.getColor(R.color.gray));
        }
        holder.urgenteClickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urgenteClick(tesk);
            }
        });

        holder.edittClickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittClick(tesk);
            }
        });

        holder.deletClickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletClick(tesk);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(RecyclerViewItemClickListener listener){
        clickListener = listener;
    }

    private void urgenteClick(Tesk tesk){
        presenter.urgenteTesk(tesk);
        presenter.updateList();
        notifyDataSetChanged();
    }

    private void edittClick(Tesk tesk) {
        presenter.openDetails(tesk);
        notifyDataSetChanged();
    }

    private void deletClick(Tesk tesk) {
        presenter.deletTesk(tesk);
        notifyDataSetChanged();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTextView;
        public ImageView urgenteClickImg;

        public ImageView edittClickImg;

        public ImageView deletClickImg;


        public ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_title_listitem);
            urgenteClickImg = itemView.findViewById(R.id.image_urgente);
            edittClickImg = itemView.findViewById(R.id.image_edit);
            deletClickImg = itemView.findViewById(R.id.image_delet);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}