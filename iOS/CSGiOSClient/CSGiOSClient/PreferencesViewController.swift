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
    var userService: UserService!
    var activeUser: User!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        userService = UserService()
        
        if let user = userService.getActiveUser() {
            activeUser = user
        }
    }
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
        let addButton = UIBarButtonItem(barButtonSystemItem: .Add, target: self, action: Selector("addPrefTapped"))
        setRightBarButton(addButton)
        setNavTitle("Favorites")
        
        userService.getFavoritesForUser(activeUser, success: { dict in
            dispatch_async(dispatch_get_main_queue()) {
                self.activeUser.parseFavorites(dict)
                self.tableView.reloadData()
            }
        }, error: nil)
    }
    
    func addPrefTapped() {
        let controller = storyboard!.instantiateViewControllerWithIdentifier("AddFavoriteViewController") as! AddFavoriteViewController
        controller.activeUser = activeUser
        
        let navController = UINavigationController(rootViewController: controller)
        presentViewController(navController, animated: true, completion: nil)
    }
    
    func favoriteForIndexPath(indexPath: NSIndexPath) -> Favorite {
        let itemList: [Favorite] = indexPath.section == 0 ? activeUser.teamFavorites : activeUser.showFavorites
        return itemList[indexPath.row]
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
        return section == 0 ? activeUser.teamFavorites.count : activeUser.showFavorites.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("CellID") as! PreferenceTableViewCell
        cell.displayForFavorite(favoriteForIndexPath(indexPath))
        return cell
    }
}

extension PreferencesViewController: UITableViewDelegate {
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let controller = storyboard!.instantiateViewControllerWithIdentifier("PreferenceDetailViewController") as! PreferenceDetailViewController
        controller.favorite = favoriteForIndexPath(indexPath)
        navigationController?.pushViewController(controller, animated: true)
    }
}
