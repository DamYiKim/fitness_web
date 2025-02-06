package com.codeWithProjects.fitnessTrackerServer.services.challenge;

import com.codeWithProjects.fitnessTrackerServer.dto.ChallengeDTO;
import java.util.List;
import java.util.Optional;

public interface ChallengeService {
    ChallengeDTO createChallenge(ChallengeDTO challengeDTO);
    List<ChallengeDTO> getAllChallenges();
    Optional<ChallengeDTO> getChallengeById(Long id);
    void deleteChallenge(Long id);
    ChallengeDTO updateChallenge(Long id, ChallengeDTO challengeDTO);
    void updateCompletionStatus(Long challengeId, String date, boolean completed);


}