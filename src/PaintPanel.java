import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.io.*;

public class PaintPanel extends JPanel{
    Color graphicBackgroundColor = new Color(243,210,120);
    Color graphicColor = new Color(67,114,33);
    Graphics g ;
    double r=160;
    static int rad;
    int x0,y0;
    final static int scale =4;
    public static ArrayList<Point> points = new ArrayList<Point>();
   // public static boolean check = true;
    public static int check=1;
    //Client client;

    public PaintPanel(double r)
    {
        this.r=r;
        setBackground(graphicBackgroundColor);
        g=getGraphics();
        //client = new Client();

    }

    public void setR(int r)
    {
        this.r=r;
    }

    public static void addPoint(Point point)
    {
        points.add(point);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        x0=getWidth()/2;
        y0=getHeight()/2;
        rad=(int)r;
        rad*=scale;
        super.paintComponent(g);
        g.setColor(graphicBackgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(graphicColor);
        g.fillRect(x0, y0, rad, rad);
        g.fillArc(x0 - rad / 2, y0 - rad / 2, rad, rad, 0, 90);
        g.fillPolygon(new int[]{x0 - rad, x0, x0}, new int[]{y0, y0, y0 + rad / 2}, 3);
        g.setColor(Color.black);
        g.drawLine(0,getHeight()/2,getWidth(),getHeight()/2);
        g.drawLine(getWidth()/2,0,getWidth()/2,getHeight());

        Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext())
        {
            Point m=iterator.next();
            //Client client = new Client(m.x,m.y, PaintPanel.rad/PaintPanel.scale);

           // Thread clientThread = new Client(m.x,m.y, PaintPanel.rad/PaintPanel.scale);
            //clientThread.start();



           // Client client1 = new Client(m.x, m.y, PaintPanel.rad / PaintPanel.scale);
          // client1.check=client1.run();
//            try {
//                Thread.sleep(100);
//            }
//            catch(Exception e){
//                System.out.println("Error: "+e);
//            }
            int check = m.getIsHit();
                    //Client.start(m.x, m.y, PaintPanel.rad / PaintPanel.scale);
//            try {
//                Thread.sleep(50);
//            }
//            catch (Exception e){
//                System.out.println(e);
//            }
            if (check==1) g.setColor(Color.red);
            else if (check==0) g.setColor(Color.green);
            else if (check==-1) g.setColor(Color.DARK_GRAY);
            g.fillArc((int)m.x*scale+x0-(int)m.getPointR()/2,y0-(int)m.y*scale-(int)m.getPointR()/2,(int)m.getPointR(),(int)m.getPointR(),0,360);
        }


    }

}
