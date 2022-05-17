package com.company.a5month.data.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.company.a5month.data.model.MainResponse;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert
    void insert(MainResponse mainResponse);

    @Query("SELECT * FROM mainresponse")
    List<MainResponse> getAll();
}
