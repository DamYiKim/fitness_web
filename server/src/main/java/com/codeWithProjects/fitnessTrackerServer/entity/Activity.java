package com.codeWithProjects.fitnessTrackerServer.entity;

import com.codeWithProjects.fitnessTrackerServer.dto.ActivityDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data

public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private LocalDate date;

    private  int steps;

    private double distance;

    private int caloriesBurned;

    private String type = "none";

    private int duration;

    public ActivityDTO getActivityDTO(){
        ActivityDTO activityDTO = new ActivityDTO();

        activityDTO.setId(id);
        activityDTO.setDate(date);
        activityDTO.setDistance(distance);
        activityDTO.setSteps(steps);
        activityDTO.setCaloriesBurned(caloriesBurned);
        activityDTO.setType(type);
        activityDTO.setDuration(duration);


        return activityDTO;
    }

}
