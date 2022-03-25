package com.example.lesson41.ui;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.lesson41.Prefs;
import com.example.lesson41.R;
import com.example.lesson41.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Prefs prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefs = new Prefs(view.getContext());
        binding.ivForgallery.setOnClickListener(view1 ->
                resultLauncher.launch("image/*"));
        binding.etSave.setText(prefs.getName());
        if(prefs.getImage()!=null){
            Glide.with(binding.ivForgallery).load(prefs.getImage()).into(binding.ivForgallery);
        }
    }


    ActivityResultLauncher<String> resultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    Glide.with(binding.ivForgallery).load(result).into(binding.ivForgallery);
                    prefs.saveImage(String.valueOf(result));
                }
            });

    @Override
    public void onDestroy() {
        prefs.saveName(binding.etSave.getText().toString());
        super.onDestroy();
    }
}
