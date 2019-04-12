import Foundation
import UIKit
import common

class StartViewController: UIViewController, StartView {

    @IBOutlet weak var labelError: UILabel!
    @IBOutlet weak var progressView: UIProgressView!
    var presenter: StartPresenter?
    
    @IBAction func viewTapped(_ sender: Any) {
        presenter?.startGame()
        progressView.setProgress(0, animated: false)
        UIView.animate(withDuration: 3) {
            self.progressView.setProgress(1.0, animated: true)
        }
        
    }
    @IBAction func settingsTapped(_ sender: Any) {
        presenter?.settingsTapped()
    }

    override func viewDidAppear(_ animated: Bool) {
        self.progressView.progress = 0;
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.gameEngine?.attachView(view: self) as? StartPresenter
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.gameEngine?.detachView(view: self)
    }
    
    func showLoading() {
        progressView.isHidden = false
    }
    
    func hideLoading() {
        progressView.isHidden = true
    }

    func showError(msg: String) {
        labelError.text = msg
    }
    
    
}
