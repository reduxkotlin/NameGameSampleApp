import Foundation
import UIKit
import common

class StartViewController: BaseNameViewController<StartPresenter>, StartView {

    @IBOutlet weak var labelError: UILabel!
    @IBOutlet weak var progressView: UIProgressView!

    @IBAction func viewTapped(_ sender: Any) {
        getPresenter()?.startGame()
        progressView.setProgress(0, animated: false)
        UIView.animate(withDuration: 3) {
            self.progressView.setProgress(1.0, animated: true)
        }
        
    }
    @IBAction func settingsTapped(_ sender: Any) {
        getPresenter()?.settingsTapped()
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        self.progressView.progress = 0;
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
