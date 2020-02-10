package net.jhayden.agenda.model;

public class Officer {
    private final String name;
    private final String title;
    private final String email;

    public Officer() {
        super();
        name = "Claudio Gismondi";
        email = "20bayprosofprose@gmail.com";
        title = "VP Membership";
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }
}
