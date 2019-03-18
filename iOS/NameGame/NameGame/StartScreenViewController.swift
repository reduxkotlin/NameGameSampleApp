import Foundation
import UIKit
import main

class StartScreenViewController: UIViewController, StartScreen {
    
    @IBOutlet weak var viewName: UIProgressView!
    @IBAction func viewTapped(_ sender: Any) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.presenter?.startGame()
    }

    override func viewDidAppear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.presenter?.attachView(view: self)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.presenter?.detachView()
    }
    
    func showLoading() {
        viewName.isHidden = false
    }
    
    func hideLoading() {
        viewName.isHidden = true
    }
    
    
}
