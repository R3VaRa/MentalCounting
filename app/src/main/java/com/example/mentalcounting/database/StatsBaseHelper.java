package com.example.mentalcounting.database;

import android.content.Context;

public class StatsBaseHelper extends DataBaseHelper{
    public StatsBaseHelper(Context context){
        super(context, "Statistic",1);
    }

    @Override
    protected String getCreationSql(){
        return "CREATE TABLE IF NOT EXISTS Statistic (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                StatsDao.INDEX_PERSONAL_NAME + " VARCHAR(50) NOT NULL," +
                StatsDao.INDEX_SCORE + " INTEGER NOT NULL" +
                ")";
    }

    @Override
    protected String getDeleteSql() {
        return null;
    }
}
