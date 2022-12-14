package usedItemProject;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class P_Two extends JPanel {
  private JLabel lblNewLabel;
  private JButton btnNewButton;
  private Frame F;

  public P_Two(Frame f) {
    setBackground(Color.GRAY);
    setSize(330, 80);
    setLayout(null);

    F = f;

    lblNewLabel = new JLabel("Panel Two");
    lblNewLabel.setBounds(12, 10, 61, 15);
    add(lblNewLabel);

    btnNewButton = new JButton("Change Panel");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        F.changePanel();
      }
    });
    btnNewButton.setBounds(12, 35, 113, 23);
    add(btnNewButton);

    setVisible(true);
  }
}
