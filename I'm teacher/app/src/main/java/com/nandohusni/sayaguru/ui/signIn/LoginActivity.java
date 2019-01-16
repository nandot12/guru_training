package com.nandohusni.sayaguru.ui.signIn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nandohusni.sayaguru.R;
import com.nandohusni.sayaguru.base.BaseActivity;
import com.nandohusni.sayaguru.network.NetworkClient;
import com.nandohusni.sayaguru.ui.home.HomeActivity;
import com.nandohusni.sayaguru.ui.SignUpActivity;
import com.nandohusni.sayaguru.ui.signIn.model.Data;
import com.nandohusni.sayaguru.ui.signIn.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {


    Button btnSignIn;
    TextView textLink;
    EditText signInHp, signInPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {

        btnSignIn = findViewById(R.id.btnSignIn);
        textLink = findViewById(R.id.linkSignUp);
        signInHp = findViewById(R.id.loginHp);
        signInPass = findViewById(R.id.loginPassword);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actionLogin();


            }
        });

        textLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));

                finish();
            }
        });


    }


    private void actionMove() {

        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

        finish();


    }

    private void showToast(String pesan) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }


    private void actionLogin() {

        NetworkClient.service.actionLogin(signInHp.getText().toString(),signInPass.getText().toString()).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if(response.isSuccessful()){

                    Boolean status  = response.body().isStatus();
                    String pesan = response.body().getPesan();

                    if(status){

                        actionMove();

                        Data data = response.body().getData();
                        sesi.setEmail(data.getUserEmail());
                        sesi.setNama(data.getUserNama());
                        sesi.setIduser(data.getUserId());
                        sesi.setPhone(data.getUserTelpon());
                        sesi.setStatus(data.getUserStatus());
                        sesi.cerateLoginSession("2");




                    }
                    else {

                        showToast(pesan);




                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }
}
