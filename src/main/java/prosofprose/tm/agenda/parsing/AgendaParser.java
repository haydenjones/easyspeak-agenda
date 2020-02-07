package prosofprose.tm.agenda.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.jhayden.agenda.model.AgendaInfo;
import net.jhayden.agenda.model.AgendaItem;
import prosofprose.tm.agenda.util.ParsingTool;

public class AgendaParser {
    // --- Constants and Variables

    static final String BEGIN_AGENDA = "<td colspan=\"5\"><hr /></td>";

    private static final Logger LOGGER = Logger.getLogger("AgendaParser");

    private final String html;

    // --- Constructor and Initialization Methods

    // --- Core and Helper Methods

    public AgendaParser(final String agendaHtml) {
        html = agendaHtml;
    }

    public AgendaInfo call() {
        final List<AgendaItem> items = new ArrayList<>();

        final AtomicInteger place = new AtomicInteger(html.indexOf(BEGIN_AGENDA));

        BeginEnd beginEnd = findTableRow(html, place.intValue());
        while (beginEnd != null) {
            LOGGER.log(Level.FINER, "");
            final String htmlDataRow = beginEnd.of(html);
            LOGGER.log(Level.FINER, htmlDataRow);

            final TableRowTypes type = deduceType(htmlDataRow);
            LOGGER.log(Level.FINER, type.toString());
            final AgendaItem item = type.parseItem(htmlDataRow);
            LOGGER.log(Level.FINER, String.valueOf(item));
            type.apply(item, items);

            beginEnd = findTableRow(html, beginEnd.getEnd());
        }

        final String theme = deduceMeetingTheme();
        final String date = deduceMeetingDate();

        final AgendaInfo info = new AgendaInfo(theme, date);
        info.setItems(items);

        return info;
    }

    String deduceMeetingTheme() {
        // Meeting Theme <b>
        return ParsingTool.findText(html, "Meeting Theme <b>", "</b>");
    }

    // <title>easy-Speak :: View agenda - Tuesday July 12, 2016</title>
    String deduceMeetingDate() {
        final String dayOfWeek = ParsingTool.findText(html, "View agenda - ", "</title>");
        final int space = dayOfWeek.indexOf(" ");
        if (space < 0) {
            return dayOfWeek;
        }
        return dayOfWeek.substring(space + 1);
    }

    static TableRowTypes deduceType(final String htmlDataRow) {
        int countTd = 0;
        int place = htmlDataRow.indexOf(TD_BEGIN);
        while (place > 0) {
            countTd++;
            place = htmlDataRow.indexOf(TD_BEGIN, place + 1);
        }

        final int htmlBreak = htmlDataRow.indexOf("<br");
        if ((countTd == 2) && (htmlBreak < 0)) {
            return TableRowTypes.GARBAGE;
            // return TableRowTypes.EDUCATIONAL_PROJECT_NAME;
        }
        else if (countTd == 2) {
            return TableRowTypes.SPEAKER_INFO;
        }
        else if (countTd < 2) {
            return TableRowTypes.GARBAGE;
        }

        return TableRowTypes.ROLE;
    }

    static final String TD_BEGIN = "<td";

    static final String TR_BEGIN = "<tr>";
    static final String TR_END = "</tr>";

    static BeginEnd findTableRow(final String html, final int initialStart) {
        final int firstBeginTr = html.indexOf(TR_BEGIN, initialStart);
        if (firstBeginTr < 0) {
            return null;
        }
        LOGGER.info("\n\n" + html.substring(firstBeginTr));

        int after = firstBeginTr + 1;

        while (true) {
            final int nextBegin = html.indexOf(TR_BEGIN, after);
            final int nextEnd = html.indexOf(TR_END, after);
            if (nextEnd < 0) {
                return null;
            }
            else if (nextEnd < nextBegin) {
                return new BeginEnd(firstBeginTr, nextEnd);
            }

            after = nextEnd + 1;
        }
    }
    // --- Getter and Setter Methods
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods
}
