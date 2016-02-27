//
//  LoginViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    var coreDataStack: CoreDataStack!
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        self.performSegueWithIdentifier("loginSegue", sender: self)
    }
}

