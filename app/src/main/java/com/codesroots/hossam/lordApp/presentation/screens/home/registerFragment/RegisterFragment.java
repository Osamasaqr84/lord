package com.codesroots.hossam.lordApp.presentation.screens.home.registerFragment;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.User;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.MainFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;


public class RegisterFragment extends Fragment implements View.OnClickListener {



    TextView male,female,birthdate,register;
    String gender="";
    EditText username,password,phone;
    RegisterViewModel registerViewModel;
    private FrameLayout progress;
    PreferenceHelper preferenceHelper;
    CallbackManager callbackManager;
    LoginButton loginButton;
    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.register, container, false);
        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.registertitle));
        registerViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(RegisterViewModel.class);


        preferenceHelper = new PreferenceHelper(getActivity());
        male= view.findViewById(R.id.male);
        female= view.findViewById(R.id.female);
        birthdate= view.findViewById(R.id.birthdate);
        register= view.findViewById(R.id.login);
        username= view.findViewById(R.id.username);
        password= view.findViewById(R.id.password);
        phone= view.findViewById(R.id.phone);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        birthdate.setOnClickListener(this);
        register.setOnClickListener(this);
        progress = view.findViewById(R.id.progress);


        callbackManager = CallbackManager.Factory.create();


        loginButton = view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        loginButton.setReadPermissions(Arrays.asList("public_profile"/*, EMAIL, "user_birthday"*/));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("fb", loginResult.toString());
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        (object, response) -> {
                            Log.v("LoginActivity", response.toString());

                            // Application code
                            try {

                                String email = object.getString("email");
                                String username = object.getString("name");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("fb", "Canceled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("fb", exception.getMessage());
            }
        });

        registerViewModel.loading.observe(getActivity(),loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));

        registerViewModel.coderesponse.observe(getActivity(),code ->
                {
                    if (code==422)
                        Toast.makeText(getActivity(), getString(R.string.aleadytoken), Toast.LENGTH_SHORT).show();

                }
        );
        registerViewModel.registerLiveData.observe(getActivity(),model ->
                {
                    if (model.isSuccess()) {
                        preferenceHelper.setUserId(model.getData().getId());
                        preferenceHelper.setToken(model.getData().getToken());
                        preferenceHelper.setUSER_GROUP_ID(2);
                        loginButton.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                            fm.popBackStack();
                        }
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();

                        Toast.makeText(getActivity(), getString(R.string.registersuccess), Toast.LENGTH_SHORT).show();

                    }

                    else
                        Toast.makeText(getActivity(), getString(R.string.erroroccur), Toast.LENGTH_SHORT).show();

                }
        );


        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.male :
                male.setTextColor(getResources().getColor(R.color.colorPrimary));
                female.setTextColor(getResources().getColor(R.color.black));
                gender="1";
                break;

            case R.id.female :
                male.setTextColor(getResources().getColor(R.color.black));
                female.setTextColor(getResources().getColor(R.color.colorPrimary));
                gender="0";
                break;

            case R.id.login:
                if (validate()) {
                    User user = new User(username.getText().toString(), password.getText().toString()
                            , gender, birthdate.getText().toString(), phone.getText().toString());
                    registerViewModel.saveUser(user);
                }


                break;

            case R.id.birthdate :
                datePicker();
                break;

        }
    }

    private boolean validate() {

        if (!(username.getText().toString().matches(""))&&!(password.getText().toString().matches(""))&&!(phone.getText().toString().matches(""))
              &&!(birthdate.getText().toString().matches(""))&&!gender.matches(""))
        {
            return true;
        }
        else {
            Toast.makeText(getActivity(), getString(R.string.commpletefileds), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void datePicker() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int  mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                (view, year, monthOfYear, dayOfMonth) -> birthdate.setText(dayOfMonth+"/"+monthOfYear+"/"+year), mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
