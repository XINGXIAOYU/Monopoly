/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;

public class Player implements CanBeFined {
    static Player player1 = new Player(50000, 20000, 0, 0, 0, "顺时针");
    static Player player2 = new Player(50000, 20000, 0, 0, 0, "顺时针");
    public static int number = 0;// 玩家创建的次序
    public static int round = 1;// 奇数局为A，偶数局为B
    private String name;// 姓名
    private ImageIcon photo1;// 头像
    private ImageIcon photo2;// 地图上显示的头像
    private ImageIcon photo3;// 罚款时显示的图标头像
    private int cash;// 现金
    private int deposit;// 存款
    private int stock;// 股票
    private int ticket;// 点券
    private int houseNum;// 房屋数量
    private int property;// 总资产
    private int houseValue;// 房屋资产
    private Card[] cards = new Card[4];
    private ArrayList<Card> cardList = new ArrayList<Card>();// 刚开始拥有的卡片
    private ArrayList<House> houseList = new ArrayList<House>();
    private String direction;// 前进方向
    private int location;// 玩家的位置
    private int step;// 玩家前进的步数
    private int count;// 控制一步步前进，直到与step相同
    private Timer timer = new Timer(300, new TimeListener());
    private Mortgage mortgage;
    private boolean nishizhen;
    private boolean bankHere;
    private boolean useDiceCard;
    private int turn = 1;

    private Player(int cash, int deposit, int ticket, int stock, int houseNum,
                   String direction) {// 不允许在外部创建
        number++;
        cards[0] = new TurnCard(1, "转向卡", 5, false);
        cards[1] = new ControlDice(2, "遥控骰子", 5, false);
        cards[2] = new TaxCard(3, "查税卡", 5, false);
        cards[3] = new BuyLandCard(4, "购地卡", 5, false);
        for (int i = 0; i < cards.length; i++) {
            cardList.add(cards[i]);
        }
        if (number == 1) {
            this.name = "丸子";
            URL url1 = this.getClass().getResource("image/photo10.png");
            this.photo1 = new ImageIcon(url1);
            URL url2 = this.getClass().getResource("image/photo11.png");
            this.photo2 = new ImageIcon(url2);
            URL url3 = this.getClass().getResource("image/photo12.png");
            this.photo3 = new ImageIcon(url3);
            this.location = 0;
        } else if (number == 2) {
            this.name = "花轮";
            URL url1 = this.getClass().getResource("image/photo20.png");
            this.photo1 = new ImageIcon(url1);
            URL url2 = this.getClass().getResource("image/photo21.png");
            this.photo2 = new ImageIcon(url2);
            URL url3 = this.getClass().getResource("image/photo22.png");
            this.photo3 = new ImageIcon(url3);
            this.location = 12;
        }
        this.direction = direction;
        this.cash = cash;
        this.deposit = deposit;
        this.ticket = ticket;
        this.stock = stock;
        this.houseNum = houseNum;
    }

    public Player() {

    }

