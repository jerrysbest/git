package erp.ws.sbo.client.swing.biz.impl;

import java.util.Timer;
import java.util.TimerTask;

import erp.ws.sbo.client.swing.view.Foot.FootView;
public class WfTimerTask {
	private static final Timer timer = new Timer(true);
    private  final int minutes;
    private FootView foot;
    
    public WfTimerTask(FootView foot,int minutes) {
       this.minutes = minutes;
       this.foot=foot;
    }
    
    public void start() {
        timer.schedule(new TimerTask() {
            public void run() {
            	   recive();          
             }
          
        }, minutes*1000*5,1000*5);
     } 
    private void recive()
    {
    	System.out.println("短信工作流正在运行");
        foot.setText("短信工作流正在运行"); 
    }
}
