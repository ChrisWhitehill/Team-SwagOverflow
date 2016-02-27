//
//  PreferenceTableViewCell.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class PreferenceTableViewCell: UITableViewCell {
    
    @IBOutlet weak var img: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    
    var favorite: Favorite?
    
    @IBAction func deleteTapped() {
        print("delete")
    }
    
    func displayForFavorite(favorite: Favorite) {
        self.favorite = favorite
        let item = favorite.item!
        nameLabel.text = item.name
        
        if let logo = item.logo_url, let url  = NSURL(string: logo), data = NSData(contentsOfURL: url) {
            img.image = UIImage(data: data)
        }
    }
}
