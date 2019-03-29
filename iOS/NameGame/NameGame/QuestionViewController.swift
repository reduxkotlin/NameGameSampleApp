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
    
    @IBAction func onAnswerTap(_ sender: Any) {
        presenter?.namePicked(name: (sender as? UIButton)!.titleLabel!.text!)
    }
    
    @IBAction func onNextTreeTap(_ sender: Any) { presenter?.nextTapped() }
    
    @IBAction func onEndGameTap(_ sender: Any) { presenter?.endGameTapped() }
    
    var presenter: QuestionPresenter?
    var confettiView: SAConfettiView?
    
    override func viewWillAppear(_ animated: Bool) {
        confettiView = SAConfettiView(frame: self.view.bounds)
        self.view.addSubview(confettiView!)
        self.view.sendSubviewToBack(confettiView!)
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.presenterFactory!.attachView(view: self) as? QuestionPresenter
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.presenterFactory?.detachView(presenter: presenter!)
        if (isMovingFromParent) {
            presenter?.onBackPressed()
        }
    }
    
    func showProfile(viewState: QuestionViewState) {
        stopCelegration()
        if (!buttonNext.isHidden) {
            fadeNextButton {
                self.setProfileAndFadeIn(viewState: viewState)
            }
        } else {
            setProfileAndFadeIn(viewState: viewState)
        }
    }
    
    func showCorrectAnswer(viewState: QuestionViewState) {
        hideButtonsShowNext()
        celebrate()
    }
    
    func showWrongAnswer(viewState: QuestionViewState) {
        hideButtonsShowNext()
    }
    
    func showCorrectAnswerEndGame(viewState: QuestionViewState) {
        hideButtonsShowEnd()
        celebrate()
    }
    
    func showWrongAnswerEndGame(viewState: QuestionViewState) {
        hideButtonsShowEnd()
    }
    
    
    private func showButtons() {
        UIView.animate(withDuration: 0.5, animations: {
            self.button1.alpha = 1.0
            self.button2.alpha = 1.0
            self.button3.alpha = 1.0
            self.button4.alpha = 1.0
            self.profileImageView.alpha = 1.0
        })
    }
    
    private func hideButtonsShowNext() {
        UIView.animate(withDuration: 0.5, animations: {
            self.button1.alpha = 0
            self.button2.alpha = 0
            self.button3.alpha = 0
            self.button4.alpha = 0
        }, completion: {_ in
            UIView.animate(withDuration: 0.5, animations: {
                self.buttonNext?.isHidden = false
                self.buttonNext?.alpha = 1
            })
        })
    }
    
    private func hideButtonsShowEnd() {
        UIView.animate(withDuration: 0.5, animations: {
            self.button1.alpha = 0
            self.button2.alpha = 0
            self.button3.alpha = 0
            self.button4.alpha = 0
        }, completion: {_ in
            self.buttonEndGame.isHidden = false
            self.buttonEndGame.alpha = 1
        })
    }
    
    private func fadeNextButton(after: @escaping () -> ()) {
        UIView.animate(withDuration: 0.5, animations: {
            self.buttonNext.alpha = 0
            self.profileImageView.alpha = 0
        }, completion: {_ in
            after()
        })
    }
    
    private func setProfileAndFadeIn(viewState: QuestionViewState) {
        labelQuestion.text = viewState.title
        button1.setTitle(viewState.button1Text, for: .normal)
        button2.setTitle(viewState.button2Text, for: .normal)
        button3.setTitle(viewState.button3Text, for: .normal)
        button4.setTitle(viewState.button4Text, for: .normal)
        profileImageView.downloaded(from: viewState.profileImageUrl)
        showButtons()
    }
    
    private func celebrate() {
        confettiView!.startConfetti()
    }
    
    private func stopCelegration() {
        confettiView!.stopConfetti()
    }
    
    func getData(from url: URL, completion: @escaping (Data?, URLResponse?, Error?) -> ()) {
        URLSession.shared.dataTask(with: url, completionHandler: completion).resume()
    }
}

