package com.example.artikolevyinf.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.artikolevyinf.R;
import com.example.artikolevyinf.Utils.Utils;

public class ProfileFragment extends Fragment {

    TextView tvUserId, tvUserName;
    EditText etPosition, etContract, etEmail, etNumber, etSupervisor, etSupervisorNumber, etCompany;
    ImageView companyLogo;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUserName = rootView.findViewById(R.id.user_name_profile);
        tvUserId = rootView.findViewById(R.id.user_id_profile);
        etPosition = rootView.findViewById(R.id.cargo_user_profile);
        etContract = rootView.findViewById(R.id.ccontrato_user_profile);
        etEmail = rootView.findViewById(R.id.cargo_user_email);
        etNumber = rootView.findViewById(R.id.ccontrato_user_number);
        etSupervisor = rootView.findViewById(R.id.cargo_user_supervisor);
        etSupervisorNumber = rootView.findViewById(R.id.ccontrato_user_number_supervisor);
        etCompany = rootView.findViewById(R.id.cargo_user_company);
        companyLogo = rootView.findViewById(R.id.imageView2);

        /** Opción para llamar al supervisor desde el perfil */
        etSupervisorNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.callPhoneNumber("3183926343", getContext(), getActivity());
            }
        });

        return rootView;
    }
}
