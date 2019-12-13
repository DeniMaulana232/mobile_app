package com.pikaboy.basah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class act_login extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1 ;
    GoogleSignInClient mGoogleSignIn;
    private EditText email_Masuk, kataSandi_Masuk;
    private Button btn_masuk;
    private FirebaseAuth mAuth;
    private ImageView login;
    private ImageView loginButton;
    CallbackManager mCallbackManager;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);

        login = findViewById(R.id.sign_in_button);
        mAuth = FirebaseAuth.getInstance();
        //fb login-----------------------------------------

        reference = FirebaseDatabase.getInstance().getReference().child("Users");

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        mCallbackManager = CallbackManager.Factory.create();
        //--------------------------------------
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        //google login
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignIn = GoogleSignIn.getClient(this, gso);

        // email pass apl
        email_Masuk = (EditText) findViewById(R.id.txtemail);
        kataSandi_Masuk = (EditText) findViewById(R.id.txtpass);
        btn_masuk = (Button) findViewById(R.id.btnlogin);


        btn_masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String $email = email_Masuk.getText().toString();
                String $kataSandi = kataSandi_Masuk.getText().toString();

                validasiMasukForm($email, $kataSandi);
            }
        });

        TextView daftar = (TextView) findViewById(R.id.txtdaftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w = new Intent(act_login.this, act_daftar.class);
                startActivity(w);
            }
        });



        //fb
        loginButton = (ImageView) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(act_login.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplication(),R.string.cancel_login,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(getApplication(),R.string.error_login,Toast.LENGTH_LONG).show();
                    }
                }
                );

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            updateUI(currentUser);
        }else if (currentUser != null){
            updateUI(currentUser);
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,resultCode,data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, "Error : "+ e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            simpanUser(user);
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void updateUI(FirebaseUser user) {

        if (user != null){
            startActivity(new Intent(act_login.this, menu_home.class));
        }

    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            simpanUser(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(act_login.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignIn.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //kumpulan method masuk melalui email password
    public void MasukUser(String email, String kataSandi){
        mAuth.signInWithEmailAndPassword(email, kataSandi)
                .addOnCompleteListener(act_login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            if (mAuth.getCurrentUser().isEmailVerified())
                            {
                                FirebaseUser user = mAuth.getCurrentUser();
                                simpanUser(user);
                                Intent intent = new Intent(act_login.this, menu_home.class);

                                startActivity(intent);

                            }else {
                                Toast.makeText(act_login.this, "Akun Belum Terverifikasi", Toast.LENGTH_SHORT).show();

                            }

                        }else {

                            Toast.makeText(act_login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }
                }
                );
    }
    public void validasiMasukForm(String email, String kataSandi){

        if (email.isEmpty())
        {
            email_Masuk.setError("Email Harus Diisi");
            email_Masuk.requestFocus();

        }else if(kataSandi.isEmpty())
        {
            kataSandi_Masuk.setError("Password Harus Diisi");
            kataSandi_Masuk.requestFocus();
        }
        else if(!ValidasiEmail(email)){

            email_Masuk.setError("Format Email Salah");
            email_Masuk.requestFocus();
        }

        else
        {

            MasukUser(email, kataSandi);

        }
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
    //-------------------------------------------------------------------------------------------

    private void simpanUser(FirebaseUser user){

         final user_pembeli userPembeli = new user_pembeli(user.getDisplayName(), user.getEmail(), String.valueOf(user.getPhotoUrl()), user.getUid());

        Query input = reference.orderByChild("uid").equalTo(userPembeli.getUID());

        input.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){

                    reference.push().setValue(userPembeli);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

