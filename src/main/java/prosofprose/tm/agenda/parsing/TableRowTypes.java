package prosofprose.tm.agenda.parsing;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import net.jhayden.agenda.model.AgendaItem;
import prosofprose.tm.agenda.util.ParsingTool;

public enum TableRowTypes {
    GARBAGE {
        @Override
        public AgendaItem parseItem(final String htmlDataRow) {
            return null;
        }

        @Override
        public void apply(final AgendaItem item, final List<AgendaItem> items) {
            // Do Nothing
        }
    },
    //
    ROLE {
        @Override
        public AgendaItem parseItem(final String html) {
            final AtomicInteger place = new AtomicInteger(0);
            final String time = ParsingTool.findText(html, "<span class=\"gensmall\">", "</span>", place);
            final String role = ParsingTool.findText(html, "<span class=\"gen\">", "</span>", place);

            String presenter = ParsingTool.findText(html, "<span class=\"gen\">", "</span>", place);
            presenter = presenter.replaceAll("<i>", "");
            presenter = presenter.replaceAll("</i>", "");
            presenter = presenter.replace('?', ' ');

            final String event = ParsingTool.findText(html, "<span class=\"gen\">", "</span>", place);

            ParsingTool.findText(html, "<table", ">", place);

            final String green = ParsingTool.findText(html, "<span class=\"gensmall\">", "&nbsp;", place);
            final String yellow = ParsingTool.findText(html, "<span class=\"gensmall\">", "&nbsp;", place);
            final String red = ParsingTool.findText(html, "<span class=\"gensmall\">", "</td>", place);

            final AgendaItem item = new AgendaItem();
            item.setTime(time);
            item.setRole(role);
            item.setPresenter(presenter);
            item.setEvent(event);
            item.setDurationGreen(green);
            item.setDurationYellow(yellow);
            item.setDurationRed(red);

            return item;
        }

        @Override
        public void apply(final AgendaItem item, final List<AgendaItem> items) {
            items.add(item);
        }

    },
    //
    EDUCATIONAL_PROJECT_NAME {
        @Override
        public AgendaItem parseItem(final String htmlDataRow) {
            final AtomicInteger place = new AtomicInteger(0);

            final String name = ParsingTool.findText(htmlDataRow, "<i>", "</i>", place);

            final AgendaItem ai = new AgendaItem();
            ai.setProjectName(name);
            return ai;
        }

        @Override
        public void apply(final AgendaItem item, final List<AgendaItem> items) {
            final AgendaItem previous = items.get(items.size() - 1);
            previous.setSpeechDetails(item);
        }
    },
    //
    SPEAKER_INFO {
        @Override
        public AgendaItem parseItem(final String htmlDataRow) {
            final AtomicInteger place = new AtomicInteger(0);

            final String name = ParsingTool.findText(htmlDataRow, "<i>", "</i>", place);
            final String details = ParsingTool.findText(htmlDataRow, "<br>", "</span>", place);

            final AgendaItem ai = new AgendaItem();
            ai.setProjectName(name);
            ai.setProjectDetails(details);
            return ai;
        }

        @Override
        public void apply(final AgendaItem item, final List<AgendaItem> items) {
            final AgendaItem previous = items.get(items.size() - 1);
            previous.setSpeechDetails(item);
        }
    };

    public abstract AgendaItem parseItem(String htmlDataRow);

    public abstract void apply(AgendaItem item, List<AgendaItem> items);

    // --- Constants and Variables
    // --- Constructor and Initialization Methods
    // --- Core and Helper Methods
    // --- Getter and Setter Methods
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods
}
