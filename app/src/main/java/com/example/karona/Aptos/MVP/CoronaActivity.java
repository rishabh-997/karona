package com.example.karona.Aptos.MVP;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.karona.Aptos.Model.CoronaResponse;
import com.example.karona.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import ss.com.bannerslider.Slider;

public class CoronaActivity extends AppCompatActivity implements CoronaContract.view {
    CoronaContract.presenter presenter;

    TextView sendurl;
    TextView result, select_image, generatereport, report;
    ImageView selected_image, selected_image_stage2;
    ProgressBar progressBar;
    View overlay;
    Slider slider;

    FirebaseStorage firebasestorage = FirebaseStorage.getInstance();
    StorageReference storageReference = firebasestorage.getReference().child("Corona/" + System.currentTimeMillis() + ".png");

    String doc_url = "";
    Uri doc_data = null;
    String link = "null";
    String final_report = "";
    int capture = 1000;
    ImageView stage1, stage2, stage3;
    MainSliderAdapter3 mainSliderAdapter3 = new MainSliderAdapter3();
    PicassoImageLoadingService3 picassoImageLoadingService3 = new PicassoImageLoadingService3(CoronaActivity.this);
    //private int check = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter = new CoronaPresenter(this);

        sendurl = findViewById(R.id.sendurl);
        result = findViewById(R.id.result);
        progressBar = findViewById(R.id.progressbar);
        select_image = findViewById(R.id.select_image);
        selected_image = findViewById(R.id.selected_image);
        selected_image_stage2 = findViewById(R.id.selected_image_stage2);
        overlay = findViewById(R.id.overlay);
        slider = findViewById(R.id.banner_slider1);
        stage1 = findViewById(R.id.stage1);
        stage2 = findViewById(R.id.stage2);
        stage3 = findViewById(R.id.stage3);
        generatereport = findViewById(R.id.generate_report);
        report = findViewById(R.id.reports);

        Slider.init(picassoImageLoadingService3);
        slider.setAdapter(mainSliderAdapter3);

        sendurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                overlay.setVisibility(View.VISIBLE);

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("url", link);

                presenter.getResponse(jsonObject);
            }
        });

        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, capture);
            }
        });

        generatereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatereport.setVisibility(View.GONE);
                sendurl.setVisibility(View.GONE);
                result.setVisibility(View.GONE);
                selected_image_stage2.setVisibility(View.GONE);
                stage2.setClickable(false);

                progressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        report.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        report.setText(final_report);
                        stage3.setImageResource(R.drawable.completed_circle);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void showResult(CoronaResponse body) {
        progressBar.setVisibility(View.GONE);
        overlay.setVisibility(View.GONE);
        result.setVisibility(View.VISIBLE);
        generatereport.setVisibility(View.VISIBLE);
        sendurl.setVisibility(View.GONE);
        stage2.setImageResource(R.drawable.completed_circle);
        selected_image.setVisibility(View.GONE);
        selected_image_stage2.setVisibility(View.VISIBLE);
        int severity = body.getNum();

        result.setText("The chances of you having corona is  " + severity + " %");

        if (severity <= 40) {
            final_report = "Stay Home !!! Stay Safe !!!";
        } else if (severity <= 60) {
            final_report = "Quarantine yourself and consult a doctor immediately";
        } else {
            final_report = "You have high risk of having contaminated\n by Covid-19 virus.\n Contact the officals immediately and avoid contact with anyone.";
        }
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        overlay.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == capture) && (resultCode == RESULT_OK) && (data != null) && (data.getData() != null)) {
            progressBar.setVisibility(View.VISIBLE);
            overlay.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Uploading ...", Toast.LENGTH_SHORT).show();

            doc_data = data.getData();

            storageReference.putFile(doc_data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri data = uri;
                            doc_url = data.toString();
                            link = doc_url;
                            selected_image.setVisibility(View.VISIBLE);
                            Picasso.get()
                                    .load(link)
                                    .into(selected_image);
                            Picasso.get()
                                    .load(link)
                                    .into(selected_image_stage2);

                            Toast.makeText(CoronaActivity.this, "File uploaded on server...\n you can Proceed to stage 2", Toast.LENGTH_SHORT).show();
                            overlay.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                            sendurl.setVisibility(View.VISIBLE);
                            select_image.setVisibility(View.GONE);
                            stage1.setImageResource(R.drawable.completed_circle);
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "did not selected the image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

