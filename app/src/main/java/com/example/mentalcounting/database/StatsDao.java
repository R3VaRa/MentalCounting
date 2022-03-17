package com.example.mentalcounting.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.mentalcounting.entity.Stats;

import java.util.List;

public class StatsDao extends BaseDao<Stats> {

    static final String INDEX_PERSONAL_NAME = "personalName";
    static final String INDEX_SCORE = "score";

    public StatsDao(DataBaseHelper helper) {
        super(helper);
    }

    @Override
    protected String getTableName() {
        return "Statistic";
    }

    @Override
    protected void putValues(ContentValues values, Stats entity) {
        values.put(INDEX_PERSONAL_NAME, entity.getPersonalName());
        values.put(INDEX_SCORE, entity.getScore());
    }

    @Override
    protected Stats getEntity(Cursor cursor) {
        Stats stats = new Stats();
        Integer indexPersonalName = cursor.getColumnIndex(INDEX_PERSONAL_NAME);
        stats.setPersonalName(cursor.getString(indexPersonalName));
        Integer indexScore = cursor.getColumnIndex(INDEX_SCORE);
        stats.setScore(cursor.getInt(indexScore));
        return stats;
    }

    // Récupère les stats de name
    public List<Stats> query(String name) {
        return super.query(INDEX_PERSONAL_NAME+"='"+name+"'", null, null);
    }

    // Récupères toute les stats
    public List<Stats> query(){
        return super.query(null, null, INDEX_SCORE + " DESC, " + INDEX_PERSONAL_NAME + " ASC");
    }
}
