class P150369Kt {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        // 가장 멀리 떨어진 배송, 픽업 위치
        var lastDeliveredPlace = 0;
        var lastPickupPlace = 0;

        for (i in n - 1 downTo 0) {
            if (lastDeliveredPlace == 0 && deliveries[i] != 0) {
                lastDeliveredPlace = i
            }

            if (lastPickupPlace == 0 && pickups[i] != 0) {
                lastPickupPlace = i
            }

            if (lastDeliveredPlace != 0 && lastPickupPlace != 0) {
                break
            }
        }

        // 총 이동 거리
        var distance = 0L
        // 모든 배송과 픽업이 처리될 때까지
        while (deliveries[lastDeliveredPlace] > 0 || pickups[lastPickupPlace] > 0) {
            // 거리는 배송지와 픽업지 중 더 먼 곳을 기준으로 왕복하는 것이 됨
            distance += (maxOf(lastDeliveredPlace, lastPickupPlace) + 1) * 2

            // cap을 모두 꽉꽉 채워, 가장 먼 곳부터 배송을 처리
            var sumOfDeliveryPack = 0
            for (i in lastDeliveredPlace downTo 0) {
                lastDeliveredPlace = i
                if (sumOfDeliveryPack + deliveries[i] > cap) {
                    deliveries[i] -= cap - sumOfDeliveryPack
                    break
                }

                sumOfDeliveryPack += deliveries[i]
                deliveries[i] = 0
            }

            // cap이 텅 빈 상태로, 가장 먼 곳부터 픽업을 처리
            var sumOfPickupPack = 0
            for (i in lastPickupPlace downTo 0) {
                lastPickupPlace = i
                if (sumOfPickupPack + pickups[i] > cap) {
                    pickups[i] -= cap - sumOfPickupPack
                    break
                }

                sumOfPickupPack += pickups[i]
                pickups[i] = 0
            }
        }

        return distance
    }
}