/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.keyboard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Thomas
 */
public class MusicKeyboard extends JApplet {
    private double mytime;
    private boolean on = false;
    private int buttoncount = 0;
    private static final int JFXPANEL_WIDTH_INT = 500;
    private static final int JFXPANEL_HEIGHT_INT = 500;
    private static JFXPanel fxContainer;
    private int tempo = 120;
    private ArrayList<KeyNode> thelist = new ArrayList<KeyNode>();
    private double previoustime;
    private double previousprevioustime;
    private String lastchar;
    private double cumulativedistance;
    /*
    private class KeyNode {
        private String note;
        private double seconds;
        private double duration;
        public KeyNode(String note, double seconds, double duration) {
            this.note = note;
            this.seconds = seconds;
            this.duration = duration;
        }
        public String note() {
            return note;
        }
        public double seconds() {
            return seconds;
        }
        public double duration() {
            return duration;
        }
        
    }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }
                JFrame frame = new JFrame("Keyboard Notes");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                JApplet applet = new MusicKeyboard();
                applet.init();
                
                frame.setContentPane(applet.getContentPane());
                
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
                applet.start();
            }
        });
    }
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
            }
        });
        /*
        KeyEventDispatcher keyEventDispatcher;
        keyEventDispatcher = new KeyEventDispatcher() {
            Set<Integer> pressedKeys = new TreeSet<Integer>();

            
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    int code = e.getKeyCode();
               
                    if (pressedKeys.contains(code)) {
                        return false;
                    }
                    else {
                        pressedKeys.add(code);
                        buttoncount += 1;
                        previousprevioustime = System.currentTimeMillis() / 1000.0 - previoustime;
                        previoustime = System.currentTimeMillis() / 1000.0;
                        lastchar = String.valueOf(e.getKeyChar());
                    }
                }
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                    pressedKeys.remove(e.getKeyCode());
                    thelist.add(new KeyNode(lastchar, previousprevioustime, System.currentTimeMillis() / 1000.0 - previoustime));
                }
                // Pass the KeyEvent to the next KeyEventDispatcher in the chain
                return false;
            }
            
        };
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
*/
    }
   
    private void createScene() {
        Button btn = new Button();
        btn.setText("Start/Stop tempo");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!on) {
                    System.out.println("Starting to count!");
                    buttoncount = 0;
                    mytime = System.currentTimeMillis();
                    on = true;
                }
                else {
                    mytime = System.currentTimeMillis() - mytime;
                    System.out.println("This is your beat: " + Math.round(60 * ((double) buttoncount / ((double) mytime / 1000.0))));
                    tempo = (int) Math.round(60 * ((double) buttoncount / ((double) mytime / 1000.0)));
                    on = false;
                }
                
            }
        });
        
        
        Button record = new Button();
        record.setText("Start recording");
        record.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!on) {
                    System.out.println("Starting to record notes!");
                    previoustime = System.currentTimeMillis();
                    thelist.clear();
                    on = true;
                }
                else {
                    for (int i = 0; i < thelist.size() - 1; i++) {
                        thelist.get(i).seconds = thelist.get(i + 1).seconds;
                    }
                    for (int i = 0; i < thelist.size() - 1; i++) {
                        thelist.get(i + 1).seconds += thelist.get(i).seconds;
                    }
                    thelist.get(thelist.size() - 1).seconds = 0;
                    for (int i = 0; i < thelist.size(); i++) {
                        System.out.print(thelist.get(i).note + thelist.get(i).seconds);
                    }
                    on = false;
                }
                
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(record);
        fxContainer.setScene(new Scene(root));
    }
    
}
