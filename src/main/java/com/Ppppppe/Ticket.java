package com.Ppppppe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.*;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

        private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");

        private String origin_name;
        private String destination_name;
        private ZonedDateTime departure_date;
        private ZonedDateTime arrival_date;

        private LocalDate departureLocalDate;
        private LocalTime departureLocalTime;
        private LocalDate arrivalLocalDate;
        private LocalTime arrivalLocalTime;

        public Ticket() { }

        @Override
        public String toString() {
                return  origin_name +
                        "----" + destination_name +
                        "----" + departure_date.toString() +
                        "----" + arrival_date.toString() +
                        "----" + Duration.between(departure_date, arrival_date);
        }

        public void setOrigin_name(String origin_name) {
                this.origin_name = origin_name;
        }

        public void setDestination_name(String destination_name) {
                this.destination_name = destination_name;
        }

        @JsonSetter("departure_date")
        public void setDeparture_date(String date) {
                this.departureLocalDate = LocalDate.parse(date, dateFormatter);
        }

        @JsonSetter("departure_time")
        public void setDeparture_time(String time) {
                this.departureLocalTime = LocalTime.parse(time, timeFormatter);
                this.departure_date = ZonedDateTime.of(departureLocalDate, departureLocalTime, UtcOffset.of(origin_name));
        }

        @JsonSetter("arrival_date")
        public void setArrival_date(String date) {
                this.arrivalLocalDate = LocalDate.parse(date, dateFormatter);
        }

        @JsonSetter("arrival_time")
        public void setArrival_time(String time) {
                this.arrivalLocalTime = LocalTime.parse(time, timeFormatter);
                this.arrival_date = ZonedDateTime.of(arrivalLocalDate, arrivalLocalTime, UtcOffset.of(destination_name));
        }

        public ZonedDateTime getDeparture_date() {
                return departure_date;
        }

        public ZonedDateTime getArrival_date() {
                return arrival_date;
        }

        public String getOrigin_name() {
                return origin_name;
        }

        public String getDestination_name() {
                return destination_name;
        }
}
