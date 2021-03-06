//Authors: Grace Hunter and Sanjana Venkat
//Date: June 1, 2018
//Java puzzle project, scrambles and image for user to put back together as puzzle, tells them when they have correctly solved it

//imports
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Puzzle implements ActionListener, MouseListener{
//frame set up and variables
	JFrame frame = new JFrame();
	PuzzleFrame puzzleFrame = new PuzzleFrame();
	JLabel rowlb = new JLabel("Rows:");
	JTextField rowtf = new JTextField("");
	JLabel columnlb = new JLabel("Columns:");
	JTextField columntf = new JTextField("");
	JLabel filepathlb = new JLabel("File Name:");
	JTextField filepathtf = new JTextField("");
	JButton makePuzzle = new JButton("Make Puzzle");
	Container south = new Container();
	int tallyCount;
	Image original; 
	int firstx;
	int firsty;
	//ints for states of playing
	final int NOT_PLAYING = 0; //game has not started
	final int FIRST = 1; //waiting for first image to be clicked
	final int SECOND = 2; //waiting for second image to be clicked
	int state = NOT_PLAYING;
	
	//puzzle class constructor
	public Puzzle() {
		//add frame elements to frame
		frame.setSize(960, 720);
		frame.setLayout(new BorderLayout());
		south.setLayout(new GridLayout(1,7));
		south.add(filepathlb);
		south.add(filepathtf);
		south.add(rowlb);
		south.add(rowtf);
		south.add(columnlb);
		south.add(columntf);
		south.add(makePuzzle);
		frame.add(puzzleFrame, BorderLayout.CENTER);
		makePuzzle.addActionListener(this);
		frame.addMouseListener(this);
		frame.add(south, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
		
		//starts with kitten image on screen
		try {
			original = ImageIO.read(new File("PuzzleBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Puzzle();
	}
// when make puzzle is clicked
	// reads from filepath for image
	//reads in rows and columns to divide image
	//splits and scrambles images, repaints frame
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(makePuzzle)) {
			puzzleFrame.move = 0;
			//inserts kittens and 3X3 if text fields are blank
			if (filepathtf.getText().length() == 0) {
				filepathtf.setText("kittens.jpg");
			}
			String filePath = filepathtf.getText();
			//System.out.println(filePath);
			puzzleFrame.changeImage(filePath);
			if (rowtf.getText().length() == 0) {
				rowtf.setText("3");
			}
			puzzleFrame.setRows(Integer.parseInt(rowtf.getText()));
			if (columntf.getText().length() == 0) {
				columntf.setText("3");
			}
			puzzleFrame.setColumns(Integer.parseInt(columntf.getText()));
			frame.setSize(puzzleFrame.getImageWidth(), puzzleFrame.getImageHeight() + 60);
			puzzleFrame.splitImage();
			//System.out.println("split images has been run. new images is this long: " + puzzleFrame.newImages.length);
			puzzleFrame.scrambleImages();
			//System.out.println("Scramble has been run");
			state = FIRST;
			frame.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	//swaps two images based on their location when the mouse has been released
	public void mouseReleased(MouseEvent e) {
		//System.out.println(e.getX() + "," + e.getY());
		int x = e.getX();
		int y = e.getY();
		//won't run if click is outside range of image
		if(y <= puzzleFrame.getImageHeight() && x <= puzzleFrame.getImageWidth()) {
			int widthSq = puzzleFrame.getImageWidth() / puzzleFrame.getColumns();
			int heightSq = puzzleFrame.getImageHeight() / puzzleFrame.getRows();
			if(state == FIRST) {
				//calculate indexes and store as first x and first y
				firstx = x / widthSq;
				firsty = y / heightSq;
				//System.out.println("firstx,y: " + firstx + "," + firsty);
				//highlight
				puzzleFrame.highlight(firstx, firsty);
				frame.repaint();
				state = SECOND;
			}
			else if(state == SECOND) {
				//calculate indexes of second image and swap.
				int secondx = x / widthSq;
				int secondy = y / heightSq;
				puzzleFrame.swap(firstx, firsty, secondx, secondy);
				puzzleFrame.clearHighlight();
				frame.repaint();
				puzzleFrame.checkWin();
				 if (puzzleFrame.checkWin() == true) {
					 JOptionPane.showMessageDialog(frame, "Congratulations! You have solved the puzzle! " + "Number of moves: " + puzzleFrame.move);
				 }
				state = FIRST;
			}
		}
	}
}
