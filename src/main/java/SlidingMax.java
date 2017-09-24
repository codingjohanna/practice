import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingMax {

    public static void main(String args[]) {
        int[] nums = {9, 10, 9, -7, -4, -8, 2, -6};
        int window = 5;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, window)));
    }

    public static int[] maxSlidingWindow(int[] nums, int window) {
        if (window == 0 || nums == null || nums.length == 0) {
            return new int[] {};
        }
        int[] results = new int[nums.length - window + 1];
        // The queue stores potential *index* values for the max (ordered)
        // The max index will be stored at queue[0]
        // Double ended queue
        Deque<Integer> queue = new ArrayDeque<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (queue.isEmpty()) {
                // Initialization
                queue.addFirst(i);
            } else {
                Integer firstIndex = queue.peekFirst();
                Integer lastIndex = queue.peekLast();
                if (nums[i] > nums[firstIndex]) {
                    queue.removeFirst();
                    queue.addFirst(i);
                } else if (nums[i] < nums[lastIndex]) {
                    while (lastIndex != null && lastIndex < i - window + 1) {
                        queue.removeLast();
                        lastIndex = queue.peekLast();
                    }
                    queue.addLast(i);
                } else {
                    while (lastIndex != null && nums[lastIndex] <= nums[i]) {
                        queue.removeLast();
                        lastIndex = queue.peekLast();
                    }
                    queue.addLast(i);
                }
            }

            // Getting the maximum for current window
            if (i >= window - 1) {
                Integer firstIndex = queue.peekFirst();
                while (firstIndex != null && firstIndex < i - window + 1) {
                    queue.removeFirst();
                    firstIndex = queue.peekFirst();
                }
                results[i - window + 1] = nums[firstIndex];
            }
        }

        return results;
    }

}
