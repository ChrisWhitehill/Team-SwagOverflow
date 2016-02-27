//
//  User+CoreDataProperties.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import Foundation

class User: NSObject {

    var id: Int?
    var name: String?
    var email: String?
    var phone: String?
    
    var teamFavorites = [Favorite]()
    var showFavorites = [Favorite]()
    
    func parseFavorites(dict: [String: AnyObject]) {
        if let teamFavArr = dict["teams"] as? [[String: AnyObject]] {
            for teamFavDict in teamFavArr {
                let teamFavorite = Favorite()
                
                teamFavorite.id = teamFavDict["id"] as? Int
                teamFavorite.user = self
                teamFavorite.notifs_enabled = teamFavDict["notifications"] as? Bool
                
                if let teamDict = teamFavDict["team"] as? [String: AnyObject] {
                    let team = Team()
                    
                    team.id = teamDict["id"] as? Int
                    team.name = teamDict["name"] as? String
                    team.league = teamDict["league"] as? String
                    team.logo_url = teamDict["logo_url"] as? String
                    
                    teamFavorite.item = team
                }
                
                teamFavorites.append(teamFavorite)
            }
        }
        
        if let showFavArr = dict["shows"] as? [[String: AnyObject]] {
            for showFavDict in showFavArr {
                let showFavorite = Favorite()
                
                showFavorite.id = showFavDict["id"] as? Int
                showFavorite.user = self
                showFavorite.notifs_enabled = showFavDict["notifications"] as? Bool
                
                if let showDict = showFavDict["show"] as? [String: AnyObject] {
                    let show = Show()
                    
                    show.id = showDict["id"] as? Int
                    show.name = showDict["name"] as? String
                    show.logo_url = showDict["logo_url"] as? String
                    
                    showFavorite.item = show
                }
                
                showFavorites.append(showFavorite)
            }
        }
    }

}
