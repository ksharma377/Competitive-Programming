#include <bits/stdc++.h>
using namespace std;

const int N = 1e5 + 2;

int a[N];
int seg[4 * N];

void build(int i, int l, int r) {
  if (l == r) {
    seg[i] = a[r];
    return;
  }
  int m = (l + r) >> 1;
  int left = i + i;
  int right = left | 1;
  build(left, l, m);
  build(right, m + 1, r);
  seg[i] = seg[left] + seg[right];
}

void update(int i, int l, int r, int idx, int val) {
  if (l > idx || r < idx || l > r) {
    return;
  }
  if (l == r) {
    seg[i] = a[idx] = val;
    return;
  }
  int m = (l + r) >> 1;
  int left = i + i;
  int right = left | 1;
  update(left, l, m, idx, val);
  update(right, m + 1, r, idx, val);
  seg[i] = seg[left] + seg[right];
}

int query(int i, int l, int r, int ll, int rr) {
  if (l > rr || r < ll || l > r) {
    return 0;
  }
  if (l == r) {
    return seg[i];
  }
  int m = (l + r) >> 1;
  int left = i + i;
  int right = left | 1;
  int leftAns = query(left, l, m, ll, rr);
  int rightAns = query(right, m + 1, r, ll, rr);
  return leftAns + rightAns;
}

int main() {
  int n;
  scanf("%d", &n);
  for (int i = 1; i <= n; i++) {
    scanf("%d", a + i);
  }
  build(1, 1, n);
  int q;
  scanf("%d", &q);
  while (q--) {
    int type;
    scanf("%d", &type);
    if (type == 0) {
      int idx, val;
      scanf("%d %d", &idx, &val);
      update(1, 1, n, idx, val);
    } else {
      int l, r;
      scanf("%d %d", &l, &r);
      int ans = query(1, 1, n, l, r);
      printf("%d\n", ans);
    }
  }
  return 0;
}
