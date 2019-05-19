import java.util.Scanner;

public class ZAlgorithm {
    public static void main(String[] args) {
        new ZAlgorithmExecutor().run();
    }
}

class ZAlgorithmExecutor {
    private void ZAlgorithm(String s) {
        int n = s.length();
        int[] z = new int[n];
        z[0] = 0;
        int start = 0, end = 0;
        for (int i = 1; i < n; i++) {
            if (i > end) {
                start = end = i;
                while (end < n && s.charAt(end) == s.charAt(end - start)) {
                    end++;
                }
                z[i] = end - start;
                end--;
            } else {
                int k = i - start;
                if (z[k] < end - i + 1) {
                    z[i] = z[k];
                } else {
                    start = i;
                    while (end < n && s.charAt(end) == s.charAt(end - start)) {
                        end++;
                    }
                    z[i] = end - start;
                    end--;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(z[i] + " ");
        }
    }

    public void run() {
        final Scanner scanner = new Scanner(System.in);
        String text = scanner.next();
        String pattern = scanner.next();
        String s = pattern + "$" + text;
        System.out.println(s);
        ZAlgorithm(s);
    }
}
