import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PuzzleFrame extends JPanel {
	int rows;
	int columns;
	BufferedImage original;
	BufferedImage[][] newImages;
	int widthImage;
	int heightImage;
	boolean drawImage;
	
	
	public PuzzleFrame() {
		super();
		try {
			original = ImageIO.read(new File("greencar2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.PINK);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (drawImage == false) {
		drawOriginal(g);
		}
		else if (drawImage == true) {
		 drawnewImages(g);
		}
	}
	public void drawOriginal(Graphics g) {
		g.drawImage(original, 0, 0, null);
	}
	public void drawnewImages(Graphics g) {
		for (int i = 0; i < newImages.length; i++) {
			for (int j = 0; j < newImages.length; j++) {
				g.drawImage(newImages[i][j], (original.getWidth() / columns) * i, (original.getHeight() / rows) * j, null );
			}
		}
	}
	public void changeImage(String filePath) {
		try {
			original = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		widthImage = original.getWidth();
		heightImage = original.getHeight();
	}
	
	//getter for image height
	public int getImageHeight() {
		return heightImage;
	}
	//getter for image width
	public int getImageWidth() {
		return widthImage;
	}
	
	public void splitImage() {
		newImages = new BufferedImage[rows][columns];
		for (int i = 0; i < newImages.length; i++) {
			for (int j = 0; j < newImages.length; j++) {
				newImages[i][j] = original.getSubimage((original.getWidth() / columns) * i, (original.getHeight() / rows) * j,
						original.getWidth() / columns, original.getHeight() / rows);
			}
		}
	drawImage = true;
	}
}
//
