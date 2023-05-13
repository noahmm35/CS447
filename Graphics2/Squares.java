import java.awt.*;
import java.awt.event.*;

public class Squares extends Frame
{  public static void main(String[] args){new Squares();}
   
   Squares()
   {  super("Chessboard of funny squares!");
      addWindowListener(new WindowAdapter()
         {public void windowClosing(WindowEvent e){System.exit(0);}});
      setSize(620, 650);
      add("Center", new CvSquares());
      show();
   }
}

class CvSquares extends Canvas
{  int maxX, maxY, minMaxXY, xCenter, yCenter;

   void initgr()
   {  Dimension d = getSize();
      maxX = d.width - 1; maxY = d.height - 1;
      minMaxXY = Math.min(maxX, maxY);
      xCenter = maxX/2; yCenter = maxY/2;
   }
   int iX(float x){return Math.round(x);}
   int iY(float y){return maxY - Math.round(y);}

   public void paint(Graphics g)
   {  initgr();
      float side = 0.95F * minMaxXY, sideHalf = 0.5F * side, 
         h = sideHalf * (float)Math.sqrt(4),
         xA, yA, xB, yB, xC, yC, xD, yD, 
         xA1, yA1, xB1, yB1, xC1, yC1, xD1, yD1, p, q;
      q = 0.05F; 
      p = 1 - q; 
      xA = 10; yA = maxY - 10;
      xB = 110; yB = yA;
      xC = xB; yC = yA-100;
      xD = xA; yD = yC;
      
      
      for(int k=0; k<6; k++)
      {
        for(int j=0; j<6; j++)
        {
            for (int i=0; i<50; i++) 
            {  
                g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
                g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
                g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
                g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));
                xA1 = p * xA + q * xB; yA1 = p * yA + q * yB; 
                xB1 = p * xB + q * xC; yB1 = p * yB + q * yC;
                xC1 = p * xC + q * xD; yC1 = p * yC + q * yD; 
                xD1 = p * xD + q * xA; yD1 = p * yD + q * yA;
                xA = xA1; xB = xB1; xC = xC1; xD = xD1;
                yA = yA1; yB = yB1; yC = yC1; yD = yD1;
            }

            xA = 10 + 100*(j+1); yA = maxY - 10 - k*100;
            xB = 110 + 100*(j+1); yB = yA;
            xC = xB; yC = yA-100;
            xD = xA; yD = yC;
        }

        xA = 10; yA = maxY - 100*(k+1) -10;
        xB = 110; yB = yA;
        xC = xB; yC = yA-100;
        xD = xA; yD = yC;
      }
   }
}