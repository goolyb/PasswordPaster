package org.example;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();

        System.out.println("Starting in 5 seconds. Please switch to your target application (e.g., Telegram)...");
        Thread.sleep(5000);
        robot.delay(500);

        File passwordsFile = new File("passwords.txt");
        if (!passwordsFile.exists()) {
            System.err.println("Error: passwords.txt not found!");
            return;
        }

        List<String> words = Files.readAllLines(passwordsFile.toPath());

        for (String word : words) {
            if (word.trim().isEmpty()) continue;
            
            System.out.println("Processing: " + word);
            
            StringSelection selection = new StringSelection(word);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            robot.delay(100);

            RobotFunc();

            robot.delay(500);

            System.out.println("  Sending Enter via Robot...");
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            
            robot.delay(1000);
        }
        System.out.println("Finished processing all words.");
    }
    public static void RobotFunc() throws Exception {
        Robot robot = new Robot();
        String os = System.getProperty("os.name").toLowerCase();
        int ctrl = KeyEvent.VK_META;

        if (os.contains("mac")){
            ctrl = KeyEvent.VK_META;
        } else {
            ctrl = KeyEvent.VK_CONTROL;
        }

        robot.keyPress(ctrl);
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(50);
        robot.keyRelease(ctrl);
    }
}
