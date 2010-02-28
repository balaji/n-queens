package com.balaji.nqueens.ui;

import com.balaji.nqueens.Position;
import com.balaji.nqueens.Rules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuiFrame extends JFrame {

  private static final long serialVersionUID = 1L;
  private static int count = 0;
  private static List<List<Position>> lists;
  static int N = Rules.LIMIT;

  public GuiFrame(List<List<Position>> lst) {
    lists = lst;
    setLookAndFeel(1);
    drawChessBoard();
    drawButtons();
    if (!lists.isEmpty()) {
      placeQueens(lists.get(0));
      draw();
    } else {
      showMessage("No Solutions");
    }
  }

  private void draw() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Eight Queens");
    setSize(600, 600);
    Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    setLocation(center.x - (getSize().width / 2), center.y - (getSize().height / 2));
    setVisible(true);
  }

  private void showMessage(String text) {
    JPanel panel = new JPanel();
    panel.add(new JLabel(text, SwingConstants.HORIZONTAL));

    final JDialog dialog = new JDialog(this, "Alert!!!");
    dialog.add(panel, BorderLayout.CENTER);
    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    dialog.setSize(300, 100);
    Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    dialog.setLocation(center.x - dialog.getSize().width / 2, center.y - dialog.getSize().height / 2);
    JButton button = new JButton("OK");
    button.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        if (!isVisible()) {
          System.exit(0);
        } else {
          dialog.dispose();
        }
      }
    });

    JPanel bPanel = new JPanel();
    bPanel.add(button);
    dialog.add(bPanel, BorderLayout.SOUTH);
    dialog.setModal(true);
    dialog.setVisible(true);

  }

  private void drawChessBoard() {

    JPanel child = new JPanel(new GridLayout(N, N));
    JLabel label;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        label = new JLabel("", SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        label.setOpaque(true);
        if ((i + j) % 2 == 0) {
          label.setBackground(Color.WHITE);
        } else {
          label.setBackground(Color.BLACK);
          label.setForeground(Color.WHITE);
        }
        child.add(label);
      }
    }

    JPanel parent = new JPanel(new BorderLayout());
    parent.add(child, BorderLayout.CENTER);
    setContentPane(parent);
  }

  private void drawButtons() {

    final JLabel label = new JLabel(Integer.toString(count + 1), SwingConstants.CENTER);
    JButton next = new JButton("Next");
    next.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        if (count < (lists.size() - 1)) {
          placeQueens(lists.get(++count));
          label.setText(Integer.toString(count + 1));
        } else {
          showMessage("This is where it ends.");
        }
      }
    });

    JButton previous = new JButton("Previous");
    previous.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        if (count > 0) {
          placeQueens(lists.get(--count));
          label.setText(Integer.toString(count + 1));
        } else {
          showMessage("This is where it starts");
        }
      }
    });

    JComboBox combo = new JComboBox();
    combo.addItem("Default");
    combo.addItem("Nimbus");
    combo.addItem("Metal");

    combo.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        String item = (String) ((JComboBox) e.getSource()).getSelectedItem();

        if ("Default".equals(item)) {
          setLookAndFeel(1);
        } else if ("Nimbus".equals(item)) {
          setLookAndFeel(2);
        } else if ("Metal".equals(item)) {
          setLookAndFeel(3);
        }
      }
    });
    JPanel child = new JPanel();
    child.add(label);
    child.add(previous);
    child.add(next);
    child.add(combo);
    getContentPane().add(child, BorderLayout.SOUTH);


  }

  private void placeQueens(List<Position> list) {
    for (int i = 0; i < N; i++) {
      Position p = list.get(i);
      for (int j = 0; j < N; j++) {
        JPanel child = (JPanel) getContentPane().getComponent(0);
        JLabel label = (JLabel) child.getComponent(i * N + j);
        label.setIcon(null);

        if (p.checkPosition(i + 1, j + 1)) {
          ImageIcon icon = new ImageIcon(this.getClass().getResource("/queen.png"));
          label.setIcon(icon);
        }
      }
    }
  }

  private void setLookAndFeel(int option) {
    try {
      if (option == 1) {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } else if (option == 2) {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
          }
        }
      } else if (option == 3) {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }
      SwingUtilities.updateComponentTreeUI(this);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(GuiFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
