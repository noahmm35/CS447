import java.awt.*;
import java.awt.event.*;

public class robot extends Frame
{
    public static void main(String[] args){new robot();}

    robot()
    {
        super("Robot CS447");
        addWindowListener(new WindowAdapter()
            {public void windowClosing(WindowEvent e){System.exit(0);}});
        setSize(1000,600); //(x,y)
        add("Center", new CvRobot());
        show();
    }
}

class CvRobot extends Canvas
{
    public void paint (Graphics g)
    {
        Dimension d = getSize();
        int maxX = d.width -1, maxY = d.height -1;

        g.setColor(Color.black);
        g.drawLine(0, 500, maxX, 500); // draw floor
        g.drawRect(maxX - 75, 500-75, 75, 75);
        g.drawString("Goal!", maxX - 50, 500 - 30);

        //set init variables
        int w = 100;
        int h = 150;
        int BodyX = 50;
        int BodyY = 500 - h - 5;
        int armRotateX = BodyX + (w/2) -12, armRotateY = BodyY + 24;

        int [] ArmX = {88,250,250,88};
        int [] ArmY = {369,369,393,393};
        int [] newAX = new int[4];
        int [] newAY = new int[4];

        int [] HandX = {250,250,320,320};
        int [] HandY = {369,393,393,369};
        int [] newHX = new int[4];
        int [] newHY = new int[4];

        int [] FingerLX = {320,320,370,370,330,330};
        int [] FingerLY = {369,345,345,357,357,369};
        int [] newFLX = new int[6];
        int [] newFLY = new int[6];

        int [] FingerRX = {320,320,370,370,330,330};
        int [] FingerRY = {393,417,417,405,405,393};
        int [] newFRX = new int[6];
        int [] newFRY = new int[6];

        //timestep 0
        //body
        g.drawOval(BodyX + 10, BodyY + h -5, 10,10);
        g.drawOval(BodyX + w - 20, BodyY + h -5, 10,10);
        g.drawRect(BodyX, BodyY, w, h);
        g.drawOval(armRotateX, armRotateY, 24,24);

        //arm
        double angle = Math.toRadians(-90);
        for (int i = 0; i < 4; i++) {
            int deltaX = ArmX[i] - armRotateX;
            int deltaY = ArmY[i] - armRotateY;
            newAX[i] = (int) (deltaX * Math.cos(angle) - deltaY * Math.sin(angle) + armRotateX);
            newAY[i] = (int) (deltaX * Math.sin(angle) + deltaY * Math.cos(angle) + armRotateY+12);

            int hdeltaX = HandX[i] - armRotateX;
            int hdeltaY = HandY[i] - armRotateY;
            newHX[i] = (int) (hdeltaX * Math.cos(angle) - hdeltaY * Math.sin(angle) + armRotateX);
            newHY[i] = (int) (hdeltaX * Math.sin(angle) + hdeltaY * Math.sin(angle) + armRotateY+12);
            if(i==2 || i==1)
                newHY[i] += 24;
        }
        g.drawPolygon(newAX, newAY, 4);

        //hand
        double angleHand = Math.toRadians(0);

        int handRotateX = newAX[2];
        int handRotateY = newAY[2];
        for(int i=0; i<4; i++) {
            int deltaX = newHX[i] - handRotateX;
            int deltaY = newHY[i] - handRotateY;
            newHX[i] = (int) (deltaX * Math.cos(angleHand) - deltaY * Math.sin(angleHand) + handRotateX);
            newHY[i] = (int) (deltaX * Math.sin(angleHand) + deltaY * Math.cos(angleHand) + handRotateY);
        }
        g.drawPolygon(newHX, newHY, 4);

        //Fingers
        for(int i=0; i<6; i++){
            int fdeltaLX = FingerLX[i] - armRotateX;
            int fdeltaLY = FingerLY[i] - armRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angle) - fdeltaLY * Math.sin(angle) + armRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angle) + fdeltaLY * Math.cos(angle) + armRotateY+12);

            int fdeltaRX = FingerRX[i] - armRotateX;
            int fdeltaRY = FingerRY[i] - armRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angle) - fdeltaRY * Math.sin(angle) + armRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angle) + fdeltaRY * Math.cos(angle) + armRotateY+12);
        }

        double flAngle = Math.toRadians(-45);
        double frAngle = Math.toRadians(45);

        int flRotateX = newHX[3];
        int flRotateY = newHY[3];
        int frRotateX = newHX[2];
        int frRotateY = newHY[2];

        for(int i=0; i<6; i++){
            int fdeltaLX = newFLX[i] - flRotateX;
            int fdeltaLY = newFLY[i] - flRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(flAngle) - fdeltaLY * Math.sin(flAngle) + flRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(flAngle) + fdeltaLY * Math.cos(flAngle) + flRotateY);

            int fdeltaRX = newFRX[i] - frRotateX;
            int fdeltaRY = newFRY[i] - frRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(frAngle) - fdeltaRY * Math.sin(frAngle) + frRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(frAngle) + fdeltaRY * Math.cos(frAngle) + frRotateY);
        }
        g.drawPolygon(newFLX, newFLY, 6);
        g.drawPolygon(newFRX, newFRY, 6);

        // timestep 1
        //body
        BodyX += 150;
        g.drawOval(BodyX + 10, BodyY + h -5, 10,10);
        g.drawOval(BodyX + w - 20, BodyY + h -5, 10,10);
        g.drawRect(BodyX, BodyY, w, h);
        g.drawOval(BodyX + (w/2) - 12, BodyY + 24, 24,24);

        //arm
        angle = Math.toRadians(-72); 
        for (int i = 0; i < 4; i++) {
            int deltaX = ArmX[i] - armRotateX;
            int deltaY = ArmY[i] - armRotateY;
            newAX[i] = (int) (deltaX * Math.cos(angle) - deltaY * Math.sin(angle) + armRotateX + 150);
            newAY[i] = (int) (deltaX * Math.sin(angle) + deltaY * Math.cos(angle) + armRotateY+12);

            int hdeltaX = HandX[i] - armRotateX;
            int hdeltaY = HandY[i] - armRotateY;
            newHX[i] = (int) (hdeltaX * Math.cos(angle) - hdeltaY * Math.sin(angle) + armRotateX + 150);
            newHY[i] = (int) (hdeltaX * Math.sin(angle) + hdeltaY * Math.sin(angle) + armRotateY+12);
            if(i==2 || i==1)
                newHY[i] += 30;
        }
        g.drawPolygon(newAX, newAY, 4);

        //hand
        angleHand = Math.toRadians(17);

        handRotateX = newAX[2];
        handRotateY = newAY[2];
        for(int i=0; i<4; i++) {
            int deltaX = newHX[i] - handRotateX;
            int deltaY = newHY[i] - handRotateY;
            newHX[i] = (int) (deltaX * Math.cos(angleHand) - deltaY * Math.sin(angleHand) + handRotateX);
            newHY[i] = (int) (deltaX * Math.sin(angleHand) + deltaY * Math.cos(angleHand) + handRotateY);
        }
        g.drawPolygon(newHX, newHY, 4);

        //Fingers
        for(int i=0; i<6; i++){
            int fdeltaLX = FingerLX[i] - armRotateX;
            int fdeltaLY = FingerLY[i] - armRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angle) - fdeltaLY * Math.sin(angle) + armRotateX+150);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angle) + fdeltaLY * Math.cos(angle) + armRotateY+12);

            fdeltaLX = newFLX[i] - handRotateX;
            fdeltaLY = newFLY[i] - handRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angleHand) - fdeltaLY * Math.sin(angleHand) + handRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angleHand) + fdeltaLY * Math.cos(angleHand) + handRotateY);

            int fdeltaRX = FingerRX[i] - armRotateX;
            int fdeltaRY = FingerRY[i] - armRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angle) - fdeltaRY * Math.sin(angle) + armRotateX +150);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angle) + fdeltaRY * Math.cos(angle) + armRotateY+12);

            fdeltaRX = newFRX[i] - handRotateX;
            fdeltaRY = newFRY[i] - handRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angleHand) - fdeltaRY * Math.sin(angleHand) + handRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angleHand) + fdeltaRY * Math.cos(angleHand) + handRotateY);
        }

        flAngle = Math.toRadians(-18);
        frAngle = Math.toRadians(21);

        flRotateX = newHX[3];
        flRotateY = newHY[3];
        frRotateX = newHX[2];
        frRotateY = newHY[2];

        for(int i=0; i<6; i++){
            int fdeltaLX = newFLX[i] - flRotateX;
            int fdeltaLY = newFLY[i] - flRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(flAngle) - fdeltaLY * Math.sin(flAngle) + flRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(flAngle) + fdeltaLY * Math.cos(flAngle) + flRotateY);

            int fdeltaRX = newFRX[i] - frRotateX;
            int fdeltaRY = newFRY[i] - frRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(frAngle) - fdeltaRY * Math.sin(frAngle) + frRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(frAngle) + fdeltaRY * Math.cos(frAngle) + frRotateY);
        }

        g.drawPolygon(newFLX, newFLY, 6);
        g.drawPolygon(newFRX, newFRY, 6);

        //timestep 2
        //body
        BodyX += 150;
        g.drawOval(BodyX + 10, BodyY + h -5, 10,10);
        g.drawOval(BodyX + w - 20, BodyY + h -5, 10,10);
        g.drawRect(BodyX, BodyY, w, h);
        g.drawOval(BodyX + (w/2) - 12, BodyY + 24, 24,24);

        //arm
        angle = Math.toRadians(-54); 
        for (int i = 0; i < 4; i++) {
            int deltaX = ArmX[i] - armRotateX;
            int deltaY = ArmY[i] - armRotateY;
            newAX[i] = (int) (deltaX * Math.cos(angle) - deltaY * Math.sin(angle) + armRotateX + 150*2);
            newAY[i] = (int) (deltaX * Math.sin(angle) + deltaY * Math.cos(angle) + armRotateY+12);

            int hdeltaX = HandX[i] - armRotateX;
            int hdeltaY = HandY[i] - armRotateY;
            newHX[i] = (int) (hdeltaX * Math.cos(angle) - hdeltaY * Math.sin(angle) + armRotateX + 150*2);
            newHY[i] = (int) (hdeltaX * Math.sin(angle) + hdeltaY * Math.sin(angle) + armRotateY+12);
            if(i==2 || i==1)
                newHY[i] += 34;
        }
        g.drawPolygon(newAX, newAY, 4);

        //hand
        angleHand = Math.toRadians(35);

        handRotateX = newAX[2];
        handRotateY = newAY[2];
        for(int i=0; i<4; i++) {
            int deltaX = newHX[i] - handRotateX;
            int deltaY = newHY[i] - handRotateY;
            newHX[i] = (int) (deltaX * Math.cos(angleHand) - deltaY * Math.sin(angleHand) + handRotateX);
            newHY[i] = (int) (deltaX * Math.sin(angleHand) + deltaY * Math.cos(angleHand) + handRotateY);
        }
        g.drawPolygon(newHX, newHY, 4);

        //Fingers
        for(int i=0; i<6; i++){
            int fdeltaLX = FingerLX[i] - armRotateX;
            int fdeltaLY = FingerLY[i] - armRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angle) - fdeltaLY * Math.sin(angle) + armRotateX+150*2);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angle) + fdeltaLY * Math.cos(angle) + armRotateY+12);

            fdeltaLX = newFLX[i] - handRotateX;
            fdeltaLY = newFLY[i] - handRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angleHand) - fdeltaLY * Math.sin(angleHand) + handRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angleHand) + fdeltaLY * Math.cos(angleHand) + handRotateY);

            int fdeltaRX = FingerRX[i] - armRotateX;
            int fdeltaRY = FingerRY[i] - armRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angle) - fdeltaRY * Math.sin(angle) + armRotateX +150*2);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angle) + fdeltaRY * Math.cos(angle) + armRotateY+12);

            fdeltaRX = newFRX[i] - handRotateX;
            fdeltaRY = newFRY[i] - handRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angleHand) - fdeltaRY * Math.sin(angleHand) + handRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angleHand) + fdeltaRY * Math.cos(angleHand) + handRotateY);
        }

        flAngle = Math.toRadians(-8);
        frAngle = Math.toRadians(6);

        flRotateX = newHX[3];
        flRotateY = newHY[3];
        frRotateX = newHX[2];
        frRotateY = newHY[2];

        for(int i=0; i<6; i++){
            int fdeltaLX = newFLX[i] - flRotateX;
            int fdeltaLY = newFLY[i] - flRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(flAngle) - fdeltaLY * Math.sin(flAngle) + flRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(flAngle) + fdeltaLY * Math.cos(flAngle) + flRotateY);

            int fdeltaRX = newFRX[i] - frRotateX;
            int fdeltaRY = newFRY[i] - frRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(frAngle) - fdeltaRY * Math.sin(frAngle) + frRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(frAngle) + fdeltaRY * Math.cos(frAngle) + frRotateY);
        }

        g.drawPolygon(newFLX, newFLY, 6);
        g.drawPolygon(newFRX, newFRY, 6);

        //timestep 3
        //body
        BodyX += 150;
        g.drawOval(BodyX + 10, BodyY + h -5, 10,10);
        g.drawOval(BodyX + w - 20, BodyY + h -5, 10,10);
        g.drawRect(BodyX, BodyY, w, h);
        g.drawOval(BodyX + (w/2) - 12, BodyY + 24, 24,24);

        //arm
        angle = Math.toRadians(-36); 
        for (int i = 0; i < 4; i++) {
            int deltaX = ArmX[i] - armRotateX;
            int deltaY = ArmY[i] - armRotateY;
            newAX[i] = (int) (deltaX * Math.cos(angle) - deltaY * Math.sin(angle) + armRotateX + 150*3);
            newAY[i] = (int) (deltaX * Math.sin(angle) + deltaY * Math.cos(angle) + armRotateY+9);

            int hdeltaX = HandX[i] - armRotateX;
            int hdeltaY = HandY[i] - armRotateY;
            newHX[i] = (int) (hdeltaX * Math.cos(angle) - hdeltaY * Math.sin(angle) + armRotateX + 150*3);
            newHY[i] = (int) (hdeltaX * Math.sin(angle) + hdeltaY * Math.sin(angle) + armRotateY+9);
            if(i==2 || i==1)
                newHY[i] += 34;
        }
        g.drawPolygon(newAX, newAY, 4);

        //hand
        angleHand = Math.toRadians(52);

        handRotateX = newAX[2];
        handRotateY = newAY[2];
        for(int i=0; i<4; i++) {
            int deltaX = newHX[i] - handRotateX;
            int deltaY = newHY[i] - handRotateY;
            newHX[i] = (int) (deltaX * Math.cos(angleHand) - deltaY * Math.sin(angleHand) + handRotateX);
            newHY[i] = (int) (deltaX * Math.sin(angleHand) + deltaY * Math.cos(angleHand) + handRotateY);
        }
        g.drawPolygon(newHX, newHY, 4);

        //Fingers
        for(int i=0; i<6; i++){
            int fdeltaLX = FingerLX[i] - armRotateX;
            int fdeltaLY = FingerLY[i] - armRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angle) - fdeltaLY * Math.sin(angle) + armRotateX+150*3);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angle) + fdeltaLY * Math.cos(angle) + armRotateY+12);

            fdeltaLX = newFLX[i] - handRotateX;
            fdeltaLY = newFLY[i] - handRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angleHand) - fdeltaLY * Math.sin(angleHand) + handRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angleHand) + fdeltaLY * Math.cos(angleHand) + handRotateY);

            int fdeltaRX = FingerRX[i] - armRotateX;
            int fdeltaRY = FingerRY[i] - armRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angle) - fdeltaRY * Math.sin(angle) + armRotateX +150*3);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angle) + fdeltaRY * Math.cos(angle) + armRotateY+12);

            fdeltaRX = newFRX[i] - handRotateX;
            fdeltaRY = newFRY[i] - handRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angleHand) - fdeltaRY * Math.sin(angleHand) + handRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angleHand) + fdeltaRY * Math.cos(angleHand) + handRotateY);
        }

        flAngle = Math.toRadians(2);
        frAngle = Math.toRadians(-9);

        flRotateX = newHX[3];
        flRotateY = newHY[3];
        frRotateX = newHX[2];
        frRotateY = newHY[2];

        for(int i=0; i<6; i++){
            int fdeltaLX = newFLX[i] - flRotateX;
            int fdeltaLY = newFLY[i] - flRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(flAngle) - fdeltaLY * Math.sin(flAngle) + flRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(flAngle) + fdeltaLY * Math.cos(flAngle) + flRotateY);

            int fdeltaRX = newFRX[i] - frRotateX;
            int fdeltaRY = newFRY[i] - frRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(frAngle) - fdeltaRY * Math.sin(frAngle) + frRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(frAngle) + fdeltaRY * Math.cos(frAngle) + frRotateY);
        }
        g.drawPolygon(newFLX, newFLY, 6);
        g.drawPolygon(newFRX, newFRY, 6);

        //timestep 4
        //body
        BodyX += 150;
        g.drawOval(BodyX + 10, BodyY + h -5, 10,10);
        g.drawOval(BodyX + w - 20, BodyY + h -5, 10,10);
        g.drawRect(BodyX, BodyY, w, h);
        g.drawOval(BodyX + (w/2) - 12, BodyY + 24, 24,24);

        //arm
        angle = Math.toRadians(-18); 
        for (int i = 0; i < 4; i++) {
            int deltaX = ArmX[i] - armRotateX;
            int deltaY = ArmY[i] - armRotateY;
            newAX[i] = (int) (deltaX * Math.cos(angle) - deltaY * Math.sin(angle) + armRotateX + 150*4);
            newAY[i] = (int) (deltaX * Math.sin(angle) + deltaY * Math.cos(angle) + armRotateY+5);

            int hdeltaX = HandX[i] - armRotateX;
            int hdeltaY = HandY[i] - armRotateY;
            newHX[i] = (int) (hdeltaX * Math.cos(angle) - hdeltaY * Math.sin(angle) + armRotateX + 150*4);
            newHY[i] = (int) (hdeltaX * Math.sin(angle) + hdeltaY * Math.sin(angle) + armRotateY+5);
            if(i==2 || i==1)
                newHY[i] += 30;
        }
        g.drawPolygon(newAX, newAY, 4);

        //hand
        angleHand = Math.toRadians(70);
        handRotateX = newAX[2];
        handRotateY = newAY[2];
        for(int i=0; i<4; i++) {
            int deltaX = newHX[i] - handRotateX;
            int deltaY = newHY[i] - handRotateY;
            newHX[i] = (int) (deltaX * Math.cos(angleHand) - deltaY * Math.sin(angleHand) + handRotateX);
            newHY[i] = (int) (deltaX * Math.sin(angleHand) + deltaY * Math.cos(angleHand) + handRotateY);
        }
        g.drawPolygon(newHX, newHY, 4);

        //Fingers
        for(int i=0; i<6; i++){
            int fdeltaLX = FingerLX[i] - armRotateX;
            int fdeltaLY = FingerLY[i] - armRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angle) - fdeltaLY * Math.sin(angle) + armRotateX+150*4);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angle) + fdeltaLY * Math.cos(angle) + armRotateY+12);

            fdeltaLX = newFLX[i] - handRotateX;
            fdeltaLY = newFLY[i] - handRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(angleHand) - fdeltaLY * Math.sin(angleHand) + handRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(angleHand) + fdeltaLY * Math.cos(angleHand) + handRotateY);

            int fdeltaRX = FingerRX[i] - armRotateX;
            int fdeltaRY = FingerRY[i] - armRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angle) - fdeltaRY * Math.sin(angle) + armRotateX +150*4);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angle) + fdeltaRY * Math.cos(angle) + armRotateY+12);

            fdeltaRX = newFRX[i] - handRotateX;
            fdeltaRY = newFRY[i] - handRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(angleHand) - fdeltaRY * Math.sin(angleHand) + handRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(angleHand) + fdeltaRY * Math.cos(angleHand) + handRotateY);
        }

        flAngle = Math.toRadians(12);
        frAngle = Math.toRadians(-24);

        flRotateX = newHX[3];
        flRotateY = newHY[3];
        frRotateX = newHX[2];
        frRotateY = newHY[2];

        for(int i=0; i<6; i++){
            newFLX[i]+=8;
            newFLY[i]-=2;
        }
        
        for(int i=0; i<6; i++){
            int fdeltaLX = newFLX[i] - flRotateX;
            int fdeltaLY = newFLY[i] - flRotateY;
            newFLX[i] = (int) (fdeltaLX * Math.cos(flAngle) - fdeltaLY * Math.sin(flAngle) + flRotateX);
            newFLY[i] = (int) (fdeltaLX * Math.sin(flAngle) + fdeltaLY * Math.cos(flAngle) + flRotateY);

            int fdeltaRX = newFRX[i] - frRotateX;
            int fdeltaRY = newFRY[i] - frRotateY;
            newFRX[i] = (int) (fdeltaRX * Math.cos(frAngle) - fdeltaRY * Math.sin(frAngle) + frRotateX);
            newFRY[i] = (int) (fdeltaRX * Math.sin(frAngle) + fdeltaRY * Math.cos(frAngle) + frRotateY);
        }
        g.drawPolygon(newFLX, newFLY, 6);
        g.drawPolygon(newFRX, newFRY, 6);
    }
}