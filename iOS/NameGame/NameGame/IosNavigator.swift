import Foundation
import common
import UIKit


class IosNavigator: NSObject, Navigator {
    
    func goto(screen: Screen) {
        switch screen {
        case Screen.question:
            pushViewController(identifier: "questionScreen")
        case Screen.gameComplete:
            pushViewController(identifier: "gameResultsScreen")
        case Screen.start:
            UIApplication.topViewController()!.navigationController?.popToRootViewController(animated: true)
        case Screen.settings:
            presentModal(identifier: "settingsScreen")
        default:
            Logger().d(message: "Unhandled navigation goto: " + screen.name)
        }
    }
    
    private func pushViewController(identifier: String) {
        if let rootViewController = UIApplication.topViewController() {
            
            let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
            let newViewController = storyBoard.instantiateViewController(withIdentifier: identifier)
            //do sth with root view controller
            let navi = rootViewController.navigationController
            navi?.pushViewController(newViewController, animated: true)
        }
    }

    private func presentModal(identifier: String) {
        if let rootViewController = UIApplication.topViewController() {
            let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
            let modalVC = storyBoard.instantiateViewController(withIdentifier: identifier)
            rootViewController.present(modalVC, animated: true, completion: nil)
        }
    }
}
