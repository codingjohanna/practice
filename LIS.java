public class Main {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.print(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        // Sanitization
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Helper substructure storing the current LIS length at every index of nums
        int[] lengthsOfLIS = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) { // Initialization
                lengthsOfLIS[i] = 1;
            } else {
                int maxLengthOfLISAtI = 1;
                for (int j = 0; j < i; j++) { // Iterating through the left sub-array
                    if (nums[j] < nums[i] && lengthsOfLIS[j] >= maxLengthOfLISAtI) {
                        // Take the maximum length of all possible longest subsequences' lengths
                        maxLengthOfLISAtI = lengthsOfLIS[j] + 1;
                    }
                }
                lengthsOfLIS[i] = maxLengthOfLISAtI;
            }
        }
        int maxLength = 1;
        // Find maximum LIS length
        for (int i = 0; i < lengthsOfLIS.length; i++) {
            maxLength = (maxLength < lengthsOfLIS[i]) ? lengthsOfLIS[i] : maxLength;
        }
        return maxLength;
    }

}
