public class AsciiCharSequence implements java.lang.CharSequence {

    byte[] sequence;

    public AsciiCharSequence(byte[] sequence) {
        this.sequence = sequence;
    }

    @Override
    public int length() {
        return sequence.length;
    }

    @Override
    public char charAt(int i) {
        return (char) sequence[i];
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return new AsciiCharSequence(
                java.util.Arrays.copyOfRange(sequence, i, i1)
        );
    }

    @Override
    public String toString() {
        return new String(sequence);
    }
}