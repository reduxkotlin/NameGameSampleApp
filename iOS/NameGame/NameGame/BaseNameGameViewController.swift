import Foundation
import UIKit
import common


class BaseNameViewController<T: Presenter>: UIViewController, View {
    var presenter: Any?

    //Needed since Kotlin -> Swift interopt does not support generics
    func getPresenter() -> T? {
        return presenter as! T?
    }
    
    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.gameEngine!.detachView(view: self)
    }


    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.gameEngine!.attachView(view: self)
    }
}
