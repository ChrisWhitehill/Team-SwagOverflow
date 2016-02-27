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
        let uri = isTeam ? "user/\(userId)/teams" : "user/\(userId)/shows"
        let key = isTeam ? "team" : "show"
        let postDict = ["user":userId, key: itemId]
        
        HttpClient(domain: "https://swagoverflow.brobin.me/api/").executeRequest(uri, method: .POST, headers: nil, postData: postDict, success: nil, error: nil)
    }
}
