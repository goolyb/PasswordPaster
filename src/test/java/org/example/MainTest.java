package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class MainTest {
    @Test
    public void testPasswordsFileExists() {
        File file = new File("passwords.txt");
        assertTrue(file.exists(), "passwords.txt should exist in the project root");
    }

    @Test
    public void testReadPasswords() throws Exception {
        File file = new File("passwords.txt");
        if (file.exists()) {
            List<String> lines = Files.readAllLines(file.toPath());
            assertNotNull(lines);
            // Verify it's not empty if we expect content
            assertFalse(lines.isEmpty(), "passwords.txt should not be empty");
        }
    }
}
