package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {
    private final UserServiceInterface userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        if (!isValid(id)) {
            return ResponseEntity.badRequest().build();
        }

        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> findByCompanyIdOrAccelerationName(@RequestParam(required = false) Optional<Long> companyId,
                                                                        @RequestParam(required = false) Optional<String> accelerationName) {
        if (companyId.isPresent()) {
            return ResponseEntity.ok(userService.findByCompanyId(companyId.get()));
        }
        if (accelerationName.isPresent()) {
            return ResponseEntity.ok(userService.findByAccelerationName(accelerationName.get()));
        }

        return ResponseEntity.notFound().build();
    }
}