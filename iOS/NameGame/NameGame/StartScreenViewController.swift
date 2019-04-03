import Foundation
import UIKit
import main

class StartViewController: UIViewController, StartView {
    
    @IBOutlet weak var viewName: UIProgressView!
    var presenter: StartPresenter?
    
    @IBAction func viewTapped(_ sender: Any) {
        presenter?.startGame()
    }

    override func viewDidAppear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.gameEngine?.attachView(view: self) as? StartPresenter
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.gameEngine?.detachView(presenter: presenter!)
    }
    
    func showLoading() {
        viewName.isHidden = false
    }
    
    func hideLoading() {
        viewName.isHidden = true
    }
    
    
}
