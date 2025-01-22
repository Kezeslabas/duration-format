package com.example.duration_format.controller;

import com.example.duration_format.DurationFormatControllerTestBase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test {@link DurationFormatterController}
 */
public class DurationFormatterControllerTest extends DurationFormatControllerTestBase {

    @Override
    public String requestMapping() {
        return DurationFormatterController.REQUEST_MAPPING;
    }

    /**
     * Test {@link DurationFormatterController#format(Integer)}
     * - When no 'seconds' param is passed, then BAD_REQUEST is returned
     * - When the 'seconds' param is empty, then BAD_REQUEST is returned
     * - When the 'seconds' param is not an integer, then BAD_REQUEST is returned
     * - When the 'seconds' param is an integer, then OK is returned with the resulted formatted string
     */
    @Test
    @SneakyThrows
    public void formatTest() {
        doGet("format", Map.of())
                .andExpect(status().isBadRequest());

        doGet("format", Map.of("seconds", ""))
                .andExpect(status().isBadRequest());

        doGet("format", Map.of("seconds", "ABC"))
                .andExpect(status().isBadRequest());

        doGet("format", Map.of("seconds", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1 second"));
    }
}