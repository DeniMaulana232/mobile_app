package com.pikaboy.basah;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.pikaboy.basah.fragment.Keranjang;
import com.pikaboy.basah.fragment.edukasi;
import com.pikaboy.basah.fragment.home_fragment;
import com.pikaboy.basah.fragment.pesan;
import com.pikaboy.basah.fragment.profile_fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class menu_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToogle;
    BottomNavigationView bottomNavigationView;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView nama, email, id;
    ImageView image;
    CallbackManager mCallbackManager;

    FrameLayout fmLayout;

    Fragment aboutFragment;
    Fragment logoutFragment;
    Fragment keranjangFragment;
    Fragment homeFragment;
    Fragment edukasifragment;
    Fragment pesanFragment;
    TextView teks, teks2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_home);

//        teks=(TextView)findViewById(R.id.font);
//        Typeface customfont=Typeface.createFromAsset(getAssets(), "font/goodmorning.otf");
//        teks.setTypeface(customfont);
//        Typeface customfont2= Typeface.createFromAsset(getAssets(), "font/goodmorningg.ttf");
//        teks2.setTypeface(customfont2);

        homeFragment = new home_fragment();
        keranjangFragment = new Keranjang();
        pesanFragment = new pesan();
        edukasifragment = new edukasi();

        mCallbackManager = CallbackManager.Factory.create();

        //google punya
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        nama = findViewById(R.id.nama_usr);
        email = findViewById(R.id.email_user);
        id = findViewById(R.id.id_user);
        image = findViewById(R.id.profile_image);

        mDrawerLayout = findViewById(R.id.drawer_navigasi);
        mDrawerToogle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mDrawerToogle);
        mDrawerToogle.syncState();

        // inisialisasi Home -----------------------------------------------------------------------
        profile_fragment fragment = new profile_fragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
        fragmentTransaction.commit();

        // Read Navigasi ---------------------------------------------------------------------------
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


            View headerView = navigationView.getHeaderView(0);
        nama = (TextView) headerView.findViewById(R.id.nama_user);
        email = (TextView) headerView.findViewById(R.id.email_user);
        id = (TextView) headerView.findViewById(R.id.id_user);
        image = (CircleImageView) headerView.findViewById(R.id.profile_image);




        loadUserInformation();


        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.Home:
                        setFragment(homeFragment);

                        return true;

                    case R.id.keranjang:
                        setFragment(keranjangFragment);

                        return true;

                    case R.id.email:
                        setFragment(pesanFragment);
                        return true;

                    case R.id.edukasi:
                        setFragment(edukasifragment);
                        return true;

                    default:
                        return true;

                }

            }
        });

        //fragment

        setFragment(homeFragment);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent intent = new Intent(menu_home.this, act_profile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//
//        }else if (id == R.id.edukasi) {
//            edukasi fragment = new edukasi();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame_layout, fragment, "Edukasi");
//            fragmentTransaction.commit();
//
//        }else if (id == R.id.home) {
//            fragmenr_whislist fragment = new fragmenr_whislist();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame_layout, fragment, "Whislist");
//            fragmentTransaction.commit();
//
//        }else if (id == R.id.setting) {
//            setting_fragmen fragment = new setting_fragmen();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame_layout, fragment, "Setting");
//            fragmentTransaction.commit();
//
//        }else if (id == R.id.history) {
//            history_fragment fragment = new history_fragment();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame_layout, fragment, "History");
//            fragmentTransaction.commit();

        }else if (id == R.id.keluar){
            firebaseAuth.signOut();
            LoginManager.getInstance().logOut();
            mGoogleSignInClient.signOut();

            Intent t = new Intent(menu_home.this, act_login.class);
            t.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
            startActivity(t);

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else
        {
            super.onBackPressed();
        }
    }



    //mengambil info user
    public void loadUserInformation(){

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {

            if(firebaseUser.getPhotoUrl() == null){
                image.setEnabled(false);
            }else {
                Glide.with(this).load(firebaseUser.getPhotoUrl().toString()).into(image);

            }

            nama.setText(firebaseUser.getDisplayName());
            email.setText(firebaseUser.getEmail());

            if (firebaseUser.getPhotoUrl() == null) {

                for (UserInfo profile : firebaseUser.getProviderData()) {
                    if(profile.getPhotoUrl() == null){
                        image.setEnabled(false);

                    }else{Glide.with(this).load(profile.getPhotoUrl().toString()).into(image);
                    }
                }
            }
            if (firebaseUser.getDisplayName() == null) {

                for (UserInfo profile : firebaseUser.getProviderData()) {

                    nama.setText(profile.getDisplayName());
                }
            }
            if (firebaseUser.getEmail() == null) {

                for (UserInfo profile : firebaseUser.getProviderData()) {

                    email.setText(profile.getEmail());

                }

            }
        }
    }


    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "blank");
        fragmentTransaction.commit();

    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.navigasi_profile, menu);
//
//
//        return true;
//    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        switch (item.getItemId())
//        {
//            case R.id.action_settings:
//                AboutFragment fragment = new AboutFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame_layout, fragment, "About");
//                fragmentTransaction.commit();
//                return true;
//
//            default:
                return super.onOptionsItemSelected(item);
//        }
    }
}
