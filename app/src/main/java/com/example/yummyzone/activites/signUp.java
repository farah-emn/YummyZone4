package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummyzone.R;
import com.example.yummyzone.classes.user;
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

public class signUp extends AppCompatActivity {
    TextView signUp_tv;
    Button bt_skip;
    Button bt_signUp;
    FirebaseAuth mAuth;
    EditText et_email;
    EditText et_password;
    EditText et_confirmPassword;
    EditText et_username;
    ProgressBar progressBar;
    TextView tv_text;
    DatabaseReference userR;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(signUp.this, MainActivity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        signUp_tv = findViewById(R.id.signUp_tv_signIn);
        bt_skip = findViewById(R.id.signup_bt_skip);
        bt_signUp = findViewById(R.id.signUp_bt_signUp);
        et_email = findViewById(R.id.signUp_et_email);
        et_password = findViewById(R.id.signUp_et_password);
        et_username = findViewById(R.id.signUp_et_username);
        progressBar = findViewById(R.id.signUp_prog);
        et_confirmPassword = findViewById(R.id.signUp_et_confirmPassword);
        tv_text = findViewById(R.id.signUp_tv_text);
        userR = FirebaseDatabase.getInstance().getReference().child("user");


        signUp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this, signIn.class);
                startActivity(intent);
            }
        });

        bt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, username, password, confirmpassword;
                email = String.valueOf(et_email.getText());
                username = String.valueOf(et_username.getText());
                password = String.valueOf(et_password.getText());
                confirmpassword = String.valueOf(et_confirmPassword.getText());

                userR.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(username).exists()){
                            progressBar.setVisibility(View.GONE);
                            tv_text.setText("The username is already exit");
                        } else {
                            if (!email.equals("") && !password.equals("") && !confirmpassword.equals("")) {

                                if (password.equals(confirmpassword)) {

                                    mAuth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {

                                                    progressBar.setVisibility(View.GONE);
                                                    if (task.isSuccessful()) {
                                                        insertUser(username, email, password);
                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                        Toast.makeText(signUp.this, "Acount created",
                                                                Toast.LENGTH_SHORT).show();
                                                        return;

                                                    } else {
                                                        tv_text.setText("The email is incorrect or already exists");
                                                    }
                                                }
                                            });

                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    tv_text.setText("password wrong");
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                tv_text.setText("Enter all filed");
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }

            public void insertUser(String username, String email, String password) {
                String firstName = username;
                String lastName = "";
                String mobileNumber = "";
                String city = "";
                String street = "";
                String district = "";
                user u = new user(username, email, password, firstName, lastName, mobileNumber, city, street, district);
                userR.child(String.valueOf(username)).setValue(u);
            }

        });

    }
}














/*

package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummyzone.R;
import com.example.yummyzone.classes.user;
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

public class signUp extends AppCompatActivity {
    TextView signUp_tv;
    Button bt_skip;
    Button bt_signUp;
    FirebaseAuth mAuth;
    EditText et_email;
    EditText et_password;
    EditText et_confirmPassword;
    EditText et_username;
    ProgressBar progressBar;
    TextView tv_text;
    DatabaseReference userR;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(signUp.this, MainActivity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        signUp_tv = findViewById(R.id.signUp_tv_signIn);
        bt_skip = findViewById(R.id.signup_bt_skip);
        bt_signUp = findViewById(R.id.signUp_bt_signUp);
        et_email = findViewById(R.id.signUp_et_email);
        et_password = findViewById(R.id.signUp_et_password);
        et_username = findViewById(R.id.signUp_et_username);
        progressBar = findViewById(R.id.signUp_prog);
        et_confirmPassword = findViewById(R.id.signUp_et_confirmPassword);
        tv_text = findViewById(R.id.signUp_tv_text);
        userR = FirebaseDatabase.getInstance().getReference().child("user");


        signUp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this, signIn.class);
                startActivity(intent);
            }
        });

        bt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bt_signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, username, password, confirmpassword;
                email = String.valueOf(et_email.getText());
                username = String.valueOf(et_username.getText());
                password = String.valueOf(et_password.getText());
                confirmpassword = String.valueOf(et_confirmPassword.getText());

                userR.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(username).exists()){
                            progressBar.setVisibility(View.GONE);
                            tv_text.setText("The username is already exit");
                        } else {
                            if (!email.equals("") && !password.equals("") && !confirmpassword.equals("")) {

                                if (password.equals(confirmpassword)) {

                                    mAuth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    insertUser(username, email, password);
                                                    progressBar.setVisibility(View.GONE);
                                                    if (task.isSuccessful()) {

                                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                        Toast.makeText(signUp.this, "Acount created",
                                                                Toast.LENGTH_SHORT).show();
                                                        return;

                                                    } else {
                                                        tv_text.setText("The email is incorrect or already exists");
                                                    }
                                                }
                                            });

                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    tv_text.setText("password wrong");
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                                tv_text.setText("Enter all filed");
                            }
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }

            public void insertUser(String username, String email, String password) {
                String firstName = username;
                String lastName = "";
                String mobileNumber = "";
                String city = "";
                String street = "";
                String district = "";
                user u = new user(username, email, password, firstName, lastName, mobileNumber, city, street, district);
                userR.child(String.valueOf(username)).setValue(u);
            }

        });

    }
}

*/