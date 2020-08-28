package GinkgoStack.P24_Enterprise.Tencent.qiu2020;

/**
 * 作者：js8544
 * 链接：https://www.nowcoder.com/discuss/486673
 * 来源：牛客网
 *
 *  Top-down dp，dp[l][r]代表解决l到r区间的最小次数。
 *  按区间长度遍历，dp[l][r]初始化成(r-l+1)，因为可以对每个木板竖着刷一次。
 *  如果横着刷，我们刷arr[l]...arr[r]中最小的次，
 *  然后分割成小区间，求和。然后取两者最小值。
 */
public class PaintedWood {

//    int n;
//    int arr[5001];
//
//    int dfs(int l, int r){
//        if(l==r){
//            if(arr[l]==0) return 0;
//            else return 1;
//        }
//
//        int ans = r-l+1;
//        int minl = INT_MAX;
//        for(int i=l;i<=r;i++){
//            minl = min(minl, arr[i]);
//        }
//        int ans1 = minl;
//        int curl = -1;
//        for(int i=l;i<=r;i++){
//            arr[i] -= minl;
//            if(arr[i] > 0){
//                if(i==0 || arr[i-1]==0){
//                    curl = i;
//                }
//                if(i==r){
//                    ans1 += dfs(curl, i);
//                }
//            }else{
//                if(i>0 && arr[i-1] > 0){
//                    ans1 += dfs(curl, i-1);
//                }
//            }
//        }
//        return min(ans, ans1);
//    }
//
//    void solve() {
//        cin>>n;
//        for(int i=0;i<n;i++){
//            cin>>arr[i];
//        }
//        cout<<dfs(0,n-1)<<endl;
//    }
}
