//
//  PreferenceDetailViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class PreferenceDetailViewController: UIViewController {
    
    var item: Item!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationItem.title = item.name
        
        let backItem = UIBarButtonItem()
        backItem.title = nil
        navigationItem.backBarButtonItem = backItem
    }
}
