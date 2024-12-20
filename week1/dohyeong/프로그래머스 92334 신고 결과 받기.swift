//
//  main.swift
//  programmers92334
//
//  Created by 김도형 on 12/20/24.
//

import Foundation

func solution(_ id_list:[String], _ report:[String], _ k:Int) -> [Int] {
    /// - 유저를 기준으로 신고한 사람 캐싱 초기화
    var users = [String: Set<String>]()
    /// - 신고당한 유저를 기준으로 신고당한 횟수 캐싱 초기화
    var reports = [String: Int]()
    id_list.forEach { users[$0] = .init() }
    
    for detailString in report {
        let detail = detailString.split(separator: " ")
        let reporter = String(detail[0])
        let reported = String(detail[1])
        
        /// - 유저가 신호한 유저 추가
        users[reporter]?.insert(reported)
    }
    for reportedByUser in users.values {
        /// - 유저가 신고한 유저들이 신고당한 유저 목록에 있으면 신고당한 횟수 증가
        reportedByUser.forEach { reports[$0] = (reports[$0] ?? 0) + 1 }
    }
    /// - 이용 정지 기준으로 신고당한 유저 목록 필터링
    reports = reports.filter({ $0.value >= k })
    
    /// - 신고 결과 초기화
    var result = Array(repeating: 0, count: id_list.count)
    
    for (index, user) in id_list.enumerated() {
        var count = 0
        for reported in reports.keys {
            guard
                let isContains = users[user]?.contains(reported),
                isContains
            else { continue }
            /// - 유저가 신고한 사람 중 이용정지 유저가 있으면 결과 횟수 증가
            count += 1
        }
        result[index] = count
    }
    
    return result
}

print(solution(["con", "ryan"], ["ryan con", "ryan con", "ryan con", "ryan con"], 3))
