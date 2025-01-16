from collections import Counter

class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        counter = Counter(tasks)
        counter = sorted(list(counter.values()))

        max_task = counter.pop()  # 가장 개수가 많은 task
        idle_cnt = (max_task - 1) * n  # 남아있는 idle의 개수 - 가장 개수가 많은 task 사이사이에 들어간다

        while counter and idle_cnt > 0:
            idle_cnt -= min(max_task - 1, counter.pop())  # 가장 개수가 많은 task 사이 idle 에 하나씩 들어가거나 or 현재 task 개수만큼(task 개수가 더 적을 경우) idle 에 들어간다

        return len(tasks) + idle_cnt if idle_cnt > 0 else len(tasks)