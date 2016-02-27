//
//  FavoriteService.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import Foundation

class FavoriteService: NSObject {
    
    func postFavorite(userId: Int, itemId: Int, isTeam: Bool) {
        let uri = isTeam ? "user/\(userId)/teams/" : "user/\(userId)/shows/"
        let key = isTeam ? "team" : "show"
        let postDict: [String: AnyObject] = ["user": userId, key: itemId, "notifications": 1]
        
        HttpClient(domain: "https://swagoverflow.brobin.me/api/").executeRequest(uri, method: .POST, headers: ["Content-Type": "application/json"], postData: postDict, success: nil, error: nil)
    }
    
    func deleteFavorite(userId: Int, isTeam: Bool, id: Int, success:(()->())?) {
        let key = isTeam ? "teams" : "shows"
        HttpClient(domain: "https://swagoverflow.brobin.me/api/").executeRequest("user/\(userId)/\(key)/\(id)", method: .DELETE, headers: nil, postData: nil, success: { _ in
            success?()
        }, error: nil)
    }
}
