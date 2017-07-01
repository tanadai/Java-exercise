package tanishoot;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	static Window frame;
    CanvasB s;
    Paint2 s2;
	Timer timer;
    
    public static void main(String[] args) {
    	frame = new Window("単位シュート");   	  	
        frame.setVisible(true);
    }
    
    Window(String title){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	setTitle(title);
    	getContentPane().setPreferredSize(new Dimension(1000, 700));
        pack();
    	setVisible(true);
    	JOptionPane.showMessageDialog(null, "START?", "単位シュート", JOptionPane.INFORMATION_MESSAGE);
        setVisible(true);
		Timer timer = new Timer();
		
		timer.schedule(new Time(timer, 11), 1000,1000);
    }

	class Time extends TimerTask {
		private final Timer timer;
		private int num;
		private int count = 0;
		Time(Timer timer, int num){
			this.timer=timer;
			this.num=num;
		}
		 
	    public void run() {
	    	if(count!=0){
	    		getContentPane().remove(s);
	    		getContentPane().remove(s2);
	    	}	    	
	    	
	    	s=new CanvasB();
	    	s2=new Paint2();
	        getContentPane().add(s, BorderLayout.LINE_START);
	        getContentPane().add(s2, BorderLayout.LINE_END);
	        setVisible(true);
	        
	        
	        
	        synchronized(this){
	        	if (++count == num) {
	        		JOptionPane.showMessageDialog(null, "GPA:"+Paint2.average(), "Finish!!!", JOptionPane.INFORMATION_MESSAGE);
	        		this.cancel();
	        		timer.cancel();
	        		System.exit(0);
	            }
	        }
	    }      
	}
}