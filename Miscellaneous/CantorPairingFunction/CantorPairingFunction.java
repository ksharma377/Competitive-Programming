import java.util.Scanner;

public class CantorPairingFunction {
    public static void main(String[] args) {
        new CantorPairingFunctionExecutor().run();
    }
}

class CantorPairingFunctionExecutor {
    private class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

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
                Pair pair = extract(z);
                System.out.println("x: " + pair.x + ", y: " + pair.y);
            }
        }
    }

    private long map(int x, int y) {
        return (1L * (x + y) * (x + y + 1)) / 2 + y;
    }

    private Pair extract(long z) {
        int w = (int) Math.floor((Math.sqrt(1 + 8 * z) - 1) / 2.0);
        long t = (1L * w * (w + 1)) / 2;
        int y = (int) (z - (long) t);
        int x = w - y;
        return new Pair(x, y);
    }
}
