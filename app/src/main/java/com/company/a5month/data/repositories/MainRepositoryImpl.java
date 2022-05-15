package com.company.a5month.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.company.a5month.common.Resource;
import com.company.a5month.data.model.MainResponse;
import com.company.a5month.data.remote.WeatherApi;
import com.company.a5month.data.room.dao.WeatherDao;
import com.company.a5month.domain.repository.MainRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositoryImpl implements MainRepository {

    private WeatherApi api;
    private WeatherDao dao;

    @Inject
    public MainRepositoryImpl(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }


    @Override
    public MutableLiveData<Resource<MainResponse>> getWeather(String lat, String lon) {
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getDegree(lat, lon, "c4b856e328e10d6c2c32364cb631f932", "metric").enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    dao.insert(response.body());
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }

    @Override
    public LiveData<List<MainResponse>> getWeather(List<MainResponse> mainResponses) {
        return null;
    }

    public List<MainResponse> getWeatherRoom() {
        return dao.getAll();
    }
}
