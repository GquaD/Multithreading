package section13;

public class SequentialMaxFinding {
    //linear search O(N)

    public long max(long[] nums){
        long max = nums[0];
        for (int i = 1; i < nums.length; i++) max = Math.max(nums[i], max);
        return max;
    }
}
