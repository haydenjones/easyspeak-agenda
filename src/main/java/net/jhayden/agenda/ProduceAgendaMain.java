package net.jhayden.agenda;

import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import net.jhayden.agenda.model.AgendaInfo;
import prosofprose.tm.agenda.util.Strings;

public class ProduceAgendaMain {
    static final String MEETING_ID = "MEETING_ID";
    static final String GUI = "GUI";

    public static void main(final String[] args) throws IOException {
        System.out.println("Begin");

        final ProduceLatestConfig cfg = ProduceLatestConfig.getInstance();

        System.out.println("Arguments: " + Arrays.toString(args));
        String override = null; //"302937"; // "290875"; //   

        if ((args != null) && (args.length > 0)) {
            String arg0 = Strings.trim(args[0]);
            if (GUI.equalsIgnoreCase(arg0)) {
                System.out.println("GUI");
            	String newGui = JOptionPane.showInputDialog(null, "What is the meeting ID?", cfg.getGui());
            	if (newGui != null) {
                    try {
                        int mtg = Integer.parseInt(newGui);
                        override = "" + mtg;
                        cfg.setGui("" + mtg);
                        ProduceLatestConfig.store(cfg);
                    }
                    catch (Exception e) {
                        System.out.println("Could not parse " + MEETING_ID + " '" + arg0 + "' because " + e);
                    }
            	}
            }
            else if (MEETING_ID.equalsIgnoreCase(arg0) || arg0.isEmpty()) {
                System.out.println("No override");
            }
            else {
                try {
                    int mtg = Integer.parseInt(arg0);
                    override = "" + mtg;
                }
                catch (Exception e) {
                    System.out.println("Could not parse " + MEETING_ID + " '" + arg0 + "' because " + e);
                }
            }
        }

        DeriveAgenda.OVERRIDE_URL.set(override);

        final DeriveAgenda da = new DeriveAgenda(cfg);
        final AgendaInfo info = da.call();

        final VelocityTask task = new VelocityTask(cfg, info);
        task.call();

        System.out.println("End");
    }
}
