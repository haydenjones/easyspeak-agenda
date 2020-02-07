package net.jhayden.agenda;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import prosofprose.tm.agenda.util.Strings;

public class ProduceLatestConfig {
    // --- Constants and Variables

    static final File CLUB_FILE = new File("src/main/resources/conf/club.properties");

    public static void store(ProduceLatestConfig plc) throws IOException {
        try (FileOutputStream inStream = new FileOutputStream(CLUB_FILE)) {
            plc.p.store(inStream, "");
        }
    }
    
    public static ProduceLatestConfig getInstance() throws IOException {
        final Properties p = new Properties();
        try (FileInputStream inStream = new FileInputStream(CLUB_FILE)) {
            p.load(inStream);
        }
        return new ProduceLatestConfig(p);
    }

    private final Properties p;
    private final String easySpeakUrl;
    
    // --- Constructor and Initialization Methods

    public String getEasySpeakUrl() {
        return easySpeakUrl;
    }

    private ProduceLatestConfig(final Properties props) throws MalformedURLException {
        super();
        p = props;
        easySpeakUrl = p.getProperty("easy-speak.url");
    }

    public String getGui()  {
    	return p.getProperty("meeting_number", "");
    }
    
    public void setGui(String s) {
    	p.setProperty("meeting_number", s);
    }
    
    public String getMemberName(final String presenter) {
        final String memberKey = "member." + presenter;
        if (!p.containsKey(memberKey)) {
            System.out.println("'" + memberKey + "'");
        }
        return p.getProperty(memberKey, presenter);
    }

    // --- Core and Helper Methods
    // --- Getter and Setter Methods
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods
}
