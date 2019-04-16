//
//  NameGameUITests.swift
//  NameGameUITests
//
//  Created by Patrick Jackson on 4/16/19.
//  Copyright © 2019 Willowtree. All rights reserved.
//

import Foundation
import XCTest

class NameGameUITests: XCTestCase {
    var app: XCUIApplication!

    override func setUp() {
        super.setUp()
        app = XCUIApplication()
        app.launch()
    }
    
    func test1() {
        app.buttons["Start"].tap()
    }
}
