package com.mpt.rua_java.di;

import com.mpt.rua_java.data.remote.api.RandomUserApiService;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

/**
 * Módulo de inyección de dependencias para la red
 * Configura Retrofit, OkHttp y servicios API para consumir RandomUser.me
 */
@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
//TODO Cambiar BASE_URL a app/config.properties
    private static final String BASE_URL = "https://randomuser.me/";

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Provides
    @Singleton
    public RandomUserApiService provideRandomUserApiService(Retrofit retrofit) {
        return retrofit.create(RandomUserApiService.class);
    }
}
