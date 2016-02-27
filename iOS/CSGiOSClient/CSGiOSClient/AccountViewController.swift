//
//  AccountViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class AccountViewController: UIViewController {

    @IBOutlet weak var emailField: UITextField!
    @IBOutlet weak var phoneNumberField: UITextField!
    
    @IBOutlet weak var methodView: UIView!
    @IBOutlet weak var methodLabel: UILabel!
    
    var userService: UserService!
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        setRightBarButton(nil)
        setNavTitle("Account")
        
        methodView.layer.borderColor = UIColor.blackColor().CGColor
        methodView.layer.borderWidth = 1.0
        methodView.layer.cornerRadius = 10.0
        
        let userService = UserService()
        let activeUser = userService.getActiveUser()
        
        emailField.text = activeUser?.email
        phoneNumberField.text = activeUser?.phone
    }
    
    @IBAction func changeMethodTapped() {
        let alert = UIAlertController(title: "Select Notification Method", message: nil, preferredStyle: .ActionSheet)
        
        for leagueName in ["Email", "Text", "Push Notification"] {
            alert.addAction(UIAlertAction(title: leagueName, style: .Default) { _ in
                dispatch_async(dispatch_get_main_queue()) {
                    self.methodLabel.text = leagueName
                }
            })
        }
        
        presentViewController(alert, animated: true, completion: nil)
    }
    
    @IBAction func updateTapped() {
        let alert = UIAlertController(title: "Success", message: "Your information has been updated successfully!", preferredStyle: .Alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .Default, handler: nil))
        presentViewController(alert, animated: true, completion: nil)
    }
}
