package encryptdecrypt;

public class UnicodeShift implements StringShift {
    @Override
    public String execute(String str, int shift) {

        char[] shifted = new char[str.length()];

        int firstChar = 32; // " " 32 (space char)
        int lastChar = 126; // "~" 126
        int dictLength = lastChar - firstChar;

        shift %= dictLength;

        for (int i = 0; i < str.length(); i++) {
            int iChar = str.charAt(i);
            int shiftedChar = iChar + shift;

            if (shiftedChar > lastChar) {
                shiftedChar -= dictLength;
            } else if(shiftedChar < firstChar) {
                shiftedChar += dictLength;
            }
            shifted[i] = (char) shiftedChar;
        }
        return new String(shifted);
    }
}
