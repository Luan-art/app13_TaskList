package com.example.br.edu.ifsp.dmos.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.br.edu.ifsp.dmos.R;
import com.example.br.edu.ifsp.dmos.mvp.TeskDetailsMVP;
import com.example.br.edu.ifsp.dmos.presenter.TeskDetailsPresenter;

public class TeskDetailsActivity extends AppCompatActivity implements TeskDetailsMVP.View, View.OnClickListener {

    private TeskDetailsMVP.Presenter presenter;
    private EditText nameTesk;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tesk);

        presenter = new TeskDetailsPresenter(this);
        findViews();
        setListener();
        setToolbar();


    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.verifyUpdate();
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    private void setListener() {
        saveButton.setOnClickListener(this);
    }

    private void findViews() {
        nameTesk = findViewById(R.id.edittext_title_details);
        saveButton = findViewById(R.id.button_save_article);
    }

    @Override
    public void onClick(View v) {
        if(v == saveButton){
            presenter.saveTesk(
                    nameTesk.getText().toString());
        }
    }

    @Override
    public void updateUI(String name) {
        nameTesk.setText(name);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void close() {
        presenter.deatach();
        finish();

    }


    private void setToolbar() {
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        }
}
