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
    
    override func viewDidAppear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.presenterFactory!.attachView(view: self) as? QuestionPresenter
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.presenterFactory?.detachView(presenter: presenter!)
    }
    
    func showProfile(viewState: QuestionViewState) {
        if (buttonNext.isHidden) {
            fadeNextButton {
                self.setProfileAndFadeIn(viewState: viewState)
            }
        } else {
            setProfileAndFadeIn(viewState: viewState)
        }
    }
    
    func showCorrectAnswer() {
        hideButtonsShowNext()
        celebrate()
    }
    
    func showWrongAnswer() {
        hideButtonsShowNext()
    }
    
    func showCorrectAnswerEndGame() {
        hideButtonsShowEnd()
        celebrate()
    }
    
    func showWrongAnswerEndGame() {
        hideButtonsShowEnd()
    }
    
    
    private func showButtons() {
        UIView.animate(withDuration: 0.5, animations: {
            self.button1.alpha = 1.0
            self.button2.alpha = 1.0
            self.button3.alpha = 1.0
            self.button4.alpha = 1.0
        })
    }
    
    private func hideButtonsShowNext() {
        UIView.animate(withDuration: 0.5, animations: {
            self.button1.alpha = 1.0
            self.button2.alpha = 1.0
            self.button3.alpha = 1.0
            self.button4.alpha = 1.0
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
        }, completion: {_ in
            after()
        })
    }
    
    private func setProfileAndFadeIn(viewState: QuestionViewState) {
        button1.setTitle(viewState.button1Text, for: .normal)
        button2.setTitle(viewState.button2Text, for: .normal)
        button3.setTitle(viewState.button3Text, for: .normal)
        button4.setTitle(viewState.button4Text, for: .normal)
        loadImage(url: URL(string: viewState.profileImageUrl)!)
        showButtons()
    }
    
    private func celebrate() {
        
    }
    
    func loadImage(url: URL) {
        let session = URLSession(configuration: .default)
        
        let downloadPicTask = session.dataTask(with: url) { (data, response, error) in
            // The download has finished.
            if let e = error {
                print("Error downloading profile picture: \(e)")
            } else {
                // No errors found.
                // It would be weird if we didn't have a response, so check for that too.
                if let res = response as? HTTPURLResponse {
                    print("Downloaded profile picture with response code \(res.statusCode)")
                    if let imageData = data {
                        DispatchQueue.main.async {
                            // Finally convert that Data into an image and do what you wish with it.
                            let image = UIImage(data: imageData)
                            self.profileImageView.image = image
                        }
                        // Do something with your image.
                    } else {
                        print("Couldn't get image: Image is nil")
                    }
                } else {
                    print("Couldn't get response code for some reason")
                }
            }
        }
        downloadPicTask.resume()
    }
    
}
