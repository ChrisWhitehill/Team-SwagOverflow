//
//  PreferenceDetailViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class PreferenceDetailViewController: UIViewController {
    
    var favorite: Favorite!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationItem.title = favorite.item!.name
    }
}
