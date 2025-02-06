package com.codeWithProjects.fitnessTrackerServer.entity;

import com.codeWithProjects.fitnessTrackerServer.dto.ChallengeDTO;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;

    @ElementCollection
    private Set<String> completedDates = new HashSet<>();

    public ChallengeDTO toDTO() {
        ChallengeDTO dto = new ChallengeDTO();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setDescription(this.description);
        dto.setCompleted(this.completed);
        dto.setCompletedDates(this.completedDates != null ? this.completedDates : new HashSet<>());
        return dto;
    }
}