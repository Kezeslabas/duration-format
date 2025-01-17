package formatter;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

/**
 * Use the {@link DurationFormatter#format(Duration)} to format any duration to human readable format.
 * Note, that the class considers 365 days as one year, not 365.2425
 * <br>
 * Additional time parts can be applied with {@link DurationFormatter#addFormatterComponent(ChronoUnit, Function)}
 */
public class DurationFormatter {

    private static final String ZERO_LENGTH_DEFAULT = "now";
    private static final String SEPARATOR_DEFAULT = ", ";
    private static final String LAST_SEPARATOR_DEFAULT = " and ";

    private static final int SECONDS_IN_YEAR = 31536000;
    private static final int DAYS_IN_YEAR = 365;

    private final Map<ChronoUnit, Function<Duration, String>> formatterComponents = new TreeMap<>(Collections.reverseOrder());

    public DurationFormatter() {
        addFormatterComponent(ChronoUnit.YEARS, DurationFormatter::YEAR_FORMATTER);
        addFormatterComponent(ChronoUnit.DAYS, DurationFormatter::DAY_FORMATTER);
        addFormatterComponent(ChronoUnit.HOURS, DurationFormatter::HOUR_FORMATTER);
        addFormatterComponent(ChronoUnit.MINUTES, DurationFormatter::MINUTE_FORMATTER);
        addFormatterComponent(ChronoUnit.SECONDS, DurationFormatter::SECOND_FORMATTER);
    }

    public void addFormatterComponent(ChronoUnit chronoUnit, Function<Duration, String> formatterFunc){
        formatterComponents.put(chronoUnit, formatterFunc);
    }

    /**
     * The provided duration is split to parts (years, days, hours, minutes, seconds in default),
     * and displays their respective value with the correct plurality.
     * Each part is separated by a comma, except the last one, which is separated by 'and'.
     * Each part is displayed in chronological order, starting from the largest one.
     *
     * @param duration duration to format
     * @return formatted duration
     */
    public String format(Duration duration) {
        if (duration.isZero()){
            return ZERO_LENGTH_DEFAULT;
        }

        final Iterator<String> iterator = formatterComponents.values().stream()
                .map(formatterFunc -> formatterFunc.apply(duration))
                .filter(result -> !result.isEmpty())
                .iterator();

        final StringBuilder builder = new StringBuilder();

        boolean firstSeparation = true;
        while (iterator.hasNext()){
            String next = iterator.next();
            builder.append(firstSeparation ? "" : iterator.hasNext() ? SEPARATOR_DEFAULT : LAST_SEPARATOR_DEFAULT);
            builder.append(next);

            firstSeparation = false;
        }

        return builder.toString();
    }

    public static String YEAR_FORMATTER(Duration duration){
        return GENERIC_CHRONO_FORMATTER((int) duration.getSeconds() / SECONDS_IN_YEAR, "year","years");
    }

    public static String DAY_FORMATTER(Duration duration){
        return GENERIC_CHRONO_FORMATTER((int)(duration.toDays() % DAYS_IN_YEAR), "day","days");
    }

    public static String SECOND_FORMATTER(Duration duration){
        return GENERIC_CHRONO_FORMATTER(duration.toSecondsPart(), "second","seconds");
    }

    public static String MINUTE_FORMATTER(Duration duration){
        return GENERIC_CHRONO_FORMATTER(duration.toMinutesPart(), "minute","minutes");
    }

    public static String HOUR_FORMATTER(Duration duration){
        return GENERIC_CHRONO_FORMATTER(duration.toHoursPart(), "hour","hours");
    }

    /**
     * Generic formater, that returns an empty string on no time part, the singular name on 1 time part, and plural name otherwise.
     * @param timePart the time representation of this part
     * @param singularName the singular name of this part
     * @param pluralName the plural name of this part
     * @return formatted string with 'value name' format, or empty string
     */
    public static String GENERIC_CHRONO_FORMATTER(int timePart, String singularName, String pluralName){
        return timePart < 1 ? "" : timePart + " " + (timePart == 1 ? singularName : pluralName);
    }

}
