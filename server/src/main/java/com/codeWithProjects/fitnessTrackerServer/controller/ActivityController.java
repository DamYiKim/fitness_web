package com.codeWithProjects.fitnessTrackerServer.controller;

import com.codeWithProjects.fitnessTrackerServer.dto.ActivityDTO;
import com.codeWithProjects.fitnessTrackerServer.services.activity.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")

public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activity")
    public ResponseEntity<?> postActivity(@RequestBody ActivityDTO dto){
        ActivityDTO createActivity = activityService.postActivity(dto);

        if(createActivity != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing went wrong");
        }
    }

    @GetMapping("/activities")

    public ResponseEntity<?> getActivities(){
        try {
            return ResponseEntity.ok(activityService.getActivities());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some thing went wrong");
        }
    }

    @DeleteMapping("activities/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
}
