import java.awt.*;
import java.awt.event.*;

public class House extends Frame
{
    public static void main(String[] args){new House();}

    House()
    {
        super("House Project CS447");
        addWindowListener(new WindowAdapter()
            {public void windowClosing(WindowEvent e){System.exit(0);}});
        setSize(800,500); //(x,y)
        add("Center", new CvHouse());
        show();
    }
}

class CvHouse extends Canvas
{
    public void paint(Graphics g)
    {
        Dimension d = getSize();
        int maxX = d.width -1, maxY = d.height -1;

        //Colors to be used
        Color sky = new Color(135,206,235);
        Color grass = new Color(124,252,0);
        Color DriveWay = new Color(210,180,140);
        Color HMain = new Color(255,255,150);
        Color roof = new Color(139,69,19);
        Color HMain2 = new Color(255,253,208);
        Color Door = new Color(150,100,50);
        Color Window = new Color(235,243,250);

        //Setting points for polygons
        int [] RoofX = {125,500,560,180};
        int [] RoofY = {45,50,120,120};
        Polygon RoofMain = new Polygon(RoofX, RoofY, 4);

        int [] FMX = {310,310,420,420,355};
        int [] FMY = {120,410,410,120,70};
        Polygon FrontMid = new Polygon(FMX, FMY, 5);

        int [] TRX = {355,300,310};
        int [] TRY = {70,85,120};
        Polygon RoofTri = new Polygon(TRX, TRY, 3);

        int [] LeftX = {180,180,80,80,125};
        int [] LeftY = {120,410,360,165,45};
        Polygon LeftSide = new Polygon(LeftX, LeftY, 5);

        int [] RightTX = {560,650,740};
        int [] RightTY = {240,140,240};
        Polygon RoofRight = new Polygon(RightTX, RightTY, 3);

        int [] RightTX2 = {560,650,560};
        int [] RightTY2 = {240,140,160};
        Polygon RoofRight2 = new Polygon(RightTX2, RightTY2, 3);

        int [] TrimX = {560,650,740,730,650,570};
        int [] TrimY = {240,140,240,240,150,240};
        Polygon TrimRight = new Polygon(TrimX, TrimY,6);
        
        int [] TrimFX = {180,310,355,420,560,560,420,355,310,180};
        int [] TrimFY = {120,120,70,120,120,125,125,75,125,125};
        Polygon TrimFront = new Polygon(TrimFX, TrimFY, 10);

        int [] TrimLX = {180,125,80,80,85,85,125,175,175,180};
        int [] TrimLY = {120,45,165,360,362,165,53,120,408,410};
        Polygon TrimLeft = new Polygon(TrimLX, TrimLY, 10);

        int [] SDFX = {106,106,134,134};
        int [] SDFY = {373,287,301,387};
        Polygon SDFrame = new Polygon(SDFX, SDFY,4);
        int [] SDX = {110,110,130,130};
        int [] SDY = {375,294,305,385};
        Polygon SideDoor = new Polygon(SDX, SDY, 4);

        int [] FDWalkX = {330,400,470,400};
        int [] FDWalkY = {410,410,500,500};
        Polygon FDWalk = new Polygon(FDWalkX, FDWalkY, 4);

        int [] SDWalkX = {106,36,136,184,84,134};
        int [] SDWalkY = {373,373,500,500,387,387};
        Polygon SDWalk = new Polygon(SDWalkX, SDWalkY,6);

        int [] DWX = {583,717,787,653};
        int [] DWY = {410,410,500,500};
        Polygon DW = new Polygon(DWX, DWY, 4);

        //Draw and Color
        g.setColor(sky);
        g.fillRect(0,0,maxX,maxY);
        g.setColor(grass.darker());
        g.fillRect(0,250,maxX,maxY);

        g.setColor(HMain);
        g.fillRect(180,120,380,290); //Front wall
        g.fillPolygon(LeftSide); //Left Wall
        g.fillRect(560,240,180,170); //Right Side
        g.fillPolygon(RoofRight); //Triangle on top of right portion

        g.setColor(roof);
        g.fillPolygon(RoofMain);
        g.fillPolygon(RoofRight2);

        g.setColor(roof.darker());
        g.fillPolygon(RoofTri);

        g.setColor(HMain2);
        g.fillPolygon(FrontMid); //Middle piece on front

        g.setColor(Door.darker());
        g.fillRect(330,285,70,125); //Door Frame
        g.fillPolygon(SDFrame); //Side Door Frame

        g.setColor(Door);
        g.fillRect(335,290,60,120); //Front Door
        g.fillPolygon(SideDoor);

        g.setColor(Color.lightGray);
        g.fillRect(343, 345, 5, 10); //Front Doorknob
        g.fillRect(114,335,3,5); //Side Doorknob
        g.fillRect(590,280,120,130); //garage
    
        g.setColor(Color.white); //For trim and windows
        g.fillRect(180,125,5,285);
        g.fillRect(555,125,5,285);
        g.fillPolygon(TrimFront);
        g.fillPolygon(TrimLeft);
        g.fillRect(560,240,180,10);
        g.fillRect(735,250,5,160);
        g.fillPolygon(TrimRight);
        g.fillRect(210,160,70,70);
        g.fillRect(455,160,70,70);
        g.fillRect(210,290,70,80);
        g.fillRect(455,290,70,80);
        
        g.setColor(DriveWay); //For walkways
        g.fillPolygon(FDWalk); 
        g.fillPolygon(DW);
        g.fillPolygon(SDWalk);

        g.setColor(Color.black); //To draw everything and provide outline
        g.drawPolygon(FrontMid);
        g.drawPolygon(LeftSide);
        g.drawLine(125,45,500,50);
        g.drawLine(500,50,560,120);
        g.drawPolygon(RoofRight);
        g.drawPolygon(RoofRight2);
        g.drawPolygon(RoofTri);
        g.drawLine(180,410,740,410);
        g.drawPolygon(FDWalk);
        g.drawPolygon(DW);
        g.drawPolygon(SDWalk);
        g.drawOval(330,125,70,70);
        g.drawRect(210,160,70,70);
        g.drawRect(455,160,70,70);
        g.drawRect(210,290,70,80);
        g.drawRect(455,290,70,80);
        g.drawRect(330,160,70,85);
        g.drawPolygon(TrimFront);
        g.drawPolygon(TrimLeft);
        g.drawPolygon(TrimRight);
        g.drawRect(180,125,5,285);
        g.drawRect(555,125,5,285);
        g.drawRect(560,240,180,10);
        g.drawRect(735,250,5,160);
        g.drawRect(343, 345, 5, 10);
        g.drawRect(115,335,3,5);
        g.drawRect(590,280,120,130);
        g.drawRect(330,285,70,125);
        g.drawPolygon(SDFrame);
        g.drawRect(335,290,60,120);
        g.drawPolygon(SideDoor);
        g.drawRect(590,280,120,130);
        int y=306;
        for(int i=0;i<5;i++) //for lines on garage
        {
            g.drawLine(590,y,710,y);
            y+=26;
        }
        
        g.setColor(Color.white); //For big middle window
        g.fillOval(330,125,70,70);

        g.setColor(Window); //for window panes
        g.fillOval(335,130,60,60);
        g.setColor(Color.black);

        g.setColor(Color.black);
        g.drawOval(335,130,60,60);

        g.setColor(Color.white); //For big middle window
        g.fillRect(330,160,70,85);

       g.setColor(Window);
        g.fillRect(335,165,28,35);
        g.fillRect(368,165,28,35);
        g.fillRect(335,205,28,35);
        g.fillRect(368,205,28,35);
        g.fillRect(215,165,28,28);
        g.fillRect(248,165,28,28);
        g.fillRect(215,198,28,28);
        g.fillRect(248,198,28,28);
        g.fillRect(460,165,28,28);
        g.fillRect(493,165,28,28);
        g.fillRect(460,198,28,28);
        g.fillRect(493,198,28,28);
        g.fillRect(215,295,28,33);
        g.fillRect(248,295,28,33);
        g.fillRect(215,333,28,33);
        g.fillRect(248,333,28,33);
        g.fillRect(460,295,28,33);
        g.fillRect(493,295,28,33);
        g.fillRect(460,333,28,33);
        g.fillRect(493,333,28,33);

        g.setColor(Color.black);
        g.drawRect(335,165,28,35);
        g.drawRect(368,165,28,35);
        g.drawRect(335,205,28,35);
        g.drawRect(368,205,28,35);
        g.drawLine(335,160,395,160);
        g.drawRect(215,165,28,28);
        g.drawRect(248,165,28,28);
        g.drawRect(215,198,28,28);
        g.drawRect(248,198,28,28);
        g.drawRect(460,165,28,28);
        g.drawRect(493,165,28,28);
        g.drawRect(460,198,28,28);
        g.drawRect(493,198,28,28);
        g.drawRect(215,295,28,33);
        g.drawRect(248,295,28,33);
        g.drawRect(215,333,28,33);
        g.drawRect(248,333,28,33);
        g.drawRect(460,295,28,33);
        g.drawRect(493,295,28,33);
        g.drawRect(460,333,28,33);
        g.drawRect(493,333,28,33);
    }
}