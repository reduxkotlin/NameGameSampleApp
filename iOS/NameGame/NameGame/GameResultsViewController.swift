import Foundation
import common
import UIKit

class GameResultsViewController: BaseNameViewController<GameResultsPresenter>, GameResultsView {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var msgLabel: UILabel!

    @IBAction func viewTapped(_ sender: Any) {
        getPresenter()?.startOverTapped()
    }

    func showResults(viewState: GameResultsViewState) {
        titleLabel.text = viewState.resultsText
        msgLabel.text = viewState.messageText
    }
}
