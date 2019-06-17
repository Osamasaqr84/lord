package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.entities.StoreSettingEntity;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.dao.FooterDao;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.homePage.entities.FooterEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashRepository {

    private FooterDao footerDao;
    private ApiInterface apiService;
    private Consumer<StoreSettingEntity> onSuccess;
    private Consumer<Throwable> onError;

    public SplashRepository(FooterDao footerDao, ApiInterface apiService) {
        this.footerDao = footerDao;
        this.apiService = apiService;
        getAllData(50);
    }


    private void getAllData(int userId) {
        try {
            apiService.getStoreSettingData(userId).enqueue(new Callback<StoreSettingEntity>() {
                @Override
                public void onResponse(Call<StoreSettingEntity> call, final Response<StoreSettingEntity> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(response.body());
                            }
                        }
                        else {
                            // TODO: return error
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<StoreSettingEntity> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("SplashRepository", e.getMessage());
            if (onError != null) {
                onError.accept(e);
            }
        }
    }

    public void saveDataInDB(List<StoreSettingEntity.DataBean> data) {
        new AgentAsyncTask(footerDao).execute(getFooterFromAllData(data));
        // localDatabase.footerDao().insertFooter(getFooterFromAllData(data));
    }

    private FooterEntity getFooterFromAllData(List<StoreSettingEntity.DataBean> data) {
        FooterEntity footerEntity = new FooterEntity();
        footerEntity.setBackground(data.get(0).getStoresettings().get(0).getDesign().getFooter().getBackground());
        footerEntity.setFirst_label(data.get(0).getStoresettings().get(0).getDesign().getFooter().getFirst_label());
        footerEntity.setFirst_icon(data.get(0).getStoresettings().get(0).getDesign().getFooter().getFirst_icon());
        footerEntity.setSecond_label(data.get(0).getStoresettings().get(0).getDesign().getFooter().getSecond_label());
        footerEntity.setSecond_icon(data.get(0).getStoresettings().get(0).getDesign().getFooter().getSecond_icon());
        footerEntity.setThird_icon(data.get(0).getStoresettings().get(0).getDesign().getFooter().getThird_icon());
        footerEntity.setThird_label(data.get(0).getStoresettings().get(0).getDesign().getFooter().getThird_label());
        footerEntity.setForth_label(data.get(0).getStoresettings().get(0).getDesign().getFooter().getForth_label());
        footerEntity.setForth_icon(data.get(0).getStoresettings().get(0).getDesign().getFooter().getForth_icon());
        footerEntity.setFifth_label(data.get(0).getStoresettings().get(0).getDesign().getFooter().getFifth_label());
        footerEntity.setFifth_icon(data.get(0).getStoresettings().get(0).getDesign().getFooter().getFifth_icon());
        footerEntity.setBorder(data.get(0).getStoresettings().get(0).getDesign().getFooter().getBorder());
        footerEntity.setFont_color(data.get(0).getStoresettings().get(0).getDesign().getFooter().getFont_color());

        return footerEntity;

    }

    public void setOnSuccess(Consumer<StoreSettingEntity> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

    private static class AgentAsyncTask extends AsyncTask<FooterEntity, Void, Void> {

        private FooterDao footerDao;

        public AgentAsyncTask(FooterDao footerDao1) {
            footerDao = footerDao1;
        }

        @Override
        protected Void doInBackground(FooterEntity... footer) {

            footerDao.insertFooter(footer[0]);
            return null;
        }

    }
}
