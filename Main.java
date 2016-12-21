import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String arg[] = {"save", "uuid1"};

        try {
            MainArray.main(arg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
