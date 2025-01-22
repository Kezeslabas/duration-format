package com.example.duration_format.service;

import com.example.duration_format.util.CustomizableDurationFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.mockito.Mockito.verify;


/**
 * Unit test {@link DurationFormatterService}
 */
@ExtendWith(MockitoExtension.class)
public class DurationFormatterServiceTest {

    @Mock
    CustomizableDurationFormatter mockFormatter;

    @InjectMocks
    DurationFormatterService formatterService;

    /**
     * Test {@link DurationFormatterService#formatSeconds(Integer)}
     * When a value is provided, it is passed to the formatter as the duration of seconds.
     */
    @Test
    public void formatTest() {
        //Arrange
        final Duration expectedDuration = Duration.of(60, ChronoUnit.SECONDS);

        //Act
        formatterService.formatSeconds(60);

        //Assert
        verify(mockFormatter).format(expectedDuration);
    }
}