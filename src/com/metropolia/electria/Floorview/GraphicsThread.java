package com.metropolia.electria.Floorview;

import com.metropolia.electria.MainView.PanelSetting;

/**
 * Thread class for painting all the floor in certain time interval
 * Output: updates all the floors data with time interval and handles threads
 */

public class GraphicsThread extends Thread {
    
    private boolean running = true;

    public GraphicsThread(){
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(200);
                FirstfloorView.getInstance().update1stscreen();
                SecondfloorView.getInstance().update2ndscreen();
                ThirdfloorView.getInstance().update3rdscreen();               
                //System.out.println("Graphics updated "+Thread.activeCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void finish(){
        running = false;
    }
}
