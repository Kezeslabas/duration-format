package test;

import org.junit.Assert;
import org.junit.Test;

import formatter.DurationFormatter;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Unit Test {@link DurationFormatter}
 */
public class DurationFormatterTest {

    /**
     * Test {@link DurationFormatter#GENERIC_CHRONO_FORMATTER(int, String, String)}
     * - When the time part is 0 or less, then an empty string is returned
     * - When the time part is 1, then the value and the singular name is returned
     * - When the time part is greater than 1, then the value and the plural name is returned
     */
    @Test
    public void genericChronoFormatterTest() {
        //Arrange & Act
        String zeroPart = DurationFormatter.GENERIC_CHRONO_FORMATTER(0, "singular", "plural");
        String onePart = DurationFormatter.GENERIC_CHRONO_FORMATTER(1, "singular", "plural");
        String twoPart = DurationFormatter.GENERIC_CHRONO_FORMATTER(2, "singular", "plural");
        String multiplePart = DurationFormatter.GENERIC_CHRONO_FORMATTER(66, "singular", "plural");

        //-Assert
        Assert.assertEquals("", zeroPart);
        Assert.assertEquals("1 singular", onePart);
        Assert.assertEquals("2 plural", twoPart);
        Assert.assertEquals("66 plural", multiplePart);
    }

    /**
     * Test {@link DurationFormatter#YEAR_FORMATTER(Duration)}
     * - When the seconds are less than a year, then an empty string is returned
     * - When the seconds are exactly one year, but less than two, then '1 year' is returned
     * - When the seconds are two or more years, then the number of years and 'years' is returned
     */
    @Test
    public void yearFormatterTest() {
        //Arrange & Act
        String zeroPart = DurationFormatter.YEAR_FORMATTER(Duration.of(0, ChronoUnit.SECONDS));
        String lessThanAYearPart = DurationFormatter.YEAR_FORMATTER(Duration.of(200, ChronoUnit.DAYS));
        String exactlyAYearPart = DurationFormatter.YEAR_FORMATTER(Duration.of(365, ChronoUnit.DAYS));
        String moreThanAYearPart = DurationFormatter.YEAR_FORMATTER(Duration.of(400, ChronoUnit.DAYS));
        String exactlyTwoYearPart = DurationFormatter.YEAR_FORMATTER(Duration.of(730, ChronoUnit.DAYS));
        String moreThanTwoYearPart = DurationFormatter.YEAR_FORMATTER(Duration.of(1000, ChronoUnit.DAYS));

        //Assert
        Assert.assertEquals("", zeroPart);
        Assert.assertEquals("", lessThanAYearPart);
        Assert.assertEquals("1 year", exactlyAYearPart);
        Assert.assertEquals("1 year", moreThanAYearPart);
        Assert.assertEquals("2 years", exactlyTwoYearPart);
        Assert.assertEquals("2 years", moreThanTwoYearPart);
    }

