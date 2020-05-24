package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/acceleration")
public class AccelerationController {
    private final AccelerationServiceInterface accelerationService;

    @GetMapping("/{id}")
    public ResponseEntity<Acceleration> findById(@PathVariable Optional<Long> id) {
        if (!id.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return accelerationService.findById(id.get())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Acceleration>> findByCompanyId(@RequestParam(required = false) Optional<Long> companyId) {
        if (!companyId.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(accelerationService.findByCompanyId(companyId.get()));
    }
}