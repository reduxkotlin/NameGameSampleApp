import Foundation
import UIKit
import common

class StartViewController: BaseNameViewController, StartView {

    func presenter() -> (Presenter_middlewareView, Kotlinx_coroutines_coreCoroutineScope) -> (LibStore) -> () -> KotlinUnit {
        return StartViewKt.startPresenter
    }

    @IBOutlet weak var labelError: UILabel!
    @IBOutlet weak var progressView: UIProgressView!

    @IBAction func viewTapped(_ sender: Any) {
        dispatch(UiActions.StartGameTapped())
        progressView.isHidden = false
        progressView.setProgress(0, animated: false)
        UIView.animate(withDuration: 3) {
            self.progressView.setProgress(1.0, animated: true)
        }
    }
    
    @IBAction func settingsTapped(_ sender: Any) {
        dispatch(UiActions.SettingsTapped())
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        progressView.progress = 0;
        progressView.isHidden = true
        navigationController?.setNavigationBarHidden(true, animated: false)
    }
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        progressView.progress = 0;
        progressView.isHidden = true
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
