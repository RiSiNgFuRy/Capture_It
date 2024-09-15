import SwiftUI

@main
struct iOSApp: App {
	var body: some View {
        MainViewControllerWrapper().ignoresSafeArea(.all)
    }
}

struct MainViewControllerWrapper: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}