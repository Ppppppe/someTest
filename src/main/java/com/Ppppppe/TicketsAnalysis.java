package com.Ppppppe;

import java.time.Duration;
import java.util.List;
import java.util.OptionalDouble;

public class TicketsAnalysis {

    public static String getAverageFlightTime(List<Ticket> list, String origin, String destination) {
        OptionalDouble sum;
        sum = list.stream()
                .filter(t -> t.getOrigin_name().equals(origin))
                .filter(t -> t.getDestination_name().equals(destination))
                .mapToDouble(t -> Duration.between(
                        t.getDeparture_date(),
                        t.getArrival_date()
                ).toMinutes())
                .average();
        int minutes = (int) sum.orElse(0.0);

        return (minutes / 60 + "h " + minutes % 60 + "m");
    }

    public static String getPercentileFlightTime(List<Ticket> list, int percentile, String origin, String destination) {
        if (percentile == 0) return "------";

        double[] ftimeArr = list.stream()
                .filter(t -> t.getOrigin_name().equals(origin))
                .filter(t -> t.getDestination_name().equals(destination))
                .mapToDouble(t -> Duration.between(
                        t.getDeparture_date(),
                        t.getArrival_date()
                ).toMinutes())
                .sorted()
                .toArray();

        double percent = ftimeArr.length / 100.0;
        int percentileAmount = (int) Math.ceil(percent * percentile) - 1;

        int minutes = (int) ftimeArr[percentileAmount];

        return (minutes / 60 + "h " + minutes % 60 + "m");
    }
}
