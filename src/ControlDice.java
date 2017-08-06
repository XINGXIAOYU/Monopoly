/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlDice extends Card {
    private JDialog diceNum = new JDialog();
    private JLabel label = new JLabel("请选择你下一局要扔出的骰子数");
    private JButton[] buttons = new JButton[6];
    private int diceImage;

    public ControlDice(int id, String name, int quality, boolean isUsed) {
        super(id, name, quality, isUsed);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 3));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(i + 1 + "");
            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getSource() == buttons[0]) {
                        diceImage = 0;
                        if (Player.round % 2 == 1) {
                            Main.frame.setTips(Player.player1.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 1");
                        } else if (Player.round % 2 == 0) {
                            Main.frame.setTips(Player.player2.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 1");
                        }
                    } else if (e.getSource() == buttons[1]) {
                        diceImage = 1;
                        if (Player.round % 2 == 1) {
                            Main.frame.setTips(Player.player1.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 2");
                        } else if (Player.round % 2 == 0) {
                            Main.frame.setTips(Player.player2.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 2");
                        }
                    } else if (e.getSource() == buttons[2]) {
                        diceImage = 2;
                        if (Player.round % 2 == 1) {
                            Main.frame.setTips(Player.player1.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 3");
                        } else if (Player.round % 2 == 0) {
                            Main.frame.setTips(Player.player2.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 3");
                        }
                    } else if (e.getSource() == buttons[3]) {
                        diceImage = 3;
                        if (Player.round % 2 == 1) {
                            Main.frame.setTips(Player.player1.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 4");
                        } else if (Player.round % 2 == 0) {
                            Main.frame.setTips(Player.player2.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 4");
                        }
                    } else if (e.getSource() == buttons[4]) {
                        diceImage = 4;
                        if (Player.round % 2 == 1) {
                            Main.frame.setTips(Player.player1.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 5");
                        } else if (Player.round % 2 == 0) {
                            Main.frame.setTips(Player.player2.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 5");
                        }
                    } else if (e.getSource() == buttons[5]) {
                        diceImage = 5;
                        if (Player.round % 2 == 1) {
                            Main.frame.setTips(Player.player1.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 6");
                        } else if (Player.round % 2 == 0) {
                            Main.frame.setTips(Player.player2.getName()
                                    + "使用遥控骰子，下一局扔出的骰子数为 6");
                        }
                    }
                    setUsed(true);
                    minusQuality();
                    diceNum.dispose();
                    Main.frame.getMenu().getTool().show.dispose();
                    Player.player2.ressetCardList2();
                    Player.player1.ressetCardList2();
                }
            });
            p.add(buttons[i]);
        }
        diceNum.setLayout(new BorderLayout(10, 10));
        diceNum.add(label, BorderLayout.NORTH);
        diceNum.add(p, BorderLayout.CENTER);

    }

    @Override
    public void use() {
        diceNum.setVisible(true);
        diceNum.setSize(200, 100);
        diceNum.setLocation(400, 250);
    }

    public int getDiceImage() {
        return diceImage;
    }

    public void setDiceImage(int diceImage) {
        this.diceImage = diceImage;
    }

}

