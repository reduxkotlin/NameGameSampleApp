import Foundation
import main
import UIKit


class IosNavigator: NSObject, Navigator {
    
    func goto(screen: Screen) {
        if let rootViewController = UIApplication.topViewController() {

            let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
            let newViewController = storyBoard.instantiateViewController(withIdentifier: "questionScreen") as! QuestionViewController
            //do sth with root view controller
            let navi = rootViewController.navigationController
            navi?.pushViewController(newViewController, animated: true)
        }
    }
}
