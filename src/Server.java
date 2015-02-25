import java.net.*;

public class Server extends Thread {
    static DatagramPacket receivePacket;
    static byte[]receiveData;
    static byte[]sendData;
    static DatagramSocket serverSocket;
    String sentence;
    static InetAddress IPAdress;
    static int port;
    static double x,y,r;

    public static int Check(double x, double y, double r)
    {
        System.out.printf("\nx: %f\ny: %f\nr: %f",x,y,r);
        if (((x>0)&&(y>0)&&(x*x+y*y<r*r/4))||
                ((x>0)&&(y<0)&&(x<r)&&(y>-r))||
                ((x<0)&&(y<0)&&(y>(-x/2-r/2)))
                )
            return 1;
        else return 0;
    }
    public static void main(String[]args) throws Exception{

        System.out.println("Server is started...");
        serverSocket = new DatagramSocket(8888);
        sendData = new byte[1];

        while (true) {
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            new Server(receivePacket);

           // new Server(sentence);
        }





        //}
    }
    public Server(DatagramPacket receivePacket){
        //this.sentence=sentence;
        this.receivePacket=receivePacket;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    public void run(){
        try {
            IPAdress=receivePacket.getAddress();
            port=receivePacket.getPort();
            String sentence = new String(receivePacket.getData());
            String[] values = sentence.trim().split("\\s+");
            x=Double.parseDouble(values[0]);
            y=Double.parseDouble(values[1]);
            r=Double.parseDouble(values[2]);
            sendData= new byte[1];
            sendData=(Integer.toString(Check(x, y, r))).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, port);
            serverSocket.send(sendPacket);
        }
        catch (Exception e)
        {
            System.out.print("Error: "+e);
        }
    }
}
