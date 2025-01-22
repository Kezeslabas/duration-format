package com.example.duration_format.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Unit Test {@link CustomizableDurationFormatter}
 */
public class CustomizableDurationFormatterTest {

    private final CustomizableDurationFormatter defaultFormatter;

    public CustomizableDurationFormatterTest() {
        defaultFormatter = new CustomizableDurationFormatter();

        defaultFormatter.addFormatterComponent(ChronoUnit.YEARS, CustomizableDurationFormatter::YEAR_FORMATTER);
        defaultFormatter.addFormatterComponent(ChronoUnit.DAYS, CustomizableDurationFormatter::DAY_FORMATTER);
        defaultFormatter.addFormatterComponent(ChronoUnit.HOURS, CustomizableDurationFormatter::HOUR_FORMATTER);
        defaultFormatter.addFormatterComponent(ChronoUnit.MINUTES, CustomizableDurationFormatter::MINUTE_FORMATTER);
        defaultFormatter.addFormatterComponent(ChronoUnit.SECONDS, CustomizableDurationFormatter::SECOND_FORMATTER);
    }

    /**
     * Test {@link CustomizableDurationFormatter#GENERIC_CHRONO_FORMATTER(int, String, String)}
     * - When the time part is 0 or less, then an empty string is returned
     * - When the time part is 1, then the value and the singular name is returned
     * - When the time part is greater than 1, then the value and the plural name is returned
     */
    @Test
    public void genericChronoFormatterTest() {
        //Arrange & Act
        String zeroPart = CustomizableDurationFormatter.GENERIC_CHRONO_FORMATTER(0, "singular", "plural");
        String onePart = CustomizableDurationFormatter.GENERIC_CHRONO_FORMATTER(1, "singular", "plural");
        String twoPart = CustomizableDurationFormatter.GENERIC_CHRONO_FORMATTER(2, "singular", "plural");
        String multiplePart = CustomizableDurationFormatter.GENERIC_CHRONO_FORMATTER(66, "singular", "plural");

        //-Assert
        Assertions.assertEquals("", zeroPart);
        Assertions.assertEquals("1 singular", onePart);
        Assertions.assertEquals("2 plural", twoPart);
        Assertions.assertEquals("66 plural", multiplePart);
    }

    /**
     * Test {@link CustomizableDurationFormatter#YEAR_FORMATTER(Duration)}
     * - When the seconds are less than a year, then an empty string is returned
     * - When the seconds are exactly one year, but less than two, then '1 year' is returned
     * - When the seconds are two or more years, then the number of years and 'years' is returned
     */
    @Test
    public void yearFormatterTest() {
        //Arrange & Act
        String zeroPart = CustomizableDurationFormatter.YEAR_FORMATTER(Duration.of(0, ChronoUnit.SECONDS));
        String lessThanAYearPart = CustomizableDurationFormatter.YEAR_FORMATTER(Duration.of(200, ChronoUnit.DAYS));
        String exactlyAYearPart = CustomizableDurationFormatter.YEAR_FORMATTER(Duration.of(365, ChronoUnit.DAYS));
        String moreThanAYearPart = CustomizableDurationFormatter.YEAR_FORMATTER(Duration.of(400, ChronoUnit.DAYS));
        String exactlyTwoYearPart = CustomizableDurationFormatter.YEAR_FORMATTER(Duration.of(730, ChronoUnit.DAYS));
        String moreThanTwoYearPart = CustomizableDurationFormatter.YEAR_FORMATTER(Duration.of(1000, ChronoUnit.DAYS));

        //Assert
        Assertions.assertEquals("", zeroPart);
        Assertions.assertEquals("", lessThanAYearPart);
        Assertions.assertEquals("1 year", exactlyAYearPart);
        Assertions.assertEquals("1 year", moreThanAYearPart);
        Assertions.assertEquals("2 years", exactlyTwoYearPart);
        Assertions.assertEquals("2 years", moreThanTwoYearPart);
    }

