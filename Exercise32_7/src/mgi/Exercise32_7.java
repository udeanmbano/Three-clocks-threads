/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mgi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
/*
 * Student: UDEAN MBANO
 * STUDENT NUMBER- MGI2012-3244
 * Chapter 32 Multithreading and Parallel Programming
 * exercise 32.7 Page 1197
 * 
 * Rewrite Exercise 18.14 using a thread to control clock animation.
 * Dependencies:StillClock.java
 */
public class Exercise32_7 extends JApplet  {
   
    // Declare the variables as private for the program
    private ClockControl myclock, myclock2, myclock3;
    private JButton resumeallButton, suspendallButton;
   
       
    public static void main(String[] args){
        JFrame groupofclocks = new JFrame("Exercise 32.7");
        Exercise32_7 applet = new Exercise32_7();
        groupofclocks.add(applet, BorderLayout.CENTER);
        applet.init();
        applet.start();
        groupofclocks.setSize(800,400);
        groupofclocks.setResizable(false);
        groupofclocks.setLocationRelativeTo(null);
        groupofclocks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        groupofclocks.setVisible(true);
        
        
    }
    /** To intialise the applet ,invoked after applet is created**/
    
    @Override
    public void init() {
       
        //creating Threads one for each clock
        
        myclock = new ClockControl();
        myclock2 = new ClockControl();
        myclock3 = new ClockControl();
        
        Clock firstcount = new Clock();
        Thread thread1 = new Thread(firstcount);
        Clock secondcount = new Clock();
        Thread thread2 = new Thread(secondcount);
        Clock thirdcount = new Clock();
        Thread thread3 = new Thread(thirdcount);
     
        //staring 3 threads
      
        thread1.start();
        thread2.start();
        thread3.start();
        
        JPanel clockpanel = new JPanel();
        clockpanel.setLayout(new GridLayout(1,3));
        clockpanel.add(myclock = new ClockControl());
        clockpanel.add(myclock2 = new ClockControl());
        clockpanel.add(myclock3 = new ClockControl()); 
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());
        buttonpanel.add(resumeallButton = new JButton("Resume All"));
        buttonpanel.add(suspendallButton =new JButton("Suspend All"));
        
        setLayout (new BorderLayout());
        add(clockpanel, BorderLayout.CENTER);
        add(buttonpanel, BorderLayout.SOUTH);
        
        
        
        
        resumeallButton.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent arg0) {
       
            myclock.resume();
            myclock2.resume();
            myclock3.resume();
    }
        });
        
        suspendallButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
               
              myclock.suspend();
              myclock2.suspend();
              myclock3.suspend();
         }
        });
        
    }
    
    }
    class ClockControl extends JPanel {
        private Clock theclock = new Clock();
       
        private final JButton buttonSuspend = new JButton("Suspend");
        private final JButton buttonResume = new JButton("Resume");
        
        public ClockControl() {
            JPanel panel = new JPanel();
            panel.add(buttonSuspend);
            panel.add(buttonResume);
            
            setLayout(new BorderLayout());
            add(theclock, BorderLayout.CENTER);
            add(panel, BorderLayout.SOUTH);
            
            buttonSuspend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                 theclock.suspend();
                } 
          });
            
             buttonResume.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                  theclock.resume();
   
                }
        });
        }  
        
        
        public void resume() {
          
            theclock.resume();
        }
        
        public void suspend() {
           
           theclock.suspend();
                
        }
        
        
    }
    
    class Clock extends StillClock implements Runnable {
        private final Timer theclocktimer = new Timer(1000, new Listener());
         
       
        public Clock() {
          
          theclocktimer.start();
        }
        
        class Listener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentTime();
                repaint();
             }

             }
        
        public void suspend() {
         
          theclocktimer.stop();
          
           
        }
        public void resume() {
         
           theclocktimer.start();
            
        }
        
        
        // run() method to tell the system what task to perform when you use
        //the start()
        @Override
    public void run() {
            
         try
            {
         while(true)  {
             
             //gets current time and repaints all clocks
             setCurrentTime();
             repaint();
    
       }
            }
            catch(Exception e) {}//do nothing
            
        }
 }

