package ru.cool.amlapp.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Violation {

    private final int code;
    private final String message;

  }

