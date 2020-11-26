package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageReader {
    Image image;
    ImageView imageView;
    final String filePath;
    public ImageReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        image = new Image(new FileInputStream(filePath));
        imageView = new ImageView(image);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
