package ro.sd.a2.validators;

public class ProductValidator {

    private int val;
    public static boolean isNameValid(String name){
        return name != null && !name.equals("") && name.matches("([A-Z][a-z]*)");
    }

    public static boolean isColorValid(String color){
        return color != null && !color.equals("") && color.matches("([A-Z][a-z]*)");
    }

    public static boolean isPriceValid(float price){
        return price >= 0;
    }

    public static boolean isQuantityValid(int quantity){
        return quantity > 0;
    }

}
