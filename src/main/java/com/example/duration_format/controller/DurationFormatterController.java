package com.example.duration_format.controller;

import com.example.duration_format.service.DurationFormatterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(DurationFormatterController.REQUEST_MAPPING)
public class DurationFormatterController {

    public static final String REQUEST_MAPPING = "/durationformatter";

    private final DurationFormatterService formatterService;

    @GetMapping("/format")
    public ResponseEntity<String> format(@RequestParam Integer seconds){
        return ResponseEntity.ok(formatterService.formatSeconds(seconds));
    }
}
