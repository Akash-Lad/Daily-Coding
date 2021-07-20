class Arrays {

    public static int equilibriumPoint(int arr[]) {
        // Time Complexity: O(N)
        // Space Complexity: O(1)

        int leftSum = 0, rightSum = 0;

        for (int i = 0; i < arr.length; i++) {
            rightSum += arr[i];
        }

        for (int i = 0; i < arr.length; i++) {

            leftSum += arr[i];

            if (leftSum == rightSum)
                return i;

            rightSum -= arr[i];

        }

        return -1;
    }

    public static void leaderInArray(int[] arr) {
        // {16, 17, 4, 3, 5, 2}

        int n = arr.length;
        int max = arr[n - 1];

        for (int i = n - 1; i >= 0; i--) {

            if (i == n - 1) {
                System.out.println(arr[i]);
            }
            if (arr[i] > max) {
                max = arr[i];
                System.out.println(max);
            }
        }

    }

    public static void main(String args[]) {

        int arr[] = { 16, 17, 4, 3, 5, 2 };
        leaderInArray(arr);

    }

}