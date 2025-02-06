package com.codeWithProjects.fitnessTrackerServer.dto;

import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class ChallengeDTO {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Set<String> completedDates = new HashSet<>();
}