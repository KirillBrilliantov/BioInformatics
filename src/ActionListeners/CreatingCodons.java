package ActionListeners;

import Database.DataBase;
import Start.Start;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatingCodons implements ActionListener {
    private Color mixedColor(Color a, Color b){
        return new Color((a.getRed()+b.getRed())%256, (a.getGreen()+b.getGreen())%256, (a.getBlue()+b.getBlue())%256);
    }
    private boolean isOneCodonChange(String o1, String o2) {
        boolean flag1 = o1.substring(0, 2).compareTo(o2.substring(0, 2)) == 0;
        boolean flag2 = o1.substring(1, 3).compareTo(o2.substring(1, 3)) == 0;
        boolean flag3 = o1.charAt(0) == o2.charAt(0) && o1.charAt(2) == o2.charAt(2);
        if (flag1 && flag2) {
            return false;
        }

        return flag1 || flag2 || flag3;

    }

    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.WHITE};

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton tmp = (JButton) e.getSource();
        String key = tmp.getName();
        int x = tmp.getX();
        int y = tmp.getY();

        DataBase db = Start.getDb();
        int k = -1;
        for (int i = 0; i < db.getDatabase().size(); i++) {
            if (key.compareTo(db.getDatabase().get(i).getTitle()) == 0) {
                k = i;
            }
        }
        if (k == -1) {
            return;
        }
        if (Start.isCompareMode()) {
            JPanel panel = new JPanel(new FlowLayout(1, 1, 5));
            panel.setName(key);
            panel.setBackground(Start.getGui().getPanelBottom().getBackground());
            for (String s : db.getDatabase().get(k).getCodons()) {
                JLabel lb = new JLabel(s);
                lb.setName(s);
                panel.add(lb);
            }
            panel.setBounds(x + tmp.getWidth() / 4, y + tmp.getHeight(), 30, panel.getComponents().length * 25);

            if (Start.panel1 == null) {
                Start.panel1 = panel;
                Start.getGui().getPanelBottom().add(Start.panel1, 2);
                Start.setPreviousList(2);
            } else if (Start.panel2 == null) {
                if (Start.panel1.getName().compareTo(panel.getName()) != 0) {
                    Start.panel2 = panel;
                    Start.setPreviousList(1);
                    Start.getGui().getPanelBottom().add(Start.panel2, 2);
                } else {
                    Start.panel1.setVisible(false);
                    Start.panel1 = null;
                }
            } else if (Start.panel1.getName().compareTo(panel.getName()) == 0) {
                Start.panel1.setVisible(false);
                Start.panel1.removeAll();
                Start.panel1 = null;
            } else if (Start.panel2.getName().compareTo(panel.getName()) == 0) {
                Start.panel2.setVisible(false);
                Start.panel2.removeAll();
                Start.panel2 = null;
            } else {
                if (Start.getPreviousList() == 1) {
                    Start.panel1.setVisible(false);
                    Start.panel1 = panel;
                    Start.getGui().getPanelBottom().add(Start.panel1, 2);
                    Start.setPreviousList(2);
                } else {
                    Start.panel2.setVisible(false);
                    Start.panel2 = panel;
                    Start.getGui().getPanelBottom().add(Start.panel2, 2);
                    Start.setPreviousList( 1);
                }
            }


            if (Start.panel1 != null) {
                for (int i = 0; i < Start.panel1.getComponentCount(); i++) {
                    Start.panel1.getComponent(i).setForeground(Color.BLACK);
                }
            }
            if (Start.panel2 != null) {
                for (int i = 0; i < Start.panel2.getComponentCount(); i++) {
                    Start.panel2.getComponent(i).setForeground(Color.BLACK);
                }
            }
            if (Start.panel1 != null && Start.panel2 != null) {

                if (Start.panel1.getName().compareTo(Start.panel2.getName()) != 0) {
                    int count1 = 0;

                    for (int i = 0; i < Start.panel1.getComponents().length; i++) {
                        boolean flag = false;

                        Component comp1 = Start.panel1.getComponents()[i];
                        for (int j = 0; j < Start.panel2.getComponents().length; j++) {
                            Component comp2 = Start.panel2.getComponents()[j];
                            if (isOneCodonChange(comp1.getName(), comp2.getName())) {
                                flag = true;
                                if (comp2.getForeground() != Color.BLACK) {
                                   comp1.setForeground(comp2.getBackground());
                                } else {
                                    if (comp1.getForeground() != Color.BLACK) {
                                        comp2.setForeground(colors[count1]);
                                        comp1.setForeground(mixedColor(comp1.getForeground(), colors[count1]));
                                    } else {
                                        comp1.setForeground(colors[count1]);
                                        comp2.setForeground(colors[count1]);
                                    }
                                }
                            }
                        }
                        if (flag) {
                            count1++;
                        }

                    }
                }

            }
            Start.getGui().getPanelBottom().repaint();
            Start.getGui().getPanelBottom().revalidate();
            Start.getGui().getContentPane().setFocusable(true);

        } else {
            if (Start.panel1 != null) {
                Start.panel1.setVisible(false);
                Start.panel1 = null;
            }
            if (Start.panel2 != null) {
                Start.panel2.setVisible(false);
                Start.panel2 = null;
            }
            JPopupMenu pop = new JPopupMenu();
            JPanel panel = new JPanel(new GridLayout(db.getDatabase().get(k).getCodons().length, 1, 0, 10));
            for (int i = 0; i < db.getDatabase().get(k).getCodons().length; i++) {
                JLabel label = new JLabel(db.getDatabase().get(k).getCodons()[i]);
                panel.add(label);
            }
            pop.add(panel);
            pop.show(Start.getGui().getPanelBottom(), x + tmp.getWidth() / 4, y + tmp.getHeight());
            tmp.setFocusable(false);
            pop.setFocusable(false);
            Start.getGui().getContentPane().setFocusable(true);
        }


    }
}

