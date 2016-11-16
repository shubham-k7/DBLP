import java.util.*;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUI extends JFrame implements ActionListener,ItemListener
{
        private JLabel heading = new JLabel();
        private JPanel headingPanel = new JPanel();
        GUI()
        {
            this.setSize(1600,900);
            this.setLocationRelativeTo(null);
            this.setTitle("DBLP Query Engine");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            heading.setText("DBLP Query Engine");
            heading.setFont(new Font("Calibri", Font.BOLD,50));
            heading.setBounds(200, 50, 200, 50);
            heading.setVisible(true);
            headingPanel.add(heading);
            this.add(headingPanel);
            this.setVisible(true);

        }
        public static void main(String[] args)
        {
            new GUI();
        }
        public void actionPerformed(ActionEvent e)
        {

        }
        public void itemStateChanged(ItemEvent me)
        {

        }
}
