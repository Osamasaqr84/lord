package com.codesroots.hossam.lordApp.presentation.screens.profileActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.MainFragment;


public class ProfileFragment extends Fragment implements View.OnClickListener {



    EditText username, password;
    ProfileViewModel profileViewModel;
    private FrameLayout progress;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login, container, false);
        //((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.logintitle));


        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

//
//        username = view.findViewById(R.id.username);
//        password = view.findViewById(R.id.password);
      //  login = view.findViewById(R.id.login);
       // progress = view.findViewById(R.id.progress);


        profileViewModel.loading.observe(getActivity(), loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));



        profileViewModel.profileLiveData.observe(getActivity(), model ->
                {
                    if (model.isSuccess()) {
                        PreferenceHelper.setUserId(model.getData().getId());

                    }

                }
        );

        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//
//            case R.id.login:
//                if (validate()) {
//                    User user = new User(username.getText().toString(), password.getText().toString());
//                   // loginViewModel.login(user);
//                }
//                break;

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    private boolean validate() {

        if (!(username.getText().toString().matches("")) && !(password.getText().toString().matches(""))) {
            return true;
        } else {
            Toast.makeText(getActivity(), getString(R.string.commpletefileds), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }
}
