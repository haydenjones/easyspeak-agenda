package prosofprose.tm.agenda.util;

public class Strings {

    public static String trim(Object o) {
        if (o == null) {
            return "";
        }

        String s = o.toString();
        s = s.replace("&nbsp;", " ");
        return s.trim();
    }

    // --- Constants and Variables
    // --- Constructor and Initialization Methods

    private Strings() {
        super();
    }

    // --- Core and Helper Methods
    // --- Getter and Setter Methods
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods
}
