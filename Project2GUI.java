//Timothy Fisher
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Project2GUI extends JFrame implements ActionListener {

    protected JTextField lhs[], rhs[], angle, startSymbol;
    protected JButton drawButton;
    protected JSpinner iterationSpinner;
    protected JLabel ruleLabels[], angleLabel, startLabel, spinnerLabel;
    protected DrawingCanvas myCanvas;

    public Project2GUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(buildGUI());
        this.pack();
        this.setVisible(true);
    }

    private JPanel buildGUI() {
        JPanel ourGUI = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        ourGUI.setLayout(new GridBagLayout());

        buildAngle(ourGUI, gbc);
        buildStartSymbol(ourGUI, gbc);
        drawButton = new JButton("Draw");
        drawButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        ourGUI.add(drawButton, gbc);

        buildSpinner(ourGUI, gbc);

        lhs = new JTextField[5];
        rhs = new JTextField[5];
        ruleLabels = new JLabel[5];

        for (int i = 0; i < 5; i++) {
            buildRules(ourGUI, gbc, i);
        }
        JLabel title = new JLabel("L-System Project");
        title.setSize(20, 200);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 10;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        ourGUI.add(title, gbc);

        // Initial drawing when panel is loaded
        myCanvas = new DrawingCanvas();
        myCanvas.setAngleIncrement(90.0);
        myCanvas.setDrawString("F+F-F-F+F+F+F-F-F+F-F+F-F-F+F-F+F-F-F+F+F+F-F-F+F");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 10;
        gbc.gridheight = 10;
        ourGUI.add(myCanvas, gbc);

        return ourGUI;
    }

    private void buildStartSymbol(JPanel ourGUI, GridBagConstraints gbc) {
        startLabel = new JLabel("Start Symbol: ");
        startSymbol = new JTextField(2);
        gbc.gridx = 8;
        gbc.gridy = 4;
        ourGUI.add(startLabel, gbc);
        gbc.gridx = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ourGUI.add(startSymbol, gbc);
        gbc.fill = GridBagConstraints.NONE;
    }

    private void buildAngle(JPanel ourGUI, GridBagConstraints gbc) {
        angleLabel = new JLabel("Angle: ");
        angle = new JTextField(4);
        gbc.gridx = 8;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        ourGUI.add(angleLabel, gbc);
        gbc.gridx = 9;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ourGUI.add(angle, gbc);
        gbc.fill = GridBagConstraints.NONE;
    }

    private void buildSpinner(JPanel ourGUI, GridBagConstraints gbc) {
        spinnerLabel = new JLabel("Number of Iterations: ");
        gbc.gridx = 8;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        ourGUI.add(spinnerLabel, gbc);
        iterationSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        gbc.gridx = 9;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ourGUI.add(iterationSpinner, gbc);
        gbc.fill = GridBagConstraints.NONE;
    }

    private void buildRules(JPanel ourGUI, GridBagConstraints gbc, int i) {
        ruleLabels[i] = new JLabel("Rule " + i + " : ");
        gbc.gridx = 1;
        gbc.gridy = i + 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        ourGUI.add(ruleLabels[i], gbc);
        gbc.gridx = 2;
        lhs[i] = new JTextField(2);
        ourGUI.add(lhs[i], gbc);
        gbc.gridwidth = 5;
        gbc.gridx = 3;
        rhs[i] = new JTextField(10);
        ourGUI.add(rhs[i], gbc);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == drawButton) {

            for (int i = 0; i < 5; i++) {
                System.out.println("Rule " + i + ": " + lhs[i].getText() +
                        " -> " + rhs[i].getText());
            }
            System.out.println("Start Symbol = " + startSymbol.getText());
            String ourStartSymbol = startSymbol.getText();
            ourStartSymbol.trim();
            if (ourStartSymbol.length() > 1) {
                JOptionPane.showMessageDialog(this,
                        "Start Symbol must be a single character!  Multiple characters found.",
                        "Start Symbol Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (ourStartSymbol.length() == 0) {
                JOptionPane.showMessageDialog(this,
                        "You must have a start symbol!  No start symbol found.",
                        "Start Symbol Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            System.out.println("Angle = " + angle.getText());
            try {
                float angleValue = Float.parseFloat(angle.getText());
                myCanvas.setAngleIncrement(angleValue);
            } catch (NumberFormatException e) {  // bad angle -- should notify user and try again...
                JOptionPane.showMessageDialog(this,
                        "Angle must be a valid floating point number.  Try again.",
                        "Angle Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            Rule[] ruleArray = new Rule[5];
            // Filling the Rule Array
            for (int i = 0; i < 5; i++) {
                String leftSide = lhs[i].getText().trim().toUpperCase();
                String rightSide =  rhs[i].getText().trim().toUpperCase();
                Rule newRule = new Rule(leftSide, rightSide);
                ruleArray[i] = newRule;
            }

            LSystem lSystem = new LSystem(ruleArray,Double.parseDouble(angle.getText()),
                    Integer.parseInt(iterationSpinner.getValue().toString()),startSymbol.getText());

            myCanvas.setDrawString(lSystem.getResult());
            System.out.println("Number of iterations = " + iterationSpinner.getValue());
            myCanvas.repaint();
        }
    }

    public static void main(String[] args) {
        Project2GUI project2 = new Project2GUI();
    }

}
