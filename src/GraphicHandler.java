import java.awt.*;
import java.awt.event.*;

public class GraphicHandler {
    public static void main(String[] args){
        Frame frame = new Frame("hi?");
        frame.setSize(500,500);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        frame.addWindowListener(new WindowAdapter(){ 
            @Override public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        Canvas canvas = new Canvas(){
            @Override public void paint(Graphics g){
                Graphics2D board = (Graphics2D) g;
                

                int scale = 20;
                int offsetX = 50;
                int offsetY = 50;
                
                board.setStroke(new BasicStroke(1/scale));

                board.scale(scale,scale);
                board.translate(offsetY, offsetX);
                board.drawRect(0,0,6,6);
                board.drawRect(1,1,4,4);
                board.drawRect(2,2,2,2);
                board.drawLine(3,0,3,2);
                board.drawLine(3,4,3,6);
                board.drawLine(0,3,2,3);
                board.drawLine(4,3,6,3);
            }
        };
        canvas.setBackground(new Color(38,58,50));
        frame.add(canvas);
        frame.setVisible(true);
        
    }

   
}
