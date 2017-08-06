/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map extends JPanel {
    private House[] houses = new House[28];// ok
    private Present[] presents = new Present[2];// ok
    private Bank[] banks = new Bank[2];// ok
    private Lottery[] lottery = new Lottery[1];
    private SendTicket[] tickets = new SendTicket[5];// ok
    private Dice dice = new Dice();// ok

    private JPanel musicStop = new JPanel();
    private JPanel musicNext = new JPanel();
    private JPanel musicLast = new JPanel();
    private JPanel musicStart = new JPanel();
    private JLabel tips = new JLabel();

    public Map() {
        houses[0] = new House(0, "巴黎贝甜", 2000, 0, Position.HOUSE, null);
        houses[1] = new House(1, "可颂坊", 2000, 0, Position.HOUSE, null);
        houses[2] = new House(2, "赫兹", 2000, 0, Position.HOUSE, null);
        houses[3] = new House(3, "面包新语", 2000, 0, Position.HOUSE, null);
        houses[4] = new House(5, "85度C", 2000, 0, Position.HOUSE, null);
        houses[5] = new House(6, "红宝石", 2000, 0, Position.HOUSE, null);
        houses[6] = new House(7, "贝儿多爸爸", 3000, 0, Position.HOUSE, null);
        houses[7] = new House(8, "西树泡芙", 3000, 0, Position.HOUSE, null);
        houses[8] = new House(9, "鲷鱼烧", 3000, 0, Position.HOUSE, null);
        houses[9] = new House(11, "食之秘", 4000, 0, Position.HOUSE, null);
        houses[10] = new House(12, "俏江南", 5000, 0, Position.HOUSE, null);
        houses[11] = new House(13, "避风塘", 4000, 0, Position.HOUSE, null);
        houses[12] = new House(14, "味千拉面", 4000, 0, Position.HOUSE, null);
        houses[13] = new House(15, "陌生人火锅", 4000, 0, Position.HOUSE, null);
        houses[14] = new House(16, "CoCo奶茶", 3000, 0, Position.HOUSE, null);
        houses[15] = new House(18, "鲜芋仙", 3000, 0, Position.HOUSE, null);
        houses[16] = new House(19, "芋贵人", 3000, 0, Position.HOUSE, null);
        houses[17] = new House(20, "必胜客", 3000, 0, Position.HOUSE, null);
        houses[18] = new House(21, "肯德基", 3000, 0, Position.HOUSE, null);
        houses[19] = new House(22, "周黑鸭", 2000, 0, Position.HOUSE, null);
        houses[20] = new House(24, "来伊份", 2000, 0, Position.HOUSE, null);
        houses[21] = new House(25, "泰康食品", 2000, 0, Position.HOUSE, null);
        houses[22] = new House(26, "小杨生煎", 1000, 0, Position.HOUSE, null);
        houses[23] = new House(27, "麻辣火锅", 1000, 0, Position.HOUSE, null);
        houses[24] = new House(28, "辣炒年糕", 1000, 0, Position.HOUSE, null);
        houses[25] = new House(35, "蜀府", 2000, 0, Position.HOUSE, null);
        houses[26] = new House(36, "萨莉亚", 2000, 0, Position.HOUSE, null);
        houses[27] = new House(37, "顺风酒店", 5000, 0, Position.HOUSE, null);
        presents[0] = new Present(4, "NEWTON赠送道具点", Position.GET_PROP);
        presents[1] = new Present(29, "EINSTEIN赠送道具点", Position.GET_PROP);
        banks[0] = new Bank(10, "建设银行", Position.BANK);
        banks[1] = new Bank(17, "农业银行", Position.BANK);
        lottery[0] = new Lottery(23, "证券中心", Position.LOTTERY);
        tickets[0] = new SendTicket(30, "赠送点券点", Position.GET_TICKET);
        tickets[1] = new SendTicket(31, "赠送点券点", Position.GET_TICKET);
        tickets[2] = new SendTicket(32, "赠送点券点", Position.GET_TICKET);
        tickets[3] = new SendTicket(33, "赠送点券点", Position.GET_TICKET);
        tickets[4] = new SendTicket(34, "赠送点券点", Position.GET_TICKET);
        this.setLayout(null);
        for (int i = 0; i < houses.length; i++) {
            if (i >= 0 && i <= 3) {
                houses[i].setBounds(50 + 50 * i, 80, 50, 50);
                houses[i].getMark().setBounds(50 + 50 * i, 30, 50, 50);
            } else if (i >= 4 && i <= 5) {
                houses[i].setBounds(385 + 50 * (i - 4), 80, 50, 50);
                houses[i].getMark().setBounds(385 + 50 * (i - 4), 30, 50, 50);
            } else if (i >= 6 && i <= 8) {
                houses[i].setBounds(435 + 50 * (i - 6), 130, 50, 50);
                houses[i].getMark().setBounds(435 + 50 * (i - 6), 180, 50, 50);
            } else if (i >= 9 && i <= 10) {
                houses[i].setBounds(735 + 50 * (i - 9), 130, 50, 50);
                houses[i].getMark().setBounds(735 + 50 * (i - 9), 80, 50, 50);
            } else if (i >= 11 && i <= 14) {
                houses[i].setBounds(785, 130 + (i - 10) * 50, 50, 50);
                houses[i].getMark().setBounds(785 + 50, 130 + (i - 10) * 50,
                        50, 50);
            } else if (i >= 15 && i <= 16) {
                houses[i].setBounds(740 - 50 * (i - 14), 480, 50, 50);
                houses[i].getMark().setBounds(740 - 50 * (i - 14), 530, 50, 50);
            } else if (i >= 17 && i <= 19) {
                houses[i].setBounds(640 - 50 * (i - 17), 430, 50, 50);
                houses[i].getMark().setBounds(640 - 50 * (i - 17), 380, 50, 50);
            } else if (i >= 20 && i <= 21) {
                houses[i].setBounds(390, 430 + 50 * (i - 19), 50, 50);
                houses[i].getMark().setBounds(440, 430 + 50 * (i - 19), 50, 50);
            } else if (i >= 22 && i <= 24) {
                houses[i].setBounds(390 - 50 * (i - 21), 530, 50, 50);
                houses[i].getMark().setBounds(390 - 50 * (i - 21), 580, 50, 50);
            } else if (i >= 25 && i <= 27) {
                houses[i].setBounds(50, 280 - 50 * (i - 24), 50, 50);
                houses[i].getMark().setBounds(0, 280 - 50 * (i - 24), 50, 50);
            }

            this.add(houses[i]);
            this.add(houses[i].getMark());

        }
        for (int i = 0; i < tickets.length; i++) {
            if (i == 0)
                tickets[i].setBounds(100, 430, 50, 50);
            else if (i >= 1 && i <= 4)
                tickets[i].setBounds(50, 430 - 50 * (i - 1), 50, 50);
            this.add(tickets[i]);
        }
        presents[0].setBounds(50 + 50 * 4, 80, 135, 135);
        this.add(presents[0]);
        presents[1].setBounds(100, 480, 135, 135);
        this.add(presents[1]);
        banks[0].setBounds(585, 85, 150, 150);
        this.add(banks[0]);
        banks[1].setBounds(740, 380, 150, 150);
        this.add(banks[1]);
        lottery[0].setBounds(390, 330, 150, 150);
        this.add(lottery[0]);
        dice.setBounds(150, 230, 250, 250);
        this.add(dice);

        tips.setBounds(785 + 50, 50, 250, 30);
        this.add(tips);
        URL url1 = this.getClass().getResource("image/start.png");
        ImageIcon i1 = new ImageIcon(url1);
        JLabel music1 = new JLabel(i1);
        musicStart.add(music1);
        musicStart.setBounds(785 + 107, 20, 30, 30);
        musicStart.addMouseListener(new Listener());
        this.add(musicStart);
        musicStart.setOpaque(false);

        URL url2 = this.getClass().getResource("image/pause.png");
        ImageIcon i2 = new ImageIcon(url2);
        JLabel music2 = new JLabel(i2);
        musicStop.add(music2);
        musicStop.setBounds(785 + 80, 20, 30, 30);
        musicStop.addMouseListener(new Listener());
        this.add(musicStop);
        musicStop.setOpaque(false);

        URL url3 = this.getClass().getResource("image/last.png");
        ImageIcon i3 = new ImageIcon(url3);
        JLabel music3 = new JLabel(i3);
        musicNext.add(music3);
        musicNext.setBounds(785 + 50, 20, 30, 30);
        this.add(musicNext);
        musicNext.addMouseListener(new Listener());
        musicNext.setOpaque(false);

        URL url4 = this.getClass().getResource("image/next.png");
        ImageIcon i4 = new ImageIcon(url4);
        JLabel music4 = new JLabel(i4);
        musicLast.add(music4);
        musicLast.setBounds(785 + 135, 20, 30, 30);
        this.add(musicLast);
        musicLast.addMouseListener(new Listener());
        musicLast.setOpaque(false);

    }

    private class Listener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() == musicNext) {
                if (Main.music.isOn() == true) {
                    switch (Main.frame3.getCount()) {
                        case 1:
                            Main.music.getAau().stop();
                            Main.music.play("music2.wav");
                            Main.frame3.setCount(2);
                            break;
                        case 2:
                            Main.music.getAau().stop();
                            Main.music.play("music3.wav");
                            Main.frame3.setCount(3);
                            break;
                        case 3:
                            Main.music.getAau().stop();
                            Main.music.play("music4.wav");
                            Main.frame3.setCount(4);
                            break;
                        case 4:
                            Main.music.getAau().stop();
                            Main.music.play("music5.wav");
                            Main.frame3.setCount(5);
                            break;
                        case 5:
                            Main.music.getAau().stop();
                            Main.music.play("music1.wav");
                            Main.frame3.setCount(1);
                            break;
                    }
                }
            } else if (e.getSource() == musicStop) {
                if (Main.music.isOn() == true) {
                    Main.music.getAau().stop();
                }
            } else if (e.getSource() == musicLast) {
                if (Main.music.isOn() == true) {
                    switch (Main.frame3.getCount()) {
                        case 1:
                            Main.music.getAau().stop();
                            Main.music.play("music5.wav");
                            Main.frame3.setCount(5);
                            break;
                        case 2:
                            Main.music.getAau().stop();
                            Main.music.play("music1.wav");
                            Main.frame3.setCount(1);
                            break;
                        case 3:
                            Main.music.getAau().stop();
                            Main.music.play("music2.wav");
                            Main.frame3.setCount(2);
                            break;
                        case 4:
                            Main.music.getAau().stop();
                            Main.music.play("music3.wav");
                            Main.frame3.setCount(3);
                            break;
                        case 5:
                            Main.music.getAau().stop();
                            Main.music.play("music4.wav");
                            Main.frame3.setCount(4);
                            break;
                    }
                }
            } else if (e.getSource() == musicStart) {
                if (Main.music.isOn() == true) {
                    switch (Main.frame3.getCount()) {
                        case 1:
                            Main.music.getAau().stop();
                            Main.music.play("music1.wav");
                            break;
                        case 2:
                            Main.music.getAau().stop();
                            Main.music.play("music2.wav");
                            break;
                        case 3:
                            Main.music.getAau().stop();
                            Main.music.play("music3.wav");
                            break;
                        case 4:
                            Main.music.getAau().stop();
                            Main.music.play("music4.wav");
                            break;
                        case 5:
                            Main.music.getAau().stop();
                            Main.music.play("music5.wav");
                            break;
                    }
                }else if (Main.music.isOn() == false) {
                    Main.music.play("music1.wav");
                    Main.frame3.setCount(1);
                    Main.music.setOn(true);
                }
            }
        }

    }

    public void showPlayer(House[] house, Present[] presents, Bank[] banks,
                           Lottery[] lottery, SendTicket[] tickets) {
        for (int i = 0; i < house.length; i++) {
            house[i].showPlayer();// 每次扔完骰子后，所有格子中的内容都要重画一遍
        }
        for (int i = 0; i < presents.length; i++) {
            presents[i].showPlayer();
        }
        for (int i = 0; i < banks.length; i++) {
            banks[i].showPlayer();

        }
        for (int i = 0; i < lottery.length; i++) {
            lottery[i].showPlayer();

        }
        for (int i = 0; i < tickets.length; i++) {
            tickets[i].showPlayer();

        }
    }

    public House[] getHouses() {
        return houses;
    }

    public Bank[] getBanks() {
        return banks;
    }

    public Present[] getPresents() {
        return presents;
    }

    public Lottery[] getLottery() {
        return lottery;
    }

    public SendTicket[] getSendTicket() {
        return tickets;
    }

    public Dice getDice() {
        return dice;
    }

    public JLabel getTips() {
        return tips;
    }
}

