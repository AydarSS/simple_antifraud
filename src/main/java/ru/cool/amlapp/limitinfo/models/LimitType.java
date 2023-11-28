package ru.cool.amlapp.limitinfo.models;

import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;


public enum LimitType {
    MONTH (30),
    DAY (1),
    WEEK (7);

    LimitType(long minusDays) {
        this.minusDays = minusDays;
    }

    private long minusDays;

    public OffsetDateTime calculateBeginDateByDate(OffsetDateTime endDateTime) {
        return endDateTime.minusDays(minusDays);
    }
}
