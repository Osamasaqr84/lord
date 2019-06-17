package com.codesroots.hossam.lordApp.presentation.screens.chatAndMapActivity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.chatAndMapActivity.Helper.message;
import com.codesroots.hossam.lordApp.R;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.CustomView> {


    private Context context;
    private List<message> allMessage;
    PreferenceHelper preferenceHelper;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    public ChatListAdapter(FragmentActivity activity,List<message> messages) {

        this.context =  activity;
        allMessage = messages;
        preferenceHelper =new PreferenceHelper(context);
    }


    @Override
    public ChatListAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view;

            if (viewType == 1)
                view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_chat_recieved, parent, false);
            else
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_item_chat_send, parent, false);

       // cardView.setCardElevation((float) (width*0.09));


        return new ChatListAdapter.CustomView(view);

    }


    @Override
    public int getItemViewType(int position) {

        if (allMessage.get(position).getFrom()==1)
            return  VIEW_TYPE_MESSAGE_RECEIVED;
        else
            return VIEW_TYPE_MESSAGE_SENT;

    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {


        holder.message.setText(allMessage.get(position).getMessage());
        if (allMessage.get(position).getImageuri()!=null)
        {
            holder.cardwithimage.setVisibility(View.VISIBLE);
            holder.cardwithmessage.setVisibility(View.GONE);
            if(allMessage.get(position).getLocalOrOnlinePath()==0)
            {
                Uri myUri = Uri.parse(allMessage.get(position).getImageuri());
               holder.itemImage.setImageURI(myUri);
            }

        }

    }

    @Override
    public int getItemCount() {
        return allMessage.size();
    }

     class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;
        private TextView message,time;
        LinearLayout cardwithimage;
        CardView cardwithmessage;
        private CustomView(View view) {
            super(view);
            mView = view;

            message=mView.findViewById(R.id.tvMessage);
            time=mView.findViewById(R.id.tvTime);
            itemImage=mView.findViewById(R.id.image);
            cardwithmessage=mView.findViewById(R.id.cardmessage);
            cardwithimage=mView.findViewById(R.id.cardforimage);

        }
    }



}
