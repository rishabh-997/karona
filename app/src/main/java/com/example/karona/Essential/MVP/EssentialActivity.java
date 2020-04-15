package com.example.karona.Essential.MVP;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.karona.databinding.ActivityEssentialBinding;

public class EssentialActivity extends AppCompatActivity implements EssentialContract.view {
    private ActivityEssentialBinding binding;
    private EssentialContract.presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEssentialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new EssentialPresenter(this);

        presenter.getEssentials();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
