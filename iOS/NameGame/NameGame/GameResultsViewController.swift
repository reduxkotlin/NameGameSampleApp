//
//  GameResultsViewController.swift
//  NameGame
//
//  Created by Patrick Jackson on 3/20/19.
//  Copyright © 2019 Willowtree. All rights reserved.
//

import Foundation
import main
import UIKit

class GameResultsViewController: UIViewController, GameResultsScreen {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var msgLabel: UILabel!
    
      var presenter: GameResultsPresenter?
    
    @IBAction func viewTapped(_ sender: Any){
        presenter?.startOverTapped()
    }

    
    override func viewWillAppear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.presenterFactory!.attachView(view: self) as? GameResultsPresenter
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.presenterFactory?.detachView(presenter: presenter!)
    }
    
    func showResults(viewState: GameResultsViewState) {
        titleLabel.text = viewState.resultsText
        msgLabel.text = viewState.messageText
    }
}
