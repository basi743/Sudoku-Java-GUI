import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Cell extends JPanel {

    JLabel label;
    int row,col;
    int state = 0;
    Boolean isModifiable = true;


    public Cell(int row, int col) {

        this.row = row;
        this.col = col;

        setBorder(BorderFactory.createLineBorder(new Color(30, 80, 138)));
        setBackground(new Color(236, 236, 236));

        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        label.setForeground(new Color(0, 38, 69));

        add(label);

    }

    public void setValue(int value) {
        if (value == 0){
            label.setText("");
            return;
        }

        label.setText(String.valueOf(value));
    }

    public int getValue() {
        if(label.getText().equals("")){
            return 0;
        }
        return Integer.parseInt(label.getText());
    }

    public void changeColour(int state){

        /*
        * Unselected = 0
        * Selected = 1
        * Selected Row,Col,SubGrid  = 2
        * Incorrect Input = 3
        * */

        switch(state){
            case 0:
                setBackground(new Color(236, 236, 236));
                state = 0;
                break;
            case 1:
                setBackground(new Color(139, 168, 213));
                state = 1;
                break;
            case 2:
                setBackground(new Color(197, 210, 228));
                state = 2;
                break;
            case 3:
                setBackground(new Color(239, 159, 159));
                state = 3;
                break;
        }



    }

}
