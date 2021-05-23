package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {
@Test
    public void ImageWriter(){
    ImageWriter imageWriter =new ImageWriter("imageWriterTest",800,500);
    for(int i=0;i<imageWriter.getNx();++i)//We go through all the pixels
        for (int j=0;j<imageWriter.getNy();++j){

            if(i%50==0||j%50==0)//If the pixel on the grid is painted a different color
                imageWriter.writePixel(i,j,new Color(java.awt.Color.black));
            else//If the pixel not on the grid is painted a different color
                imageWriter.writePixel(i,j,new Color(java.awt.Color.green));
        }
    imageWriter.writeToImage();

}
}