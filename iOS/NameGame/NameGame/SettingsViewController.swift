import Foundation
import UIKit
import common
import Speech

class SettingsViewController: BaseNameViewController,
        UIPickerViewDelegate,
        UIPickerViewDataSource,
SettingsView {
    func presenter() -> (View, Kotlinx_coroutines_coreCoroutineScope) -> (LibStore) -> () -> KotlinUnit {
        return SettingsViewKt.settingsPresenter
    }
    

    @IBAction func okButtonTap(_ sender: Any?) {
        let selectedValue = pickerData[numPicker.selectedRow(inComponent: 0)]
        let selectedCategory = QuestionCategoryId.Companion.init().fromOrdinal(ordinal: Int32(categoryPicker.selectedRow(inComponent: 0)))
        dispatch(UiActions.CategoryPicked(categoryId: selectedCategory))
        dispatch(UiActions.NumQuestionsPicked(numQuestions: Int32(selectedValue)))
        self.dismiss(animated: true)
    }

    @IBOutlet weak var numPicker: UIPickerView!
    @IBOutlet weak var categoryPicker: UIPickerView!

    @IBAction func voiceModeSwitched(_ sender: UISwitch) {
        dispatch(UiActions.MicrophoneModeToggled(enabled: sender.isOn))
    }
    
    let pickerData: [Int] = Array(1...20)
    let categoryData: [String] = QuestionCategoryId.Companion.init().displayNameListWithoutWT

    override func viewDidLoad() {
        super.viewDidLoad()
        numPicker.dataSource = self
        numPicker.delegate = self
        categoryPicker.dataSource = self
        categoryPicker.delegate = self
    }

    func showSettings(viewState: SettingsViewState) {
        numPicker.selectRow((Int(viewState.numQuestions - 1)), inComponent: 0, animated: true)
        categoryPicker.selectRow(Int(QuestionCategoryId.Companion.init().displayNameListWithoutWT.firstIndex(of: viewState.categoryId.displayName)!), inComponent: 0, animated: true)
    }
    
    func askForMicPermissions() {
        authorizeSR()
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
    
    private func authorizeSR() {
        SFSpeechRecognizer.requestAuthorization { authStatus in
            OperationQueue.main.addOperation {
                switch authStatus {
                case .authorized:
                    dispatch(UiActions.MicrophoneModeToggled(enabled: true))
                case .denied:
                    dispatch(UiActions.MicrophoneModeToggled(enabled: false))
                case .restricted:
                    dispatch(UiActions.MicrophoneModeToggled(enabled: false))
                case .notDetermined:
                    dispatch(UiActions.MicrophoneModeToggled(enabled: false))

                }
            }
        }
    }
}

