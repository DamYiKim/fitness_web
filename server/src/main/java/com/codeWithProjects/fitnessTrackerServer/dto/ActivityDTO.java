package com.codeWithProjects.fitnessTrackerServer.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data

public class ActivityDTO {

    private Long id;

    private LocalDate date;

    private  int steps;

    private double distance;

    private int caloriesBurned;

    private String type= "none";

    private int duration;
}
