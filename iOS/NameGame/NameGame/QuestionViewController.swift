import Foundation
import UIKit
import main


class QuestionViewController: UIViewController, QuestionView {
    @IBOutlet weak var labelQuestion: UILabel!
    @IBOutlet weak var profileImageView: UIImageView!
    @IBOutlet weak var button1: UIButton!
    @IBOutlet weak var button2: UIButton!
    @IBOutlet weak var button3: UIButton!
    @IBOutlet weak var button4: UIButton!
    @IBOutlet weak var buttonNext: UIButton!
    @IBOutlet weak var buttonEndGame: UIButton!
    @IBOutlet weak var labelTimer: UILabel!

    @IBAction func onAnswerTap(_ sender: Any) {
        presenter?.namePicked(name: (sender as? UIButton)!.titleLabel!.text!)
    }

    @IBAction func onNextTreeTap(_ sender: Any) {
        presenter?.nextTapped()
    }

    @IBAction func onEndGameTap(_ sender: Any) {
        presenter?.endGameTapped()
    }

    var presenter: QuestionPresenter?
    var confettiView: SAConfettiView?
    var restoreX: CGFloat?
    var restoreY: CGFloat?
    var lastCorrectBtn: UIButton?
    var lastSelectedBtn: UIButton?
    var lastSelectedColor: UIColor?


