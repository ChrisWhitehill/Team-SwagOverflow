//
//  TabBarController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class TabBarController: UITabBarController {
    
    @IBAction func logOutTapped() {
        self.navigationController?.dismissViewControllerAnimated(true, completion: nil)
    }
}
