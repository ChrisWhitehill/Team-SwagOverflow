//
//  AddFavoriteViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class AddFavoriteViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationItem.title = "Add Favorite"
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(barButtonSystemItem: .Stop, target: self, action: Selector("stopTapped"))
    }
    
    func stopTapped() {
        navigationController?.dismissViewControllerAnimated(true, completion: nil)
    }
}
