import Foundation
import UIKit
import main

class QuestionViewController: UIViewController, QuestionScreen {
    @IBOutlet weak var labelQuestion: UILabel!
    @IBOutlet weak var profileImageView: UIImageView!
    @IBOutlet weak var button1: UIButton!
    @IBOutlet weak var button2: UIButton!
    @IBOutlet weak var button3: UIButton!
    @IBOutlet weak var button4: UIButton!
    @IBOutlet weak var buttonNext: UIButton!
    @IBOutlet weak var buttonEndGame: UIButton!
    
    var presenter: QuestionPresenter?
    
    override func viewDidAppear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.presenterFactory!.attachView(view: self) as? QuestionPresenter
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.presenterFactory?.detachView(presenter: presenter!)
    }
    
    func showProfile(viewState: RoundViewState) {
        
    }
    
    func showCorrectAnswer() {
        
    }
    
    func showWrongAnswer() {
        
    }
    
    func showCorrectAnswerEndGame() {
        
    }
    
    func showWrongAnswerEndGame() {
        
    }
    
    func showEndOfGame() {
        
    }
    
    
    
}
