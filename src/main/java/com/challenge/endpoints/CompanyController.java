package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.interfaces.CompanyServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController extends BaseController {
    private final CompanyServiceInterface companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id) {
        if (!isValid(id)) {
            return ResponseEntity.badRequest().build();
        }

        return companyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Company>> findByAccelerationIdOrUserId(
            @RequestParam(required = false) Optional<Long> accelerationId,
            @RequestParam(required = false) Optional<Long> userId) {
        if (accelerationId.isPresent()) {
            return ResponseEntity.ok(companyService.findByAccelerationId(accelerationId.get()));
        }

        if (userId.isPresent()) {
            return ResponseEntity.ok(companyService.findByUserId(userId.get()));
        }

        return buildBadRequest();
    }

    private ResponseEntity<List<Company>> buildBadRequest() {
        return ResponseEntity.badRequest().body(Collections.emptyList());
    }
}