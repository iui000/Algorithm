package GinkgoStack.P24_Enterprise.Tencent.qiu2020;


/**
 * 二维DP。dp[i][j]代表区间i到j的答案。首先检查i到j是否是回文，如果是的话答案是1，
 * 如果不是的话遍历所有分割区间方法求和取最小值。
 */
public class PalindromeSegmentationII {

    //c++
//    int n;
//    string s;
//    int dp[401][401];
//    int q;
//    bool check(int i, int j){
//        for(int k=0;k<(j-i+1)/2;k++){
//            if(s[i+k] != s[j-k]) return false;
//        }
//        return true;
//    }
//
//    void solve() {
//        cin>>s;
//        n = s.size();
//        memset(dp,0x3f,sizeof(dp));
//        for(int l=0;l<n;l++){
//            for(int i=0;i+l<n;i++){
//                int j = i+l;
//                if(check(i,j)) dp[i][j] = 1;
//                else{
//                    for(int k=i+1;k<=j;k++){
//                        dp[i][j] = min(dp[i][j], dp[i][k-1]+dp[k][j]);
//                    }
//                }
//            }
//        }
//        cin>>q;
//        for(int i=0;i<q;i++){
//            int a,b;
//            cin>>a>>b;
//            cout<<dp[a-1][b-1]<<endl;
//        }
//    }
}
