package tanishoot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Window extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	static Window frame;
//	static CanvasB canvas;
    JPanel p;
    JPanel s;
    JButton btn;
	Timer timer;
	public static int t=0;
    
    
    public static void main(String[] args) {
    	frame = new Window("単位シュート");   	  	
//    	canvas = new CanvasB();

        frame.setVisible(true);
    }
    
    Window(String title){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	setTitle(title);
    	setBounds(100, 100, 800, 800);
    	p = new JPanel();
    	btn = new JButton("Start!!");
    	btn.addActionListener(this);
        p.add(btn);
        this.add(p);
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		p.setVisible(false);
		p.remove(btn);
		this.remove(p);
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
	    	s=new CanvasB();
	        getContentPane().add(s);
	        setVisible(true);
	        t=t+Mouse.score;
	        synchronized(this){
	        	if (++count == num) {
	        		JOptionPane.showMessageDialog(null, "GPA:"+average(), "Finish!!!", JOptionPane.INFORMATION_MESSAGE);
	        		this.cancel();
	        		timer.cancel();
	        		System.exit(0);
	        	}
	        }
	    }      
	}
	 
	public String average(){
		int c, d;
		c=t/10;
		d=t%10;
		return c+"."+d;
	}
}
