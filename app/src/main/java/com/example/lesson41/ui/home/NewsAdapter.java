package com.example.lesson41.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson41.databinding.ItemNewsBinding;
import com.example.lesson41.ui.interfaces.OnClickListener;
import com.example.lesson41.ui.model.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> list;

    private OnClickListener onClickListener;

    public NewsAdapter() {
        this.list = new ArrayList<>();
    }

    public void setList(List<News> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(0);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        if (position % 2 == 0 ){
            holder.binding.getRoot().setBackgroundColor(Color.parseColor("#9F9595"));
        }else{
            holder.binding.getRoot().setBackgroundColor(Color.WHITE);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public News getItem(int position) {
        return list.get(position);
    }


    public void updateItem(News news, int position) {
        list.set(position, news);
        notifyItemChanged(position);
    }

    public void addList(List<News> list) {
        this.list=list;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemNewsBinding binding;
        public ViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onItemClick(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("updateTask", binding.tvNews.getText().toString());

                }
            });

              binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                  @Override
                  public boolean onLongClick(View view) {
                      onClickListener.onItemLongClick(getAdapterPosition());

                      return true;
                  }
              });
        }

        public void bind(News news) {
            binding.tvNews.setText(news.getTitle());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss, dd MMM yyyy", Locale.ROOT);
            String date = String.valueOf(simpleDateFormat.format(news.getCreatedAt()));
            binding.tvDate.setText(date);
        }
    }
}
