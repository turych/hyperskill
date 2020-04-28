// Posted from EduTools plugin
import java.util.*;

public class Main {

    private static int uppercase;
    private static int lowercase;
    private static int digits;
    private static int passwordLength;
    private static List<SymbolRandom> randomList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        randomList = new ArrayList<>();
        uppercase = scanner.nextInt();
        lowercase = scanner.nextInt();
        digits = scanner.nextInt();
        passwordLength = scanner.nextInt();

        if (uppercase > 0) {
            randomList.add(new SymbolRangeRandom('A', 'Z', uppercase));
        }
        if (lowercase > 0) {
            randomList.add(new SymbolRangeRandom('a', 'z', lowercase));
        }
        if (digits > 0) {
            randomList.add(new SymbolRangeRandom('0', '9', digits));
        }

        checkSum();

        char[] password = new char[passwordLength];

        // add first symbol
        password[0] = findCharacter('.');

        for (int i = 1; i < passwordLength; i++) {
            password[i] = findCharacter(password[i - 1]);
        }

        System.out.println(password);
    }

    /**
     * If uppercase + lowercase + digits < passwordLength
     */
    private static void checkSum() {
        int sumOfParams = uppercase + lowercase + digits;
        if (sumOfParams == 0) {
            randomList.add(new SymbolRangeRandom('A', 'Z', passwordLength));
        } else {
            randomList.get(0).setQty(randomList.get(0).getQty() + passwordLength - sumOfParams);
        }
    }

    /**
     * Randomize character.
     *
     * @param exclude char
     * @return char
     */
    private static char findCharacter(char exclude) {
        Random random = new Random();
        SymbolRandom symbolRandom = randomList.get(random.nextInt(randomList.size()));
        char c = symbolRandom.random(exclude);
        if (symbolRandom.getQty() < 1) {
            randomList.remove(symbolRandom);
        }
        return c;
    }
}

/**
 * Randomizer of character
 */
interface SymbolRandom {
    public char random(char exclude);

    public int getQty();

    public void setQty(int qty);
}

/**
 * Randomizer by character range
 */
class SymbolRangeRandom implements SymbolRandom {

    private int startSymbol;
    private int endSymbol;

    private int qty;

    private Random random;

    public SymbolRangeRandom(int startSymbol, int endSymbol, int qty) {
        this.startSymbol = startSymbol;
        this.endSymbol = endSymbol;
        this.qty = qty;
        random = new Random();
    }

    @Override
    public char random(char exclude) {
        int charFromRange;
        do {
            charFromRange = random.nextInt(endSymbol - startSymbol + 1) + startSymbol;
        } while (charFromRange == exclude);
        qty--;
        return (char) charFromRange;
    }

    @Override
    public int getQty() {
        return qty;
    }

    @Override
    public void setQty(int qty) {
        this.qty = qty;
    }
}