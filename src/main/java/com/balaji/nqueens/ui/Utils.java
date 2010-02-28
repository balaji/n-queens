package com.balaji.nqueens.ui;

import com.balaji.nqueens.Rules;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

  public static void setLookAndFeel(int option, JFrame frame) {
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
      SwingUtilities.updateComponentTreeUI(frame);
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


  public static void createFrame(JFrame frame, String title, int width, int height) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle(title);
    frame.setSize(width, height);
    Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    frame.setLocation(center.x - (frame.getSize().width / 2), center.y - (frame.getSize().height / 2));
    frame.setVisible(true);
  }

  public static JDialog createDialog(JFrame frame, String title, String text, int width, int height) {
    JPanel panel = new JPanel();
    panel.add(new JLabel(text, SwingConstants.HORIZONTAL));
    final JDialog dialog = new JDialog(frame, title);
    dialog.add(panel, BorderLayout.CENTER);
    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    dialog.setSize(width, height);
    Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
    dialog.setLocation(center.x - dialog.getSize().width / 2, center.y - dialog.getSize().height / 2);
    return dialog;
  }


  public static void drawChessBoard(JFrame frame) {

    JPanel child = new JPanel(new GridLayout(Rules.LIMIT, Rules.LIMIT));
    JLabel label;

    for (int i = 0; i < Rules.LIMIT; i++) {
      for (int j = 0; j < Rules.LIMIT; j++) {
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
    frame.setContentPane(parent);
  }
}
