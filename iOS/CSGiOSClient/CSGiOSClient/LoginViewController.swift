//
//  LoginViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    var appeared = false
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        if appeared {
            return
        }
        
        appeared = true
        
        if let _ = UserService().getActiveUser() {
            self.performSegueWithIdentifier("loginSegue", sender: self)
        }
    }
}

