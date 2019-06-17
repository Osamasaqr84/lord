package com.codesroots.hossam.lordApp.presentation.screens.chatAndMapActivity.chat;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.CategoryViewModelFactory;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.chatAndMapActivity.Helper.message;
import com.codesroots.hossam.lordApp.presentation.screens.chatAndMapActivity.adapter.ChatListAdapter;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class ChatingFragment extends Fragment {


    RecyclerView recyclerView;
    ChatListAdapter chatListAdapter;
    ArrayList<message> messages =new ArrayList<>();
    TextView etMessage;
    private static final int LOAD_IMG_REQUEST_CODE = 123;
    ImageView send,getimage;


    public ChatingFragment() {
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
        View view = inflater.inflate(R.layout.activity_chat, container, false);

        recyclerView = view.findViewById(R.id.chatrecycle);
        etMessage = view.findViewById(R.id.etMessage);
        send = view.findViewById(R.id.ivSend);
        getimage = view.findViewById(R.id.ivPhoto);
        send.setOnClickListener(v -> sendmessage());
        getimage.setOnClickListener(v -> addimage(view));



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        messages.add(new message("السلام عليكم",0,null,0));
        messages.add(new message(" عليكم السلام",1,null,0));
        messages.add(new message(" كيف الحال ",0,null,0));
        messages.add(new message("الحمد  لله",1,null,0));

        chatListAdapter = new ChatListAdapter(getActivity(),messages);
        recyclerView.setAdapter(chatListAdapter);

        return view;

    }


    public void sendmessage() {
        messages.add(new message(etMessage.getText().toString(),1,null,-1));
        chatListAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(messages.size()-1);
        etMessage.setText("");

    }



    private void addimage(View view) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOAD_IMG_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                final Uri imageUri = data.getData();
                messages.add(new message(etMessage.getText().toString(),1,imageUri.toString(),0));
                chatListAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(messages.size());

            } else {
                Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        }
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new CategoryViewModelFactory(getActivity().getApplication());
    }

}
