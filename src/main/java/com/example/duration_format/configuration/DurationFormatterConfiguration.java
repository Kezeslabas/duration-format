package com.example.duration_format.configuration;

import com.example.duration_format.util.CustomizableDurationFormatter;
import com.example.duration_format.util.DurationFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.temporal.ChronoUnit;

@Configuration
public class DurationFormatterConfiguration {

    /**
     * Using a bean instead of a {@link org.springframework.stereotype.Component} annotation,
     * so the formatter is easily customizable.
     * <br>
     * This default formatter uses years, days, hours, minutes and seconds.
     */
    @Bean
    public DurationFormatter durationFormatter(){
        CustomizableDurationFormatter formatter = new CustomizableDurationFormatter();

        formatter.addFormatterComponent(ChronoUnit.YEARS, CustomizableDurationFormatter::YEAR_FORMATTER);
        formatter.addFormatterComponent(ChronoUnit.DAYS, CustomizableDurationFormatter::DAY_FORMATTER);
        formatter.addFormatterComponent(ChronoUnit.HOURS, CustomizableDurationFormatter::HOUR_FORMATTER);
        formatter.addFormatterComponent(ChronoUnit.MINUTES, CustomizableDurationFormatter::MINUTE_FORMATTER);
        formatter.addFormatterComponent(ChronoUnit.SECONDS, CustomizableDurationFormatter::SECOND_FORMATTER);

        return formatter;
    }
}
