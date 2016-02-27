//
//  EventsViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit
import AVKit
import MediaPlayer

class EventsViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    
    let sectionTitles = ["Games", "Episodes"]
    
    var games = [Event]()
    var episodes = [Event]()
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        setRightBarButton(nil)
        setNavTitle("Events")
    }
    
    func getEventForIndexPath(indexPath: NSIndexPath) -> Event {
        // let events = indexPath.section == 0 ? games : episodes
        let event = Game()
        
        event.channelName = "ESPN 2"
        event.date = NSDate()
        event.thumbnailUrl = "https://bloximages.chicago2.vip.townnews.com/journalstar.com/content/tncms/assets/v3/editorial/a/16/a16b74c9-354b-5501-a86b-4665352346ca/563ed5aac990d.image.jpg?resize=100%2C100"
        event.videoUrl = "https://0.s3.envato.com/h264-video-previews/80fad324-9db4-11e3-bf3d-0050569255a8/490527.mp4"
        
        let hTeam = Team()
        hTeam.name = "Nebraska"
        event.homeTeam = hTeam
        
        let aTeam = Team()
        aTeam.name = "Missouri"
        event.awayTeam = aTeam
        
        return event
        // return events[indexPath.row]
    }
}

extension EventsViewController: UITableViewDataSource {
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return sectionTitles.count
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return sectionTitles[section]
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // return section == 0 ? games.count : episodes.count
        return 3
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("CellID") as! EventTableViewCell
        let event = getEventForIndexPath(indexPath)
        cell.displayEvent(event)
        return cell
    }
}

extension EventsViewController: UITableViewDelegate {
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let event = getEventForIndexPath(indexPath)
        let url = NSURL(string: event.videoUrl!)!
        
        let player = AVPlayer(URL: url)
        let playerViewController = AVPlayerViewController()
        playerViewController.player = player
        
        self.presentViewController(playerViewController, animated: true) {
            playerViewController.player!.play()
        }
    }
}
