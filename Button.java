import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Button {

    private JButton button;

    //button settings
    public Button(){
            button = new JButton();
            button.setBackground(Color.BLACK);
            Border line = new LineBorder(Color.BLACK);
            Border margin = new EmptyBorder(5, 15, 5, 15);
            Border compound = new CompoundBorder(line, margin);
            button.setBorder(compound);
    }

    public JButton getButton() {
        return button;
    }
}
