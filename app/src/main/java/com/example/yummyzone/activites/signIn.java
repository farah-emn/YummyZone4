package com.example.yummyzone.activites;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummyzone.R;
import com.example.yummyzone.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signIn extends AppCompatActivity {
    TextView signUp_tv, tv_text;
    Button bt_signin;
    EditText et_email;
    EditText et_password;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    DatabaseReference userR, rootR;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        signUp_tv = findViewById(R.id.signUp_tv_signIn);
        tv_text = findViewById(R.id.signIn_tv_text);
        bt_signin = findViewById(R.id.signIn_bt_signIn);
        et_email = findViewById(R.id.signIn_et_email);
        et_password = findViewById(R.id.signIn_et_password);
        progressBar = findViewById(R.id.signIn_pro);
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.GONE);
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");

        progressBar.setVisibility(View.GONE);

        signUp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signIn.this, signUp.class);
                startActivity(intent);
            }
        });


        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(et_email.getText());
                password = String.valueOf(et_password.getText());

                userR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot keyId : snapshot.getChildren()) {
                            if (keyId.child("email").getValue().equals(email)) {
                                if (!keyId.child("accountState").getValue().equals("two")){
                                    mAuth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    progressBar.setVisibility(View.GONE);
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(signIn.this, "sign in",
                                                                Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        startActivity(intent);
                                                        finish();

                                                    } else {
                                                        Toast.makeText(signIn.this, "Authentication failed.",
                                                                Toast.LENGTH_SHORT).show();
                                                        tv_text.setText("Email or password wrong");
                                                        progressBar.setVisibility(View.GONE);
                                                    }
                                                }
                                            });
                                }else {
                                    tv_text.setText("Your account is not available");
                                    progressBar.setVisibility(View.GONE);
                                }

                            }else{
                                tv_text.setText("Email or password wrong");
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}