//
//  AddFavoriteViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class AddFavoriteViewController: UIViewController {

    @IBOutlet weak var segmentedControl: UISegmentedControl!
    @IBOutlet weak var showSearchField: UITextField!
    
    @IBOutlet weak var leagueView: UIView!
    @IBOutlet weak var leagueLabel: UILabel!
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var addFavoriteButton: UIButton!
    
    var allTeams = [Team]()
    var allShows = [Show]()
    
    var itemsToDisplay = [Item]()
    
    var activeUser: User!
    var itemService: ItemService!
    
    var selectedItem: NSIndexPath?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.navigationItem.title = "Add Favorite"
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(barButtonSystemItem: .Stop, target: self, action: Selector("stopTapped"))
        
        leagueView.layer.borderColor = UIColor.blackColor().CGColor
        leagueView.layer.borderWidth = 1.0
        leagueView.layer.cornerRadius = 10.0
        
        leagueLabel.text = "Select League"
        
        itemService = ItemService()
        itemService.getAllTeams({ dict in
            if let teamArr = dict["teams"] as? [[String: AnyObject]] {
                for teamDict in teamArr {
                    let team = Team()
                    
                    team.id = teamDict["id"] as? Int
                    team.name = teamDict["name"] as? String
                    team.league = teamDict["league"] as? String
                    team.logo_url = teamDict["logo_url"] as? String
                    
                    let exists = self.activeUser.teamFavorites.filter({ f in f.item!.id == team.id }).count > 0
                    
                    if !exists {
                        self.allTeams.append(team)
                    }
                }
            }
            
            dispatch_async(dispatch_get_main_queue()) {
                self.itemsToDisplay = self.allTeams
                self.tableView.reloadData()
            }
            
        }, error: nil)
        
        itemService.getAllShows({ dict in
            if let showArr = dict["shows"] as? [[String: AnyObject]] {
                for showDict in showArr {
                    let show = Show()
                    
                    show.id = showDict["id"] as? Int
                    show.name = showDict["name"] as? String
                    show.logo_url = showDict["logo_url"] as? String
                    
                    let exists = self.activeUser.showFavorites.filter({ f in f.item!.id == show.id }).count > 0
                    
                    if !exists {
                        self.allShows.append(show)
                    }
                }
            }
        }, error: nil)
        
        addFavoriteButton.enabled = false
        
        showSearchField.addTarget(self, action: "textFieldDidChange:", forControlEvents: UIControlEvents.EditingChanged)
    }
    
    @IBAction func indexChanged(sender: UISegmentedControl) {
        showSearchField.hidden = segmentedControl.selectedSegmentIndex == 0
        leagueView.hidden = segmentedControl.selectedSegmentIndex != 0
        
        selectedItem = nil
        addFavoriteButton.enabled = false
        itemsToDisplay = segmentedControl.selectedSegmentIndex == 0 ? allTeams : allShows
        tableView.reloadData()
    }
    
    func stopTapped() {
        navigationController?.dismissViewControllerAnimated(true, completion: nil)
    }
    
    @IBAction func changeLeagueTapped() {
        let alert = UIAlertController(title: "Select League", message: nil, preferredStyle: .ActionSheet)
        
        for leagueName in ["MLB", "NBA", "NFL", "NHL"] {
            alert.addAction(UIAlertAction(title: leagueName, style: .Default) { _ in
                dispatch_async(dispatch_get_main_queue()) {
                    self.leagueLabel.text = leagueName
                    self.selectedItem = nil
                    self.addFavoriteButton.enabled = false
                    
                    self.itemsToDisplay = self.allTeams.filter { item in
                        return item.league == leagueName
                    }
                    
                    self.tableView.reloadData()
                }
            })
        }
        
        presentViewController(alert, animated: true, completion: nil)
    }
    
    @IBAction func addFavoriteTapped() {
        FavoriteService().postFavorite(activeUser.id!, itemId: itemsToDisplay[selectedItem!.row].id!, isTeam: segmentedControl.selectedSegmentIndex == 0)
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    func textFieldDidChange(sender: AnyObject) {
        if showSearchField.text! == "" {
            itemsToDisplay = allShows
        } else {
            itemsToDisplay = allShows.filter({ show in
                return show.name!.containsString(showSearchField.text!)
            })
        }
        
        tableView.reloadData()
    }
}

extension AddFavoriteViewController: UITableViewDataSource {
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return itemsToDisplay.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("CellID") as! PreferenceTableViewCell
        
        let item = itemsToDisplay[indexPath.row]
        cell.nameLabel.text = item.name
        
        if selectedItem != nil && indexPath == selectedItem {
            cell.accessoryType = .Checkmark
        } else {
            cell.accessoryType = .None
        }
        
        if let logo = item.logo_url, let url  = NSURL(string: logo), data = NSData(contentsOfURL: url) {
            cell.img.image = UIImage(data: data)
        }
        
        return cell
    }
}

extension AddFavoriteViewController: UITableViewDelegate {
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        selectedItem = indexPath
        tableView.reloadData()
        addFavoriteButton.enabled = true
    }
}
