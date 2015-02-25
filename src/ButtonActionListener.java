import java.awt.event.*;

class ButtonActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent event)
    {
        float X=0,Y=0;
        if (GUI.y1.isSelected()) {Y=-40;}
        if (GUI.y2.isSelected()) {Y=-20;}
        if (GUI.y3.isSelected()) {Y=20;}
        if (GUI.y4.isSelected()) {Y=40;}
        X=GUI.xList.getSelectedValue().floatValue();
        GUI.pressbutton(X,Y);

    }
}