    /**
     * Test {@link DurationFormatter#DAY_FORMATTER(Duration)}
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
        String zeroPart = DurationFormatter.DAY_FORMATTER(Duration.of(0, ChronoUnit.DAYS));
        String fullYearPart = DurationFormatter.DAY_FORMATTER(Duration.of(365, ChronoUnit.DAYS));
        String lessThanADayPart = DurationFormatter.DAY_FORMATTER(Duration.of(23, ChronoUnit.HOURS));
        String exactlyOneDayPart = DurationFormatter.DAY_FORMATTER(Duration.of(24, ChronoUnit.HOURS));
        String moreThanOneDayPart = DurationFormatter.DAY_FORMATTER(Duration.of(25, ChronoUnit.HOURS));
        String exactlyTwoDaysPart = DurationFormatter.DAY_FORMATTER(Duration.of(48, ChronoUnit.HOURS));
        String moreThanTwoDaysPart = DurationFormatter.DAY_FORMATTER(Duration.of(50, ChronoUnit.HOURS));
        String moreThanAYearPart = DurationFormatter.DAY_FORMATTER(Duration.of(366, ChronoUnit.DAYS));

        //Assert
        Assert.assertEquals("", zeroPart);
        Assert.assertEquals("", fullYearPart);
        Assert.assertEquals("", lessThanADayPart);
        Assert.assertEquals("1 day", exactlyOneDayPart);
        Assert.assertEquals("1 day", moreThanOneDayPart);
        Assert.assertEquals("2 days", exactlyTwoDaysPart);
        Assert.assertEquals("2 days", moreThanTwoDaysPart);
        Assert.assertEquals("1 day", moreThanAYearPart);
    }

    /**
     * Test {@link DurationFormatter#HOUR_FORMATTER(Duration)}
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
        String zeroPart = DurationFormatter.HOUR_FORMATTER(Duration.of(0, ChronoUnit.HOURS));
        String oneDayPart = DurationFormatter.HOUR_FORMATTER(Duration.of(24, ChronoUnit.HOURS));
        String lessThanOneHourPart = DurationFormatter.HOUR_FORMATTER(Duration.of(59, ChronoUnit.MINUTES));
        String oneHourPart = DurationFormatter.HOUR_FORMATTER(Duration.of(60, ChronoUnit.MINUTES));
        String moreThanOneHourPart = DurationFormatter.HOUR_FORMATTER(Duration.of(61, ChronoUnit.MINUTES));
        String twoHourPart = DurationFormatter.HOUR_FORMATTER(Duration.of(120, ChronoUnit.MINUTES));
        String moreThanTwoHourPart = DurationFormatter.HOUR_FORMATTER(Duration.of(121, ChronoUnit.MINUTES));
        String moreThanADayPart = DurationFormatter.HOUR_FORMATTER(Duration.of(25, ChronoUnit.HOURS));

        //Assert
        Assert.assertEquals("", zeroPart);
        Assert.assertEquals("", oneDayPart);
        Assert.assertEquals("", lessThanOneHourPart);
        Assert.assertEquals("1 hour", oneHourPart);
        Assert.assertEquals("1 hour", moreThanOneHourPart);
        Assert.assertEquals("2 hours", twoHourPart);
        Assert.assertEquals("2 hours", moreThanTwoHourPart);
        Assert.assertEquals("1 hour", moreThanADayPart);
    }

    /**
     * Test {@link DurationFormatter#MINUTE_FORMATTER(Duration)} (Duration)}
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
        String zeroPart = DurationFormatter.MINUTE_FORMATTER(Duration.of(0, ChronoUnit.HOURS));
        String oneHourPart = DurationFormatter.MINUTE_FORMATTER(Duration.of(60, ChronoUnit.MINUTES));
        String lessThanOneMinutePart = DurationFormatter.MINUTE_FORMATTER(Duration.of(59, ChronoUnit.SECONDS));
        String oneMinutePart = DurationFormatter.MINUTE_FORMATTER(Duration.of(60, ChronoUnit.SECONDS));
        String moreThanOneMinutePart = DurationFormatter.MINUTE_FORMATTER(Duration.of(61, ChronoUnit.SECONDS));
        String twoMinutePart = DurationFormatter.MINUTE_FORMATTER(Duration.of(120, ChronoUnit.SECONDS));
        String moreThanTwoMinutePart = DurationFormatter.MINUTE_FORMATTER(Duration.of(121, ChronoUnit.SECONDS));
        String moreThanAnHourPart = DurationFormatter.MINUTE_FORMATTER(Duration.of(61, ChronoUnit.MINUTES));

        //Assert
        Assert.assertEquals("", zeroPart);
        Assert.assertEquals("", oneHourPart);
        Assert.assertEquals("", lessThanOneMinutePart);
        Assert.assertEquals("1 minute", oneMinutePart);
        Assert.assertEquals("1 minute", moreThanOneMinutePart);
        Assert.assertEquals("2 minutes", twoMinutePart);
        Assert.assertEquals("2 minutes", moreThanTwoMinutePart);
        Assert.assertEquals("1 minute", moreThanAnHourPart);
    }

    /**
     * Test {@link DurationFormatter#SECOND_FORMATTER(Duration)}
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
        String zeroPart = DurationFormatter.SECOND_FORMATTER(Duration.of(0, ChronoUnit.SECONDS));
        String oneMinutePart = DurationFormatter.SECOND_FORMATTER(Duration.of(60, ChronoUnit.SECONDS));
        String lessThanOneSecondPart = DurationFormatter.SECOND_FORMATTER(Duration.of(999, ChronoUnit.MILLIS));
        String oneSecondPart = DurationFormatter.SECOND_FORMATTER(Duration.of(1000, ChronoUnit.MILLIS));
        String moreThanOneSecondPart = DurationFormatter.SECOND_FORMATTER(Duration.of(1001, ChronoUnit.MILLIS));
        String twoSecondPart = DurationFormatter.SECOND_FORMATTER(Duration.of(2000, ChronoUnit.MILLIS));
        String moreThanTwoSecondPart = DurationFormatter.SECOND_FORMATTER(Duration.of(2001, ChronoUnit.MILLIS));
        String moreThanAMinutePart = DurationFormatter.SECOND_FORMATTER(Duration.of(61, ChronoUnit.SECONDS));

        //Assert
        Assert.assertEquals("", zeroPart);
        Assert.assertEquals("", oneMinutePart);
        Assert.assertEquals("", lessThanOneSecondPart);
        Assert.assertEquals("1 second", oneSecondPart);
        Assert.assertEquals("1 second", moreThanOneSecondPart);
        Assert.assertEquals("2 seconds", twoSecondPart);
        Assert.assertEquals("2 seconds", moreThanTwoSecondPart);
        Assert.assertEquals("1 second", moreThanAMinutePart);
    }

    /**
     * Test {@link DurationFormatter#format(Duration)}
     * - When the duration is 0, then 'now' is returned.
     * - When there is only one part (year, day, hour, minute, second), then that part is returned
     */
    @Test
    public void formatTest_singlePartAndNow(){
        //Arrange
        DurationFormatter durationFormatter = new DurationFormatter();

        //Act
        String zeroSec = durationFormatter.format(Duration.of(0, ChronoUnit.SECONDS));

        String oneSec = durationFormatter.format(Duration.of(1, ChronoUnit.SECONDS));
        String oneMin = durationFormatter.format(Duration.of(1, ChronoUnit.MINUTES));
        String oneHour = durationFormatter.format(Duration.of(1, ChronoUnit.HOURS));
        String oneDay = durationFormatter.format(Duration.of(1, ChronoUnit.DAYS));
        String oneYear = durationFormatter.format(Duration.of(365, ChronoUnit.DAYS));

        //Assert
        Assert.assertEquals("now", zeroSec);

        Assert.assertEquals("1 second", oneSec);
        Assert.assertEquals("1 minute", oneMin);
        Assert.assertEquals("1 hour", oneHour);
        Assert.assertEquals("1 day", oneDay);
        Assert.assertEquals("1 year", oneYear);
    }

