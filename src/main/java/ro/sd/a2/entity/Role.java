package ro.sd.a2.entity;

public enum Role {

    CLIENT("Client"),
    ADMIN("Administrator");

    private final String displayValue;

    private Role(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}
