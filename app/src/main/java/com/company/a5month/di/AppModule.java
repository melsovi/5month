package com.company.a5month.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.company.a5month.data.remote.WeatherApi;
import com.company.a5month.data.repositories.MainRepositoryImpl;
import com.company.a5month.data.room.dao.WeatherDao;
import com.company.a5month.data.room.database.AppDataBase;
import com.company.a5month.domain.repository.MainRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static OkHttpClient provideClient() {
        return new OkHttpClient().newBuilder().addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }

    @Provides
    public static Retrofit provideretrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    public static WeatherApi provideApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    public static MainRepository provideRepository(WeatherApi api, WeatherDao dao) {
        return (MainRepository) new MainRepositoryImpl(api, dao);
    }


    @Singleton
    @Provides
    public static AppDataBase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,
                AppDataBase.class, "database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    public static WeatherDao provideResponseDao(AppDataBase appDatabase) {
        return appDatabase.dao();
    }
}
