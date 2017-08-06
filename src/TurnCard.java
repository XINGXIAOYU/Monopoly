/**
 * Created by xingxiaoyu on 16/6/2.
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class TurnCard extends Card {
    JDialog choose;
    JRadioButton p1 ;
    JRadioButton p2 ;

    public TurnCard(int id, String name, int quality, boolean isUsed) {
        super(id, name, quality, isUsed);
        choose = new JDialog();
        p1 = new JRadioButton("丸子");
        p2 = new JRadioButton("花轮");
        choose.setLayout(new GridLayout(2, 1));
        choose.add(p1);
        choose.add(p2);
        p1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p1.isSelected() == true) {
                    if (Player.round % 2 == 1) {
                        Player.player1.turning(Player.player1);
                        minusQuality();
                        setUsed(true);
                        choose.dispose();
                        Main.frame.getMenu().getTool().show.dispose();
                        Player.player1.ressetCardList2();
                    } else {
                        int distance = 0;
                        if(Math.abs(Player.player1.getLocation()
                                - Player.player2.getLocation())<=19){
                            distance =Math.abs(Player.player1.getLocation()
                                    - Player.player2.getLocation());
                        }else{
                            distance =38-Math.abs(Player.player1.getLocation()
                                    - Player.player2.getLocation());
                        }
                        if (distance <= 5) {
                            Player.player2.turning(Player.player1);
                            minusQuality();
                            setUsed(true);
                            choose.dispose();
                            Main.frame.getMenu().getTool().show.dispose();
                            //Player.player1.ressetCardList2();
                            Player.player2.ressetCardList2();
                        } else{
                            JOptionPane.showMessageDialog(null, "距离太远！");
                        }
                    }
                }
            }

        });
        p2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p2.isSelected() == true) {
                    if (Player.round % 2 == 0) {
                        Player.player2.turning(Player.player2);
                        minusQuality();
                        setUsed(true);
                        choose.dispose();
                        Main.frame.getMenu().getTool().show.dispose();
                        //Player.player1.ressetCardList2();
                        Player.player2.ressetCardList2();
                    } else {
                        int distance = 0;
                        if(Math.abs(Player.player1.getLocation()
                                - Player.player2.getLocation())<=19){
                            distance =Math.abs(Player.player1.getLocation()
                                    - Player.player2.getLocation());
                        }else{
                            distance =38-Math.abs(Player.player1.getLocation()
                                    - Player.player2.getLocation());
                        }
                        if (distance <= 5) {
                            Player.player1.turning(Player.player2);
                            minusQuality();
                            setUsed(true);
                            choose.dispose();
                            Main.frame.getMenu().getTool().show.dispose();
                            Player.player1.ressetCardList2();
                            Player.player2.ressetCardList2();
                        } else{
                            JOptionPane.showMessageDialog(null, "距离太远！");
                        }

                    }
                }
            }
        });
    }

    public void use() {
        choose.setVisible(true);
        choose.setSize(200, 100);
        choose.setLocation(400, 250);
        p1.setSelected(false);
        p2.setSelected(false);
    }
}

