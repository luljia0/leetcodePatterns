import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        // iterate and sort the array
        while(i < nums.length) {
            // if not in the right pos, swap it to the right pos
            if(nums[i] != i + 1) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
                

            } else {
                i++;
            }

        }

        // iterate the array again
        for(int j = 0; i < nums.length; j++) {
            if(nums[j] != j + 1) {
                res.add( j + 1);
            }

        }

        return res;
        
    }
}
class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // test case 1
        int[] nums1 = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> res1 = solution.findDisappearedNumbers(nums1);
        for(int num: res1) {
            System.out.print(num + "\n");
        }
        
        


    }
    
}
