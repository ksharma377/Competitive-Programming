import java.util.Scanner;

public class SegmentTree {
    private int[] seg;
    private final int n;

    SegmentTree(int n) {
        this.n = 4 * (n + 1);
        seg = new int[this.n];
    }

    public void build(int curr, int start, int end, int[] arr) {
        if (start == end) {
            seg[curr] = arr[end];
            return;
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        build(left, start, mid, arr);
        build(right, mid + 1, end, arr);
        seg[curr] = seg[left] + seg[right];
    }

    public void update(int curr, int start, int end, int idx, int val) {
        if (start > idx || end < idx) {
            return;
        }
        if (start == end) {
            seg[curr] = val;
            return;
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        update(left, start, mid, idx, val);
        update(right, mid + 1, end, idx, val);
        seg[curr] = seg[left] + seg[right];
    }

    public int query(int curr, int start, int end, int qStart, int qEnd) {
        if (start > qEnd || end < qStart || start > end) {
            return 0;
        }
        if (start >= qStart && end <= qEnd) {
            return seg[curr];
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        int leftAns = query(left, start, mid, qStart, qEnd);
        int rightAns = query(right, mid + 1, end, qStart, qEnd);
        return leftAns + rightAns;
    }

    public static void main(String[] args) {
        new Solver().run();
    }
}

class Solver {
    public void run() {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = scanner.nextInt();
        }
        SegmentTree segmentTree = new SegmentTree(n);
        segmentTree.build(1, 1, n, arr);
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            String type = scanner.next();
            if (type.equals("update")) {
                int idx = scanner.nextInt();
                int val = scanner.nextInt();
                arr[idx] = val;
                segmentTree.update(1, 1, n, idx, val);
            } else {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                int ans = segmentTree.query(1, 1, n, left, right);
                System.out.println(ans);
            }
        }
    }
}
