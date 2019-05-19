import java.util.Scanner;

public class CantorPairingFunction {
    public static void main(String[] args) {
        new CantorPairingFunctionExecutor().run();
    }
}

class CantorPairingFunctionExecutor {
    public void run() {
        final Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            String type = scanner.next();
            if (type.equals("map")) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                System.out.println(map(x, y));
            } else {
                long z = scanner.nextLong();
                Integer x = new Integer(0);
                Integer y = new Integer(0);
                extract(z, x, y);
                System.out.println("x: " + x + ", y: " + y);
            }
        }
    }

    private long map(int x, int y) {
        return 1L * (x + y) * (x + y + 1) / 2 + y;
    }

    private void extract(long z, Integer x, Integer y) {
        return;
    }
}
