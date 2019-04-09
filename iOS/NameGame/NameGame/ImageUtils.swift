//
//  ImageUtils.swift
//  NameGame
//
//  Created by Patrick Jackson on 3/21/19.
//  Copyright © 2019 Willowtree. All rights reserved.
//

import Foundation
import UIKit
import common

public extension UIImageView {
    func downloaded(from url: URL, contentMode mode: UIView.ContentMode = .scaleAspectFit, onComplete: @escaping () -> ()) {  // for swift 4.2 syntax just use ===> mode: UIView.ContentMode
        contentMode = mode
        URLSession.shared.dataTask(with: url) { data, response, error in
            guard
                    let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                    let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                    let data = data, error == nil,
                    let image = UIImage(data: data)
                    else {
                Logger().d(message: "error fetching image " + url.absoluteString + " " + error!.localizedDescription)
                return
            }
            DispatchQueue.main.async() {
                self.image = image
                onComplete()
            }
        }.resume()
    }

    func downloaded(from link: String, contentMode mode: UIView.ContentMode = .scaleAspectFit, onComplete: @escaping () -> ()) {  // for swift 4.2 syntax just use ===> mode: UIView.ContentMode
        guard let url = URL(string: link) else {
            return
        }
        downloaded(from: url, contentMode: mode, onComplete: onComplete)
    }
}
