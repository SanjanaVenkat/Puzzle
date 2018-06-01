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
	BufferedImage[][] scrambledImages;
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
		System.out.println("drew original");
		}
		else {
		  drawnewImages(g);
		}
	}
	public void drawOriginal(Graphics g) {
		g.drawImage(original, 0, 0, null);
	}
	public void drawnewImages(Graphics g) {
		for (int i = 0; i < scrambledImages.length; i++) {
			for (int j = 0; j < scrambledImages[0].length; j++) {
				g.drawImage(scrambledImages[i][j], (original.getWidth() / columns) * j, (original.getHeight() / rows) * i, null );
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
	
	public void setRows(int rows2) {
		rows = rows2;
	}
	
	public void setColumns(int columns2) {
		columns = columns2;
	}
	public void splitImage() {
		newImages = new BufferedImage[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int x = original.getWidth();
				int y = original.getHeight();
				System.out.println("i: " + i);
				System.out.println("j " + j);
				System.out.println("dimension: " + x + "x" + y);
				System.out.println("startx " + (original.getHeight() / rows) * i);
				System.out.println("starty " + (original.getWidth() / columns) * j);
				System.out.println("width " + original.getWidth() / columns);
				System.out.println("height " + original.getHeight() / rows);
				newImages[i][j] = original.getSubimage((original.getWidth() / columns) * j, (original.getHeight() / rows) * i,
						original.getWidth() / columns, original.getHeight() / rows);
			}
		}

	}
	public void scrambleImages() {
		scrambledImages = new BufferedImage[rows][columns];
		System.out.println(newImages.length);
		for (int i = 0; i < scrambledImages.length; i++) {
			for (int j = 0; j < scrambledImages[i].length; j++) {
				int newi = (int)( Math.random() * rows);
				int newj = (int)( Math.random() * columns);
				while (newi == i && newj == j || scrambledImages[newi][newj] != null) {
					//System.out.println("i: " + i + " j: " + j);
					newi = (int)( Math.random() * rows);
					newj = (int)( Math.random() * columns);
				}
				System.out.println(i + '.' + j);
				scrambledImages[newi][newj] = newImages[i][j];
			}
			
		}
		drawImage = true;

	}
}
//
