import java.awt.*;
import java.awt.event.*;

public class rangeMap extends Frame
{
    public static void main(String[] args){new rangeMap();}

    rangeMap()
    {
        super("Range Map CS447");
        addWindowListener(new WindowAdapter()
            {public void windowClosing(WindowEvent e){System.exit(0);}});
        setSize(450,400); //(x,y)
        add("Center", new CvRangeMap());
        show();
    }
}

class CvRangeMap extends Canvas
{
    public void paint (Graphics g)
    {
        int numSensors = 40;
        double changeDegree = 360/numSensors;
        double changeAngle = Math.toRadians(changeDegree);
        double angle = changeAngle;
        int startX = 175;
        int x = startX;
        int startY = 325;
        int y = startY;


        int [] rangeX = {100,150,150,200,200,250,250,100};
        int [] rangeY = {100,100,300,300,100,100,350,350};
        Polygon rangeShape = new Polygon(rangeX, rangeY, 8);

        g.drawPolygon(rangeShape);

        for(int i=0; i<numSensors;i++)
        {
            angle += changeAngle;
            int r = 1;
            x = startX;
            y = startY;

            while(rangeShape.contains(x,y))
            {
                //System.out.println("angle: " + angle);
                x = (int)(startX + (r * Math.sin(angle)));
                //System.out.println("x: " + x);
                y = (int)(startY + (r * Math.cos(angle)));
                //System.out.println("y: " + y);
                r+=1;
            }
            g.drawLine(startX,startY, x, y);
        }
        g.drawOval(startX-5,startY-5,10,10);
        g.setColor(Color.white);
        g.fillOval(startX-5,startY-5,10,10);
    }
}