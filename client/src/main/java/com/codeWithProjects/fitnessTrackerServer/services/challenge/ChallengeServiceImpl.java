package com.codeWithProjects.fitnessTrackerServer.services.challenge;

import com.codeWithProjects.fitnessTrackerServer.dto.ChallengeDTO;
import com.codeWithProjects.fitnessTrackerServer.entity.Activity;
import com.codeWithProjects.fitnessTrackerServer.entity.Challenge;
import com.codeWithProjects.fitnessTrackerServer.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    @Override
    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setTitle(challengeDTO.getTitle());
        challenge.setDescription(challengeDTO.getDescription());
        challenge.setCompleted(challengeDTO.isCompleted());
        challenge.setCompletedDates(new HashSet<>(challengeDTO.getCompletedDates()));
        challenge = challengeRepository.save(challenge);
        return challenge.toDTO();
    }

    @Override
    public List<ChallengeDTO> getAllChallenges() {
        return challengeRepository.findAll().stream()
                .map(Challenge::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChallengeDTO> getChallengeById(Long id) {
        return challengeRepository.findById(id)
                .map(Challenge::toDTO);
    }

    @Override
    public void deleteChallenge(Long id) {
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if (challenge.isPresent()) {
            challengeRepository.delete(challenge.get());
        } else {
            throw new RuntimeException("Activity not found with id " + id);
        }
    }

    @Override
    public ChallengeDTO updateChallenge(Long id, ChallengeDTO challengeDTO) {
        Challenge challenge = challengeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + id));

        challenge.setTitle(challengeDTO.getTitle());
        challenge.setDescription(challengeDTO.getDescription());
        challenge.setCompleted(challengeDTO.isCompleted());
        challenge.setCompletedDates(new HashSet<>(challengeDTO.getCompletedDates()));
        challengeRepository.save(challenge);
        return challenge.toDTO();
    }

    @Override
    public void updateCompletionStatus(Long challengeId, String date, boolean completed) {
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + challengeId));
        if (completed) {
            challenge.getCompletedDates().add(date);
        } else {
            challenge.getCompletedDates().remove(date);
        }
        challengeRepository.save(challenge);
    }

}