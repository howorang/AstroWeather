package com.example.astroweather.settings;

/**
 * Created by Piotr Borczyk on 04.05.2017.
 */

public enum UpdateTimeIntervalValues {
    SECOND(1000),
    FIVE_SECONDS(5000),
    THIRTY_SECONDS(30000),
    MINUTE(1000 * 60),
    FIFTEEN_MINUTES(1000 * 60 * 15);

    public final int seconds;

    UpdateTimeIntervalValues(int seconds) {
        this.seconds = seconds;
    }
}
