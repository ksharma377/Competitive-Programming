#include <bits/stdc++.h>
using namespace std;

class FenwickTree {
private:
  vector<int> bit;
  int n;

public:
  FenwickTree(int _n) {
    n = _n + 1;
    bit.resize(n);
  }

  void build(const vector<int>& arr, int n) {
    for (int i = 1; i <= n; i++) {
      update(i, arr[i]);
    }
  }

  void update(int i, int delta) {
    for (; i <= n; i += i & -i) {
      bit[i] += delta;
    }
  }

  int query(int i) {
    int sum = 0;
    for (; i > 0; i -= i & -i) {
      sum += bit[i];
    }
    return sum;
  }

  int query(int l, int r) {
    return query(r) - query(l - 1);
  }
};

int main() {
  int n;
  scanf("%d", &n);
  vector<int> a(n + 1);
  for (int i = 1; i <= n; i++) {
    scanf("%d", &a[i]);
  }
  FenwickTree tree(n);
  tree.build(a, n);
  int queries;
  scanf("%d", &queries);
  while (queries-- > 0) {
    char type[10];
    scanf("%s", &type);
    if (type[0] == 'u') {
      int idx, val;
      scanf("%d %d", &idx, &val);
      int delta = val - a[idx];
      a[idx] = val;
      tree.update(idx, delta);
    } else {
      int l, r;
      scanf("%d %d", &l, &r);
      int ans = tree.query(l, r);
      printf("%d\n", ans);
    }
  }
  return 0;
}
