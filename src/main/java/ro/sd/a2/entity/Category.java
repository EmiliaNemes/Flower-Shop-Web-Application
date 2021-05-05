package ro.sd.a2.entity;

public enum Category {
    FLOWER("Flower"),
    BOUQUET("Bouquet");

    private final String displayValue;

    private Category(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }
}
