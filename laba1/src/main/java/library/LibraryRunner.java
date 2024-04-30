package library;

import library.windows.MainWindow;

public class LibraryRunner {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        MainWindow window = new MainWindow();
        window.openMainWindow();
    }
}
