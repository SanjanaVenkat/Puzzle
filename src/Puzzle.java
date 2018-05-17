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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Puzzle implements ActionListener, MouseListener{

	JFrame frame = new JFrame();
	JLabel rowlb = new JLabel("Rows:");
	JTextField rowtf = new JTextField(" ");
	JLabel columnlb = new JLabel("Columns:");
	JTextField columntf = new JTextField(" ");
	JLabel filepathlb = new JLabel("File Name:");
	JTextField filepathtf = new JTextField(" ");
	JButton makePuzzle = new JButton("Make Puzzle");
	Container south = new Container();
	int tallyCount;
	
	public Puzzle() {
	frame.setSize(1000, 1000);
	frame.setLayout(new BorderLayout());
	south.setLayout(new GridLayout(1,7));
	south.add(filepathlb);
	south.add(filepathtf);
	south.add(rowlb);
	south.add(rowtf);
	south.add(columnlb);
	south.add(columntf);
	south.add(makePuzzle);
	makePuzzle.addActionListener(this);
	frame.addMouseListener(this);
	frame.add(south, BorderLayout.SOUTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);	

	
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Puzzle();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + "," + e.getY());
	}

}