package com.example.lesson41.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson41.R;
import com.example.lesson41.databinding.FragmentHomeBinding;
import com.example.lesson41.ui.interfaces.OnClickListener;
import com.example.lesson41.ui.model.News;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    NewsAdapter adapter;
    private boolean isChanged = false;
    private int position;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NewsAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChanged = false;
                open(null);
            }
        });

        getParentFragmentManager().setFragmentResultListener("rk_news", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("key");
                Log.e("TAG",  news.getTitle());
                if (isChanged) adapter.updateItem(news , position);
                else adapter.addItem(news);
            }
        });
        binding.recyclerNews.setAdapter(adapter);
        adapter.setOnClickListener(new OnClickListener() {
            @Override
            public void onItemClick(int position) {
                News news = adapter.getItem(position);
                isChanged = true;
                open(news);
                HomeFragment.this.position = position;

            }

            @Override
            public void onItemLongClick(int position) {

            }
        });

    }

    private void open(News news) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        Bundle bundle = new Bundle();
        bundle.putSerializable("updateTask" ,news );
        navController.navigate(R.id.newsFragment , bundle);
    }
}