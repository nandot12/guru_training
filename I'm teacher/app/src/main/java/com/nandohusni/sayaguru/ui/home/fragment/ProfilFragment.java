package com.nandohusni.sayaguru.ui.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nandohusni.sayaguru.R;
import com.nandohusni.sayaguru.network.NetworkClient;
import com.nandohusni.sayaguru.ui.home.fragment.model.ResultChangeStatus;
import com.nandohusni.sayaguru.ui.signIn.LoginActivity;
import com.nandohusni.sayaguru.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {


    @BindView(R.id.profilName)
    TextView profilName;
    @BindView(R.id.profilEmail)
    TextView profilEmail;
    @BindView(R.id.profilHp)
    TextView profilHp;
    @BindView(R.id.profilBtn)
    Button profilBtn;
    Unbinder unbinder;
    @BindView(R.id.profilSwitchStatus)
    Switch profilSwitchStatus;

    private SessionManager sesi;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        unbinder = ButterKnife.bind(this, view);
        sesi = new SessionManager(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profilEmail.setText("Email :" + sesi.getEmail());
        profilHp.setText("Handphone : " + sesi.getPhone());

        if(sesi.getIStatus().equals("1")){
            profilSwitchStatus.setChecked(false);
        }
        else{
            profilSwitchStatus.setChecked(true);
        }


        profilName.setText("Nama : " + sesi.getNama());

        profilSwitchStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

               changeStatus();

            }
        });


    }

    private void changeStatus() {

        NetworkClient.service.actionChangeStatus(sesi.getIdUser()).enqueue(new Callback<ResultChangeStatus>() {
            @Override
            public void onResponse(Call<ResultChangeStatus> call, Response<ResultChangeStatus> response) {

                if(response.body().isStatus()){
                    Toast.makeText(getContext(), "update berhasil", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResultChangeStatus> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.profilBtn)
    public void onViewClicked() {
        sesi.logout();
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }
}
