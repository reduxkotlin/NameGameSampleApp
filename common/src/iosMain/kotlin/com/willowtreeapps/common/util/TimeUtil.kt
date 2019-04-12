package com.willowtreeapps.common.util

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual object TimeUtil {
    actual fun systemTimeMs(): Long = NSDate().timeIntervalSince1970.toLong() * 1000
}