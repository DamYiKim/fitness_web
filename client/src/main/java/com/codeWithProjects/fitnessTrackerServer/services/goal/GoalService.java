package com.codeWithProjects.fitnessTrackerServer.services.goal;

import com.codeWithProjects.fitnessTrackerServer.dto.GoalDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.Goal;

import java.util.List;

public interface GoalService {

    GoalDTO postGoal(GoalDTO dto);

    List<GoalDTO> getGoals();

    void deleteGoal(Long id);

    GoalDTO updateStatus(Long id);
}
