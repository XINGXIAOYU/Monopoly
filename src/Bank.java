/**
 * Created by xingxiaoyu on 16/6/2.
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bank extends Position {
    private URL url = getClass().getResource("image/bank.png");
    private ImageIcon image = new ImageIcon(url);

    public Bank(int id, String name, int type) {
        super(id, name, type);
        JLabel label = new JLabel(image);
        label.setSize(image.getIconWidth(), image.getIconHeight());
        this.setOpaque(false);
        this.add(label);
    }

    public static void interest() {
        if (Main.frame.getInfo().getTimeInfo().endOfMonth() == true) {
            if (Player.round % 2 == 1) {
                Player.player1.addDeposit((int) (0.2 * Player.player1
                        .getDeposit()));
            } else {
                Player.player2.addDeposit((int) (0.2 * Player.player2
                        .getDeposit()));
            }
            Main.frame.setTips("今天是月底，银行发放20%的利息！^_^");
        }
    }
}

class WelcomeToBank extends JDialog {
    JButton[] buttons = new JButton[12];
    JButton in = new JButton("存款");
    JButton out = new JButton("取款");
    JButton exit = new JButton("离开");
    JTextField area = new JTextField(10);
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    StringBuilder str = new StringBuilder();
    int x = 0;

    public WelcomeToBank() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(i + 1 + "");
        }

        buttons[9] = new JButton("确定");
        buttons[10] = new JButton(0 + "");
        buttons[11] = new JButton("删除");
        p3.setLayout(new GridLayout(3, 1));
        p3.add(in);
        p3.add(out);
        p3.add(exit);
        p1.add(p3);
        p1.add(area);

        p2.setLayout(new GridLayout(4, 3));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(new Listener());
            p2.add(buttons[i]);
        }
        area.setEnabled(false);
        area.setText(str.toString());
        in.addActionListener(new Listener());
        out.addActionListener(new Listener());
        exit.addActionListener(new Listener());
        this.setLayout(new BorderLayout());
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.setLocation(400, 300);
        this.setSize(400, 250);
    }

    public void close() {
        this.dispose();
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == in) {
                x = 1;
            } else if (e.getSource() == out) {
                x = 2;
            } else if (e.getSource() == exit) {
                close();
                x = 0;
                str.delete(0, str.length());
                if (Player.round % 2 == 1) {
                    if (Player.player1.getCount() < Player.player1.getStep()) {
                        Player.player1.getTimer().start();
                    } else if (Player.player1.getCount() == Player.player1
                            .getStep()) {
                        Player.round++;
                        Main.frame.getMapPanel().getDice().setCurrentImage(0);
                        Main.frame.setTips("现在是花轮的操作时间");
                        Main.frame.getInfo().changeTimeInfo();
                        Main.frame.getInfo().changePlayerInfo();
                        Player.player1.setCount(0);
                    }
                } else if (Player.round % 2 == 0) {
                    if (Player.player2.getCount() < Player.player2.getStep()) {
                        Player.player2.getTimer().start();
                    } else if (Player.player2.getCount() == Player.player2
                            .getStep()) {
                        Player.round++;
                        Main.frame.getMapPanel().getDice().setCurrentImage(0);
                        Main.frame.setTips("现在是丸子的操作时间");
                        Main.frame.getInfo().changeTimeInfo();
                        Main.frame.getInfo().changePlayerInfo();
                        Player.player2.setCount(0);
                    }
                }
            }

            for (int i = 0; i < buttons.length; i++) {
                if (e.getSource() == buttons[9]) {
                    try {
                        if (x == 1) {
                            System.out.print(area.getText());
                            if (Player.round % 2 == 1) {

                                if (Player.player1.getCash() >= Integer
                                        .parseInt(area.getText())) {
                                    Player.player1.addCash(-Integer
                                            .parseInt(area.getText()));
                                    Player.player1.addDeposit(Integer
                                            .parseInt(area.getText()));
                                    JOptionPane.showMessageDialog(null, "成功存入"
                                            + Integer.parseInt(area.getText()));
                                    Main.frame.getInfo().changePlayerInfo2();
                                    close();
                                    x = 0;
                                    str.delete(0, str.length());
                                    area.setText(str.toString());

                                    if (Player.player1.getCount() < Player.player1
                                            .getStep())
                                        Player.player1.getTimer().start();
                                    else if (Player.player1.getCount() == Player.player1
                                            .getStep()) {
                                        Player.round++;
                                        Main.frame.getInfo().changeTimeInfo();
                                        Main.frame.getInfo().changePlayerInfo();
                                        Player.player1.setCount(0);
                                    }

                                } else {
                                    JOptionPane
                                            .showMessageDialog(null, "现金不足！");
                                    str.delete(0, str.length());
                                    area.setText(str.toString());
                                }
                                break;
                            } else if (Player.player2.getCash() >= Integer
                                    .parseInt(area.getText())) {
                                Player.player2.addCash(-Integer.parseInt(area
                                        .getText()));
                                Player.player2.addDeposit(Integer.parseInt(area
                                        .getText()));
                                JOptionPane.showMessageDialog(null, "成功存入"
                                        + Integer.parseInt(area.getText()));
                                Main.frame.getInfo().changePlayerInfo2();
                                //Main.frame.getMapPanel().getDice().setCurrentImage(0);
                                close();
                                x = 0;
                                str.delete(0, str.length());
                                area.setText(str.toString());
                                if (Player.player2.getCount() < Player.player2
                                        .getStep())
                                    Player.player2.getTimer().start();
                                else if (Player.player2.getCount() == Player.player2
                                        .getStep()) {
                                    Player.round++;
                                    Main.frame.getInfo().changeTimeInfo();
                                    Main.frame.getInfo().changePlayerInfo();
                                    Player.player2.setCount(0);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "现金不足！");
                                str.delete(0, str.length());
                                area.setText(str.toString());
                            }
                            break;
                        } else if (x == 2) {
                            if (Player.round % 2 == 1) {
                                if (Player.player1.getDeposit() >= Integer
                                        .parseInt(area.getText())) {
                                    Player.player1.addCash(Integer
                                            .parseInt(area.getText()));
                                    Player.player1.addDeposit(-Integer
                                            .parseInt(area.getText()));
                                    JOptionPane.showMessageDialog(null, "成功取出"
                                            + Integer.parseInt(area.getText()));
                                    Main.frame.getInfo().changePlayerInfo2();
                                    //Main.frame.getMapPanel().getDice().setCurrentImage(0);
                                    close();
                                    x = 0;
                                    str.delete(0, str.length());
                                    area.setText(str.toString());
                                    if (Player.player1.getCount() < Player.player1
                                            .getStep())
                                        Player.player1.getTimer().start();
                                    else if (Player.player1.getCount() == Player.player1
                                            .getStep()) {
                                        Player.round++;
                                        Main.frame.getInfo().changeTimeInfo();
                                        Main.frame.getInfo().changePlayerInfo();
                                        Player.player1.setCount(0);
                                    }

                                } else {
                                    JOptionPane
                                            .showMessageDialog(null, "存款不足！");
                                    str.delete(0, str.length());
                                    area.setText(str.toString());
                                }

                                break;
                            } else {
                                if (Player.player2.getDeposit() >= Integer
                                        .parseInt(area.getText())) {
                                    Player.player2.addCash(Integer
                                            .parseInt(area.getText()));
                                    Player.player2.addDeposit(-Integer
                                            .parseInt(area.getText()));
                                    JOptionPane.showMessageDialog(null, "成功取出"
                                            + Integer.parseInt(area.getText()));
                                    Main.frame.getInfo().changePlayerInfo2();
                                    //Main.frame.getMapPanel().getDice().setCurrentImage(0);
                                    close();
                                    x = 0;
                                    str.delete(0, str.length());
                                    area.setText(str.toString());
                                    if (Player.player2.getCount() < Player.player2
                                            .getStep())
                                        Player.player2.getTimer().start();
                                    else if (Player.player2.getCount() == Player.player2
                                            .getStep()) {
                                        Player.round++;
                                        Main.frame.getInfo().changeTimeInfo();
                                        Main.frame.getInfo().changePlayerInfo();
                                        Player.player2.setCount(0);
                                    }

                                } else {
                                    JOptionPane
                                            .showMessageDialog(null, "存款不足！");
                                    str.delete(0, str.length());
                                    area.setText(str.toString());
                                }
                                break;
                            }

                        } else if (x == 0) {
                            JOptionPane.showMessageDialog(null, "请先选择存款或取款！");
                            str.delete(0, str.length());
                            area.setText(str.toString());
                            break;
                        }
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "没有金额！");
                        break;
                    }
                } else if (e.getSource() == buttons[11]) {
                    str.deleteCharAt(str.length() - 1);
                    area.setText(str.toString());
                    break;
                } else if (e.getSource() == buttons[i]) {
                    str.append(buttons[i].getText());
                    area.setText(str.toString());
                    break;

                }
            }
        }
    }
}

