import Foundation
import UIKit
import common

class StartViewController: BaseNameViewController, StartView {
    func presenter() -> (View, Kotlinx_coroutines_coreCoroutineScope) -> (LibStore) -> () -> KotlinUnit {
        return StartPresenterKt.startPresenter
    }
    

    @IBOutlet weak var labelError: UILabel!
    @IBOutlet weak var progressView: UIProgressView!

    @IBAction func viewTapped(_ sender: Any) {
        dispatch(UiActions.StartGameTapped())
        progressView.setProgress(0, animated: false)
        UIView.animate(withDuration: 3) {
            self.progressView.setProgress(1.0, animated: true)
        }
        
    }
    @IBAction func settingsTapped(_ sender: Any) {
        dispatch(UiActions.SettingsTapped())
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
