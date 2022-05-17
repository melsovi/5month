package com.company.a5month.data.room.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.company.a5month.data.converter.Converter;
import com.company.a5month.data.model.MainResponse;
import com.company.a5month.data.room.dao.WeatherDao;

@Database(entities = {MainResponse.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract WeatherDao dao();
}
