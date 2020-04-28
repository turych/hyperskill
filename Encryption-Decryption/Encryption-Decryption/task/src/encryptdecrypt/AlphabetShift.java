package encryptdecrypt;

public class AlphabetShift implements StringShift {

    @Override
    public String execute(String str, int shift) {

        char[] shifted = new char[str.length()];

        int dictLength = 26;
        shift %= dictLength;

        for (int i = 0; i < str.length(); i++) {
            int currentChar = str.charAt(i);
            int shiftedChar = currentChar + shift;

            int firstChar;
            int lastChar;
            // a-z
            if (currentChar >= 'a' && currentChar <= 'z') {
                firstChar = 'a';
                lastChar = 'z';
            // A-Z
            } else if (currentChar >= 'A' && currentChar <= 'Z') {
                firstChar = 'A';
                lastChar = 'Z';
            // other symbols
            } else {
                shifted[i] = (char) currentChar;
                continue;
            }

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
