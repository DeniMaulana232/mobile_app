package com.pikaboy.basah.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pikaboy.basah.R;
import com.pikaboy.basah.menu_home;

public class kategori extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    TextView back;
    private ImageView kerajinan, sembako, sampah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        kerajinan = findViewById(R.id.kategori_kerajinan);
        sembako = findViewById(R.id.kategori_sembako);
        sampah = findViewById(R.id.kategori_sampah);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        kerajinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kerajinan = new Intent (kategori.this, act_uploadkerajinan.class);
                kerajinan.putExtra("kategori", "Kerajinan");
                kerajinan.putExtra("profilnama", currentUser.getDisplayName());
                kerajinan.putExtra("profilimage", currentUser.getPhotoUrl());
                startActivity(kerajinan);
            }
        });

        sembako.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sembako = new Intent (kategori.this, act_uploadsembako.class);

                sembako.putExtra("kategori", "Sembako");
                sembako.putExtra("profilnama", currentUser.getDisplayName());
                sembako.putExtra("profilimage", currentUser.getPhotoUrl());
                startActivity(sembako);
            }
        });

        sampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sampah = new Intent (kategori.this, act_uploadsampah.class);
                sampah.putExtra("kategori", "Sampah");
                sampah.putExtra("profilnama", currentUser.getDisplayName());
                sampah.putExtra("profilimage", currentUser.getPhotoUrl());
                startActivity(sampah);
            }
        });


        back = findViewById(R.id.kembalikeberanda);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(kategori.this, menu_home.class );
                startActivity(back);
            }
        });

    }
}
