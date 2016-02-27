//
//  UserService.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import Foundation

class UserService: NSObject {
    
    func getActiveUser() -> User? {
        let user = User()
        
        user.id = 1
        user.name = "Jake Sanchez"
        user.phone = "8597979941"
        user.email = "jacob.sanchez116@gmail.com"
        
        return user
    }
}