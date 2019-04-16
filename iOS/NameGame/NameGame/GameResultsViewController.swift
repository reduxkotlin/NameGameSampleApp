import Foundation
import common
import UIKit

class GameResultsViewController: BaseNameViewController<GameResultsPresenter>, GameResultsView {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var msgLabel: UILabel!

    @IBAction func viewTapped(_ sender: Any) {
        getPresenter()?.startOverTapped()
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
