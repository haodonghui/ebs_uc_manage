package com.yestae.user.manage.utils;

public class Solar {
    public int solarDay;
    public int solarMonth;
    public int solarYear;

    public int getSolarDay() {
        return solarDay;
    }

    public void setSolarDay(int solarDay) {
        this.solarDay = solarDay;
    }

    public int getSolarMonth() {
        return solarMonth;
    }

    public void setSolarMonth(int solarMonth) {
        this.solarMonth = solarMonth;
    }

    public int getSolarYear() {
        return solarYear;
    }

    public void setSolarYear(int solarYear) {
        this.solarYear = solarYear;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Solar [solarDay=");
        builder.append(solarDay);
        builder.append(", solarMonth=");
        builder.append(solarMonth);
        builder.append(", solarYear=");
        builder.append(solarYear);
        builder.append("]");
        return builder.toString();
    }


}
