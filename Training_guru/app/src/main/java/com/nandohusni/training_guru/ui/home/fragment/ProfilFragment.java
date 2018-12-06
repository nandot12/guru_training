package com.nandohusni.training_guru.ui.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nandohusni.training_guru.R;
import com.nandohusni.training_guru.ui.signIn.LoginActivity;
import com.nandohusni.training_guru.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    private SessionManager sesi ;

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

        profilEmail.setText("Email :" +sesi.getEmail());
        profilHp.setText("Handphone : " + sesi.getPhone());

        profilName.setText("Nama : " +sesi.getNama());



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.profilBtn)
    public void onViewClicked() {
        sesi.logout();
        startActivity(new Intent(getContext(),LoginActivity.class));
        getActivity().finish();
    }
}
