package com.ali.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.ali.quizapp.databinding.ActivityAboutUsBinding;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class aboutUs extends AppCompatActivity {
    ActivityAboutUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        simulateDayNight(/*Day*/0);

        Element adsElement = new Element();
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription(" Development of android-based Quiz application" +
                        " is mainly required by students and learners to prepare" +
                        " themselves for different examinations directly through " +
                        "smart phones and tablets in hands. One of the major goal" +
                        " of our project is to facilitate students in learning," +
                        "gaining and improving their knowledge skills.")
                .addItem(new Element().setTitle("Version 1.0"))
                .addGroup("CONNECT WITH US!")
                .addEmail("quizapp@gmail.com ")
                .addWebsite("Your website/")
                .addYoutube("")   //youtube link
                .addPlayStore("com.ali.quizapp")   // package name
                .addInstagram("aliahmad0051")    // instagram id
                .addItem(createCopyright())
                .create();
        setContentView(aboutPage);
    }

    private void simulateDayNight(int currentsetting) {
        int DAY=0;
        int NIGHT=1;
        int FOLLOW_SYSTEM=3;
        int  currentNightmode=getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (currentsetting==DAY && currentNightmode!=Configuration.UI_MODE_NIGHT_NO){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentsetting==NIGHT && currentNightmode!=Configuration.UI_MODE_NIGHT_YES){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else if (currentsetting==FOLLOW_SYSTEM){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }




    }

    private Element createCopyright()
    {
        Element copyright = new Element();
        @SuppressLint("DefaultLocale") final String copyrightString = String.format("Copyright %d by Ali Ahmad", Calendar.getInstance().get(Calendar.YEAR));
        copyright.setTitle(copyrightString);
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);
        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(aboutUs.this,copyrightString,Toast.LENGTH_SHORT).show();
            }
        });
        return copyright;
    }
}
