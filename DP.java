//import java.util.*;

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

    public static int countSubsetSumWithGivenDifference(int arr[], int diff) {

        int sumofarray = 0;

        for (int x : arr)
            sumofarray += x;

        int sum = (diff + sumofarray) / 2;

        return countOfSubsetOfGivenSum(arr, sum);
    }

    public int countOfSubsetSumWithGivenDifference(int[] arr, int sum) {

        int n = arr.length;

        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 0; i < n + 1; i++)
            dp[i][0] = 1;

        for (int j = 1; j < sum + 1; j++)
            dp[0][j] = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {

                if (arr[i - 1] == 0)
                    dp[i][j] = dp[i - 1][j];
                else if (arr[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];

            }
        }

        return dp[n][sum];

    }

    public int findTargetSumWays(int[] arr, int target) {

        int sumofarray = 0, count = 0;

        for (int x : arr) {

            sumofarray += x;

            // Edge-case-1
            if (x == 0)
                count = count + 1;

        }

        // Edge-case-2
        if (target > sumofarray)
            return 0;

        // Edge-case-3
        if ((target + sumofarray) % 2 != 0)
            return 0;

        int sum = (target + sumofarray) / 2;

        int result = countOfSubsetSumWithGivenDifference(arr, sum);

        return (int) Math.pow(2, count) * result;

    }

    // Rod Cutting Problem

    public static int cutRod(int n, int[] price) {
        /*
         * length[] price[] n
         */

        int[] length = new int[n];
        int size = price.length;

        for (int i = 0; i < n; i++)
            length[i] = i + 1;

        int[][] dp = new int[n + 1][size + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 0;
        }

        for (int j = 1; j < size + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < size + 1; j++) {

                if (length[i - 1] <= j) {
                    dp[i][j] = Math.max(price[i - 1] + dp[i][j - length[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][size];
    }

    public static int sticklerThief(int arr[], int n) {

        if (n == 0)
            return 0;
        if (n == 1)
            return arr[0];
        if (n == 2)
            return Math.max(arr[0], arr[1]);

        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++)
            dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);

        return dp[n - 1];
    }

    // Coin change - I (Maximum number of coins)

    public static int coinChange_01(int[] coin, int sum) {

        int n = coin.length;
        int m = sum;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n + 1; i++)
            dp[i][0] = 1;

        for (int j = 1; j < m + 1; j++)
            dp[0][j] = 0;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {

                if (coin[i - 1] <= j) {
                    dp[i][j] = dp[i][j - coin[i - 1]] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }

        return dp[n][m];

    }

    // Coin change - II (Minimum number of coins)

    public static int coinChange_02(int[] coin, int sum) {

        int n = coin.length;
        int m = sum;

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {

                if (coin[i - 1] <= j) {
                    dp[i][j] = Math.min(1 + dp[i][j - coin[i - 1]], dp[i - 1][j]);

                } else {
                    dp[i][j] = dp[i - 1][j];
                }

            }
        }

        return dp[n][m];

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

        int arr[] = new int[] { 1, 5, 8, 9, 10, 17, 17, 20 };
        int size = arr.length;

        System.out.println(cutRod(size, arr));

    }

}
