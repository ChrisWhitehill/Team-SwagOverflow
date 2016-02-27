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
    
    @IBOutlet weak var loadingThrobberView: UIView!
    
    let sectionTitles = ["Teams", "TV Shows"]
    var userService: UserService!
    var favoriteService: FavoriteService!
    var activeUser: User!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        userService = UserService()
        favoriteService = FavoriteService()
        
        loadingThrobberView.layer.cornerRadius = 10.0
        
        if let user = userService.getActiveUser() {
            activeUser = user
        }
    }
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        loadingThrobberView.hidden = true
        
        let addButton = UIBarButtonItem(barButtonSystemItem: .Add, target: self, action: Selector("addPrefTapped"))
        setRightBarButton(addButton)
        setNavTitle("Favorites")
        reloadPreferences()
    }
    
    func reloadPreferences() {
        loadingThrobberView.hidden = false
        activeUser.teamFavorites = []
        activeUser.showFavorites = []
        userService.getFavoritesForUser(activeUser, success: { dict in
            dispatch_async(dispatch_get_main_queue()) {
                self.activeUser.parseFavorites(dict)
                self.tableView.reloadData()
                self.loadingThrobberView.hidden = true
            }
        }, error: nil)
    }
    
    func addPrefTapped() {
        let controller = storyboard!.instantiateViewControllerWithIdentifier("AddFavoriteViewController") as! AddFavoriteViewController
        controller.activeUser = activeUser
        
        let navController = UINavigationController(rootViewController: controller)
        navController.navigationBar.barTintColor = UIColor.blackColor()
        navController.navigationBar.titleTextAttributes = [NSForegroundColorAttributeName : UIColor.whiteColor()]
        
        presentViewController(navController, animated: true, completion: nil)
    }
    
    func favoriteForIndexPath(indexPath: NSIndexPath) -> Favorite {
        let itemList: [Favorite] = indexPath.section == 0 ? activeUser.teamFavorites : activeUser.showFavorites
        return itemList[indexPath.row]
    }
}

extension PreferencesViewController: UITableViewDataSource, UITableViewDelegate {
    
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
        cell.delegate = self
        return cell
    }
    
    func tableView(tableView: UITableView, willDisplayHeaderView view: UIView, forSection section: Int) {
        let headerView = view as! UITableViewHeaderFooterView
        headerView.contentView.backgroundColor = UIColor(red: 0.38, green: 0.38, blue: 0.38, alpha: 1.0)
        headerView.textLabel?.textColor = UIColor.whiteColor()
    }
}

extension PreferencesViewController: PreferenceTableViewCellDelegate {
    
    func deleteFavorite(id: Int, isTeam: Bool) {
        favoriteService.deleteFavorite(activeUser.id!, isTeam: isTeam, id: id) {
            self.reloadPreferences()
        }
    }
}
