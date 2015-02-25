import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
       // if (!GUI.xList.isSelectionEmpty())X=((Number)GUI.xList.getSelectedValue()).floatValue();
        X=GUI.xList.getSelectedValue().floatValue();
        GUI.pressbutton(X,Y);

    }
}