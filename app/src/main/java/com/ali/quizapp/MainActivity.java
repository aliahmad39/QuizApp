package com.ali.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.ali.quizapp.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;



import org.w3c.dom.Document;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new HomeFragment());
        transaction.commit();

        binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                switch (i){
                    case 0:
                        transaction.replace(R.id.content,new HomeFragment());
                        transaction.commit();
                        break;
                    case 1:
                        transaction.replace(R.id.content,new LeaderboardsFragment());
                        transaction.commit();
                        break;
                    case 2:
                        transaction.replace(R.id.content,new WalletFragment());
                        transaction.commit();
                        break;
                    case 3:
                        transaction.replace(R.id.content,new ProfileFragment());
                        transaction.commit();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.wallet){
            FragmentTransaction transaction1=getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.content,new WalletFragment());
            transaction1.commit();
        }
        return super.onOptionsItemSelected(item);
    }
}