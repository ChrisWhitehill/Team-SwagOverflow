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
    
    @IBOutlet weak var loadingThrobberView: UIView!
    
    let sectionTitles = ["Games", "Episodes"]
    
    var userService: UserService!
    var activeUser: User!
    
    var games = [Game]()
    var episodes = [Episode]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        userService = UserService()
        activeUser = userService.getActiveUser()
        
        loadingThrobberView.hidden = true
        loadingThrobberView.layer.cornerRadius = 10.0
        reloadEvents()
    }
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        setRightBarButton(nil)
        setNavTitle("Events")
        
        if loadingThrobberView.hidden {
            loadingThrobberView.hidden = false
            reloadEvents()
        }
    }
    
    func reloadEvents() {
        loadingThrobberView.hidden = false
        
        games = []
        episodes = []
        
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
        
        userService.getEventsForUser(activeUser, success: { dict in
            if let gameArr = dict["games"] as? [[String: AnyObject]] {
                for gameDict in gameArr {
                    let game = Game()
                    
                    game.id = gameDict["id"] as? Int
                    game.channelName = gameDict["channel_name"] as? String
                    game.channelNumber = gameDict["channel_number"] as? Int
                    game.date = dateFormatter.dateFromString(gameDict["date"] as! String)
                    
                    game.thumbnailUrl = gameDict["thumbnail_url"] as? String
                    game.videoUrl = gameDict["video_url"] as? String
                    
                    if let teamDict = gameDict["away_team"] as? [String: AnyObject] {
                        let team = Team()
                        
                        team.id = teamDict["id"] as? Int
                        team.name = teamDict["name"] as? String
                        team.league = teamDict["league"] as? String
                        team.logo_url = teamDict["logo_url"] as? String
                        
                        game.awayTeam = team
                    }
                    
                    if let teamDict = gameDict["home_team"] as? [String: AnyObject] {
                        let team = Team()
                        
                        team.id = teamDict["id"] as? Int
                        team.name = teamDict["name"] as? String
                        team.league = teamDict["league"] as? String
                        team.logo_url = teamDict["logo_url"] as? String
                        
                        game.homeTeam = team
                    }
                    
                    self.games.append(game)
                }
            }
            
            if let epArr = dict["episodes"] as? [[String: AnyObject]] {
                for epiDict in epArr {
                    let ep = Episode()
                    
                    ep.id = epiDict["id"] as? Int
                    ep.channelName = epiDict["channel_name"] as? String
                    ep.channelNumber = epiDict["channel_number"] as? Int
                    ep.date = dateFormatter.dateFromString(epiDict["date"] as! String)
                    
                    ep.thumbnailUrl = epiDict["thumbnail_url"] as? String
                    ep.videoUrl = epiDict["video_url"] as? String
                    
                    if let showDict = epiDict["show"] as? [String: AnyObject] {
                        let show = Show()
                        
                        show.id = showDict["id"] as? Int
                        show.name = showDict["name"] as? String
                        show.logo_url = showDict["logo_url"] as? String
                        
                        ep.show = show
                    }
                    
                    self.episodes.append(ep)
                }
            }
            
            dispatch_async(dispatch_get_main_queue()) {
                self.tableView.reloadData()
                self.loadingThrobberView.hidden = true
            }
        }, error: nil)
    }
    
    func getEventForIndexPath(indexPath: NSIndexPath) -> Event {
        let events: [Event] = indexPath.section == 0 ? games : episodes
        return events[indexPath.row]
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
        return section == 0 ? games.count : episodes.count
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
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
        
        let event = getEventForIndexPath(indexPath)
        let url = NSURL(string: event.videoUrl!)!
        
        let player = AVPlayer(URL: url)
        let playerViewController = AVPlayerViewController()
        playerViewController.player = player
        
        self.presentViewController(playerViewController, animated: true) {
            playerViewController.player!.play()
        }
    }
    
    func tableView(tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        let headerView = view as! UITableViewHeaderFooterView
        headerView.contentView.backgroundColor = UIColor(red: 0.38, green: 0.38, blue: 0.38, alpha: 1.0)
        headerView.textLabel?.textColor = UIColor.whiteColor()
    }
}
