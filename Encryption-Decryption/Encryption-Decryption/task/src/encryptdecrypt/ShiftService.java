package encryptdecrypt;

public class ShiftService {

    StringShift stringShift;

    public ShiftService(StringShift stringShift) {
        this.stringShift = stringShift;
    }

    public String shift(String string, int shift) {
        return stringShift.execute(string, shift);
    }
}
