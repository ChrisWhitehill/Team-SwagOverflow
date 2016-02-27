//
//  UserService.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import Foundation

class UserService: NSObject {
    
    func getActiveUser() -> User? { // mocked
        let user = User()
        
        user.id = 1
        user.name = "Jake Sanchez"
        user.phone = "8597979941"
        user.email = "jacob.sanchez116@gmail.com"
        
        return user
    }
    
    func getFavoritesForUser(user: User, success: (([String: AnyObject])->())?,
        error: ((NSError?, String)->())?) {
        guard let id = user.id else {
            return
        }
        
        HttpClient(domain: "https://swagoverflow.brobin.me/api/").executeRequest("user/\(id)/favorites", method: .GET, headers: nil, postData: nil, success: { string in
            if let dict = parseDictionaryFromJSON(string) {
                success?(dict)
            } else {
                error?(nil, "could not parse response data")
            }
        }, error: error)
    }
    
    func getEventsForUser(user: User, success: (([String: AnyObject])->())?,
        error: ((NSError?, String)->())?) {
        guard let id = user.id else {
            return
        }
        
        HttpClient(domain: "https://swagoverflow.brobin.me/api/").executeRequest("user/\(id)/events",
            method: .GET, headers: nil, postData: nil, success: { string in
            if let dict = parseDictionaryFromJSON(string) {
                success?(dict)
            } else {
                error?(nil, "could not parse response data")
            }
        }, error: error)
    }
}