    /**
     * Test {@link CustomizableDurationFormatter#DAY_FORMATTER(Duration)}
     * - When then number of days is 0, then an empty string is returned
     * - When the number of days is divisible by 365, then an empty string is returned
     * - When the number of hours is less than one day, then an empty string is returned
     * - When the number of hours is exactly 1 day or more than 1 day, then '1 day' is returned
     * - When the number of hours is exactly or more than two days, then the number of days and 'days' is returned
     * - When the number of hours represent more than 365 days, then only the excess is counted
     */
    @Test
    public void dayFormatterTest() {
        //Arrange & Act
        String zeroPart = CustomizableDurationFormatter.DAY_FORMATTER(Duration.of(0, ChronoUnit.DAYS));
        String fullYearPart = CustomizableDurationFormatter.DAY_FORMATTER(Duration.of(365, ChronoUnit.DAYS));
        String lessThanADayPart = CustomizableDurationFormatter.DAY_FORMATTER(Duration.of(23, ChronoUnit.HOURS));
        String exactlyOneDayPart = CustomizableDurationFormatter.DAY_FORMATTER(Duration.of(24, ChronoUnit.HOURS));
        String moreThanOneDayPart = CustomizableDurationFormatter.DAY_FORMATTER(Duration.of(25, ChronoUnit.HOURS));
        String exactlyTwoDaysPart = CustomizableDurationFormatter.DAY_FORMATTER(Duration.of(48, ChronoUnit.HOURS));
        String moreThanTwoDaysPart = CustomizableDurationFormatter.DAY_FORMATTER(Duration.of(50, ChronoUnit.HOURS));
        String moreThanAYearPart = CustomizableDurationFormatter.DAY_FORMATTER(Duration.of(366, ChronoUnit.DAYS));

        //Assert
        Assertions.assertEquals("", zeroPart);
        Assertions.assertEquals("", fullYearPart);
        Assertions.assertEquals("", lessThanADayPart);
        Assertions.assertEquals("1 day", exactlyOneDayPart);
        Assertions.assertEquals("1 day", moreThanOneDayPart);
        Assertions.assertEquals("2 days", exactlyTwoDaysPart);
        Assertions.assertEquals("2 days", moreThanTwoDaysPart);
        Assertions.assertEquals("1 day", moreThanAYearPart);
    }

    /**
     * Test {@link CustomizableDurationFormatter#HOUR_FORMATTER(Duration)}
     * - When the number of hours is 0, then an empty string is returned
     * - When the number of hours is divisible by 24, then an empty string is returned
     * - When the number of minutes is less than 1 hour, then an empty string is returned
     * - When the number of minutes is exactly 1 hour, then '1 hour' is returned
     * - When the number of minutes is more than 1 hour but less than two, then '1 hour' is returned
     * - When the number of minutes is 2 hours or more, then the number of hours and 'hours' is returned
     * - When then number of hours represent more than a day, then only the excess is counted
     */
    @Test
    public void hourFormatterTest() {
        //Arrange & Act
        String zeroPart = CustomizableDurationFormatter.HOUR_FORMATTER(Duration.of(0, ChronoUnit.HOURS));
        String oneDayPart = CustomizableDurationFormatter.HOUR_FORMATTER(Duration.of(24, ChronoUnit.HOURS));
        String lessThanOneHourPart = CustomizableDurationFormatter.HOUR_FORMATTER(Duration.of(59, ChronoUnit.MINUTES));
        String oneHourPart = CustomizableDurationFormatter.HOUR_FORMATTER(Duration.of(60, ChronoUnit.MINUTES));
        String moreThanOneHourPart = CustomizableDurationFormatter.HOUR_FORMATTER(Duration.of(61, ChronoUnit.MINUTES));
        String twoHourPart = CustomizableDurationFormatter.HOUR_FORMATTER(Duration.of(120, ChronoUnit.MINUTES));
        String moreThanTwoHourPart = CustomizableDurationFormatter.HOUR_FORMATTER(Duration.of(121, ChronoUnit.MINUTES));
        String moreThanADayPart = CustomizableDurationFormatter.HOUR_FORMATTER(Duration.of(25, ChronoUnit.HOURS));

        //Assert
        Assertions.assertEquals("", zeroPart);
        Assertions.assertEquals("", oneDayPart);
        Assertions.assertEquals("", lessThanOneHourPart);
        Assertions.assertEquals("1 hour", oneHourPart);
        Assertions.assertEquals("1 hour", moreThanOneHourPart);
        Assertions.assertEquals("2 hours", twoHourPart);
        Assertions.assertEquals("2 hours", moreThanTwoHourPart);
        Assertions.assertEquals("1 hour", moreThanADayPart);
    }

