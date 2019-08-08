import java.util.Scanner;

public class QuickUnion {
    private int[] id;
    private final int n;

    QuickUnion(int n) {
        this.n = n;
        id = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            id[i] = i;
        }
    }

    public void union(int a, int b) {
        int rootA = root(a);
        int rootB = root(b);
        id[rootA] = rootB;
    }

    public boolean connected(int a, int b) {
        return (root(a) == root(b));
    }

    private int root(int x) {
        if (id[x] == x) {
            return x;
        }
        return root(id[x]);
    }

    public static void main(String[] args) {
        new Solver().run();
    }
}

class Solver {
    public void run() {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        QuickUnion quickUnion = new QuickUnion(n);
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            String type = scanner.next();
            if (type.equals("union")) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                quickUnion.union(a, b);
            } else {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                System.out.print("Connected: " + a + " " + b + ": ");
                System.out.println(quickUnion.connected(a, b));
            }
        }
    }
}
