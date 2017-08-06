/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStart extends JFrame {
    URL url1;
    URL url2;
    ImageIcon gameStart;
    ImageIcon exit;
    URL url3;
    URL url4;
    ImageIcon continue1;
    ImageIcon music;
    JPanel panel = new JPanel();
    JLabel label2;
    JPanel panel2 = new JPanel();
    JLabel label3;
    JPanel panel3 = new JPanel();
    JLabel label4;
    JPanel panel4 = new JPanel();
    JLabel label5;

    public GameStart() {
        this.setLayout(null);
        this.setTitle("Monopoly");
        URL tubiao = this.getClass().getResource("image/tubiao.png");// 设置界面图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(tubiao));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (Main.music.isOn() == true) {
                    Main.music.getAau().stop();
                }
            }
        });
        URL background = this.getClass().getResource("image/background2.png");// 设置游戏背景颜色,放在层面板中
        ImageIcon icon = new ImageIcon(background);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        this.setBounds(0, 0, label.getWidth(), label.getHeight());
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel jp = (JPanel) this.getContentPane();// 内容面板设置为透明
        jp.setOpaque(false);
        url1 = getClass().getResource("image/gamestart2.png");
        gameStart = new ImageIcon(url1);
        label2 = new JLabel(gameStart);
        label2.addMouseListener(new Listener());
        panel.add(label2);

        url2 = getClass().getResource("image/exit2.png");
        exit = new ImageIcon(url2);
        label3 = new JLabel(exit);
        label3.addMouseListener(new Listener());
        panel2.add(label3);

        url3 = getClass().getResource("image/continue2.png");
        continue1 = new ImageIcon(url3);
        label4 = new JLabel(continue1);
        label4.addMouseListener(new Listener());
        panel3.add(label4);

        url4 = getClass().getResource("image/music2.png");
        music = new ImageIcon(url4);
        label5 = new JLabel(music);
        label5.addMouseListener(new Listener());
        panel4.add(label5);

        panel.setOpaque(false);
        panel.setBounds(50, 400, 250, 250);
        panel2.setOpaque(false);
        panel2.setBounds(850, 265, 250, 250);
        panel3.setOpaque(false);
        panel3.setBounds(300, 350, 250, 250);
        panel4.setOpaque(false);
        panel4.setBounds(600, 460, 250, 250);
        this.add(panel);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);

    }

    private class Listener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == label2) {
                Main.frame.setVisible(true);
                close();
            } else if (e.getSource() == label3) {
                close();
            } else if (e.getSource() == label4) {
                read();
                Main.frame.getMapPanel().showPlayer(
                        Main.frame.getMapPanel().getHouses(),
                        Main.frame.getMapPanel().getPresents(),
                        Main.frame.getMapPanel().getBanks(),
                        Main.frame.getMapPanel().getLottery(),
                        Main.frame.getMapPanel().getSendTicket());
                Main.frame.setVisible(true);
                close();
            } else if (e.getSource() == label5) {
                Main.frame3.setVisible(true);
            }
        }

        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == label2) {
                url1 = getClass().getResource("image/gamestart.png");
                gameStart = new ImageIcon(url1);
                label2.setIcon(gameStart);
            } else if (e.getSource() == label3) {
                url2 = getClass().getResource("image/exit.png");
                exit = new ImageIcon(url2);
                label3.setIcon(exit);
            } else if (e.getSource() == label4) {
                url3 = getClass().getResource("image/continue.png");
                continue1 = new ImageIcon(url3);
                label4.setIcon(continue1);
            } else if (e.getSource() == label5) {
                url4 = getClass().getResource("image/music.png");
                music = new ImageIcon(url4);
                label5.setIcon(music);
            }
        }

        public void mouseExited(MouseEvent e) {
            if (e.getSource() == label2) {
                url1 = getClass().getResource("image/gamestart2.png");
                gameStart = new ImageIcon(url1);
                label2.setIcon(gameStart);
            } else if (e.getSource() == label3) {
                url2 = getClass().getResource("image/exit2.png");
                exit = new ImageIcon(url2);
                label3.setIcon(exit);
            } else if (e.getSource() == label4) {
                url3 = getClass().getResource("image/continue2.png");
                continue1 = new ImageIcon(url3);
                label4.setIcon(continue1);
            } else if (e.getSource() == label5) {
                url4 = getClass().getResource("image/music2.png");
                music = new ImageIcon(url4);
                label5.setIcon(music);
            }
        }

    }

    public void close() {
        this.dispose();
    }

    public void read() {
        try {
            JFileChooser chooser = new JFileChooser();
            File file = null;
            chooser.setDialogTitle("Open JPEG file");
            int result = chooser.showOpenDialog(null); // 打开"打开文件"对话框
            if (result == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
            }
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            Player.round = in.readInt();
            Player.player1.setCash(in.readInt());
            Player.player1.setDeposit(in.readInt());
            Player.player1.setTicket(in.readInt());
            Player.player1.setStock(in.readInt());
            Player.player1.setLocation(in.readInt());
            Player.player1.changeDirection(in.readUTF());
            if (Player.player1.getDirection().equals("逆时针")) {
                Player.player1.changeB();
            }

            Player.player2.setCash(in.readInt());
            Player.player2.setDeposit(in.readInt());
            Player.player2.setTicket(in.readInt());
            Player.player2.setStock(in.readInt());
            Player.player2.setLocation(in.readInt());
            Player.player2.changeDirection(in.readUTF());
            if (Player.player2.getDirection().equals("逆时针")) {
                Player.player2.changeB();
            }

            House[] house = Main.frame.getMapPanel().getHouses();
            for (int i = 0; i < house.length; i++) {
                house[i].setLevel(in.readInt());
            }
            String[] str = new String[house.length];
            for (int i = 0; i < house.length; i++) {
                str[i] = in.readUTF();
            }
            for (int i = 0; i < str.length; i++) {
                if (str[i].equals(Player.player1.getName())) {
                    house[i].setOwner(Player.player1);
                    Player.player1.addHouse();
                    Player.player1.getHouseList().add(house[i]);
                } else if (str[i].equals(Player.player2.getName())) {
                    house[i].setOwner(Player.player2);
                    Player.player2.addHouse();
                    Player.player2.getHouseList().add(house[i]);
                }
            }
            ArrayList<Card> player1Card = new ArrayList<Card>();
            Card[] card = new Card[4];
            card[0] = new TurnCard(1, "转向卡", in.readInt(), false);
            card[1] = new ControlDice(2, "遥控骰子", in.readInt(), false);
            card[2] = new TaxCard(3, "查税卡", in.readInt(), false);
            card[3] = new BuyLandCard(4, "购地卡", in.readInt(), false);
            for (int i = 0; i < card.length; i++) {
                if (card[i].getQuality() > 0)
                    System.out.println(card[i].getQuality());
                player1Card.add(card[i]);
            }

            ArrayList<Card> player2Card = new ArrayList<Card>();
            Card[] card2 = new Card[4];
            card2[0] = new TurnCard(1, "转向卡", in.readInt(), false);
            card2[1] = new ControlDice(2, "遥控骰子", in.readInt(), false);
            card2[2] = new TaxCard(3, "查税卡", in.readInt(), false);
            card2[3] = new BuyLandCard(4, "购地卡", in.readInt(), false);
            for (int i = 0; i < card2.length; i++) {
                if (card2[i].getQuality() > 0)
                    player2Card.add(card2[i]);
            }
            Player.player1.setCards(card);
            Player.player1.setCardList(player1Card);

            Player.player2.setCards(card2);
            Player.player2.setCardList(player2Card);

            Main.frame.getMapPanel().showPlayer(
                    Main.frame.getMapPanel().getHouses(),
                    Main.frame.getMapPanel().getPresents(),
                    Main.frame.getMapPanel().getBanks(),
                    Main.frame.getMapPanel().getLottery(),
                    Main.frame.getMapPanel().getSendTicket());// 地图上显示位置
            for (int i = 0; i < house.length; i++) {
                house[i].getMark().changeIcon(house[i]);
            }
            System.out.println(Player.round);
            Main.frame.getInfo().changePlayerInfo();
            Main.frame.getInfo().changeTimeInfo();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

