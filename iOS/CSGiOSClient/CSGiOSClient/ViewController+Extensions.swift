//
//  ViewController+Extensions.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

extension UIViewController {
    
    func setNavTitle(title: String) {
        self.tabBarController?.navigationItem.title = title
    }
    
    func setRightBarButton(button: UIBarButtonItem?) {
        self.tabBarController?.navigationItem.rightBarButtonItem = button
    }
}
