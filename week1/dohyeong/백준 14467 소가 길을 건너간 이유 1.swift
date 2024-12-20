//
//  main.swift
//  baekjoon14467
//
//  Created by 김도형 on 12/20/24.
//

import Foundation

var cows = [Int: Int]()
var count = 0
var n = readInt()

for _ in 0..<n {
    let (cow, position) = readTupleInt()
    if let cowPosition = cows[cow],
       position != cowPosition {
        count += 1
    }
    cows[cow] = position
}
print(count)

func readTupleInt() -> (Int, Int) {
    guard
        let strings = readLine()?.split(separator: " ").map({ String($0) }),
        let int1 = Int(strings.first ?? ""),
        let int2 = Int(strings.last ?? "")
    else { fatalError() }
    return (int1, int2)
}

func readInt() -> Int {
    guard
        let sting = readLine(),
        let int = Int(sting)
    else { fatalError() }
    return int
}
