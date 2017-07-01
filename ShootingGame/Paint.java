package tanishoot;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

class CanvasB extends JPanel {
	private static final long serialVersionUID = 1L;
	final static int RADIUS1=20;
	final static int RADIUS2=40;
	final static int RADIUS3=60;
	final static int RADIUS4=80;
	public static int x=0;
	public static int y=0;
	public static int x2=0;
	public static int y2=0;


	public CanvasB(){ 
		setPreferredSize(new Dimension(800, 700));
		this.setBackground(Color.white);
    	Mouse mouse = new Mouse();
    	addMouseListener(mouse);
    	Random rand= new Random();
    	x=rand.nextInt(640)+80;
    	y=rand.nextInt(540)+80;

	}
	
    public void paintComponent(Graphics g) { //ペイントのコンポーネント
    	super.paintComponent(g);
    	this.getGraphics();
    	g.setColor(Color.blue);
    	g.fillOval(x-RADIUS4, y-RADIUS4, RADIUS4 * 2, RADIUS4 * 2);
            
    	g.setColor(Color.yellow);
    	g.fillOval(x - RADIUS3, y - RADIUS3, RADIUS3 * 2, RADIUS3 * 2);
    	
    	g.setColor(Color.green);
    	g.fillOval(x - RADIUS2, y - RADIUS2, RADIUS2 * 2, RADIUS2 * 2);
    	
    	g.setColor(Color.red);
    	g.fillOval(x - RADIUS1, y - RADIUS1, RADIUS1 * 2, RADIUS1 * 2); 
    
    }
 
    

}