    public void setCards(Card[] card) {
        cards = card;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCardList(ArrayList<Card> array) {
        cardList = array;
    }

    // 一轮结束使用完道具后重置道具列表
    public void ressetCardList() {
        cardList.clear();
        for (int i = 0; i < cards.length; i++) {
            cards[i].setUsed(false);
            if (cards[i].getQuality() > 0) {
                cardList.add(cards[i]);
            }
        }
    }

    // 重复点击重置道具List
    public void ressetCardList2() {
        cardList.clear();
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getQuality() > 0 && cards[i].getUsed() == false) {
                cardList.add(cards[i]);
            }
        }
    }

    // 重置房屋列表
    public void ressetHouseList() {
        player1.houseList.clear();
        player2.houseList.clear();
        House[] houses = Main.frame.getMapPanel().getHouses();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i].getOwner() == player1) {
                player1.houseList.add(houses[i]);
            } else if (houses[i].getOwner() == player2) {
                player2.houseList.add(houses[i]);
            }
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<House> getHouseList() {
        return houseList;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getPhoto() {
        return photo1;
    }

    public ImageIcon getIcon() {
        return photo2;
    }

    public int getCash() {
        return cash;
    }

    public void addCash(int cash) {
        this.cash = this.cash + cash;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public Mortgage getMortgage() {
        return mortgage;
    }

    public void addDeposit(int deposit) {
        this.deposit = this.deposit + deposit;
    }

    public int getStock() {
        return stock;
    }

    public int getTicket() {
        return ticket;
    }

    public void addTicket(int ticket) {
        if (ticket >= 0) {
            this.ticket = this.ticket + ticket;
        } else {
            this.ticket = this.ticket - ticket;
        }
    }

    public ImageIcon getPhoto3() {
        return photo3;
    }

    public void addHouse() {
        this.houseNum++;
    }

    public void minusHouse() {
        this.houseNum--;
    }

    public int getProperty() {
        property = cash + deposit + getHouseValue();// 现金＋存款＋房屋价值
        return property;
    }

    public String getDirection() {
        return direction;
    }

    public int getStep() {
        return step;
    }

    public int getLocation() {
        return location;
    }

    public void changeDirection(String direction) {
        this.direction = direction;
    }

    public Timer getTimer() {
        return timer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int x) {
        count = x;
    }

    public void goForward() {
        step = Main.frame.getMapPanel().getDice().getCurrentImage() + 1;
        timer.start();
    }

    public void changeB() {
        nishizhen = true;
    }

    public void changeF() {
        nishizhen = false;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void setLocation(int x) {
        this.location = x;
    }

    public boolean getUseDiceCard() {
        return useDiceCard;
    }

    public void setUseDiceCard(boolean useDiceCard) {
        this.useDiceCard = useDiceCard;
    }

    public ControlDice getControlDice() {
        return (ControlDice) cards[1];
    }

    public void turning(Player player) {
        if (player.turn == 1) {
            player.changeDirection("逆时针");
            player.nishizhen = true;
            player.turn++;
        } else if (player.turn == 2) {
            player.changeDirection("顺时针");
            player.nishizhen = false;
            player.turn--;

        }
        Main.frame.setTips("由于转向卡的使用" + player.getName() + "的前进方向为"
                + player.getDirection());
    }

    public void setbankHere(boolean x) {
        bankHere = x;
    }

    public int getHouseValue() {
        int sum = 0;
        for (int i = 0; i < houseList.size(); i++) {
            sum = sum + houseList.get(i).getPrice();
        }
        houseValue = sum;
        return houseValue;
    }

    private class TimeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            count++;
            if (nishizhen == false) {
                location++;
                location = location % 38;
            } else if (nishizhen == true) {
                location--;
                if (location < 0) {
                    location = (location % 38) + 38;
                }
            }
            // 地图上显示玩家的位置
            Main.frame.getMapPanel().showPlayer(
                    Main.frame.getMapPanel().getHouses(),
                    Main.frame.getMapPanel().getPresents(),
                    Main.frame.getMapPanel().getBanks(),
                    Main.frame.getMapPanel().getLottery(),
                    Main.frame.getMapPanel().getSendTicket());
            // 检查当前情况（银行，路障）（路障已删除）
            Bank[] bank = Main.frame.getMapPanel().getBanks();
            for (int i = 0; i < bank.length; i++) {
                if (round % 2 == 1 && bank[i].getID() == player1.getLocation()) {
                    timer.stop();
                    player1.welcomeToBank(bank[i]);
                    if (count == step) {
                        bankHere = true;
                    } else {
                        bankHere = false;
                    }
                } else if (round % 2 == 0
                        && bank[i].getID() == player2.getLocation()) {
                    timer.stop();
                    player2.welcomeToBank(bank[i]);
                    if (count == step) {
                        bankHere = true;
                    } else {
                        bankHere = false;
                    }
                }
            }
            if (count == step && bankHere == false) {
                timer.stop();
                // 检查最终目的地的情况(买土地，道具点，券点⋯⋯)
                upGradeHouse();
                buyHouse();
                beFined();
                Present[] pre = Main.frame.getMapPanel().getPresents();
                for (int i = 0; i < pre.length; i++) {
                    if (round % 2 == 1 && pre[i].getID() == player1.location) {
                        player1.getTools();
                    } else if (round % 2 == 0
                            && pre[i].getID() == player2.location) {
                        player2.getTools();
                    }
                }
                Lottery[] lottery = Main.frame.getMapPanel().getLottery();
                for (int i = 0; i < lottery.length; i++) {
                    if (location == lottery[i].getID()) {
                        JOptionPane.showMessageDialog(null, "股票市场正在建设中⋯⋯");
                    }
                }
                SendTicket[] sendTickets = Main.frame.getMapPanel()
                        .getSendTicket();
                for (int i = 0; i < sendTickets.length; i++) {
                    if (location == sendTickets[i].getID()) {
                        int a = sendTickets[i].sendTicket();
                        JOptionPane.showMessageDialog(null, "恭喜你获得" + a
                                + "张点券！");
                        if (round % 2 == 1) {
                            player1.addTicket(a);
                        } else {
                            player2.addTicket(a);
                        }
                    }
                }
                round++;
                if (round % 2 == 1) {
                    Main.frame.setTips("现在是" + Player.player1.getName()
                            + "的操作时间");

                } else {
                    Main.frame.setTips("现在是" + Player.player2.getName()
                            + "的操作时间");
                }
                Main.frame.getMapPanel().getDice().setCurrentImage(0);
                Main.frame.getInfo().changeTimeInfo();
                Main.frame.getInfo().changePlayerInfo();
                count = 0;
                setUseDiceCard(false);
                Player.player1.ressetCardList();
                Player.player1.ressetHouseList();
                Player.player2.ressetCardList();
                Player.player2.ressetHouseList();
            } else if (count == step && bankHere == true) {
                timer.stop();
                bankHere = false;
                Player.player1.ressetCardList();
                Player.player2.ressetCardList();
                setUseDiceCard(false);
            }
        }
    }

    // 来到银行
    public void welcomeToBank(Bank bank) {
        WelcomeToBank welcome = new WelcomeToBank();
        welcome.setVisible(true);
    }

    // 使用道具
    public void useTool(int x) {
        if (x == cards[0].getID()) {
            if (cards[0] instanceof TurnCard) {
                ((TurnCard) cards[0]).use();
            }
        } else if (x == cards[1].getID()) {
            if (cards[1] instanceof ControlDice) {
                ((ControlDice) cards[1]).use();
                setUseDiceCard(true);
            }
        } else if (x == cards[2].getID()) {
            if (cards[2] instanceof TaxCard) {
                ((TaxCard) cards[2]).use();
            }
        } else if (x == cards[3].getID()) {
            if (cards[3] instanceof BuyLandCard) {
                ((BuyLandCard) cards[3]).use();
                Main.frame.getInfo().changePlayerInfo2();
            }
        }
    }

    // 赠送道具点
    public void getTools() {
        int random = (int) (Math.random() * 4) + 1;
        switch (random) {
            case 1:
                JOptionPane.showMessageDialog(null, "恭喜你获得转向卡");
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "恭喜你获得遥控骰子");
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "恭喜你获得查税卡");
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "恭喜你获得购地卡");
                break;
        }
        if (Player.round % 2 == 1) {
            Card[] cards = player1.getCards();
            cards[random - 1].addQuality();
        } else if (Player.round % 2 == 0) {
            Card[] cards = player2.getCards();
            cards[random - 1].addQuality();
        }
    }

    // 买房子
    public void buyHouse() {
        House[] houses = Main.frame.getMapPanel().getHouses();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i].getID() == location && houses[i].canBeSold() == true) {// 到达房屋点并且房屋是可以出售情况下
                int result = JOptionPane.showConfirmDialog(null, "这里是"
                        + houses[i].getName() + "它的价格为" + houses[i].getPrice()
                        + "你是否要买下此处房屋", "Buy House", JOptionPane.YES_NO_OPTION);
                if (round % 2 == 1 && result == JOptionPane.YES_OPTION) {
                    if (player1.getCash() >= houses[i].getPrice()) {
                        JOptionPane.showMessageDialog(null, "恭喜你，成功买下"
                                + houses[i].getName());
                        // houseList.add(houses[i]);
                        player1.addCash(-houses[i].getPrice());
                        player1.addHouse();
                        houses[i].setOwner(player1);
                        houses[i].getMark().changeIcon(houses[i]);
                        Main.frame.getInfo().changePlayerInfo2();
                    } else {
                        JOptionPane.showMessageDialog(null, "现金不足，无法购买!");
                    }
                } else if (round % 2 == 0 && result == JOptionPane.YES_OPTION) {
                    if (player2.getCash() >= houses[i].getPrice()) {
                        JOptionPane.showMessageDialog(null, "恭喜你，成功买下"
                                + houses[i].getName());
                        // houseList.add(houses[i]);
                        player2.addCash(-houses[i].getPrice());
                        player2.addHouse();
                        houses[i].setOwner(player2);
                        houses[i].getMark().changeIcon(houses[i]);
                        Main.frame.getInfo().changePlayerInfo2();
                    } else {
                        JOptionPane.showMessageDialog(null, "现金不足，无法购买!");
                    }
                }
                break;
            }
        }
    }

    // 升级房屋
    public void upGradeHouse() {
        label: for (int i = 0; i < houseList.size(); i++) {
            if (houseList.get(i).getID() == location
                    && houseList.get(i).canBeUpgraded(player1) == true
                    && round % 2 == 1) {
                int result = JOptionPane.showConfirmDialog(null, "这里是"
                                + houseList.get(i).getName() + "，它现在的等级为"
                                + houseList.get(i).getLevel() + " 升级费用为"
                                + (int) (0.2 * houseList.get(i).getPrice())
                                + "你是否要升级此处房屋？", "Upgrade House",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (player1.getCash() >= (int) (0.2 * houseList.get(i)
                            .getPrice())) {
                        player1.addCash(-(int) (0.2 * houseList.get(i)
                                .getPrice()));
                        houseList.get(i).addLevel();
                        JOptionPane.showMessageDialog(null, "恭喜你，成功升级"
                                + houseList.get(i).getName() + ",现在的等级为"
                                + houseList.get(i).getLevel());
                        houseList.get(i).getMark().changeIcon(houseList.get(i));
                    } else {
                        JOptionPane.showMessageDialog(null, "现金不足，无法升级!");
                    }
                }
                break label;
            } else if (houseList.get(i).getID() == location
                    && houseList.get(i).canBeUpgraded(player2) == true
                    && round % 2 == 0) {
                int result = JOptionPane.showConfirmDialog(null, "这里是"
                                + houseList.get(i).getName() + "，它现在的等级为"
                                + houseList.get(i).getLevel() + " 升级费用为"
                                + (int) (0.2 * houseList.get(i).getPrice())
                                + "你是否要升级此处房屋？", "Upgrade House",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (player2.getCash() >= (int) (0.2 * houseList.get(i)
                            .getPrice())) {
                        player2.addCash(-(int) (0.2 * houseList.get(i)
                                .getPrice()));
                        houseList.get(i).addLevel();
                        JOptionPane.showMessageDialog(null, "恭喜你，成功升级"
                                + houseList.get(i).getName() + ",现在的等级为"
                                + houseList.get(i).getLevel());
                        houseList.get(i).getMark().changeIcon(houseList.get(i));
                    } else {
                        JOptionPane.showMessageDialog(null, "现金不足，无法升级!");
                    }
                }
                break label;
            }
        }
    }

    // 罚款
    public boolean fined(House house, Player player1, Player player2) {
        if (house.getID() == player1.getLocation()
                && house.getOwner() == player2) {
            return true;
        } else {
            return false;
        }
    }

    public void beFined() {
        House[] houses = Main.frame.getMapPanel().getHouses();
        label: for (int i = 0; i < houses.length; i++) {
            if (fined(houses[i], player1, player2) == true && round % 2 == 1) {
                int a = player1.getCash();
                int b = player1.getDeposit();
                JOptionPane.showMessageDialog(null, "这里是" + houses[i].getName()
                                + "，可惜它已经是" + player2.getName() + "的房产了，因此你要交给他"
                                + (int) (0.2 * houses[i].getPrice()), "罚款",
                        JOptionPane.PLAIN_MESSAGE, player1.photo3);
                if (a >= (int) (0.2 * houses[i].getPrice())) {
                    JOptionPane.showMessageDialog(null, "成功支付"
                                    + (int) (0.2 * houses[i].getPrice()), "付款信息",
                            JOptionPane.PLAIN_MESSAGE, player1.photo3);
                    player1.addCash(-(int) (0.2 * houses[i].getPrice()));
                } else if (a < (int) (0.2 * houses[i].getPrice())
                        && b >= (int) (0.2 * houses[i].getPrice() - a)) {
                    JOptionPane.showMessageDialog(null, "成功支付"
                                    + (int) (0.2 * houses[i].getPrice())
                                    + ",但是由于你的现金不足，不足费用已从存款中扣除。", "付款信息",
                            JOptionPane.PLAIN_MESSAGE, player1.photo3);
                    player1.addCash(-a);
                    player1.addDeposit(-((int) (0.2 * houses[i].getPrice()) - a));
                } else if ((a + b) < (int) (0.2 * houses[i].getPrice())
                        && player1.getHouseList().size() != 0) {
                    // 展示拥有的房屋列表
                    player1.addDeposit(-b);
                    player1.addCash(-((int) (0.2 * houses[i].getPrice()) - b));
                    mortgage = new Mortgage();
                    mortgage.setVisible(true);
                } else if ((a + b) < (int) (0.2 * houses[i].getPrice())
                        && player1.getHouseList().size() == 0) {
                    player1.addDeposit(-b);
                    player1.addCash(-((int) (0.2 * houses[i].getPrice()) - b));
                    JOptionPane.showMessageDialog(null, "房屋全部抵押，现金依旧不足！"
                                    + Player.player1.getName() + "破产", "破产",
                            JOptionPane.PLAIN_MESSAGE, player1.photo3);
                    JOptionPane.showMessageDialog(null,
                            Player.player1.getName() + "拥有的总资产为"
                                    + Player.player1.getProperty() + "\n"
                                    + Player.player2.getName() + "拥有的总资产为"
                                    + Player.player2.getProperty(), "最终数据",
                            JOptionPane.INFORMATION_MESSAGE);
                    Main.frame.close();
                }
                player2.addCash((int) (0.2 * houses[i].getPrice()));
                break label;
            } else if (fined(houses[i], player2, player1) == true
                    && round % 2 == 0) {
                int a = player2.getCash();
                int b = player2.getDeposit();
                JOptionPane.showMessageDialog(null, "这里是" + houses[i].getName()
                                + "，可惜它已经是" + player1.getName() + "的房产了，因此你要交给他"
                                + (int) (0.2 * houses[i].getPrice()), "罚款",
                        JOptionPane.PLAIN_MESSAGE, player2.photo3);
                if (a >= (int) (0.2 * houses[i].getPrice())) {
                    JOptionPane.showMessageDialog(null, "成功支付"
                                    + (int) (0.2 * houses[i].getPrice()), "付款信息",
                            JOptionPane.PLAIN_MESSAGE, player2.photo3);
                    player2.addCash(-(int) (0.2 * houses[i].getPrice()));

                } else if (a < (int) (0.2 * houses[i].getPrice())
                        && b >= (int) (0.2 * houses[i].getPrice() - a)) {
                    JOptionPane.showMessageDialog(null, "成功支付"
                                    + (int) (0.2 * houses[i].getPrice())
                                    + ",但是由于你的现金不足，不足费用已从存款中扣除。", "付款信息",
                            JOptionPane.PLAIN_MESSAGE, player2.photo3);
                    player2.addCash(-a);
                    player2.addDeposit(-((int) (0.2 * houses[i].getPrice()) - a));

                } else if ((a + b) < (int) (0.2 * houses[i].getPrice())
                        && player2.getHouseList().size() != 0) {
                    // 展示拥有的房屋列表
                    player2.addDeposit(-b);
                    player2.addCash(-((int) (0.2 * houses[i].getPrice()) - b));
                    mortgage = new Mortgage();
                    mortgage.setVisible(true);

                } else if ((a + b) < (int) (0.2 * houses[i].getPrice())
                        && player2.getHouseList().size() == 0) {
                    player2.addDeposit(-b);
                    player2.addCash(-((int) (0.2 * houses[i].getPrice()) - b));
                    JOptionPane.showMessageDialog(null, "房屋全部抵押，现金依旧不足！"
                                    + Player.player2.getName() + "破产", "破产",
                            JOptionPane.PLAIN_MESSAGE, player2.photo3);
                    JOptionPane.showMessageDialog(null,
                            Player.player1.getName() + "拥有的总资产为"
                                    + Player.player1.getProperty() + "\n"
                                    + Player.player2.getName() + "拥有的总资产为"
                                    + Player.player2.getProperty(), "最终数据",
                            JOptionPane.INFORMATION_MESSAGE);
                    Main.frame.close();
                }
                player1.addCash((int) (0.2 * houses[i].getPrice()));
                break label;
            }
        }
    }

    // 保存
    public void save() {
        try {
            JFileChooser chooser = new JFileChooser();
            File file = null;
            int option = chooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                if (file.exists()) {
                    int copy = JOptionPane.showConfirmDialog(null,
                            "是否要覆盖当前文件？", "保存", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (copy == JOptionPane.YES_OPTION) {
                        chooser.approveSelection();
                    }
                } else
                    chooser.approveSelection();
            }

            DataOutputStream out = new DataOutputStream(new FileOutputStream(
                    file));
            out.writeInt(round);
            out.writeInt(player1.getCash());
            out.writeInt(player1.getDeposit());
            out.writeInt(player1.getTicket());
            out.writeInt(player1.getStock());
            out.writeInt(player1.getLocation());
            out.writeUTF(player1.getDirection());

            out.writeInt(player2.getCash());
            out.writeInt(player2.getDeposit());
            out.writeInt(player2.getTicket());
            out.writeInt(player2.getStock());
            out.writeInt(player2.getLocation());
            out.writeUTF(player2.getDirection());

            House[] houses = Main.frame.getMapPanel().getHouses();

            for (int i = 0; i < houses.length; i++) {
                out.writeInt(houses[i].getLevel());
            }
            for (int i = 0; i < houses.length; i++) {
                if (houses[i].getOwner() != null) {
                    out.writeUTF(houses[i].getOwner().getName());
                } else {
                    out.writeUTF("m");
                }
            }
            for (int i = 0; i < player1.cards.length; i++) {
                out.writeInt(player1.cards[i].getQuality());
            }

            for (int i = 0; i < player2.cards.length; i++) {
                out.writeInt(player2.cards[i].getQuality());
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 抵押房屋显示的框
class Mortgage extends JFrame {
    JPanel panel = new JPanel();
    JCheckBox[] buttons;
    JButton yes = new JButton("确定");

    public Mortgage() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(0, 1));
        this.setLayout(new BorderLayout());
        this.setLocation(500, 200);
        this.setSize(400, 220);
        this.add(panel);
        this.setAlwaysOnTop(rootPaneCheckingEnabled);
        if (Player.round % 2 == 1) {
            buttons = new JCheckBox[Player.player1.getHouseList().size()];
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JCheckBox();
                buttons[i]
                        .setText(Player.player1.getHouseList().get(i).getName()
                                + "，抵押价格为"
                                + Player.player1.getHouseList().get(i)
                                .getPrice() + "元");
                panel.add(buttons[i]);

            }

        } else {
            buttons = new JCheckBox[Player.player2.getHouseList().size()];
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JCheckBox();
                buttons[i].setText(Player.player2.getHouseList().get(i)
                        .getName());
                panel.add(buttons[i]);
            }
        }
        this.add(panel, BorderLayout.CENTER);
        this.add(yes, BorderLayout.SOUTH);
        yes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (Player.round % 2 == 0) {
                    for (int i = 0; i < buttons.length; i++) {
                        if (buttons[i].isSelected() == true) {
                            Player.player1.addCash(Player.player1
                                    .getHouseList().get(i).getPrice());
                            Main.frame.getInfo().changePlayerInfo2();
                            Player.player1.getHouseList().get(i).setOwner(null);
                            Player.player1
                                    .getHouseList()
                                    .get(i)
                                    .getMark()
                                    .changeIcon(
                                            Player.player1.getHouseList()
                                                    .get(i));
                            Player.player1.getHouseList().get(i)
                                    .setSelected(true);

                        }
                    }
                    if (Player.player1.getCash() >= 0) {
                        label: for (;;) {
                            for (int i = 0; i < Player.player1.getHouseList()
                                    .size(); i++) {
                                if (Player.player1.getHouseList().get(i)
                                        .getSelected() == true) {
                                    Player.player1.getHouseList().remove(i);
                                    continue label;
                                }

                            }
                            break label;
                        }
                        Player.player1.getMortgage().close();
                    } else {
                        for (int i = 0; i < Player.player1.getHouseList()
                                .size(); i++) {
                            if (Player.player1.getHouseList().get(i)
                                    .getSelected() == true) {
                                Player.player1.addCash(-Player.player1
                                        .getHouseList().get(i).getPrice());
                                Player.player1.getHouseList().get(i)
                                        .setSelected(false);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "现金不足，继续抵押！");
                    }
                    // 破产
                    if (allSelected(Player.player1.getHouseList()) == true
                            && Player.player1.getCash() < 0) {
                        JOptionPane.showMessageDialog(null, "房屋全部抵押，现金依旧不足！"
                                        + Player.player1.getName() + "破产", "破产",
                                JOptionPane.INFORMATION_MESSAGE,
                                Player.player1.getPhoto3());
                        Main.music.getAau().stop();
                    }

                } else if (Player.round % 2 == 1) {
                    for (int i = 0; i < buttons.length; i++) {
                        if (buttons[i].isSelected() == true) {
                            Player.player2.addCash(Player.player2
                                    .getHouseList().get(i).getPrice());
                            Main.frame.getInfo().changePlayerInfo2();
                            Player.player2.getHouseList().get(i).setOwner(null);
                            Player.player2
                                    .getHouseList()
                                    .get(i)
                                    .getMark()
                                    .changeIcon(
                                            Player.player2.getHouseList()
                                                    .get(i));
                            Player.player2.getHouseList().get(i)
                                    .setSelected(true);

                        }
                    }
                    if (Player.player2.getCash() >= 0) {
                        label: for (;;) {
                            for (int i = 0; i < Player.player2.getHouseList()
                                    .size(); i++) {
                                if (Player.player2.getHouseList().get(i)
                                        .getSelected() == true) {
                                    Player.player2.getHouseList().remove(i);
                                    continue label;
                                }

                            }
                            break label;
                        }
                        Player.player2.getMortgage().close();
                    } else {
                        for (int i = 0; i < Player.player2.getHouseList()
                                .size(); i++) {
                            if (Player.player2.getHouseList().get(i)
                                    .getSelected() == true) {
                                Player.player2.addCash(-Player.player2
                                        .getHouseList().get(i).getPrice());
                                Player.player2.getHouseList().get(i)
                                        .setSelected(false);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "现金不足，继续抵押！");
                    }
                    if (allSelected(Player.player2.getHouseList()) == true
                            && Player.player2.getCash() < 0) {
                        JOptionPane.showMessageDialog(null, "房屋全部抵押，现金依旧不足！"
                                        + Player.player2.getName() + "破产", "破产",
                                JOptionPane.INFORMATION_MESSAGE,
                                Player.player2.getPhoto3());
                        Main.music.getAau().stop();
                    }
                }
            }
        });
    }

    public void close() {
        this.dispose();
    }

    public boolean allSelected(ArrayList<House> house) {
        for (int i = 0; i < house.size(); i++) {
            if (house.get(i).getSelected() == false) {
                return false;
            }
        }
        return true;
    }
}

