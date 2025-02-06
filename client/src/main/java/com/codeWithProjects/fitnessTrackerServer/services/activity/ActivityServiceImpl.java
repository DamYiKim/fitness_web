package com.codeWithProjects.fitnessTrackerServer.services.activity;

import com.codeWithProjects.fitnessTrackerServer.dto.ActivityDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.Activity;
import com.codeWithProjects.fitnessTrackerServer.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;

    public ActivityDTO postActivity(ActivityDTO dto){
        Activity activity = new Activity();

        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());
        activity.setType(dto.getType());
        activity.setDuration(dto.getDuration());

        return activityRepository.save(activity).getActivityDTO();
    }

    public List<ActivityDTO> getActivities() {
        return activityRepository.findAll().stream()
                .sorted(Comparator.comparing(Activity::getDate))
                .map(Activity::getActivityDTO)
                .collect(Collectors.toList());
    }

    public void deleteActivity(Long id) {
        Optional<Activity> activity = activityRepository.findById(id);
        if (activity.isPresent()) {
            activityRepository.delete(activity.get());
        } else {
            throw new RuntimeException("Activity not found with id " + id);
        }
    }

}
