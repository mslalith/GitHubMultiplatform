//
//  ComposeView.swift
//  iosApp
//
//  Created by @msLalith on 25/06/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct ComposeView : UIViewControllerRepresentable {
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
    
    func makeUIViewController(context: Context) -> some UIViewController {
        ComposeInteropViewControllerKt.interop()
    }
}
