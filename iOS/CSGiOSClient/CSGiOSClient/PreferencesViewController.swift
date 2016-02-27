//
//  PreferencesViewController.swift
//  CSGiOSClient
//
//  Created by Jacob Sanchez on 2/27/16.
//  Copyright © 2016 jacob.sanchez. All rights reserved.
//

import UIKit

class PreferencesViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    
    let sectionTitles = ["Teams", "TV Shows"]
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        let addButton = UIBarButtonItem(barButtonSystemItem: .Add, target: self, action: Selector("addPrefTapped"))
        setRightBarButton(addButton)
        setNavTitle("My Preferences")
    }
    
    func addPrefTapped() {
        print("add")
    }
}

extension PreferencesViewController: UITableViewDataSource {
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return sectionTitles.count
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return sectionTitles[section]
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("CellID") as! PreferenceTableViewCell
        
        if indexPath.section == 0 {
            let team = Team()
            team.name = "Arizona Coyotes"
            team.logo_url = "https://upload.wikimedia.org/wikipedia/en/thumb/2/27/Arizona_Coyotes.svg/200px-Arizona_Coyotes.svg.png"
            
            cell.displayForTeam(team)
        } else {
            let show = Show()
            show.name = "Criminal Minds"
            show.logo_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Criminal-Minds.svg/250px-Criminal-Minds.svg.png"
            
            cell.displayForShow(show)
        }
        
        return cell
    }
}
