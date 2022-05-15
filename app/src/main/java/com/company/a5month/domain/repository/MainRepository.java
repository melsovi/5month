package com.company.a5month.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.company.a5month.common.Resource;
import com.company.a5month.data.model.MainResponse;

import java.util.List;

public interface MainRepository {
    MutableLiveData<Resource<MainResponse>> getWeather(String lat, String lon);

    LiveData<List<MainResponse>> getWeather(List<MainResponse> mainResponses);
}
