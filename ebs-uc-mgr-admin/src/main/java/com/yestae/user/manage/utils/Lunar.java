package com.yestae.user.manage.utils;

public class Lunar {
    public boolean isleap;
    public int lunarDay;
    public int lunarMonth;
    public int lunarYear;

    public boolean isIsleap() {
        return isleap;
    }

    public void setIsleap(boolean isleap) {
        this.isleap = isleap;
    }

    public int getLunarDay() {
        return lunarDay;
    }

    public void setLunarDay(int lunarDay) {
        this.lunarDay = lunarDay;
    }

    public int getLunarMonth() {
        return lunarMonth;
    }

    public void setLunarMonth(int lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    public int getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(int lunarYear) {
        this.lunarYear = lunarYear;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Lunar [isleap=");
        builder.append(isleap);
        builder.append(", lunarDay=");
        builder.append(lunarDay);
        builder.append(", lunarMonth=");
        builder.append(lunarMonth);
        builder.append(", lunarYear=");
        builder.append(lunarYear);
        builder.append("]");
        return builder.toString();
    }

}
