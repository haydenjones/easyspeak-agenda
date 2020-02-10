package net.jhayden.agenda.model;

import java.util.ArrayList;
import java.util.List;

import prosofprose.tm.agenda.util.Strings;

public class AgendaInfo {
    // --- Constants and Variables

    private List<AgendaItem> items = new ArrayList<>();
    private final List<ProjectSummaryDetails> projectDetails = new ArrayList<>();

    private final String meetingTheme;
    private final String meetingDate;

    private final Officer vpOfMembership = new Officer();
    
    // --- Constructor and Initialization Methods

    public AgendaInfo(final String theme, final String date) {
        super();
        meetingTheme = Strings.trim(theme);
        meetingDate = Strings.trim(date);
    }

    // --- Core and Helper Methods
    // --- Getter and Setter Methods
    
    public Officer getVpOfMembership() {
    	return vpOfMembership;
    }
    
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods

    public AgendaInfo() {
        this("TBD", "?");
    }

    public List<ProjectSummaryDetails> getProjectDetails() {
        return projectDetails;
    }

    public String getMeetingTheme() {
        return meetingTheme;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setItems(final List<AgendaItem> newList) {
        items = newList;
    }

    public List<AgendaItem> getItems() {
        return items;
    }
}
