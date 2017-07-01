package tanishoot;

import java.awt.Point;
import java.awt.event.*;
import javax.swing.JPanel;

class Mouse extends JPanel implements MouseListener{
	public static int score = 0;
	public static String score_s;
	private static final long serialVersionUID = 1L;
	public static int count=0;
	public static int sum=0;
	
	public void mouseEntered(MouseEvent e) { }
    
    public void mouseExited(MouseEvent e) { }
    
    public void mousePressed(MouseEvent e) { }
    
    public void mouseReleased(MouseEvent e) {
    	Point point = e.getPoint();
    	int a=(int) (Math.pow((point.x-CanvasB.x),2)+Math.pow((point.y-CanvasB.y),2));


    	if(a<Math.pow((CanvasB.RADIUS1),2)){
    		score=5;
    		score_s="A+";
    	}else if(a<Math.pow((CanvasB.RADIUS2),2)){
    		score=4;
    		score_s="A";
    	}else if(a<Math.pow((CanvasB.RADIUS3),2)){
    		score=3;
    		score_s="B";
    	}else if(a<Math.pow((CanvasB.RADIUS4),2)){
    		score=2;
    		score_s="C";
    	}else{
    		score=0;
    		score_s="F";
    	}
    	
    }
    
    public void mouseClicked(MouseEvent e) {

    }
}