    /**
     * Test {@link CustomizableDurationFormatter#MINUTE_FORMATTER(Duration)} (Duration)}
     * - When the number of minutes is 0, then an empty string is returned
     * - When the number of minutes is divisible by 60, then an empty string is returned
     * - When the number of seconds is less than 1 minute, then an empty string is returned
     * - When the number of seconds is exactly 1 minute, then '1 minute' is returned
     * - When the number of seconds is more than 1 minute but less than two, then '1 minute' is returned
     * - When the number of seconds is 2 minutes or more, then the number of minutes and 'minutes' is returned
     * - When then number of minutes represent more than an hour, then only the excess is counted
     */
    @Test
    public void minuteFormatterTest() {
        //Arrange & Act
        String zeroPart = CustomizableDurationFormatter.MINUTE_FORMATTER(Duration.of(0, ChronoUnit.HOURS));
        String oneHourPart = CustomizableDurationFormatter.MINUTE_FORMATTER(Duration.of(60, ChronoUnit.MINUTES));
        String lessThanOneMinutePart = CustomizableDurationFormatter.MINUTE_FORMATTER(Duration.of(59, ChronoUnit.SECONDS));
        String oneMinutePart = CustomizableDurationFormatter.MINUTE_FORMATTER(Duration.of(60, ChronoUnit.SECONDS));
        String moreThanOneMinutePart = CustomizableDurationFormatter.MINUTE_FORMATTER(Duration.of(61, ChronoUnit.SECONDS));
        String twoMinutePart = CustomizableDurationFormatter.MINUTE_FORMATTER(Duration.of(120, ChronoUnit.SECONDS));
        String moreThanTwoMinutePart = CustomizableDurationFormatter.MINUTE_FORMATTER(Duration.of(121, ChronoUnit.SECONDS));
        String moreThanAnHourPart = CustomizableDurationFormatter.MINUTE_FORMATTER(Duration.of(61, ChronoUnit.MINUTES));

        //Assert
        Assertions.assertEquals("", zeroPart);
        Assertions.assertEquals("", oneHourPart);
        Assertions.assertEquals("", lessThanOneMinutePart);
        Assertions.assertEquals("1 minute", oneMinutePart);
        Assertions.assertEquals("1 minute", moreThanOneMinutePart);
        Assertions.assertEquals("2 minutes", twoMinutePart);
        Assertions.assertEquals("2 minutes", moreThanTwoMinutePart);
        Assertions.assertEquals("1 minute", moreThanAnHourPart);
    }

    /**
     * Test {@link CustomizableDurationFormatter#SECOND_FORMATTER(Duration)}
     * - When the number of seconds is 0, then an empty string is returned
     * - When the number of seconds is divisible by 60, then an empty string is returned
     * - When the number of millis is less than 1 second, then an empty string is returned
     * - When the number of millis is exactly 1 second, then '1 second' is returned
     * - When the number of millis is more than 1 second but less than two, then '1 second' is returned
     * - When the number of millis is 2 seconds or more, then the number of seconds and 'seconds' is returned
     * - When then number if second represent more than a minute, then only the excess is counted
     */
    @Test
    public void secondFormatterTest() {
        //Arrange & Act
        String zeroPart = CustomizableDurationFormatter.SECOND_FORMATTER(Duration.of(0, ChronoUnit.SECONDS));
        String oneMinutePart = CustomizableDurationFormatter.SECOND_FORMATTER(Duration.of(60, ChronoUnit.SECONDS));
        String lessThanOneSecondPart = CustomizableDurationFormatter.SECOND_FORMATTER(Duration.of(999, ChronoUnit.MILLIS));
        String oneSecondPart = CustomizableDurationFormatter.SECOND_FORMATTER(Duration.of(1000, ChronoUnit.MILLIS));
        String moreThanOneSecondPart = CustomizableDurationFormatter.SECOND_FORMATTER(Duration.of(1001, ChronoUnit.MILLIS));
        String twoSecondPart = CustomizableDurationFormatter.SECOND_FORMATTER(Duration.of(2000, ChronoUnit.MILLIS));
        String moreThanTwoSecondPart = CustomizableDurationFormatter.SECOND_FORMATTER(Duration.of(2001, ChronoUnit.MILLIS));
        String moreThanAMinutePart = CustomizableDurationFormatter.SECOND_FORMATTER(Duration.of(61, ChronoUnit.SECONDS));

        //Assert
        Assertions.assertEquals("", zeroPart);
        Assertions.assertEquals("", oneMinutePart);
        Assertions.assertEquals("", lessThanOneSecondPart);
        Assertions.assertEquals("1 second", oneSecondPart);
        Assertions.assertEquals("1 second", moreThanOneSecondPart);
        Assertions.assertEquals("2 seconds", twoSecondPart);
        Assertions.assertEquals("2 seconds", moreThanTwoSecondPart);
        Assertions.assertEquals("1 second", moreThanAMinutePart);
    }

