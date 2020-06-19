package com.example.citytour.Common.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.citytour.Databases.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import com.example.citytour.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Codeverfication extends AppCompatActivity {

    String verificationCodeBysystem, PhoneNo;
    ProgressBar proggbar;
    PinView pinView;
    String Name,Username,Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeverfication);
        pinView = findViewById(R.id.pinview);
        PhoneNo = getIntent().getStringExtra("phonenumber");
        proggbar=findViewById(R.id.progressBar);
        SendOtptouser(PhoneNo);
        Name=getIntent().getStringExtra("name");
        Username=getIntent().getStringExtra("username");
        Email=getIntent().getStringExtra("email");
        Password=getIntent().getStringExtra("password");

    }
    public void callnextfromotp(View view)
    {
        String code=pinView.getText().toString();
        if(!code.isEmpty())
        {
            verifyCode(code);
        }
    }
    private void SendOtptouser(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeBysystem=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if(code!=null)
            {
                proggbar.setVisibility(View.VISIBLE);
                pinView.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Codeverfication.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
    private  void verifyCode(String codeByuser)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationCodeBysystem,codeByuser);
        signInUserByCredentials(credential);

    }

    private void signInUserByCredentials(PhoneAuthCredential credential)
    {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(Codeverfication.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    proggbar.setVisibility(View.GONE);
                    storedata();
                    Intent intent=new Intent(Codeverfication.this,Login.class);


                    Toast.makeText(Codeverfication.this,"Successfull",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(Codeverfication.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });




    }
    private void storedata()
    {
        FirebaseDatabase rootnode=FirebaseDatabase.getInstance();
        DatabaseReference reference=rootnode.getReference("Users");
        UserHelperClass addnewUser= new UserHelperClass(Name,Username,Email,Password,PhoneNo);
        reference.child(Username).setValue(addnewUser);
    }
}

