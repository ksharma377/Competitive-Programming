#include <bits/stdc++.h>
using namespace std;

class SegmentTree {
private:
  vector<int> seg;
  int n;

public:
  SegmentTree(int _n) {
    n = 4 * (_n + 1);
    seg.resize(n);
  }

  void build(int i, int l, int r, const vector<int>& arr) {
    if (l == r) {
      seg[i] = arr[r];
      return;
    }
    int m = (l + r) >> 1;
    int left = i + i;
    int right = left | 1;
    build(left, l, m, arr);
    build(right, m + 1, r, arr);
    seg[i] = seg[left] + seg[right];
  }

  void update(int i, int l, int r, int idx, int val) {
    if (l > idx || r < idx || l > r) {
      return;
    }
    if (l == r) {
      seg[i] = val;
      return;
    }
    int m = (l + r) >> 1;
    int left = i + i;
    int right = left | 1;
    update(left, l, m, idx, val);
    update(right, m + 1, r, idx, val);
    seg[i] = seg[left] + seg[right];
  }

  int query(int i, int l, int r, int a, int b) {
    if (l > b || r < a || l > r) {
      return 0;
    }
    if (l >= a && r <= b) {
      return seg[i];
    }
    int m = (l + r) >> 1;
    int left = i + i;
    int right = left | 1;
    int leftAns = query(left, l, m, a, b);
    int rightAns = query(right, m + 1, r, a, b);
    return leftAns + rightAns;
  }
};

int main() {
  int n;
  scanf("%d", &n);
  vector<int> a(n + 1);
  for (int i = 1; i <= n; i++) {
    scanf("%d", &a[i]);
  }
  SegmentTree tree(n);
  tree.build(1, 1, n, a);
  int queries;
  scanf("%d", &queries);
  while (queries-- > 0) {
    char type[10];
    scanf("%s", &type);
    if (type[0] == 'u') {
      int idx, val;
      scanf("%d %d", &idx, &val);
      a[idx] = val;
      tree.update(1, 1, n, idx, val);
    } else {
      int l, r;
      scanf("%d %d", &l, &r);
      int ans = tree.query(1, 1, n, l, r);
      printf("%d\n", ans);
    }
  }
  return 0;
}
