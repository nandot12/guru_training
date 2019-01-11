package com.nandohusni.sayaguru.ui;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nandohusni.sayaguru.R;
import com.nandohusni.sayaguru.network.NetworkClient;
import com.nandohusni.sayaguru.ui.signIn.LoginActivity;
import com.nandohusni.sayaguru.ui.signUp.adapter.PacketAdapter;
import com.nandohusni.sayaguru.ui.signUp.model.ResponsePacket;
import com.nandohusni.sayaguru.ui.signUp.model.ResponseSignUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @BindView(R.id.regJenisprivate)
    Spinner regJenisprivate;
    @BindView(R.id.regjenisprivatetext)
    TextView regjenisprivatetext;
    @BindView(R.id.regAlamat)
    EditText regAlamat;
    @BindView(R.id.regKel)
    EditText regKel;
    @BindView(R.id.regKec)
    EditText regKec;
    @BindView(R.id.regKota)
    EditText regKota;
    @BindView(R.id.nameFilektp)
    TextView nameFilektp;
    @BindView(R.id.choose1)
    ImageView choose1;
    @BindView(R.id.nameFileIjazah)
    TextView nameFileIjazah;
    @BindView(R.id.choose2)
    ImageView choose2;
    @BindView(R.id.regbtnSignup)
    Button regbtnSignup;
    String mediaPath;
    String mediaPath2;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);


        getPacket();


    }

    private void getPacket() {
        NetworkClient.service.actionGetPacket().enqueue(new Callback<ResponsePacket>() {
            @Override
            public void onResponse(Call<ResponsePacket> call, Response<ResponsePacket> response) {

                if (response.body().isStatus()) {

                    setView(response.body().getData());

                }


            }

            @Override
            public void onFailure(Call<ResponsePacket> call, Throwable t) {

            }
        });

    }

    private void setView(final List<com.nandohusni.sayaguru.ui.signUp.model.DataItem> data) {

        if (!data.isEmpty()) {

            PacketAdapter adapter = new PacketAdapter(data);
            regJenisprivate.setAdapter(adapter);
            regJenisprivate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    regjenisprivatetext.setText(data.get(position).getJpId());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }


    private void actionInsert() {


        File file = new File(mediaPath);
        File file2 = new File(mediaPath2);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("*/*"), file2);
        MultipartBody.Part filePhoto = MultipartBody.Part.createFormData("userfile", file.getName(), requestBody);
        MultipartBody.Part filePhoto2 = MultipartBody.Part.createFormData("userfile2", file2.getName(), requestBody2);

        RequestBody name = MultipartBody.create(MediaType.parse("name"), regName.getText().toString());
        RequestBody email = MultipartBody.create(MediaType.parse("email"), regEmail.getText().toString());
        RequestBody password = MultipartBody.create(MediaType.parse("password"), regPassword.getText().toString());
        RequestBody alamat = MultipartBody.create(MediaType.parse("alamat"), regAlamat.getText().toString());
        RequestBody kelurahan = MultipartBody.create(MediaType.parse("kelurahan"), regKel.getText().toString());
        RequestBody kec = MultipartBody.create(MediaType.parse("kec"), regKec.getText().toString());
        RequestBody kab = MultipartBody.create(MediaType.parse("kab"), regKota.getText().toString());
        RequestBody phone = MultipartBody.create(MediaType.parse("telpon"), regHp.getText().toString());
        RequestBody jp = MultipartBody.create(MediaType.parse("jp"), regjenisprivatetext.getText().toString());


        NetworkClient.service.actionSINGup(filePhoto, filePhoto2, name, email, password, alamat, kelurahan, kec, kab, phone, jp).enqueue(new Callback<ResponseSignUp>() {
            @Override
            public void onResponse(Call<ResponseSignUp> call, Response<ResponseSignUp> response) {
                if (response.body().isStatus()) {


                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));

                } else {
                    Toast.makeText(SignUpActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSignUp> call, Throwable t) {

            }
        });


    }


    private void showToast(String pesan) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.choose1, R.id.choose2, R.id.regbtnSignup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.choose1:

                actionDialog(0);
                break;
            case R.id.choose2:


                actionDialog(2);
                break;
            case R.id.regbtnSignup:

                actionSignUp();
                break;
        }
    }

    private void actionSignUp() {

        actionInsert();


    }

    private void actionDialog(final int i) {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_camera);

        Button btnGallery = dialog.findViewById(R.id.btngallery);
        Button btnpick = dialog.findViewById(R.id.btnpick);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, i + 1);


            }
        });

        btnpick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, i + 2);

            }
        });

        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        dialog.dismiss();

        if (requestCode == 0 && resultCode == RESULT_OK) {

            Uri selectImage = data.getData();
            String namaFile = selectImage.toString();
            String[] filepatchColumn = new String[]{MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectImage, filepatchColumn, null, null, null);
            int columnIndext = cursor.getColumnIndex(filepatchColumn[0]);
            mediaPath = cursor.getString(columnIndext);
            nameFilektp.setText(mediaPath);

            cursor.close();
        } else if (requestCode == 2) {

            try {
                Bitmap datas = (Bitmap) data.getExtras().get("data");
                persisImage(datas, "camera");

            }catch (NullPointerException e){

            }
        } else if (requestCode == 3) {


            Uri selectImage = data.getData();
            String namaFile = selectImage.toString();
            nameFilektp.setText(namaFile);
            String[] filepatchColumn = new String[]{MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectImage, filepatchColumn, null, null, null);
            int columnIndext = cursor.getColumnIndex(filepatchColumn[0]);
            mediaPath2 = cursor.getString(columnIndext);

            cursor.close();


        } else if (requestCode == 4) {

            Bitmap datas = (Bitmap) data.getExtras().get("data");
            persisImage2(datas, "camera2");

        }
    }

    private void persisImage2(Bitmap datas, String camera) {
        File filesDir = getFilesDir();
        File imageFile = new File(filesDir, camera + ".jpg");
        mediaPath2 = imageFile.getPath();

        nameFileIjazah.setText(mediaPath2);

        try {
            FileOutputStream os = new FileOutputStream(imageFile);
            datas.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void persisImage(Bitmap bitmap, String name) {

        File filesDir = getFilesDir();
        File imageFile = new File(filesDir, name + ".jpg");
        mediaPath = imageFile.getPath();

        nameFilektp.setText(mediaPath);

        try {
            FileOutputStream os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void actionDialog() {


    }


}
