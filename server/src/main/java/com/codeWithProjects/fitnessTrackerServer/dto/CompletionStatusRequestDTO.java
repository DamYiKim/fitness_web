package com.codeWithProjects.fitnessTrackerServer.dto;

import lombok.Data;

@Data
public class CompletionStatusRequestDTO {
    private String date;
    private boolean completed;
}