package com.swagoverflow.androidclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.SwagApplication;
import com.swagoverflow.androidclient.models.User;

public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Spinner spinner = (Spinner) view.findViewById(R.id.notificationOption);
        spinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.options)));

        EditText email = (EditText) view.findViewById(R.id.email);
        EditText phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);
        Button button = (Button) view.findViewById(R.id.update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Updates pending", Toast.LENGTH_SHORT).show();
            }
        });

        User user = ((SwagApplication) getActivity().getApplication()).getUser();

        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhone());
    }
}
