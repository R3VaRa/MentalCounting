package com.example.mentalcounting;

public class CalculatedStats {
    String personalName;
    Integer lastScore;
    Integer numberGamesPlay;
    Integer biggestScore;
    Integer SmallestScore;
    Double average;

    public CalculatedStats(){}

    public CalculatedStats(String personalName, Integer lastScore, Integer numberGamesPlay,
                           Integer biggestScore, Integer smallestScore, Double average) {
        this.personalName = personalName;
        this.lastScore = lastScore;
        this.numberGamesPlay = numberGamesPlay;
        this.biggestScore = biggestScore;
        SmallestScore = smallestScore;
        this.average = average;
    }

    public String getPersonalName() {
        return personalName;
    }

    public Integer getLastScore() {
        return lastScore;
    }

    public Integer getNumberGamesPlay() {
        return numberGamesPlay;
    }

    public Integer getBiggestScore() {
        return biggestScore;
    }

    public Integer getSmallestScore() {
        return SmallestScore;
    }

    public Double getAverage() {
        return average;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public void setLastScore(Integer lastScore) {
        this.lastScore = lastScore;
    }

    public void setNumberGamesPlay(Integer numberGamesPlay) {
        this.numberGamesPlay = numberGamesPlay;
    }

    public void setBiggestScore(Integer biggestScore) {
        this.biggestScore = biggestScore;
    }

    public void setSmallestScore(Integer smallestScore) {
        SmallestScore = smallestScore;
    }

    public void setAverage(Double average) {
        this.average = average;
    }
}
