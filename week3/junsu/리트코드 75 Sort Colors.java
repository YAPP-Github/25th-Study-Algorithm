class Solution {
    public void sortColors(int[] nums) {
        int red = 0;
        int white = 0;
        int blue = 0;

        // 각 색깔의 개수를 계산
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                red++;
            } else if(nums[i] == 1) {
                white++;
            } else {
                blue++;
            }
        }

        // red -> white -> blue 순으로 삽입
        int idx = 0;
        while(red-- > 0) {
            nums[idx++] = 0;
        }

        while(white-- > 0) {
            nums[idx++] = 1;
        }

        while(blue-- > 0) {
            nums[idx++] = 2;
        }
    }
}