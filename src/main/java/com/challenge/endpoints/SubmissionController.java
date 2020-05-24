package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/submission")
public class SubmissionController {
    private final SubmissionServiceInterface submissionService;
    private final SubmissionMapper submissionMapper;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findByChallengeIdAndAccelerationId(@RequestParam(required = false) Optional<Long> challengeId,
                                                                @RequestParam(required = false) Optional<Long> accelerationId) {
        if (challengeId.isPresent() && accelerationId.isPresent()) {
            List<SubmissionDTO> submissionDTOS = submissionMapper.map(submissionService
                    .findByChallengeIdAndAccelerationId(challengeId.get(), accelerationId.get()));
            return ResponseEntity.ok(submissionDTOS);
        }

        return ResponseEntity.notFound().build();
    }
}