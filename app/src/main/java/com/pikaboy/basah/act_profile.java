package com.pikaboy.basah;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pikaboy.basah.fragment_profile.PelapakFragment;
import com.pikaboy.basah.fragment_profile.PembeliFragment;

public class act_profile extends AppCompatActivity {
    private ImageView back_btn, imageUser;
    private TextView namaUser, id, phone, ubah;
    private   TextView email;
    private LinearLayout loadProfileActivity;
    BottomNavigationView bottomNavigationView;
    ActionBarDrawerToggle mDrawerToogle;
    DrawerLayout mDrawerLayout;

    Fragment pembeliFragment;
    Fragment postinganFragment;

    private Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_profile);

        pembeliFragment = new PembeliFragment();
        postinganFragment = new PelapakFragment();


        back_btn = findViewById(R.id.icon_back);

        namaUser = findViewById(R.id.nama_user2);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(act_profile.this,menu_home.class);
                back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(back);
            }
        });




        bottomNavigationView = findViewById(R.id.navigasi_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.pembeli:
                        setFragment(pembeliFragment);

                        return true;

                    case R.id.posting:
                        setFragment(postinganFragment);

                        return true;

                    default:
                        return true;

                }
            }
        });
            setFragment(pembeliFragment);

        }


    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout3, fragment, "blank");
        fragmentTransaction.commit();

    }


}
