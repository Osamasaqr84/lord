package com.codesroots.hossam.lordApp.presentation.screens.home.menuFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.Menuitem;
import com.codesroots.hossam.lordApp.entities.Notifications;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.ContionsActivity;
import com.codesroots.hossam.lordApp.presentation.screens.callus.CallUsActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.MainFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.favoriteFragment.FavoriteFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.loginFragment.LoginFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.notificationFragment.NotificationsViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.profileActivity.ProfileActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MenuFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private NotificationsViewModel notificationsViewModel;
    int type, userid;
    MenuAdapter MenuAdapter;
    private FrameLayout progress;
    ArrayList<Menuitem> menus = new ArrayList<Menuitem>();

    public MenuFragment() {
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

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.recylerview);

        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.menu));
       // ((HomeActivity)Objects.requireNonNull(getActivity())).footer.getMenu().findItem(R.id.navigation_more).setChecked(true);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
        recyclerView.setLayoutAnimation(animation);
        menus.clear();
            menus.add(new Menuitem("حسابي",R.drawable.profile));
            menus.add(new Menuitem("اتصل بنا",R.drawable.callus));
            menus.add(new Menuitem(" من نحن ",R.drawable.we));
            menus.add(new Menuitem("المنتجات المفضلة",R.drawable.star_menu));
            menus.add(new Menuitem(" الشروط والاحكام",R.drawable.we));
            if (PreferenceHelper.getUserId()>0)
                menus.add(new Menuitem("  تسجيل الخروج ",R.drawable.profile));
            else
                menus.add(new Menuitem("تسجيل الدخول ",R.drawable.profile));




        recyclerView.setAdapter(new MenuAdapter(menus));
        return view;
    }


    public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

        private LayoutInflater layoutInflater;
        List<Notifications.DataBean> allnotification;
        private Context context;

        public MenuAdapter(ArrayList<Menuitem> menus) {

        }

        @Override
        public MenuAdapter.ViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menuitem, parent, false);

            return new MenuAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MenuAdapter.ViewHolder holder, final int position) {




            holder.text.setText(menus.get(position).getTitle());
            holder.imageView.setImageResource(menus.get(position).getImageResorce());

            holder.mView.setOnClickListener(v ->
                    {
                        switch (position)
                        {

                            case 0:
                                if (PreferenceHelper.getUserId()>0)
                             startActivity(new Intent(getActivity(),ProfileActivity.class));
                                else
                            Toast.makeText(getActivity(),"قم بتسجيل الدخول اولا",Toast.LENGTH_SHORT).show();
                                break;

                            case 1:
                                startActivity(new Intent(getActivity(),CallUsActivity.class));
                                break;
                            case 2:
                                String url = "http://newcodesroots.codesroots.com/login";
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);
                                break;
                            case 3:

                                if (PreferenceHelper.getUserId()>0) {
                                    for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); ++i) {
                                        getFragmentManager().popBackStack();
                                    }
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FavoriteFragment()).addToBackStack(null).commit();

                                }
                                else
                                    Toast.makeText(getActivity(),"قم بتسجيل الدخول اولا",Toast.LENGTH_SHORT).show();

                                break;

                            case 4:
                                startActivity(new Intent(getActivity(),ContionsActivity.class));
                                break;

                            case 5:
                                if(PreferenceHelper.getUserId()>0)
                                {
                                    PreferenceHelper.setUserId(0);
                                    PreferenceHelper.setUSER_GROUP_ID(0);
                                    Toast.makeText(getActivity(),"تم تسجيل خروجك",Toast.LENGTH_SHORT).show();
                                    for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); ++i) {
                                        getFragmentManager().popBackStack();
                                    }
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();

                                }
                                else
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new LoginFragment()).addToBackStack(null).commit();
                                break;
                        }
                    }
            );
        }

        @Override
        public int getItemCount() {
            return menus.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            TextView text;
            ImageView imageView;

            public ViewHolder(View v) {
                super(v);
                mView=v;
                text = v.findViewById(R.id.item_txt);
                imageView = v.findViewById(R.id.item_img);
            }
        }
    }
}
