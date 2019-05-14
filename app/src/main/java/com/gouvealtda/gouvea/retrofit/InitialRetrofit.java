package com.gouvealtda.gouvea.retrofit;

import com.gouvealtda.gouvea.services.ServicesURL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitialRetrofit {

    private static Retrofit.Builder builderFeature =
            new Retrofit.Builder()
                    .baseUrl(ServicesURL.DOMAIN_BASE_FEATURES)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofiFeature = builderFeature.build();

    public static Retrofit getRetrofitFeature() {
        return retrofiFeature;
    }
}
