package com.codeWithProjects.fitnessTrackerServer.services.stats;

import com.codeWithProjects.fitnessTrackerServer.dto.StatsDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.Activity;
import com.codeWithProjects.fitnessTrackerServer.repository.ActivityRepository;
import com.codeWithProjects.fitnessTrackerServer.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class StatsServiceImpl implements StatsService{

    private final GoalRepository goalRepository;

    private final ActivityRepository activityRepository;




    public StatsDTO getStats(){
        Long achievedGoals = goalRepository.countAchievedGoals();
        Long notAchievedGoals = goalRepository.countNotAchievedGoals();

        Integer totalSteps = activityRepository.getTotalSteps();
        Double totalDistance = activityRepository.getTotalDistance();
        Integer totalActivityCaloriesBurned = activityRepository.getTotalActivityCalories();
        Integer totalActivityDuration = activityRepository.getTotalDuration();


        StatsDTO dto = new StatsDTO();
        dto.setAchievedGoals(achievedGoals != null ? achievedGoals : 0);
        dto.setNotAchievedGoals(notAchievedGoals != null ? notAchievedGoals : 0);

        dto.setSteps(totalSteps != null ? totalSteps : 0);
        dto.setDistance(totalDistance != null ? totalDistance : 0.0);
        dto.setTotalCaloriesBurned(totalActivityCaloriesBurned != null ? totalActivityCaloriesBurned:0);
        dto.setDuration(totalActivityDuration != null ? totalActivityDuration:0);


        return dto;
    }

}
