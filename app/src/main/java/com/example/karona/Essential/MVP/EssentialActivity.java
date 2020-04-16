package com.example.karona.Essential.MVP;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.karona.Essential.Model.EssentialList;
import com.example.karona.databinding.ActivityEssentialBinding;

import java.util.ArrayList;
import java.util.List;

public class EssentialActivity extends AppCompatActivity implements EssentialContract.view, EssentialAdapter.noteListener {
    List<String> states = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    List<EssentialList> essentialLists = new ArrayList<>();
    List<EssentialList> filteredList = new ArrayList<>();
    String selected_state = "", selected_category = "";
    EssentialAdapter adapter;
    private ActivityEssentialBinding binding;
    private EssentialContract.presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEssentialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new EssentialPresenter(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        presenter.getEssentials();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUpSpinner(List<String> mStates, List<String> mCategories) {
        states = mStates;
        categories = mCategories;

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(EssentialActivity.this, android.R.layout.simple_list_item_1, states);
        binding.stateSelector.setAdapter(adapter1);
        binding.stateSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_state = binding.stateSelector.getSelectedItem().toString();
                setupRecyclerView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(EssentialActivity.this, android.R.layout.simple_list_item_1, categories);
        binding.categorySelector.setAdapter(adapter2);
        binding.categorySelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_category = binding.categorySelector.getSelectedItem().toString();
                setupRecyclerView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setUpList(List<EssentialList> mEssentialLists) {
        essentialLists = mEssentialLists;
    }

    private void setupRecyclerView() {
        filteredList.clear();
        for (int i = 0; i < essentialLists.size(); i++) {
            if (essentialLists.get(i).getState().trim().equals(selected_state.trim()) &&
                    essentialLists.get(i).getCategory().trim().equals(selected_category.trim())) {
                filteredList.add(essentialLists.get(i));
            }
        }
        if(filteredList.size()>0){
            adapter = new EssentialAdapter(this, filteredList,this);
            binding.recyclerview.setAdapter(adapter);
        }
        else {
            adapter = new EssentialAdapter(this, filteredList,this);
            binding.recyclerview.setAdapter(adapter);
            Toast.makeText(this, "No Resources Found for this Filter", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onContactClicked(int position) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(filteredList.get(position).getContact()));
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
