package com.pikaboy.basah.fragment_profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.pikaboy.basah.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PembeliFragment extends Fragment {
    private ImageView imageUser;
    private TextView namaUser, id, phone;
    private   TextView email;
    private LinearLayout loadProfileActivity;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;


    public PembeliFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pembeli, container, false);
        imageUser = view.findViewById(R.id.profile_image2);
        email = view.findViewById(R.id.email_user2);
        id = view.findViewById(R.id.id_user2);
//        phone = findViewById(R.id.phone_user);
        namaUser = view.findViewById(R.id.nama_user2);

        loadUserInformation();
//
        return view;




    }

    private void loadUserInformation() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {

            Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imageUser);
            namaUser.setText(firebaseUser.getDisplayName());
            email.setText(firebaseUser.getEmail());
            id.setText(firebaseUser.getUid());
        }
        if (firebaseUser.getEmail() == null) {

            for (UserInfo profile : firebaseUser.getProviderData()) {

                email.setText(profile.getEmail());
            }

        }
        if (firebaseUser.getUid() == null) {

            for (UserInfo profile : firebaseUser.getProviderData()) {

                id.setText(profile.getUid());
            }

        }

    }

}
