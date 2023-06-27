import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        KoinKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
                .onOpenURL { url in
                    DeepLinkRegistry.shared.handle(deepLink: url.absoluteString)
                }
		}
	}
}
