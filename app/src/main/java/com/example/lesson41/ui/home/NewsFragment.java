package com.example.lesson41.ui.home;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lesson41.App;
import com.example.lesson41.R;
import com.example.lesson41.databinding.FragmentNewsBinding;
import com.example.lesson41.ui.interfaces.OnClickListener;
import com.example.lesson41.ui.model.News;


public class NewsFragment extends Fragment {

    private News news;
    private FragmentNewsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        news = (News) requireArguments().getSerializable("updateTask");
        if (news != null) binding.etNews.setText(news.getTitle());

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    private void save() {
        Bundle bundle = new Bundle();
        String text = binding.etNews.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(requireContext(), "type task!", Toast.LENGTH_SHORT).show();
        }
        if (news == null) {
            news = new News(text, System.currentTimeMillis());
            App.getDatabase().newsDao().insert(news);
        } else {
            news.setTitle(text);
        }

        bundle.putSerializable("key", news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        close();

    }

    private void close() {
        NavController navController = Navigation.findNavController((Activity) requireContext(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();

    }
}