    /**
     * Test {@link DurationFormatter#format(Duration)}
     * - When there are at least two parts, then both is returned, separated by 'and'
     * - The parts are always in chronological order
     */
    @Test
    public void formatTest_doublePart(){
        //Arrange
        DurationFormatter durationFormatter = new DurationFormatter();

        //Act
        String oneSecOneMin = durationFormatter.format(Duration.of(61, ChronoUnit.SECONDS));
        String oneMinOnHour = durationFormatter.format(Duration.of(61, ChronoUnit.MINUTES));
        String oneHourOneDay = durationFormatter.format(Duration.of(25, ChronoUnit.HOURS));
        String oneDayOneYear = durationFormatter.format(Duration.of(366, ChronoUnit.DAYS));

        //Assert
        Assert.assertEquals("1 minute and 1 second", oneSecOneMin);
        Assert.assertEquals("1 hour and 1 minute", oneMinOnHour);
        Assert.assertEquals("1 day and 1 hour", oneHourOneDay);
        Assert.assertEquals("1 year and 1 day", oneDayOneYear);
    }

    /**
     * Test {@link DurationFormatter#format(Duration)}
     * - When there are more than two parts, then the last part is separated by 'and',
     * and the other parts are separated by a ','
     * - The parts are always in chronological order
     */
    @Test
    public void formatTest_multiPart(){
        //Arrange
        DurationFormatter durationFormatter = new DurationFormatter();

        //Act
        String oneOfEach = durationFormatter.format(Duration.of(31626061, ChronoUnit.SECONDS));

        //Assert
        Assert.assertEquals("1 year, 1 day, 1 hour, 1 minute and 1 second", oneOfEach);
    }

    /**
     * Test {@link DurationFormatter#format(Duration)}
     * - When a part's value is more than 1, then it is in plural
     */
    @Test
    public void formatTest_plurality() {
        //Arrange
        DurationFormatter durationFormatter = new DurationFormatter();

        //Act
        String twoOfEach = durationFormatter.format(Duration.of(63252122, ChronoUnit.SECONDS));

        //Assert
        Assert.assertEquals("2 years, 2 days, 2 hours, 2 minutes and 2 seconds", twoOfEach);
    }
}