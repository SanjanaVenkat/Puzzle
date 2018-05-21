import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PuzzleFrame extends JPanel {
	Image original;
	
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
		drawOriginal(g);
	}
	public void drawOriginal(Graphics g) {
		g.drawImage(original, 0, 0, null);
	}
	public void changeImage(String filePath) {
		try {
			original = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
