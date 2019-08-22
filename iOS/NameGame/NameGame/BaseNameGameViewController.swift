import Foundation
import UIKit
import common


class BaseNameViewController : UIViewController, Presenter_middlewareView {

    
    required init?(coder aDecoder: NSCoder) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.initilize()
        super.init(coder: aDecoder)
    }
    
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewWillAppear(animated)
        PresenterInjectorKt.detachView(view: self)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        PresenterInjectorKt.attachView(view: self)
    }
    
}
