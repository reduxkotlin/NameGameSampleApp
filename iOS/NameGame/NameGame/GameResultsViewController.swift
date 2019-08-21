import Foundation
import common
import UIKit

class GameResultsViewController: BaseNameViewController, GameResultsView {
    
    func presenter() -> (Presenter_middlewareView, Kotlinx_coroutines_coreCoroutineScope) -> (LibStore) -> () -> KotlinUnit {
        return GameResultsViewKt.gameResultsPresenter
    }
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var msgLabel: UILabel!

    @IBAction func viewTapped(_ sender: Any) {
        dispatch(UiActions.StartOverTapped())
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationItem.hidesBackButton = true
    }

    func showResults(viewState: GameResultsViewState) {
        titleLabel.text = viewState.resultsText
        msgLabel.text = viewState.messageText
    }
}
