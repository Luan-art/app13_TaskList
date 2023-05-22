package com.example.br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.br.edu.ifsp.dmos.R;
import com.example.br.edu.ifsp.dmos.mvp.MainMVP;

public class MainActivity extends AppCompatActivity implements MainMVP.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public Context getContext() {
        return null;
    }
}