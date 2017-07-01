package tanishoot;

import java.awt.*;
import javax.swing.*;

class Paint2 extends JPanel {
	private static int t=0;
	
	private static final long serialVersionUID = 1L;

	public Paint2(){
		setPreferredSize(new Dimension(100, 50));
		this.setBackground(Color.white);
		
	}
	
    public void paintComponent(Graphics g) { //ペイントのコンポーネント
    	super.paintComponent(g);
    	this.getGraphics();
    	g.setColor(Color.red);
    	g.drawString("評価："+Mouse.score_s, 0, 100);
    	t=t+Mouse.score;
    	Mouse.score=0;
    	Mouse.score_s="F";
    	
    }
    
	public static String average(){
		int c, d;
		c=t/10;
		d=t%10;
		return c+"."+d;
	}
	
	
	
	
}