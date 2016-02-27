//
//  BaseCDManager.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import CoreData

class BaseCDManager {
    
    var coreDataStack: CoreDataStack!
    var context: NSManagedObjectContext!
    
    init(coreDataStack: CoreDataStack, context: NSManagedObjectContext) {
        self.coreDataStack = coreDataStack
        self.context = context
    }
    
    func save() {
        coreDataStack.saveContext(context)
    }
}
