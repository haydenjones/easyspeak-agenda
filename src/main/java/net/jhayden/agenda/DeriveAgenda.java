package net.jhayden.agenda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

import net.jhayden.agenda.model.AgendaInfo;
import net.jhayden.agenda.model.AgendaItem;
import prosofprose.tm.agenda.parsing.AgendaParser;
import prosofprose.tm.agenda.util.HtmlTool;
import prosofprose.tm.agenda.util.ParsingTool;

public class DeriveAgenda {
    static final AtomicReference<String> OVERRIDE_URL = new AtomicReference<>(); // "http://easy-speak.org/viewagenda.php?t=213740&pr=2"
    static final AtomicReference<String> OVERRIDE_DOWNLOAD = new AtomicReference<>();

    String computeAgendaUrl() throws IOException {
        String meetingId = OVERRIDE_URL.get();
        if (meetingId == null) {
            final String easySpeakHome = HtmlTool.getHtml(config.getEasySpeakUrl());
            meetingId = ParsingTool.findText(easySpeakHome, "view_meeting.php?t=", "&amp;");
        }

        return "https://easy-speak.org/viewagenda.php?t=" + meetingId + "&pr=2";
    }

    // --- Constants and Variables

    private static final Logger LOGGER = Logger.getLogger("DeriveAgenda");

    private final ProduceLatestConfig config;

    // --- Constructor and Initialization Methods

    public DeriveAgenda(final ProduceLatestConfig cfg) {
        config = cfg;
    }

    // --- Core and Helper Methods

    public AgendaInfo call() throws IOException {
        final String agendaUrl = computeAgendaUrl();

        final String agendaHtml;

        String overrideDownload = DeriveAgenda.OVERRIDE_DOWNLOAD.get();
        if (overrideDownload == null) {
            agendaHtml = HtmlTool.getHtml(agendaUrl);
        }
        else {
            byte[] bytes = Files.readAllBytes(Paths.get(overrideDownload));
            agendaHtml = new String(bytes);
        }

        LOGGER.info("\n" + agendaHtml);

        final AgendaParser parser = new AgendaParser(agendaHtml);

        final AgendaInfo info = parser.call();

        updatePresenters(info);
        updateProjects(info);

        return info;
    }

    // --- Getter and Setter Methods
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods

    void updateProjects(final AgendaInfo info) {

    }

    void updatePresenters(final AgendaInfo info) {
        for (final AgendaItem ai : info.getItems()) {
            final String newPresenter = config.getMemberName(ai.getPresenter());
            ai.setPresenter(newPresenter);
        }
    }

}
