package net.jhayden.agenda.model;

import prosofprose.tm.agenda.util.Strings;

public class AgendaItem {
    // --- Constants and Variables

    private String time = "";
    private String role = "";
    private String presenter = "";
    private String event = "";
    private String durationGreen = "";
    private String durationYellow = "";
    private String durationRed = "";

    private String projectName = "";
    private String projectDetails = "";

    // --- Constructor and Initialization Methods
    // --- Core and Helper Methods
    // --- Getter and Setter Methods

    public boolean isSpeechProject() {
        return !getProjectName().isEmpty();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = Strings.trim(projectName);
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(final String projectDetails) {
        this.projectDetails = Strings.trim(projectDetails);
    }

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = Strings.trim(time);
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = Strings.trim(role);
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(final String presenter) {
        this.presenter = Strings.trim(presenter);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(final String event) {
        this.event = Strings.trim(event);
    }

    public String getDurationGreen() {
        return durationGreen;
    }

    public void setDurationGreen(final String durationGreen) {
        this.durationGreen = Strings.trim(durationGreen);
    }

    public String getDurationYellow() {
        return durationYellow;
    }

    public void setDurationYellow(final String durationYellow) {
        this.durationYellow = Strings.trim(durationYellow);
    }

    public String getDurationRed() {
        return durationRed;
    }

    public void setDurationRed(final String durationRed) {
        this.durationRed = Strings.trim(durationRed);
    }

    public void setSpeechDetails(final AgendaItem item) {
        if (!item.getProjectName().isEmpty()) {
            setProjectName(item.getProjectName());
        }
        if (!item.getProjectDetails().isEmpty()) {
            setProjectDetails(item.getProjectDetails());
        }
    }

    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods

    @Override
    public String toString() {
        return String.format("%s [%s] %s '%s' (%s, %s, %s)", getTime(), getRole(), getPresenter(), getEvent(),
                getDurationGreen(), getDurationYellow(), getDurationRed());
    }
}
