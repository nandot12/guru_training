package com.nandohusni.training_guru.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nandohusni.training_guru.R;
import com.nandohusni.training_guru.network.NetworkClient;
import com.nandohusni.training_guru.ui.signIn.LoginActivity;
import com.nandohusni.training_guru.ui.signUp.model.ResponseSignUp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {


    @BindView(R.id.regName)
    EditText regName;
    @BindView(R.id.regEmail)
    EditText regEmail;
    @BindView(R.id.regHp)
    EditText regHp;
    @BindView(R.id.regPassword)
    EditText regPassword;
    @BindView(R.id.regConfirmPas)
    EditText regConfirmPas;
    @BindView(R.id.regbtnSignup)
    Button regbtnSignup;
    @BindView(R.id.linkSignIn)
    TextView linkSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);


    }



    private void actionInsert() {
        String name = regName.getText().toString();
        String email = regEmail.getText().toString();
        String password = regPassword.getText().toString();
        String hp = regHp.getText().toString();
        String confirPass = regConfirmPas.getText().toString();


        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirPass.isEmpty() || hp.isEmpty()) {

            showToast(getString(R.string.pesan));
        } else if (!password.equals(confirPass)) {

            showToast(getString(R.string.not_match));
        } else {


            NetworkClient.service.actionSigUp(name, password, hp, email).enqueue(new Callback<ResponseSignUp>() {
                @Override
                public void onResponse(Call<ResponseSignUp> call, Response<ResponseSignUp> response) {

                    if (response.isSuccessful()) {

                        Boolean result = response.body().isStatus();
                        String pesan = response.body().getPesan();
                        if (result) {
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            finish();
                        }
                        else {

                            showToast(pesan);

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseSignUp> call, Throwable t) {

                }
            });
        }
    }



    private void showToast(String pesan) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.regbtnSignup, R.id.linkSignIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regbtnSignup:
                actionInsert();
                break;
            case R.id.linkSignIn:

                actionMove();
                break;
        }
    }

    private void actionMove() {

        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));


    }
}
