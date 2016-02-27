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
    
    var team: Team?
    var show: Show?
    
    @IBAction func deleteTapped() {
        print("delete")
    }
    
    func displayForTeam(team: Team) {
        self.team = team
        nameLabel.text = team.name
        
        if let logo = team.logo_url, let url  = NSURL(string: logo), data = NSData(contentsOfURL: url) {
            img.image = UIImage(data: data)
        }
    }
    
    func displayForShow(show: Show) {
        self.show = show
        nameLabel.text = show.name
        
        if let logo = show.logo_url, let url  = NSURL(string: logo), data = NSData(contentsOfURL: url) {
            img.image = UIImage(data: data)
        }
    }
}
