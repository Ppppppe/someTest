package com.Ppppppe;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args ) {
        ObjectMapper mapper = new ObjectMapper();

        List<Ticket> list = new ArrayList<>();

        try (FileInputStream jsonFile = new FileInputStream(args[0])){
            JsonNode arrOfTickets = mapper.readTree(jsonFile).get("tickets");
            if (arrOfTickets.isArray()) {
                for (JsonNode jn : arrOfTickets) {
                    list.add(mapper.treeToValue(jn, Ticket.class));
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        String average =
                TicketsAnalysis.getAverageFlightTime(
                        list,
                        "Владивосток",
                        "Тель-Авив"
                );
        String percentile =
                TicketsAnalysis.getPercentileFlightTime(
                        list,
                        90,
                        "Владивосток",
                        "Тель-Авив"
                );
        System.out.println("Average: " + average);
        System.out.println("Percentile 90: " + percentile);
    }
}
