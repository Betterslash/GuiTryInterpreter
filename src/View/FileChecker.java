package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileChecker {
    final String username;
    final String password;

    public FileChecker(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    public boolean check() throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("D:\\FirstApplication\\src\\View\\fileBase.cs"));
        String read = fileReader.readLine();
        while (read != null) {
            String[] checker = read.split(",");
            System.out.println(Arrays.toString(checker));
            if (username.equals(checker[1]) && password.equals(checker[2])) {
                fileReader.close();
                return true;
            }
            read = fileReader.readLine();
        }
        return false;
    }
}
