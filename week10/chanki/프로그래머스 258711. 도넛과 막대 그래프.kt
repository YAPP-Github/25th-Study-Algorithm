class P258711 {

    // k: 출발, v: 도착들
    private val startToEnds = mutableMapOf<Int, MutableList<Int>>()
    private val result = IntArray(4)

    fun solution(edges: Array<IntArray>): IntArray {
        // k: 도착, v: 출발들
        val endFromStarts = mutableMapOf<Int, MutableList<Int>>()
        val nodes = mutableSetOf<Int>()

        edges.forEach {
            startToEnds.putIfAbsent(it[0], mutableListOf())
            startToEnds[it[0]]?.add(it[1])
            endFromStarts.putIfAbsent(it[1], mutableListOf())
            endFromStarts[it[1]]?.add(it[0])
            nodes.add(it[0])
            nodes.add(it[1])
        }

        // 문제 조건 상 임의의 노드는, 자신으로부터 나가는 엣지는 있으나, 외부에서 들어오는 엣지는 없음
        // 자신으로부터 나가는 엣지는 최소 2개 이상
        // 이 조건은 유니크한 것이, 자신으로부터 나가는 엣지가 2개 이상인 경우는 8자 뿐인데
        // 8자의 경우 자신으로 들어오는 엣지가 하나 이상이다
        val node = nodes.single {
            (startToEnds.containsKey(it) && startToEnds[it]!!.size >= 2) && !endFromStarts.containsKey(it)
        }.apply { result[0] = this }

        startToEnds[node]!!.forEach {
            result[decideShape(it)] += 1
        }

        return result
    }

    // 모양을 결정하여 반환
    // 1: 도넛, 2: 막대, 3: 8자
    private fun decideShape(connectedNode: Int): Int {
        val deque = ArrayDeque<Int>()
        val visited = HashSet<Int>()
        deque.add(connectedNode)

        while (deque.isNotEmpty()) {
            val node = deque.removeFirst()
            startToEnds[node]

            // 순회 중
            // 자신을 출발로 하는 노드가 없다면, 막대 그래프의 끝에 다다른 것
            // 자신으로부터 나가는 노드가 두 개 이상이면 8자
            // 내가 방문했던 노드가 나온다면 도넛
            // 8자와 도넛이 충돌한다고 생각할 수 있지만, 곰곰히 따져보면 그런 케이스는 없음
            when {
                !startToEnds.containsKey(node) -> return 2
                startToEnds[node]!!.size >= 2 -> return 3
                startToEnds[node]!!.first() in visited -> return 1
            }

            startToEnds[node]!!.first().also {
                visited.add(it)
                deque.add(it)
            }
        }

        return -1
    }
}