//
//  ItemService.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import Foundation

class ItemService: NSObject {

    func getAllTeams(success: (([String: AnyObject])->())?, error: ((NSError?, String)->())?) {
        HttpClient(domain: "https://swagoverflow.brobin.me/api/").executeRequest("team/", method: .GET, headers: nil, postData: nil, success: { string in
            if let dict = parseDictionaryFromJSON(string) {
                success?(dict)
            } else {
                error?(nil, "could not parse response data")
            }
        }, error: error)
    }
    
    func getAllShows(success: (([String: AnyObject])->())?, error: ((NSError?, String)->())?) {
        HttpClient(domain: "https://swagoverflow.brobin.me/api/").executeRequest("show/", method: .GET, headers: nil, postData: nil, success: { string in
            if let dict = parseDictionaryFromJSON(string) {
                success?(dict)
            } else {
                error?(nil, "could not parse response data")
            }
        }, error: error)
    }
}