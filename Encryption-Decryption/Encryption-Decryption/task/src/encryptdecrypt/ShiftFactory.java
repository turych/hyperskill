package encryptdecrypt;

public class ShiftFactory {

    public static StringShift instance(String type) {
        switch (type) {
            case "unicode":
                return new UnicodeShift();
            default:
                return new AlphabetShift();
        }
    }
}
