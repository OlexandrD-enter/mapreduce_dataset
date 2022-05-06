package com.protran.bd;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class DateTransporter {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateEnd;
    Type type;

    public DateTransporter() {
    }

    public DateTransporter(LocalDateTime dateStart, LocalDateTime dateEnd, Type type) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.type = type;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DateTransporter{" +
                "dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", type=" + type +
                '}';
    }
}
