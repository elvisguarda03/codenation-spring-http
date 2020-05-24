package com.challenge.endpoints;

import com.challenge.entity.Company;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BaseController {

    public final boolean isValid(Long... ids) {
        for (Long id : ids) {
            if (Objects.isNull(id)) {
                return false;
            }
        }

        return true;
    }
}
