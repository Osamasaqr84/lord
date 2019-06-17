package com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.codesroots.hossam.lordApp.R;

public class CategryAdapter extends RecyclerView.Adapter<CategryAdapter.ViewHolder> {




    private LayoutInflater layoutInflater;
    private Context context;

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    public CategryAdapter(Context context )
    {

        this.context =  context;
    }


    @NonNull
    @Override
    public CategryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_category_item, parent, false);

//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        int width = displayMetrics.widthPixels;
//
//        //////set percentage from all screen
//
//        layoutParams.width = (width / 3) - 10;
//        view.setLayoutParams(layoutParams);

        return new CategryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategryAdapter.ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView Image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            Image = mView.findViewById(R.id.item_image);
        }
    }

}
