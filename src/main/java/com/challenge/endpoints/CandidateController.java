package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.interfaces.CandidateServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/candidate")
public class CandidateController extends BaseController {
    private final CandidateServiceInterface candidateService;
    private final CandidateMapper candidateMapper;

    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable Long userId,
                                                    @PathVariable Long companyId,
                                                    @PathVariable Long accelerationId) {
        if (!isValid(userId, companyId, accelerationId)) {
            return ResponseEntity.badRequest().build();
        }

        return candidateService.findById(userId, companyId, accelerationId)
                .map(candidate -> ResponseEntity.ok(candidateMapper.map(candidate)))
                .orElse(ResponseEntity.ok(CandidateDTO.builder().build()));
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findByCompanyIdOrAccelerationId(
            @RequestParam(required = false) Optional<Long> companyId,
            @RequestParam(required = false) Optional<Long> accelerationId) {
        if (companyId.isPresent()) {
            List<CandidateDTO> candidateDTOS = candidateMapper.map(candidateService
                    .findByCompanyId(companyId.get()));
            return ResponseEntity.ok(candidateDTOS);
        }

        if (accelerationId.isPresent()) {
            List<CandidateDTO> candidateDTOS = candidateMapper.map(candidateService
                    .findByAccelerationId(accelerationId.get()));
            return ResponseEntity.ok(candidateDTOS);
        }

        return ResponseEntity.badRequest().body(Collections.emptyList());
    }
}
