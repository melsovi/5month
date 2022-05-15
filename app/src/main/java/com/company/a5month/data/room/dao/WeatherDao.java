package com.company.a5month.data.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.company.a5month.data.model.MainResponse;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert
    void insert(MainResponse mainResponse);

    @Query("SELECT * FROM mainresponse")
    List<MainResponse> getAll();
}
