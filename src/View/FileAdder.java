package View;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class FileAdder {
    String filePath;
    BufferedWriter bufferedWriter;
    final String newUsername;
    final String newPassword;
    public FileAdder(String newUsername,String newPassword) throws IOException {
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.filePath = "D:\\FirstApplication\\src\\sample\\fileBase.cs";
        bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        Random random = new Random();
        String representation = "";
        representation += "\n"+random.nextInt(100) +","+ this.newUsername+","+this.newPassword;
        bufferedWriter.write(representation);
        bufferedWriter.close();
    }
}
