package com.codeWithProjects.fitnessTrackerServer.controller;

import com.codeWithProjects.fitnessTrackerServer.dto.ChallengeDTO;
import com.codeWithProjects.fitnessTrackerServer.dto.CompletionStatusRequestDTO;
import com.codeWithProjects.fitnessTrackerServer.services.challenge.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChallengeController {

    private final ChallengeService challengeService;

    @PostMapping("/challenge")
    public ChallengeDTO createChallenge(@RequestBody ChallengeDTO challengeDTO) {
        return challengeService.createChallenge(challengeDTO);
    }

    @GetMapping("/challenges")
    public List<ChallengeDTO> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @GetMapping("/challenges/{id}")
    public Optional<ChallengeDTO> getChallengeById(@PathVariable Long id) {
        return challengeService.getChallengeById(id);
    }


    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {
        try {
            challengeService.deleteChallenge(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    @PutMapping("/challenges/{id}")
    public ChallengeDTO updateChallenge(@PathVariable Long id, @RequestBody ChallengeDTO challengeDTO) {
        return challengeService.updateChallenge(id, challengeDTO);
    }

    @PostMapping("/challenges/{id}/completion")
    public void updateCompletionStatus(@PathVariable Long id, @RequestBody CompletionStatusRequestDTO request) {
        challengeService.updateCompletionStatus(id, request.getDate(), request.isCompleted());
    }
}