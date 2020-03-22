import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        while(true) {
            int sym = inputStream.read();
            if(sym == -1) {
                break;
            }
            System.out.print(sym);
        }
    }
}