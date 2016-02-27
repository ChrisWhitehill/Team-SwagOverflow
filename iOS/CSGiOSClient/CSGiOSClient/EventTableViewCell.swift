//
//  EventTableViewCell.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class EventTableViewCell: UITableViewCell {

    @IBOutlet weak var img: UIImageView!
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var channelLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    
    var event: Event?
    
    func displayEvent(event: Event) {
        self.event = event
        
        channelLabel.text = event.channelName
        
        let formatter = NSDateFormatter()
        formatter.dateStyle = NSDateFormatterStyle.LongStyle
        formatter.timeStyle = .NoStyle
        
        dateLabel.text = formatter.stringFromDate(event.date!)
        
        if let game = event as? Game {
            nameLabel.text = "\(game.homeTeam!.name!) vs. \(game.awayTeam!.name!)"
        }
        
        if let episode = event as? Episode {
            nameLabel.text = episode.show.name
        }
        
        if let url  = NSURL(string: event.thumbnailUrl!), data = NSData(contentsOfURL: url) {
            img.image = UIImage(data: data)
        }
    }
}
