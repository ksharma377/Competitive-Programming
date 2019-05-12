import java.util.Scanner;

class FenwickTree {
  private int[] fen;
  private final int n;

  FenwickTree(int n) {
    this.n = n + 1;
    fen = new int[this.n];
  }

  public void build(int[] arr, int n) {
    for (int i = 1; i <= n; i++) {
      update(i, arr[i]);
    }
  }

  public void update(int i, int delta) {
    for (; i <= n; i += i & -i) {
      fen[i] += delta;
    }
  }

  public int query(int i) {
    int sum = 0;
    for (; i > 0; i -= i & -i) {
      sum += fen[i];
    }
    return sum;
  }

  public int query(int l, int r) {
    return query(r) - query(l - 1);
  }

  public static void main(String[] args) {
    new Solver().run();
  }
}

class Solver {
  private final Scanner scanner = new Scanner(System.in);

  public void run() {
    int n = scanner.nextInt();
    int[] arr = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      arr[i] = scanner.nextInt();
    }
    FenwickTree fenwickTree = new FenwickTree(n);
    fenwickTree.build(arr, n);
    int queries = scanner.nextInt();
    while (queries-- > 0) {
      String type = scanner.next();
      if (type.equals("update")) {
        int idx = scanner.nextInt();
        int val = scanner.nextInt();
        int delta = val - arr[idx];
        fenwickTree.update(idx, delta);
      } else {
        int left = scanner.nextInt();
        int right = scanner.nextInt();
        int ans = fenwickTree.query(left, right);
        System.out.println(ans);
      }
    }
  }
}
