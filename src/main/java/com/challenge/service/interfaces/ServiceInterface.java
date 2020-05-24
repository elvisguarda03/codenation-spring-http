package com.challenge.service.interfaces;

import java.util.List;

public interface ServiceInterface<T> {
    List<T> findAll();
}
