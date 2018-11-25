import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIExample extends JFrame implements ActionListener {
    protected JButton goButton;
    protected JPanel contentPanel;
    protected JTextField nameField;
    protected JLabel nameLabel;

    public GUIExample() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());

        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(20);

        contentPanel.add(nameLabel);
        contentPanel.add(nameField);
        goButton = new JButton("Go!");
        goButton.addActionListener(this);
        contentPanel.add(goButton);
        this.add(contentPanel);
        this.pack();
    }

    public static void main(String[] args) {
        GUIExample example = new GUIExample();
        example.setVisible(true);

    }
    public void actionPerformed(ActionEvent event) {
        /*
         *  Go button pressed (?)
         */
        if (event.getSource() == goButton) {
            System.out.println("Go button was pressed!!");
            System.out.println("Name Field contains: "+nameField.getText());
        }

    }

}