//
//  SettingsViewController.swift
//  NameGame
//
//  Created by Patrick Jackson on 4/8/19.
//  Copyright © 2019 Willowtree. All rights reserved.
//

import Foundation
import UIKit
import common

class SettingsViewController: UIViewController,
        UIPickerViewDelegate,
        UIPickerViewDataSource,
        SettingsView {

    @IBAction func okButtonTap(_ sender: Any?) {
        var selectedValue = pickerData[numPicker.selectedRow(inComponent: 0)]

        presenter?.numQuestionsChanged(numQuestions: Int32(selectedValue))
        self.dismiss(animated: true)
    }

    @IBOutlet weak var numPicker: UIPickerView!

    var presenter: SettingsPresenter?

    let pickerData: [Int] = Array(1...20)

    override func viewDidLoad() {
        super.viewDidLoad()
        numPicker.dataSource = self
        numPicker.delegate = self
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        presenter = appDelegate.gameEngine?.attachView(view: self) as! SettingsPresenter?
    }

    override func viewDidDisappear(_ animated: Bool) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        appDelegate.gameEngine?.detachView(view: self)
    }

    func showSettings(viewState: SettingsViewState) {
        numPicker.selectRow((Int(viewState.numQuestions - 1)) , inComponent:0, animated:true)
    }

    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return String(pickerData[row])
    }

    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return pickerData.count
    }

    // Number of columns of data
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }

}
