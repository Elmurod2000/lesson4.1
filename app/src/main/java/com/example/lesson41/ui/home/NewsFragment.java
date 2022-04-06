package com.example.lesson41.ui.home;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lesson41.App;
import com.example.lesson41.R;
import com.example.lesson41.databinding.FragmentNewsBinding;
import com.example.lesson41.ui.interfaces.OnClickListener;
import com.example.lesson41.ui.model.News;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class NewsFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
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
        String text = binding.etNews.getText().toString().trim();
        Bundle bundle = new Bundle();
        if (text.isEmpty()) {
            Toast.makeText(requireContext(), "type task!", Toast.LENGTH_SHORT).show();
        }
        if (news == null) {
            news = new News(text, System.currentTimeMillis());
            bundle.putSerializable("key", news);
            App.getDatabase().newsDao().insert(news);
        } else {
            news.setTitle(text);
        }
        addToFireStore(news);
        getParentFragmentManager().setFragmentResult("rk_news", bundle);
    }

    private void addToFireStore(News news) {
        db.collection("news")
                .add(news).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                close();
                Toast.makeText(requireContext(), "Усешно", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show();

                Log.e("TAG", "onFailure: ", e);
            }
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController((Activity) requireContext(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();

    }
}