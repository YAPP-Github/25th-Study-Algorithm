class Solution:
    def sortColors(self, nums: List[int]) -> None:
        cnt0 = 0
        cnt1 = 0
        cnt2 = 0

        for num in nums:
            if num == 0:
                cnt0 += 1
            elif num == 1:
                cnt1 += 1
            else:
                cnt2 += 1

        for i in range(cnt0):
            nums[i] = 0

        for i in range(cnt0, cnt0 + cnt1):
            nums[i] = 1

        for i in range(cnt0 + cnt1, len(nums)):
            nums[i] = 2

# ----

class Solution:
    def sortColors(self, nums: List[int]) -> None:
        for i in range(len(nums) - 1):  # Bubble Sort
            for j in range(len(nums) - i - 1):
                if nums[j] > nums[j + 1]:
                    nums[j], nums[j + 1] = nums[j + 1], nums[j]

# ----

class Solution:
    def sortColors(self, nums: List[int]) -> None:

        def quicksort(start, end):
            if start >= end:
                return
            pivot = start
            left = start + 1
            right = end

            while left <= right:
                while left <= end and nums[left] <= nums[pivot]:
                    left += 1
                while right > start and nums[right] >= nums[pivot]:
                    right -= 1

                if left > right:
                    nums[right], nums[pivot] = nums[pivot], nums[right]
                else:
                    nums[left], nums[right] = nums[right], nums[left]

            quicksort(start, right - 1)
            quicksort(right + 1, end)

        quicksort(0, len(nums) - 1)
