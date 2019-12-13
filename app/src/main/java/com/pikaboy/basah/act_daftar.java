package com.pikaboy.basah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class act_daftar extends AppCompatActivity {
    private EditText email,Nama, kataSandi, konfirmasiSandi;
    private Button daftar_klik;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_daftar);
        mAuth = FirebaseAuth.getInstance();

        //field declare
        email =  findViewById(R.id.email_usr);
        Nama = findViewById(R.id.nama_usr);
        kataSandi =  findViewById(R.id.pass_usr);
        konfirmasiSandi =  findViewById(R.id.pass_kusr);

        daftar_klik =  findViewById(R.id.btn_daftar);

        daftar_klik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String $email = email.getText().toString().trim();
                String $kataSandi = kataSandi.getText().toString().trim();
                String $konfirmasi = konfirmasiSandi.getText().toString().trim();
                String $nama = Nama.getText().toString().trim();

                validasiForm($email,$nama, $kataSandi, $konfirmasi);
            }
        });

        ImageView kembali = (ImageView) findViewById(R.id.icon_back);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(act_daftar.this, act_login.class);
                startActivity(c);
            }
        });

    }


    public static boolean ValidasiEmail(String email){
        boolean validasi;
        String emailPatern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        if (email.matches(emailPatern) || email.matches(emailPattern2) && email.length() > 0)
        {
            validasi = true;
        }else{
            validasi = false;
        }

        return validasi;
    }
    private void validasiForm(String $email,String $nama, String $kataSandi, String $konfirmasi)
    {

        if($email.isEmpty())
        {
            email.setError("Email Harus Diisi");
            email.requestFocus();

        }else if (!ValidasiEmail($email))
        {
            email.setError("Format Email Salah");
            email.requestFocus();

        }else if ($nama.isEmpty())
        {
            Nama.setError("Nama Harus Diisi");
            Nama.requestFocus();

        }else if($kataSandi.isEmpty())
        {
            kataSandi.setError("Kata sandi Harus Diisi");
            kataSandi.requestFocus();

        }else if($konfirmasi.isEmpty())
        {
            konfirmasiSandi.setError("Silahkan Masukkan Konfrimasi Sandi");
            konfirmasiSandi.requestFocus();

        }else if($kataSandi.length() < 8) {
            kataSandi.setError("Password Minimal 8 karakater");
            kataSandi.requestFocus();

        }else if(!$kataSandi.matches($konfirmasi))
        {
            Toast.makeText(act_daftar.this,
                    "Kata Sandi Tidak Sama", Toast.LENGTH_SHORT).show();

            konfirmasiSandi.setText("");
            konfirmasiSandi.requestFocus();
        }else {

            DaftarUserBaru($email, $kataSandi);
        }
    }

    public void refresh()
    {
        email.setText("");
        Nama.setText("");
        kataSandi.setText("");
        konfirmasiSandi.setText("");
    }


    public void DaftarUserBaru( String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(act_daftar.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String username = Nama.getText().toString();
                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username).build();

                            user.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){

                                        mAuth.getCurrentUser().sendEmailVerification().
                                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){

                                                            Toast.makeText(act_daftar.this,
                                                                    "Daftar berhasil, Silahkan Lakukan Verfikasi",
                                                                    Toast.LENGTH_SHORT).show();
                                                            mAuth.signOut();
                                                            refresh();
                                                            startActivity(new Intent(act_daftar.this, act_login.class));

                                                        }else {
                                                            Toast.makeText(act_daftar.this,
                                                                    task.getException().getMessage(),
                                                                    Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });

                                    }
                                }
                            });




                        }else{

                            Toast.makeText(act_daftar.this,
                                    task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }


    public void Masuk(View view) {

        Intent intentMasuk = new Intent(act_daftar.this, act_login.class);
        startActivity(intentMasuk);
    }
}