    override func viewWillAppear(_ animated: Bool) {
        confettiView = SAConfettiView(frame: self.view.bounds)
        self.view.addSubview(confettiView!)
        confettiView?.isUserInteractionEnabled = false
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.gameEngine!.attachView(view: self) as? QuestionPresenter
    }

    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.gameEngine!.detachView(view: self)
        if (isMovingFromParent) {
            presenter?.onBackPressed()
        }
    }

    func showProfile(viewState: QuestionViewState) {
        stopCelebration()
        if (!buttonNext.isHidden) {
            fadeNextButton {
                self.setProfileAndFadeIn(viewState: viewState)
            }
        } else {
            setProfileAndFadeIn(viewState: viewState)
        }
    }

    func showCorrectAnswer(viewState: QuestionViewState, isEndGame: Bool) {
        hideButtonsShowNext(viewState: viewState, isEndGame: isEndGame)
        celebrate()
    }

    func showWrongAnswer(viewState: QuestionViewState, isEndGame: Bool) {
        wrongShakeAnimation(viewState: viewState, after: { self.hideButtonsShowNext(viewState: viewState, isEndGame: isEndGame) })
    }

    private func wrongShakeAnimation(viewState: QuestionViewState, after: @escaping () -> ()) {
        let selectedBtn = getBtnByNum(num: viewState.selectedBtnNum)
        if (selectedBtn != nil) {
            lastSelectedColor = selectedBtn!.tintColor
            selectedBtn!.tintColor = UIColor.red
            selectedBtn!.transform = CGAffineTransform(translationX: 20, y: 0)
            UIView.animate(withDuration: 0.4, delay: 0, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: .curveEaseInOut, animations: {
                selectedBtn!.transform = CGAffineTransform.identity
            }, completion: { _ in after() })
        } else {
            after()
        }
    }

    /**
     *  Hides the incorrect buttons and animates the correct name to be centered below profile image
     */
    private func hideButtonsShowNext(viewState: QuestionViewState, isEndGame: Bool) {
        let correctBtn = getBtnByNum(num: viewState.correctBtnNum)
        let selectedBtn = getBtnByNum(num: viewState.selectedBtnNum)

        func hideOrMoveAnimation(view: UIView) {
            if (view == correctBtn) {
                view.transform = CGAffineTransform(scaleX: 1.5, y: 1.5)

                let p = profileImageView.convert(profileImageView.bounds, to: nil)

                let endX = p.minX + (profileImageView.frame.width - view.frame.size.width) / 2
                let endY = p.minY + profileImageView.bounds.height

                view.frame.origin.x = endX
                view.frame.origin.y = endY
            } else {
                view.alpha = 0
            }
        }

        restoreX = correctBtn?.frame.origin.x
        restoreY = correctBtn?.frame.origin.y
        lastCorrectBtn = correctBtn
        lastSelectedBtn = selectedBtn

        UIView.animate(withDuration: 0.5, delay: 0, options: .curveEaseInOut, animations: {
            hideOrMoveAnimation(view: self.button1)
            hideOrMoveAnimation(view: self.button2)
            hideOrMoveAnimation(view: self.button3)
            hideOrMoveAnimation(view: self.button4)
        }, completion: { _ in
            var btn: UIButton
            if (isEndGame) {
                btn = self.buttonEndGame
            } else {
                btn = self.buttonNext
            }
            btn.isHidden = false
            btn.alpha = 0
            UIView.animate(withDuration: 0.5, animations: {
                btn.alpha = 1
            })
        })
    }

    private func showButtons() {
        if (restoreX != nil && restoreY != nil) {
            lastCorrectBtn?.alpha = 0
            lastCorrectBtn?.frame.origin.x = restoreX!
            lastCorrectBtn?.frame.origin.y = restoreY!
            lastSelectedBtn?.tintColor = lastSelectedColor
            lastCorrectBtn?.transform = CGAffineTransform(scaleX: 1, y: 1)
        }
        UIView.animate(withDuration: 0.5, animations: {
            self.button1.alpha = 1.0
            self.button2.alpha = 1.0
            self.button3.alpha = 1.0
            self.button4.alpha = 1.0
            self.profileImageView.alpha = 1.0
        })
    }

    private func fadeNextButton(after: @escaping () -> ()) {
        self.lastCorrectBtn!.alpha = 0
        UIView.animate(withDuration: 0.5, animations: {
            self.buttonNext.alpha = 0
            self.profileImageView.alpha = 0
        }, completion: { _ in
            self.buttonNext.isHidden = true
            after()
        })
    }

    private func setProfileAndFadeIn(viewState: QuestionViewState) {
        labelQuestion.text = viewState.title
        button1.setTitle(viewState.button1Text, for: .normal)
        button2.setTitle(viewState.button2Text, for: .normal)
        button3.setTitle(viewState.button3Text, for: .normal)
        button4.setTitle(viewState.button4Text, for: .normal)
        profileImageView.downloaded(from: viewState.profileImageUrl, onComplete: { self.showButtons() })
    }

    func setTimerText(viewState: QuestionViewState) {

        labelTimer.layer.anchorPoint = CGPoint(x: 0.5, y: 0.5);

        self.labelTimer.transform = CGAffineTransform(scaleX: 0, y: 0)
        self.labelTimer.alpha = 1
        self.labelTimer.text = String(viewState.questionTime)
        UIView.animate(withDuration: 0.5, animations: {
            self.labelTimer.transform = CGAffineTransform(scaleX: 1, y: 1)
        }, completion: { _ in
            UIView.animate(withDuration: 0.5, animations: {
                self.labelTimer.transform = CGAffineTransform(scaleX: 0.01, y: 0.01)
            })
        })
    }

    func showTimesUp(viewState: QuestionViewState, isEndGame: Bool) {
        labelTimer.text = "TIMES UP!!"
        let restoreColor = labelTimer.textColor
        labelTimer.textColor = UIColor.red
        UIView.animate(withDuration: 0.5, animations: {
            self.labelTimer.transform = CGAffineTransform(scaleX: 1, y: 1)
        }, completion: { _ in
            UIView.animate(withDuration: 0.3, animations: {
                self.showWrongAnswer(viewState: viewState, isEndGame: isEndGame)
                self.labelTimer.alpha = 0
            }, completion: { _ in
                self.labelTimer.textColor = restoreColor
            })
        })
    }

    private func celebrate() {
        confettiView!.startConfetti()
    }

    private func stopCelebration() {
        confettiView!.stopConfetti()
    }

    func getBtnByNum(num: Int32) -> UIButton? {
        switch num {
        case 1:
            return button1
        case 2:
            return button2
        case 3:
            return button3
        case 4:
            return button4
        default:
            return nil
        }
    }
}

