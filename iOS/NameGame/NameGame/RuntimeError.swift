//
//  RuntimeError.swift
//  NameGame
//
//  Created by Patrick Jackson on 3/20/19.
//  Copyright © 2019 Willowtree. All rights reserved.
//

import Foundation

struct RuntimeError: Error {
    let message: String
    
    init(_ message: String) {
        self.message = message
    }
    
    public var localizedDescription: String {
        return message
    }
}
