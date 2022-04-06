package com.example.lesson41.ui.board;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson41.R;
import com.example.lesson41.databinding.PagerBoardBinding;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {


    PagerBoardBinding binding;


    public final String[] titles = new String[]{"Салам", "Привет", "Hello"};

    public final String[] desc = new String[]{"Кандайсынар", "Как дела?", "How are you" };

    public final int[] lottie = new int[]{R.raw.news, R.raw.kygyz, R.raw.camp};


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
        if (position == 2) {
            binding.btnStart.setVisibility(View.VISIBLE);
        } else {

            binding.btnStart.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull PagerBoardBinding itemView) {

            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(int position) {
            binding.textTitle.setText(titles[position]);
            binding.tvAbout.setText(desc[position]);
            binding.ivPerson.setAnimation(lottie[position]);

            binding.btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();
                }
            });

        }
    }
}
