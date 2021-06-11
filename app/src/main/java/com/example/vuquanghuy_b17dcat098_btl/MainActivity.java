package com.example.vuquanghuy_b17dcat098_btl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vuquanghuy_b17dcat098_btl.databinding.ActivityMainBinding;
import com.example.vuquanghuy_b17dcat098_btl.fragment.HomeFragment;
import com.example.vuquanghuy_b17dcat098_btl.fragment.ProfileFragment;
import com.example.vuquanghuy_b17dcat098_btl.fragment.RankFragment;


import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ndung,new HomeFragment());
        transaction.commit();
        binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (i){
                    case 0:
                        transaction.replace(R.id.ndung,new HomeFragment());
                        transaction.commit();
                        break;
                    case 1:
                        transaction.replace(R.id.ndung,new RankFragment());
                        transaction.commit();
                        break;
                    case 2:
                        transaction.replace(R.id.ndung,new ProfileFragment());
                        transaction.commit();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==R.id.logout){
            Intent intent= new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()==R.id.exit){
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }


}