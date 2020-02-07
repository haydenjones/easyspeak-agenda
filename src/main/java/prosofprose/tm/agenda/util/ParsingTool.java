package prosofprose.tm.agenda.util;

import java.util.concurrent.atomic.AtomicInteger;

public class ParsingTool {

    // --- Constants and Variables
    // --- Constructor and Initialization Methods

    private ParsingTool() {
        super();
    }

    // --- Core and Helper Methods
    // --- Getter and Setter Methods

    public static String findText(final String bigText, final String begin, final String end) {
        return findText(bigText, begin, end, null);
    }

    public static String findText(final String bigText, final String begin, final String end, final AtomicInteger cursorPlace) {
        final int startingPlace = (cursorPlace == null) ? 0 : cursorPlace.intValue();

        final int beginStart = bigText.indexOf(begin, startingPlace);
        final int endStart = bigText.indexOf(end, beginStart);

        if (beginStart < 0) {
            throw new IllegalArgumentException("Unable to find begin" + begin + " in " + bigText);
        }
        if (endStart <= beginStart) {
            throw new IllegalArgumentException("Unable to find end " + end + " in " + bigText);
        }

        final int newCursorPlace = endStart + end.length();
        if (cursorPlace != null) {
            cursorPlace.set(newCursorPlace);
        }

        return bigText.substring(beginStart + begin.length(), endStart);
    }

    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods
}
