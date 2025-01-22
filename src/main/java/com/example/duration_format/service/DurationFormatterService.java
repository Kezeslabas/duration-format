package com.example.duration_format.service;

import com.example.duration_format.util.DurationFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class DurationFormatterService {

    private final DurationFormatter formatter;

    public String formatSeconds(Integer seconds){
        return formatter.format(Duration.of(seconds, ChronoUnit.SECONDS));
    }
}
