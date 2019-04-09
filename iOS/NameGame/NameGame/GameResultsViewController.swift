import Foundation
import common
import UIKit

class GameResultsViewController: UIViewController, GameResultsView {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var msgLabel: UILabel!

    var presenter: GameResultsPresenter?

    @IBAction func viewTapped(_ sender: Any) {
        presenter?.startOverTapped()
    }

    override func viewWillAppear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.gameEngine!.attachView(view: self) as? GameResultsPresenter
    }

    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.gameEngine?.detachView(view: self)
    }

    func showResults(viewState: GameResultsViewState) {
        titleLabel.text = viewState.resultsText
        msgLabel.text = viewState.messageText
    }
}
