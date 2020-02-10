package net.jhayden.agenda;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.jhayden.agenda.model.AgendaInfo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityTask {

    // --- Constants and Variables

    private final AgendaInfo info;

    // --- Constructor and Initialization Methods

    public VelocityTask(final ProduceLatestConfig cfg, final AgendaInfo agendaInfo) {
        super();
        info = agendaInfo;
    }

    // --- Core and Helper Methods

    public void call() throws IOException {
        Velocity.init("src/main/resources/conf/velocity.properties");

        final VelocityContext context = new VelocityContext();
        context.put("info", info);

        final Template template = Velocity.getTemplate("src/main/resources/conf/templates/prosofprose.vm");

        try (FileWriter writer = new FileWriter(new File("agenda.html"))) {
            template.merge(context, writer);
            writer.flush();
        }
    }

    // --- Getter and Setter Methods
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods
}
