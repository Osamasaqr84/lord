package com.codesroots.hossam.lordApp.presentation.screens.profileActivity;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.helper.FileUtils;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.CategoryViewModelFactory;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {

    EditText username, phone;
    ProfileViewModel profileViewModel;
    private FrameLayout progress;
    Uri uri;
    MultipartBody.Part part;
    ImageView upload;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        username = findViewById(R.id.luname);
        phone = findViewById(R.id.phone);
        progress = findViewById(R.id.progress);
        upload = findViewById(R.id.imgupload);

        profileViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(ProfileViewModel.class);


        profileViewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));


        profileViewModel.profileEditLiveData.observe(this,editProfile ->
                {
                    if (editProfile.isSuccess())
                    {
                        username.setText(editProfile.getUser().getUsername());
                        phone.setText(editProfile.getUser().getPhone());
                        Glide.with(this).load("http://lord.codesroots.com/library/userphoto/"+
                                editProfile.getUser().getPhoto()).into(upload);
                        Toast.makeText(this, getString(R.string.editprofilesuccess), Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(this, getString(R.string.aleadytoken), Toast.LENGTH_LONG).show();
                }

        );


        profileViewModel.profileLiveData.observe(this, model ->
                {
                    if (model.isSuccess()) {
                        username.setText(model.getData().getUsername());
                        phone.setText(model.getData().getPhone());
                        if (model.getData().getPhoto()!=null)
                        Glide.with(this).load("http://lord.codesroots.com/library/userphoto/"+model.getData().getPhoto()).into(upload);
                    }
                }
        );
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new ProfileViewModelFactory(this.getApplication());
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                uri = result.getUri();
                upload.setImageURI(uri);////set the image after taking it
                part=prepareFilePart("photo",uri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri) {
        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            file = FileUtils.getFile(ProfileActivity.this, fileUri);
        }

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("photo"),
                        file
                );

        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
    }

    public void getImage(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProfileActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    555);
        }
        else {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(ProfileActivity.this);
        }
    }

    private boolean validate() {

        if (!(username.getText().toString().matches("")) && !(phone.getText().toString().matches(""))) {
            return true;
        } else {
            Toast.makeText(this, getString(R.string.commpletefileds), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void save(View view) {

        if (validate())
          profileViewModel.editProfile(username.getText().toString(),phone.getText().toString(),part);
    }
}
