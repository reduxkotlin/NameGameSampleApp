import Foundation
import UIKit
import common


class BaseNameViewController : UIViewController, View {
    required init?(coder aDecoder: NSCoder) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.initilize()
        super.init(coder: aDecoder)
    }
    
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        PresenterInjecterKt.rootDispatch(DetachView(view: self))
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        PresenterInjecterKt.rootDispatch(AttachView(view: self))
    }
    
}
