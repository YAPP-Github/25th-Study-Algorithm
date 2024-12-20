//
//  main.swift
//  baekjoon14467
//
//  Created by 김도형 on 12/20/24.
//

import Foundation

/// - 소와 소 위치 캐싱 초기화
var cows = [Int: Int]()
/// - 건너간 소 카운팅 초기화
var count = 0
var n = readInt()

for _ in 0..<n {
    let (cow, position) = readTupleInt()
    /// - 번호에 맞는 소가 존재하고 새로운 위치와 기존의 위치가 다를경우
    if let cowPosition = cows[cow],
       position != cowPosition {
        count += 1
    }
    /// - 소 위치 업데이트
    cows[cow] = position
}
print(count)

/// - 입력받은 소 번호와 소 위치를 튜플로 변환
func readTupleInt() -> (Int, Int) {
    guard
        let strings = readLine()?.split(separator: " ").map({ String($0) }),
        let int1 = Int(strings.first ?? ""),
        let int2 = Int(strings.last ?? "")
    else { fatalError() }
    return (int1, int2)
}

/// - 입력값 정수 변환
func readInt() -> Int {
    guard
        let sting = readLine(),
        let int = Int(sting)
    else { fatalError() }
    return int
}
