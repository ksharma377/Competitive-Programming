import java.util.Scanner;

public class SegmentTree {
    private int[][] seg;
    private final int n, m;

    SegmentTree(int n, int m) {
        this.n = n;
        this.m = m;
        seg = new int[4 * n][4 * m];
    }

    public static void main(String[] args) {
        new Solver().run();
    }

    public void build(int curr, int start, int end, int[][] arr) {
        if (start == end) {
            buildColumnSegmentTree(1, 1, m, arr, curr, end);
            return;
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        build(left, start, mid, arr);
        build(right, mid + 1, end, arr);
        merge(curr, left, right, start, end);
    }
    
    public void update(int curr, int start, int end, int row, int col, int val) {
        if (start > row || end < row) {
            return;
        }
        if (start == end) {
            updateColumnSegmentTree(1, 1, m, col, val, curr);
            return;
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        update(left, start, mid, row, col, val);
        update(right, mid + 1, end, row, col, val);
        merge(curr, left, right, start, end);
    }

    public int query(int curr, int start, int end, int rowStart, int colStart, int rowEnd, int colEnd) {
        if (start > rowEnd || end < rowStart || start > end) {
            return 0;
        }
        if (start >= rowStart && end <= rowEnd) {
            return queryColumnSegmentTree(1, 1, m, colStart, colEnd, curr);
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        int leftAns = query(left, start, mid, rowStart, colStart, rowEnd, colEnd);
        int rightAns = query(right, mid + 1, end, rowStart, colStart, rowEnd, colEnd);
        return leftAns + rightAns;
    }

    private void merge(int curr, int left, int right, int start, int end) {
        for (int i = start; i <= end; i++) {
            seg[curr][i] = seg[left][i] + seg[right][i];
        }
    }

    private void buildColumnSegmentTree(int curr, int start, int end, int[][] arr, int treeIndex, int row) {
        if (start == end) {
            seg[treeIndex][curr] = arr[row][end];
            return;
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        buildColumnSegmentTree(left, start, mid, arr, treeIndex, row);
        buildColumnSegmentTree(right, mid + 1, end, arr, treeIndex, row);
        seg[treeIndex][curr] = seg[treeIndex][left] + seg[treeIndex][right];
    }

    private void updateColumnSegmentTree(int curr, int start, int end, int idx, int val, int treeIndex) {
        if (start > idx || end < idx) {
            return;
        }
        if (start == end) {
            seg[treeIndex][curr] = val;
            return;
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        updateColumnSegmentTree(left, start, mid, idx, val, treeIndex);
        updateColumnSegmentTree(right, mid + 1, end, idx, val, treeIndex);
        seg[treeIndex][curr] = seg[treeIndex][left] + seg[treeIndex][right];
    }

    private int queryColumnSegmentTree(int curr, int start, int end, int qStart, int qEnd, int treeIndex) {
        if (start > qEnd || end < qStart || start > end) {
            return 0;
        }
        if (start >= qStart && end <= qEnd) {
            return seg[treeIndex][curr];
        }
        int mid = (start + end) >> 1;
        int left = (curr << 1);
        int right = (left | 1);
        int leftAns = queryColumnSegmentTree(left, start, mid, qStart, qEnd, treeIndex);
        int rightAns = queryColumnSegmentTree(right, mid + 1, end, qStart, qEnd, treeIndex);
        return leftAns + rightAns;
    }
}

class Solver {
    public void run() {
        final Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        SegmentTree segmentTree = new SegmentTree(n, m);
        segmentTree.build(1, 1, n, arr);
        int queries = scanner.nextInt();
        while (queries-- > 0) {
            String type = scanner.next();
            if (type.equals("update")) {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                int val = scanner.nextInt();
                arr[row][col] = val;
                segmentTree.update(1, 1, n, row, col, val);
            } else {
                int rowStart = scanner.nextInt();
                int colStart = scanner.nextInt();
                int rowEnd = scanner.nextInt();
                int colEnd = scanner.nextInt();
                int ans = segmentTree.query(1, 1, n, rowStart, colStart, rowEnd, colEnd);
                System.out.println(ans);
            }
        }
    }
}
