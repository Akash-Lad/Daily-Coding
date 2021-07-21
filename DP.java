import java.util.ArrayList;

public class DP {

    // Recursion of 0-1 knapsack problem

    public static int knapsack(int wt[], int[] val, int w, int n) {

        if (n == 0 || w == 0)
            return 0;

        if (wt[n - 1] <= w) {
            return Math.max(val[n - 1] + knapsack(wt, val, w - wt[n - 1], n - 1), knapsack(wt, val, w, n - 1));
        }
        return knapsack(wt, val, w, n - 1);
    }

    // Memoized version

    public static int knapsackMemoized(int wt[], int val[], int w, int n) {

        if (n == 0 || w == 0)
            return 0;

        int[][] dp = new int[n + 1][w + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < w + 1; j++) {
                dp[i][j] = -1;
            }
        }

        if (dp[n][w] != -1) {
            return dp[n][w];
        }

        if (wt[n - 1] <= w) {
            return dp[n][w] = Math.max(val[n - 1] + knapsackMemoized(wt, val, w - wt[n - 1], n - 1),
                    knapsackMemoized(wt, val, w, n - 1));
        } else {
            return dp[n][w] = knapsackMemoized(wt, val, w, n - 1);
        }

    }

    // Tabulated DP Approach

    public static int knapsackBottomUp(int wt[], int val[], int w, int n) {

        int[][] dp = new int[n + 1][w + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < w + 1; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < w + 1; j++) {
                if (wt[i - 1] <= j) {
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }

        return dp[n][w];
    }

    // subset sum

    public static boolean subsetSum(int arr[], int k) {

        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][k + 1];

        for (int i = 0; i < n + 1; i++)
            dp[i][0] = true;

        for (int j = 1; j < k + 1; j++)
            dp[0][j] = false;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][k];

    }

    public static int countOfSubsetOfGivenSum(int arr[], int sum) {

        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j < sum + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                // wt[n-1] <=w
                // val[n-1] + dp[i-1][j-wt[i-1]]
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];

    }

    // Partition to K equal subset sum

    public static boolean canPartitionKSubsets(int[] arr, int k) {

        int sum = 0;

        for (int item : arr) {
            sum += item;
        }

        if (sum % 2 != 0) {
            return false;
        }

        return subsetSum(arr, sum / 2);

    }

    // Subset Sum with minimum difference

    public static int subsetSumWithMinimumDifference(int arr[], int n) {

        int sum = 0;
        for (int x : arr)
            sum += x;

        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i < n + 1; i++)
            dp[i][0] = true;

        for (int j = 1; j < sum + 1; j++)
            dp[0][j] = false;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                // wt[n-1]<=w
                // val[n-1] + dp[i-1][j-wt[i-1]]

                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                } else if (arr[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = sum / 2; i >= 0; i--) {
            if (dp[n][i] == true) {
                min = Math.min(min, sum - 2 * i);
                break;
            }
        }

        return min;

    }

    // Greedy min number of coins

    public static int coinNumber(int val) {

        int[] denom = { 1, 2, 5, 10, 20, 50, 100, 500, 1000 };
        int count = 0;
        int n = denom.length;

        while (val > 0) {

            for (int i = n - 1; i >= 0; i--) {
                if (val >= denom[i]) {
                    val -= denom[i];
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String args[]) {

        int arr[] = { 1, 2 };
        int k = 1;
        System.out.println(subsetSumWithMinimumDifference(arr, k));

    }

}
