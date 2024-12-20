//
//  main.swift
//  baekjoon2851
//
//  Created by 김도형 on 12/20/24.
//

import Foundation

var mushrooms = [Int]()
/// - 10번 입력 받아서 배열에 append
for _ in 0..<10 {
    mushrooms.append(readInt())
}
/// - 누적점수와 100점과의 차이 초기화
var difference = 100 - mushrooms[0]
var score = 0
for mushroom in mushrooms {
    /// - 다음 누적점수 계산
    let nextScore = score + mushroom
    /// - 다음 누적점수의 차이와 현재 차이의 절댓값 비교
    guard abs(100 - nextScore) <= abs(difference) else {
        /// - 다음 누적점수의 차이가 현재 차이보다 클 경우 반복문 종료
        break
    }
    /// - 누적점수, 차이 업데이트
    score = nextScore
    difference = 100 - score
}
print(score)

/// - 입력값 정수로 변환
func readInt() -> Int {
    let readLine = readLine()
    guard
        let string = readLine,
        let int = Int(string)
    else { fatalError() }
    return int
}
