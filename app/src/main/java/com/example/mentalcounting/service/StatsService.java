package com.example.mentalcounting.service;

import com.example.mentalcounting.CalculatedStats;
import com.example.mentalcounting.database.StatsDao;
import com.example.mentalcounting.entity.Stats;

import java.util.List;

public class StatsService {
    private StatsDao statsDao;

    public StatsService(StatsDao statsDao) {
        this.statsDao = statsDao;
    }

    public void storeStatsInDatabase(Stats stats) {
        statsDao.create(stats);
    }

    public CalculatedStats getByName(String name) {
        List<Stats> statsList = statsDao.query(name);
        if (!statsList.isEmpty()) {
            CalculatedStats rendu = new CalculatedStats("0", 0, 0, 0, 999999999, 0.0);
            for (Stats stats : statsList) {
                rendu.setPersonalName(stats.getPersonalName());
                rendu.setLastScore(stats.getScore());
                rendu.setNumberGamesPlay(statsList.size());
                if (rendu.getBiggestScore() < stats.getScore())
                    rendu.setBiggestScore(stats.getScore());
                if (rendu.getSmallestScore() > stats.getScore())
                    rendu.setSmallestScore(stats.getScore());
                rendu.setAverage(rendu.getAverage() + stats.getScore());
            }
            rendu.setAverage(rendu.getAverage() / statsList.size());
            return rendu;
        } else {
            return new CalculatedStats();
        }
    }

    public List<Stats> getSortByScore() {
        return statsDao.query();
    }
}
