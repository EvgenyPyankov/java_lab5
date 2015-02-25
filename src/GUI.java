import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseEvent;
import java.util.*;


public class GUI extends JFrame {
    static private int local;
    JPanel pointCoordinatesPanel;
    static JLabel pointCoordinatesLabel;
    JPanel infoPanel;
    static PaintPanel paintPanel;
    static JRadioButton y1,y2,y3,y4;
    ButtonGroup group;
    JSpinner rSpinner;
    static JList<Double> xList;
    ResourceBundle messages;
    Locale[] supportedLocales = {
            new Locale("en"),
            new Locale("ru"),
            new Locale("sr")
    };
    public static boolean connection=false;

    public GUI() {
        messages = ResourceBundle.getBundle("MessageBundle",supportedLocales[GUI.getLocal()]);
        prepareFrame();
        prepareInfoPanel();
        add(infoPanel,BorderLayout.NORTH);
        preparePaintPanel();
        add(paintPanel,BorderLayout.CENTER);
        preparePointCoordinatesPanel();
        add(pointCoordinatesPanel,BorderLayout.SOUTH);
    }

    void preparePointCoordinatesPanel()
    {
        pointCoordinatesPanel = new JPanel();
        pointCoordinatesPanel.setBackground(Color.white);
        String buf= (String)messages.getObject("source_1");
        pointCoordinatesLabel = new JLabel(buf);
        pointCoordinatesPanel.add(pointCoordinatesLabel);
    }

    void preparePaintPanel()
    {
        paintPanel = new PaintPanel((Integer)rSpinner.getValue());
        paintPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event)
            {
                float X=(float)((event.getX()-paintPanel.x0)*paintPanel.r/paintPanel.rad);
                float Y=(float)((paintPanel.y0-event.getY())*paintPanel.r/paintPanel.rad);
                pressbutton(X,Y);
            }
        });

    }

    void prepareInfoPanel()
    {
        infoPanel = new JPanel(new GridLayout());
        final JLabel xLabel = new JLabel("X:");
        JLabel yLabel = new JLabel("Y:");
        JLabel rLabel = new JLabel("R:");
        yLabel.setHorizontalAlignment(SwingConstants.CENTER);
        xLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rLabel.setHorizontalAlignment(SwingConstants.CENTER);

        y1=new JRadioButton("-40");
        y2=new JRadioButton("-20");
        y3=new JRadioButton("20");
        y4=new JRadioButton("40");
        y4.setSelected(true);

        y1.addActionListener(new ButtonActionListener());
        y2.addActionListener(new ButtonActionListener());
        y3.addActionListener(new ButtonActionListener());
        y4.addActionListener(new ButtonActionListener());

        group = new ButtonGroup();
        group.add(y1);
        group.add(y2);
        group.add(y3);
        group.add(y4);

        Double []xListValues = {-40.0, -20.0,20.0,20.0};
        xList = new JList(xListValues);
        xList.setSelectedIndex(1);
        xList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (group.getSelection()!=null){
                            float X=0,Y=0;
                            if (y1.isSelected()) {Y=-40;}
                            if (y2.isSelected()) {Y=-20;}
                            if (y3.isSelected()) {Y=20;}
                            if (y4.isSelected()) {Y=40;}
                            X=xList.getSelectedValue().floatValue();
                            pressbutton(X, Y);
                        }
                    }
                });

        rSpinner=new JSpinner(new SpinnerNumberModel(40,10,100,1));
        rSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                paintPanel.setR((Integer)rSpinner.getValue());
                repaint();
            }
        });

        infoPanel.add(xLabel);
        infoPanel.add(xList);
        infoPanel.add(yLabel);
        infoPanel.add(y1);
        infoPanel.add(y2);
        infoPanel.add(y3);
        infoPanel.add(y4);
        infoPanel.add(rLabel);
        infoPanel.add(rSpinner);
    }

    void prepareFrame() {
        setLayout(new BorderLayout());
        setTitle("Lab4");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void pressbutton(float X, float Y)
    {
        PaintPanel.addPoint(new Point(X, Y));
        Animation animation= new Animation(PaintPanel.points.size()-1);
        Thread AnimationThread = new Thread(animation);
        AnimationThread.start();
        pointCoordinatesLabel.setText("(" + X + "; " + Y + ")");
    }

    public static int getLocal(){
        return local;
    }
    final static void setLocal(String s) {
        switch (s) {
            case "ru":
                local = 1;
                break;
            case "sr":
                local = 2;
                break;
            default:
                local = 0;
        }
    }
    public static void main(String[] args) {
        if (args.length != 0) setLocal(args[0]);
        else setLocal("default");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI ex = new GUI();
                ex.setVisible(true);
            }
        });
    }
}
