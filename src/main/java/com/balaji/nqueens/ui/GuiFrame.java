package com.balaji.nqueens.ui;

import com.balaji.nqueens.MovesCalculator;
import com.balaji.nqueens.Position;
import com.balaji.nqueens.Rules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiFrame extends JFrame {

  private static int count = 0;

  public GuiFrame(List<List<Position>> list) {
    draw(list);
  }

  private void draw(List<List<Position>> list) {
    System.gc();
    Utils.setLookAndFeel(1, this);
    Utils.drawChessBoard(this);
    if (!list.isEmpty()) {
      prepareBottomPanel(list, new JLabel("1", SwingConstants.CENTER));
      placeQueens(list.get(0));
      Utils.createFrame(this, "N Queens", 600, 600);
    } else {
      prepareBottomPanel(Collections.<List<Position>>emptyList(), new JLabel("0", SwingConstants.CENTER));
      showMessage("No Solutions");
    }
  }

  private void showMessage(String text) {
    final JDialog dialog = Utils.createDialog(this, "Alert", text, 300, 100);
    JPanel bPanel = new JPanel();
    bPanel.add(prepareOkButton(dialog));
    dialog.add(bPanel, BorderLayout.SOUTH);
    dialog.setModal(true);
    dialog.setVisible(true);
  }

  private JButton prepareOkButton(final JDialog dialog) {
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
    return button;
  }

  private void prepareBottomPanel(List<List<Position>> list, JLabel label) {
    JPanel child = new JPanel();
    child.add(label);
    child.add(preparePreviousButton(list, label));
    child.add(prepareNextButton(list, label));
    child.add(new JLabel(" Layout Style: "));
    child.add(prepareStyleCombo());
    child.add(new JLabel(" N (4 - 10):"));
    child.add(prepareNCombo());


    getContentPane().add(child, BorderLayout.SOUTH);
  }

  private JButton preparePreviousButton(final List<List<Position>> list, final JLabel label) {
    JButton previous = new JButton("Previous");
    previous.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (count > 0) {
          placeQueens(list.get(--count));
          label.setText(Integer.toString(count + 1));
        } else {
          showMessage("This is where it starts");
        }
      }
    });
    return previous;
  }

  private JButton prepareNextButton(final List<List<Position>> list, final JLabel label) {
    JButton next = new JButton("Next");
    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (count < (list.size() - 1)) {
          placeQueens(list.get(++count));
          label.setText(Integer.toString(count + 1));
        } else {
          showMessage("This is where it ends.");
        }
      }
    });
    return next;
  }

  private JComboBox prepareStyleCombo() {
    JComboBox combo = new JComboBox();
    combo.addItem("Default");
    combo.addItem("Nimbus");
    combo.addItem("Metal");
    final JFrame parent = this;
    combo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String item = (String) ((JComboBox) e.getSource()).getSelectedItem();
        if ("Default".equals(item)) {
          Utils.setLookAndFeel(1, parent);
        } else if ("Nimbus".equals(item)) {
          Utils.setLookAndFeel(2, parent);
        } else if ("Metal".equals(item)) {
          Utils.setLookAndFeel(3, parent);
        }
      }
    });
    return combo;
  }

  private JComboBox prepareNCombo() {
    JComboBox combo = new JComboBox();
    for (int i = 4; i < 11; i++) {
      combo.addItem(i);
    }
    combo.setSelectedItem(Rules.LIMIT);
    final GuiFrame parent = this;
    combo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int newLimit = (Integer) ((JComboBox) e.getSource()).getSelectedItem();
        if (Rules.LIMIT != newLimit) {
          Rules.LIMIT = newLimit;
          parent.draw(new MovesCalculator(new ArrayDeque<Position>(), new ArrayList<List<Position>>()).process());
        }
      }
    });
    return combo;
  }

  private void placeQueens(List<Position> list) {
    for (int i = 0; i < Rules.LIMIT; i++) {
      for (int j = 0; j < Rules.LIMIT; j++) {
        JLabel label = (JLabel) ((JPanel) getContentPane().getComponent(0)).getComponent(i * Rules.LIMIT + j);
        label.setIcon(null);
        if (list.get(i).checkPosition(i + 1, j + 1)) {
          ImageIcon icon = new ImageIcon(this.getClass().getResource("/queen.png"));
          label.setIcon(icon);
        }
      }
    }
  }
}
