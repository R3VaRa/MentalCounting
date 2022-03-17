package com.example.mentalcounting.entity;

public class Stats extends BaseEntity {

    String personalName;
    Integer score;


    public String getPersonalName() {
        return personalName;
    }

    public Integer getScore() {
        return score;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return (personalName + '\n' +
                "score = " + score + "\n");
    }

    public String toStringName() {
        return (personalName + '\n');
    }

    public String toStringScore() {
        return (score.toString() + '\n');
    }
}
