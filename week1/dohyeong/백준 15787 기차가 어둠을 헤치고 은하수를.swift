//
//  main.swift
//  baekjoon15787
//
//  Created by 김도형 on 12/20/24.
//

import Foundation

/// - 첫번째 명령줄 초기화
let (trainNum, operatorNum) = readFirst()

/// - 기차 번호에 맞는 좌석 상태를 Bool 배열로 캐싱
var trains = [Int: [Bool]]()
for num in 1...trainNum {
    /// - 처음에는 전부 빈 좌석으로 초기화
    trains[num] = Array(repeating: false, count: 20)
}

for _ in 0..<operatorNum {
    /// - 명령 초기화
    let operate = readOperator()
    switch operate[0] {
    case 1: operate1(trains: &trains, i: operate[1], x: operate[2])
    case 2: operate2(trains: &trains, i: operate[1], x: operate[2])
    case 3: operate3(trains: &trains, i: operate[1])
    case 4: operate4(trains: &trains, i: operate[1])
    default: break
    }
}

/// - 중복제거를 위해 은하수가는 기차를 Set으로 초기화
var galaxyTrain = Set<[Bool]>()
for train in trains.values {
    galaxyTrain.insert(train)
}
print(galaxyTrain.count)

/// - 1 명령 수행
func operate1(trains: inout [Int: [Bool]], i: Int, x: Int) {
    guard let train = trains[i] else { return }
    var newTrain = train
    newTrain[x - 1] = true
    trains[i] = newTrain
}

/// - 2 명령 수행
func operate2(trains: inout [Int: [Bool]], i: Int, x: Int) {
    guard let train = trains[i] else { return }
    var newTrain = train
    newTrain[x - 1] = false
    trains[i] = newTrain
}

/// - 3 명령 수행
func operate3(trains: inout [Int: [Bool]], i: Int) {
    guard let train = trains[i] else { return }
    var newTrain = Array(repeating: false, count: 20)
    for (x, isSeated) in train.enumerated() {
        /// - 마지막 요소는 건너 뜀
        guard x + 1 < 20 else { continue }
        newTrain[x + 1] = isSeated
    }
    trains[i] = newTrain
}

/// - 4 명령 수행
func operate4(trains: inout [Int: [Bool]], i: Int) {
    guard let train = trains[i] else { return }
    var newTrain = Array(repeating: false, count: 20)
    for (x, isSeated) in train.enumerated() {
        /// - 첫번째 요소는 건너 뜀
        guard x > 0 else { continue }
        newTrain[x - 1] = isSeated
    }
    trains[i] = newTrain
}

/// - 첫번째 명령 입력 변환
func readFirst() -> (Int, Int) {
    let integers = readLine()!.split(separator: " ").compactMap({
        Int(String($0))
    })
    return (integers[0], integers[1])
}

/// - 나머지 명령 입력 변환
func readOperator() -> [Int] {
    let integers = readLine()!.split(separator: " ").compactMap({
        Int(String($0))
    })
    return integers
}
