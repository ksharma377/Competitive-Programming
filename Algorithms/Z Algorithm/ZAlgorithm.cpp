#include <bits/stdc++.h>
using namespace std;

void ZAlgorithm(string& s) {
  int n = s.length();
  int z[n];
  z[0] = 0;
  int l = 0, r = 0;
  for (int i = 1; i < n; i++) {
    if (i > r) {
      l = r = i;
      while (r < n && s[r] == s[r - l]) {
        r++;
      }
      z[i] = r - l;
      r--;
    } else {
      int k = i - l;
      if (z[k] < r - i + 1) {
        z[i] = z[k];
      } else {
        l = i;
        while (r < n && s[r] == s[r - l]) {
          r++;
        }
        z[i] = r - l;
        r--;
      }
    }
  }
  for (int i = 0; i < n; i++) {
    cout << z[i] << ' ';
  }
}

int main() {
  string txt, pat;
  cin >> txt >> pat;
  string s = pat + "$" + txt;
  cout << s << endl;
  ZAlgorithm(s);
  return 0;
}
