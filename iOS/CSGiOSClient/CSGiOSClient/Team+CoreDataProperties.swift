//
//  Team+CoreDataProperties.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//
//  Choose "Create NSManagedObject Subclass…" from the Core Data editor menu
//  to delete and recreate this implementation file for your updated model.
//

import Foundation
import CoreData

extension Team {

    @NSManaged var id: NSNumber?
    @NSManaged var name: String?
    @NSManaged var logo_url: String?
    @NSManaged var league: String?

}
