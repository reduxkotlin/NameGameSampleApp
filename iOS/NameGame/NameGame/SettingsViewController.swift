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

class SettingsViewController: BaseNameViewController<SettingsPresenter>,
        UIPickerViewDelegate,
        UIPickerViewDataSource,
        SettingsView {

    @IBAction func okButtonTap(_ sender: Any?) {
        let selectedValue = pickerData[numPicker.selectedRow(inComponent: 0)]
        let selectedCategory = QuestionCategoryId.Companion.init().fromOrdinal(ordinal: Int32(categoryPicker.selectedRow(inComponent: 0)))

        getPresenter()?.numQuestionsChanged(numQuestions: Int32(selectedValue))
        getPresenter()?.categoryChanged(categoryId: selectedCategory)
        self.dismiss(animated: true)
    }

    @IBOutlet weak var numPicker: UIPickerView!
    @IBOutlet weak var categoryPicker: UIPickerView!


    let pickerData: [Int] = Array(1...20)
    let categoryData: [String] = QuestionCategoryId.Companion.init().displayNameList

    override func viewDidLoad() {
        super.viewDidLoad()
        numPicker.dataSource = self
        numPicker.delegate = self
        categoryPicker.dataSource = self
        categoryPicker.delegate = self
    }

    func showSettings(viewState: SettingsViewState) {
        numPicker.selectRow((Int(viewState.numQuestions - 1)), inComponent: 0, animated: true)
        categoryPicker.selectRow(Int(QuestionCategoryId.Companion.init().displayNameList.firstIndex(of: viewState.categoryId.displayName)!), inComponent: 0, animated: true)
    }

    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if (pickerView == numPicker) {
            return String(pickerData[row])
        } else {
            return categoryData[row]
        }
    }

    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if (pickerView == numPicker) {
            return pickerData.count
        } else {
            return categoryData.count
        }
    }

    // Number of columns of data
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
}

