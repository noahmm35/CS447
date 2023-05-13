import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class BarChart extends Frame
{  public static void main(String[] args){new BarChart();}
   
   BarChart()
   {  super("Hey look, a bar chart!");
      addWindowListener(new WindowAdapter()
         {public void windowClosing(WindowEvent e){System.exit(0);}});
      setSize(1050, 600);
      add("Center", new CvBarChart());
      show();
   }
}

class CvBarChart extends Canvas
{
   public void paint (Graphics g)
    {
      
      //try-catch in case file cannot be found
      try {
         //getting info from file
         Scanner file =new Scanner(new FileReader("bardata.txt"));
         int count = 0, maxValue = 0;
         int value[] = new int [8];
         String name[] = new String[8];

         while(count<8 && file.hasNext())
         {
            name[count] = file.next();
            value[count] = file.nextInt();

            if(value[count] > maxValue)
               maxValue = value[count];
            count++;
         }

         maxValue = maxValue + (10-(maxValue%10));

         for(int i=0; i<=10; i++)
         {
            //filling values on Y axis
            int gap = 50;
            int y = 40 + gap*i;
            g.drawLine(60,y,55,y);
            g.drawString(String.valueOf(maxValue-(10*(i))), 25, y+5);
         }

         for(int i=0; i<count; i++)
         {
            //determining gap between bars
            int gap = 840/count;
            int x = 60 + (gap*i);

            //Set color for each bar
            if(i==0)
               g.setColor(Color.blue);
            else if(i==1)
               g.setColor(Color.green);
            else if(i==2)
               g.setColor(Color.red);
            else if(i==3)
               g.setColor(Color.pink);
            else if(i==4)
               g.setColor(Color.orange);
            else if(i==5)
               g.setColor(Color.cyan);
            else if(i==6)
               g.setColor(Color.yellow);
            else
               g.setColor(Color.lightGray);
            
            //draw bars and fill legend
            g.fillRect(x+10,540-5*value[i],gap-20,5*value[i]);
            g.fillRect(950,140+25*i,15,15);
            g.setColor(Color.black);
            g.drawString(name[i],975,152 + 25*i);
         }

         
         //drawing X and Y axes and mark lines on Y
         g.setColor(Color.black);
         g.drawLine(60,540,900,540);
         g.drawLine(60,540,60,40);
         g.drawString("X", 915,545);
         g.drawString("Y", 56, 30);
         g.drawRect(940,130,100,210);

      } catch (FileNotFoundException e) {
         System.out.println("File not found! oops...");
      }
    }
}