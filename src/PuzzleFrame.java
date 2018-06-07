//Grace Hunter and Sanjana Venkat
//June 1, 2018
//Java puzzle project, scrambles and image for user to put back together as puzzle, tells them when they have correctly solved it
//Puzzle Frame Class: splits image and handles swapping, draws all images
//imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PuzzleFrame extends JPanel {
	//variables
	int rows;
	int columns;
	BufferedImage original;
	BufferedImage[][] newImages;
	BufferedImage[][] scrambledImages;
	final Color highlight = new Color(153, 50, 204, 150);
	int[] highlighted = new int[2];
	boolean doHighlight = false;
	int widthImage;
	int heightImage;
	boolean drawImage;
	boolean won = false;
	int move = 0;
	//starts with kitten image
	public PuzzleFrame() {
		super();
		try {
			original = ImageIO.read(new File("PuzzleBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//paints original frame
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.PINK);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (drawImage == false) {
		drawOriginal(g);
	

		}
		//drawn new image
		else {
		  drawnewImages(g);
		}
	}
	//draws original kittens
	public void drawOriginal(Graphics g) {
		g.drawImage(original, 0, 0, null);
	}
	//draws puzzle of scrambled images
	public void drawnewImages(Graphics g) {

		for (int i = 0; i < scrambledImages.length; i++) {
			for (int j = 0; j < scrambledImages[0].length; j++) {
				g.drawImage(scrambledImages[i][j], (original.getWidth() / columns) * j, (original.getHeight() / rows) * i, null );
			}
		}
		//System.out.println(highlighted[0] + "," + highlighted[1]);
		if(doHighlight) {
			//System.out.println("highlighting!");
			g.setColor(highlight);
			g.fillRect(highlighted[1] * (widthImage / columns), highlighted[0] * (heightImage / rows), widthImage / columns, heightImage / rows);
		}
	}
// option to change image
	public void changeImage(String filePath) {
		try {
			original = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			try {
				original = ImageIO.read(new File("kittens.jpg"));
			}
			catch (IOException ex) {
				e.printStackTrace();
			}
		}
		widthImage = original.getWidth();
		heightImage = original.getHeight();
	}
	public void clearHighlight() {
		doHighlight = false;
	}
	public void highlight(int column, int row) {
		highlighted[0] = row;
		highlighted[1] = column;
		doHighlight = true;
	}
	//getter for image height
	public int getImageHeight() {
		return heightImage;
	}
	//getter for image width
	public int getImageWidth() {
		return widthImage;
	}
	//rows and columns set up
	public void setRows(int rows2) {
		rows = rows2;
	}
	public int getRows() {
		return rows;
	}
	public void setColumns(int columns2) {
		columns = columns2;
	}
	public int getColumns() {
		return columns;
	}
	//splits up image based on height, width, rows, and columns
	public void splitImage() {
		newImages = new BufferedImage[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int x = original.getWidth();
				int y = original.getHeight();
				//System.out.println("i: " + i);
				//System.out.println("j " + j);
				//System.out.println("dimension: " + x + "x" + y);
				//System.out.println("startx " + (original.getHeight() / rows) * i);
				//System.out.println("starty " + (original.getWidth() / columns) * j);
				//System.out.println("width " + original.getWidth() / columns);
				//System.out.println("height " + original.getHeight() / rows);
				newImages[i][j] = original.getSubimage((original.getWidth() / columns) * j, (original.getHeight() / rows) * i,
						original.getWidth() / columns, original.getHeight() / rows);
			}
		}
	}
	//randomly scrambles split up images and paints image onto frame
	public void scrambleImages() {
		scrambledImages = new BufferedImage[rows][columns];
		//System.out.println(newImages.length);
		for (int i = 0; i < scrambledImages.length; i++) {
			for (int j = 0; j < scrambledImages[i].length; j++) {
				int newi = (int)( Math.random() * rows);
				int newj = (int)( Math.random() * columns);
				while (newi == i && newj == j || scrambledImages[newi][newj] != null) {
					//System.out.println("i: " + i + " j: " + j);
					newi = (int)( Math.random() * rows);
					newj = (int)( Math.random() * columns);
				}
				//System.out.println(i + '.' + j);
				scrambledImages[newi][newj] = newImages[i][j];
			}
		
		}
		drawImage = true;

	}
//checks if scrambled array matches original image
	public boolean checkWin() {
	for (int i = 0; i < scrambledImages.length; i++) {
		for (int j = 0; j < scrambledImages[i].length; j++) {
			if (scrambledImages[i][j] != newImages[i][j]) {
				return false;
			}
		}
	}
	
	return true;
	}
	
	
//swaps images
	public void swap(int firstx, int firsty, int secondx, int secondy) {
		BufferedImage firstImage = scrambledImages[firsty][firstx];
		scrambledImages[firsty][firstx] = scrambledImages[secondy][secondx];
		scrambledImages[secondy][secondx] = firstImage;
		move ++;
	}
}
//
