import Foundation
import UIKit
import main

class StartScreenViewController: UIViewController, StartScreen {
    
    @IBOutlet weak var viewName: UIProgressView!
    var presenter: StartPresenter?
    
    @IBAction func viewTapped(_ sender: Any) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter?.startGame()
    }

    override func viewDidAppear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.presenterFactory!.attachView(view: self) as? StartPresenter
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.presenterFactory?.detachView(presenter: presenter!)
    }
    
    func showLoading() {
        viewName.isHidden = false
    }
    
    func hideLoading() {
        viewName.isHidden = true
    }
    
    
}
