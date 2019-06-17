package com.codesroots.hossam.lordApp.presentation.screens.home.loginFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

import com.codesroots.hossam.lordApp.entities.User;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.registerFragment.RegisterFragment;
import com.codesroots.hossam.lordApp.R;
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


public class LoginFragment extends Fragment implements View.OnClickListener {


    TextView login, register, loginWithLogin;
    EditText username, password;
    LoginViewModel loginViewModel;
    private FrameLayout progress;
    private static final String EMAIL = "email";
    CallbackManager callbackManager;
    LoginButton loginButton;
    PreferenceHelper preferenceHelper;
    public LoginFragment() {
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


        loginViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(LoginViewModel.class);

        preferenceHelper = new PreferenceHelper(getActivity());
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        progress = view.findViewById(R.id.progress);
        register = view.findViewById(R.id.register);
        //   loginWithLogin = view.findViewById(R.id.loginWithFB);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        callbackManager = CallbackManager.Factory.create();


        loginButton = view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        loginButton.setReadPermissions(Arrays.asList("public_profile", EMAIL/*, "user_birthday"*/));
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

        loginViewModel.loading.observe(getActivity(), loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));


        loginViewModel.coderesponse.observe(getActivity(), code ->
                {
                    if (code == 401)
                        Toast.makeText(getActivity(), getString(R.string.usererror), Toast.LENGTH_LONG).show();

                }
        );

        loginViewModel.loginLiveData.observe(getActivity(), model ->
                {
                    if (model.isSuccess()) {
                        PreferenceHelper.setUserId(model.getData().getId());
                        preferenceHelper.setToken(model.getData().getToken());
                        preferenceHelper.setUSER_GROUP_ID(Integer.parseInt(model.getData().getGroup()));
                        loginButton.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); ++i) {
                            getFragmentManager().popBackStack();
                        }
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();

                        Toast.makeText(getActivity(), getString(R.string.loginsuccess), Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(), getString(R.string.usererror), Toast.LENGTH_LONG).show();
                    }
                }
        );

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.login:
                if (validate()) {
                    User user = new User(username.getText().toString(), password.getText().toString());
                    loginViewModel.login(user);
                }

                break;

            case R.id.register:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new RegisterFragment()).addToBackStack(null).commit();
                break;

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
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
