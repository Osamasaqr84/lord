package com.codesroots.hossam.lordApp.presentation.screens.home.makeOrderFromGoogleStoresFeragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.helper.FileUtils;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.paymentFragment.PaymentFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductInfoToPost;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.getUserLocation.GetUserLocationActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.codesroots.hossam.lordApp.presentation.screens.getUserLocation.GetUserLocationActivity.FULL_ADDRESS;


public class MakeOrderFromGoogleFragment extends Fragment {


    private static final int LOAD_IMG_REQUEST_CODE = 123;
    public static final int ADDRESS_REQUEST_CODE = 1000;
    ImageView image, logo, deleted_img, added_img;
    EditText description;
    TextView store_name, makeOrder;
    List<ProductInfoToPost> products = new ArrayList<>();
    LinearLayout add_deliverythrough, getLocation;
    View firstcart;
    View addImageLayout;
    Uri uri;
    MultipartBody.Part photo_part;
    Spinner spinner;
    String spinnerValue = "";
    List<String> Stringlist = new ArrayList<String>();
    TextView userloc;
    String USER_ADRESS,USER_LAT,USER_LANG;
    PreferenceHelper preferenceHelper;
    public MakeOrderFromGoogleFragment() {
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
        View view = inflater.inflate(R.layout.confirmorder_from_google, container, false);

        initializeViews(view);
        setupSpinner();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerValue = Stringlist.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        addImageLayout.setOnClickListener(v ->
                addimage()
        );

        firstcart.setOnClickListener(v ->
                startActivityForResult(new Intent(getActivity(), GetUserLocationActivity.class), ADDRESS_REQUEST_CODE)
        );

        deleted_img.setOnClickListener(v ->
                {
                    added_img.setImageResource(R.drawable.order_camp);
                    deleted_img.setVisibility(View.GONE);
                }
        );


        makeOrder.setOnClickListener(v ->
                {

                    if (!description.getText().toString().matches(""))
                    {
                    products.add(new ProductInfoToPost(0,1));
                    Fragment fragment = new PaymentFragment();
                    Bundle bundle = new Bundle();
                    ImagePass imagePass = new ImagePass();
                    bundle.putSerializable("products", (Serializable) products);
                    bundle.putInt("storid", 0);
                    bundle.putBoolean("fromgoogle", true);
                    bundle.putString("store_icon", getArguments().getString("logo"));
                    bundle.putString("store_name", getArguments().getString("store_name"));
                    bundle.putString("notes", description.getText().toString());
                    bundle.putString("delivery_time", spinnerValue);
                    bundle.putString("store_lat", getArguments().getString("store_lat"));
                    bundle.putString("store_lang", getArguments().getString("store_lang"));
                    bundle.putString("store_address", getArguments().getString("store_address"));
                    bundle.putString("user_adress",USER_ADRESS);
                    bundle.putString("user_lat",USER_LAT);
                    bundle.putString("user_lang",USER_LANG);
                    bundle.putFloat("store_rate",getArguments().getFloat("store_rate"));
                    if (photo_part != null) {
                        imagePass.setPhoto_part(photo_part);
                        bundle.putParcelable("photo", imagePass);
                    }

                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();
                }
                    else
                        Snackbar.make(description,getActivity().getText(R.string.nodescription),Snackbar.LENGTH_SHORT).show();
                }

        );

        Glide.with(getActivity()).load(getArguments().getString("image")).into(image);
        Glide.with(getActivity()).load(getArguments().getString("logo")).into(logo);
        store_name.setText(getArguments().getString("name"));

        return view;
    }

    private void setupSpinner() {

        Stringlist.clear();
        Stringlist.add("الـــــتوصيل خــــلال ");
        Stringlist.add("1 ساعة");
        Stringlist.add("2 ساعة");
        Stringlist.add("3 ساعة");
        Stringlist.add("1 يوم");
        Stringlist.add("2 يوم");
        Stringlist.add("3 يوم");

       // getResources().getStringArray(R.string.delivery_through)
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item_custom, Stringlist);

        dataAdapter.setDropDownViewResource( R.layout.spinner_item_custom_popup);
        spinner.setAdapter(dataAdapter);

    }

    private void initializeViews(View view) {
        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.makeorder));
        image = view.findViewById(R.id.item_img);
        logo = view.findViewById(R.id.logo);
        store_name = view.findViewById(R.id.store_name);
        description = view.findViewById(R.id.order_details);
        makeOrder = view.findViewById(R.id.confirm);
        added_img = view.findViewById(R.id.added_img);
        add_deliverythrough = view.findViewById(R.id.secondcart);
        firstcart = view.findViewById(R.id.firstcart);
        deleted_img = view.findViewById(R.id.deleted_img);
        spinner = view.findViewById(R.id.deliverythrough);
        addImageLayout = view.findViewById(R.id.addImageLayout);
        userloc = view.findViewById(R.id.userloc);

    }

    private void addimage() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, LOAD_IMG_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1234);
        }
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == ADDRESS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                USER_ADRESS = data.getExtras().getString(FULL_ADDRESS);
                USER_LAT = data.getExtras().getString("user_lat");
                USER_LANG = data.getExtras().getString("user_lang");
                userloc.setText(USER_ADRESS);
            } else {
                Toast.makeText(getActivity(), "You haven't selected address", Toast.LENGTH_LONG).show();
            }
        } else if (reqCode == LOAD_IMG_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                final Uri imageUri = data.getData();
                added_img.setImageURI(imageUri);
                deleted_img.setVisibility(View.VISIBLE);
                photo_part = prepareFilePart("photo", imageUri);
            } else {
                Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        }
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri) {

        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            file = FileUtils.getFile(getActivity(), fileUri);
        }
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image"),
                        file
                );

        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
    }


}