    /**
     * Test {@link CustomizableDurationFormatter#format(Duration)}
     * - When the duration is 0, then 'now' is returned.
     * - When there is only one part (year, day, hour, minute, second), then that part is returned
     */
    @Test
    public void formatTest_singlePartAndNow(){
        //Arrange & Act
        String zeroSec = defaultFormatter.format(Duration.of(0, ChronoUnit.SECONDS));

        String oneSec = defaultFormatter.format(Duration.of(1, ChronoUnit.SECONDS));
        String oneMin = defaultFormatter.format(Duration.of(1, ChronoUnit.MINUTES));
        String oneHour = defaultFormatter.format(Duration.of(1, ChronoUnit.HOURS));
        String oneDay = defaultFormatter.format(Duration.of(1, ChronoUnit.DAYS));
        String oneYear = defaultFormatter.format(Duration.of(365, ChronoUnit.DAYS));

        //Assert
        Assertions.assertEquals("now", zeroSec);

        Assertions.assertEquals("1 second", oneSec);
        Assertions.assertEquals("1 minute", oneMin);
        Assertions.assertEquals("1 hour", oneHour);
        Assertions.assertEquals("1 day", oneDay);
        Assertions.assertEquals("1 year", oneYear);
    }

    /**
     * Test {@link CustomizableDurationFormatter#format(Duration)}
     * - When there are at least two parts, then both is returned, separated by 'and'
     * - The parts are always in chronological order
     */
    @Test
    public void formatTest_doublePart(){
        //Arrange & Act
        String oneSecOneMin = defaultFormatter.format(Duration.of(61, ChronoUnit.SECONDS));
        String oneMinOnHour = defaultFormatter.format(Duration.of(61, ChronoUnit.MINUTES));
        String oneHourOneDay = defaultFormatter.format(Duration.of(25, ChronoUnit.HOURS));
        String oneDayOneYear = defaultFormatter.format(Duration.of(366, ChronoUnit.DAYS));

        //Assert
        Assertions.assertEquals("1 minute and 1 second", oneSecOneMin);
        Assertions.assertEquals("1 hour and 1 minute", oneMinOnHour);
        Assertions.assertEquals("1 day and 1 hour", oneHourOneDay);
        Assertions.assertEquals("1 year and 1 day", oneDayOneYear);
    }

    /**
     * Test {@link CustomizableDurationFormatter#format(Duration)}
     * - When there are more than two parts, then the last part is separated by 'and',
     * and the other parts are separated by a ','
     * - The parts are always in chronological order
     */
    @Test
    public void formatTest_multiPart(){
        //Arrange & Act
        String oneOfEach = defaultFormatter.format(Duration.of(31626061, ChronoUnit.SECONDS));

        //Assert
        Assertions.assertEquals("1 year, 1 day, 1 hour, 1 minute and 1 second", oneOfEach);
    }

    /**
     * Test {@link CustomizableDurationFormatter#format(Duration)}
     * - When a part's value is more than 1, then it is in plural
     */
    @Test
    public void formatTest_plurality() {
        //Arrange & Act
        String twoOfEach = defaultFormatter.format(Duration.of(63252122, ChronoUnit.SECONDS));

        //Assert
        Assertions.assertEquals("2 years, 2 days, 2 hours, 2 minutes and 2 seconds", twoOfEach);
    }
}