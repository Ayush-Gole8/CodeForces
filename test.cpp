#include <bits/stdc++.h>
using namespace std;

typedef long long int ll;

void solve()
{
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++)
        cin >> a[i];

    int ans = 0;
    for (int i = 1; i < n; i++)
    {
        if (a[i] + a[i - 1] == 7 or a[i] == a[i - 1])
            ans++, i++;
    }

    cout << ans;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    ll tc = 1;
    cin >> tc;
    for (ll i = 1; i <= tc; i++)
    {
        solve();
        cout << '\n';
    }
}