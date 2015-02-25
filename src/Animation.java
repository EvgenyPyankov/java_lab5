import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Animation implements Runnable
{
    int index;
    public Animation(int index)
    {
        this.index=index;
    }
    double znam=11;
    boolean dir=true;
    DatagramPacket receivePacket;
    public void run()
    {
        //JOptionPane.showMessageDialog(null, GUI.paintPanel.r/znam);
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAdress = InetAddress.getLocalHost();
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1];
            clientSocket.setSoTimeout(40);


        while(true)
        {
            if (dir) znam++; else znam--;
            if (znam==21) dir=false;
            if (znam==11) dir=true;
            GUI.paintPanel.points.get(index).setPointR(GUI.paintPanel.r/znam*GUI.paintPanel.scale);

            try {
                //GUI.paintPanel.points.get(index).setItHit(-1);
                String sentence = new String(Double.toString( GUI.paintPanel.points.get(index).x) + " " + Double.toString( GUI.paintPanel.points.get(index).y) + " " + Double.toString(GUI.paintPanel.rad/GUI.paintPanel.scale));
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAdress, 8888);
                clientSocket.send(sendPacket);
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String receivedSentence = new String(receivePacket.getData());
                // int i = Integer.parseInt(receivedSentence);
                if (Integer.parseInt(receivedSentence) == 1) GUI.paintPanel.points.get(index).setItHit(1);
                else GUI.paintPanel.points.get(index).setItHit(0);

                //clientSocket.close();
            } catch (Exception e) {
                System.out.println(e+new String(receivePacket.getData()));
                GUI.paintPanel.points.get(index).setItHit(-1);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (index==0) GUI.paintPanel.repaint();
        }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}