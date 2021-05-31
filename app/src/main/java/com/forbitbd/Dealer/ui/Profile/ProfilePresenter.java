package com.forbitbd.Dealer.ui.Profile;

import android.content.Context;
import android.util.Log;

import com.forbitbd.Dealer.api.ApiClient;
import com.forbitbd.Dealer.api.ServiceGenerator;
import com.forbitbd.Dealer.models.Dealer;
import com.forbitbd.Dealer.utils.AppPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileContract.Presenter{

    private ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View mView) {

        this.mView = mView;

    }


    @Override
    public void updateProfile(String etname, String etaddress, String etorg, String etphone) {

        Dealer dealer = AppPreference.getInstance((Context) mView).getDealer();
        dealer.setName(etname);
        dealer.setAddress(etaddress);
        dealer.setOrganization_name(etorg);
        dealer.setMobile(etphone);
        dealer.setFcm_token(dealer.getFcm_token());

        ApiClient client = ServiceGenerator.createService(ApiClient.class);
        client.updateProfile(dealer.getEmail(),dealer).enqueue(new Callback<Dealer>() {
            @Override
            public void onResponse(Call<Dealer> call, Response<Dealer> response) {
                if (response.isSuccessful()){

                    AppPreference.getInstance((Context) mView).setDealer(dealer);

                    Log.d("SSSSSS", "onResponse: "+AppPreference.getInstance((Context) mView).getDealer().getFcm_token());
                }
            }

            @Override
            public void onFailure(Call<Dealer> call, Throwable t) {

            }
        });
    }
}
