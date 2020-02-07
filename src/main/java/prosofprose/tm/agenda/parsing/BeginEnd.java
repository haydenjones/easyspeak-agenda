package prosofprose.tm.agenda.parsing;

public class BeginEnd {

    // --- Constants and Variables

    private final int begin;
    private final int end;

    // --- Constructor and Initialization Methods

    public BeginEnd(final int b, final int e) {
        begin = b;
        end = e;
    }

    // --- Core and Helper Methods
    // --- Getter and Setter Methods

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    // --- Delegate and Convenience Methods

    public String of(final String html) {
        return html.substring(this.getBegin(), this.getEnd());
    }

    // --- Miscellaneous Methods

    @Override
    public String toString() {
        return String.format("%d, %d", begin, end);
    }
}
