/**
 * Created by xingxiaoyu on 16/6/2.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JMenuBar {
    final Color color1 = new Color(220, 200, 250);
    final Color color2 = this.getBackground();
    Looking looking = new Looking();
    Tool tool = new Tool();
    Stock stock = new Stock();

    public Menu() {
        looking.addMouseListener(new MyMouseMovement());
        add(looking);

        tool.addMouseListener(new MyMouseMovement());
        add(tool);

        stock.addMouseListener(new MyMouseMovement());
        add(stock);
    }

    class MyMouseMovement extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == looking) {
                looking.setBackground(color1);
            } else if (e.getSource() == tool) {
                tool.setBackground(color1);
            } else if (e.getSource() == stock) {
                stock.setBackground(color1);
            }
        }

        public void mouseExited(MouseEvent e) {
            if (e.getSource() == looking) {
                looking.setBackground(color2);
            } else if (e.getSource() == tool) {
                tool.setBackground(color2);
            } else if (e.getSource() == stock) {
                stock.setBackground(color2);
            }
        }
    }

    public Tool getTool() {
        return tool;
    }
}


class Looking extends JMenu {
    JMenuItem[] subMenu = new JMenuItem[2];
    String[] text = { "查看玩家信息", "查看指定地点路过须缴纳多少过路费" };

    public Looking() {
        this.setText("查看");
        this.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        for (int i = 0; i < subMenu.length; i++) {
            subMenu[i] = new JMenuItem(text[i]);
            subMenu[i].setFont(new Font("微软雅黑", Font.PLAIN, 14));
            add(subMenu[i]);
            JSeparator separator = new JSeparator();
            add(separator);
            subMenu[i].addActionListener(new Listener());
        }
    }

    class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == subMenu[0]) {
                Information info = new Information();
                info.setVisible(true);
            } else if (e.getSource() == subMenu[1]) {
                Look look = new Look();
                look.setVisible(true);
            }
        }

    }

    // 查看玩家信息
    private class Information extends JDialog {
        public Information() {
            JScrollPane panel = new JScrollPane(getTable());
            add(panel);
            this.setSize(600, 200);
            this.setLocation(350, 280);
        }

        private JTable getTable() {
            JTable table = new JTable();
            table.setRowHeight(23);
            String[] columns = { "姓名", "现金", "存款", "点券", "拥有土地价值", "总资产" };
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            table.setModel(model);
            List<String> playerInfo = getPlayerInfo();
            for (String info : playerInfo) {
                String[] args = info.split(",");
                model.addRow(args);
            }
            return table;
        }

        private List<String> getPlayerInfo() {
            List<String> list = new ArrayList<String>();
            list.add(Player.player1.getName() + "," + Player.player1.getCash()
                    + "," + Player.player1.getDeposit() + ","
                    + Player.player1.getTicket() + ","
                    + Player.player1.getHouseValue() + ","
                    + Player.player1.getProperty());
            list.add(Player.player2.getName() + "," + Player.player2.getCash()
                    + "," + Player.player2.getDeposit() + ","
                    + Player.player2.getTicket() + ","
                    + Player.player2.getHouseValue() + ","
                    + Player.player2.getProperty());
            return list;
        }

    }

    // 查看具体位置信息
    private class Look extends JDialog {
        JLabel label = new JLabel("请输入你想查询点与你相差的步数(后方输入负数)<20步之内>");
        JTextArea area = new JTextArea();
        JButton yes = new JButton("确定");
        JPanel p = new JPanel();

        public Look() {
            this.setLayout(new BorderLayout(10, 10));
            p.setLayout(null);
            area.setBounds(5, 5, 300, 20);
            yes.setBounds(300, 50, 50, 20);
            p.add(area);
            p.add(yes);
            add(label, BorderLayout.NORTH);
            add(p, BorderLayout.CENTER);
            this.setSize(400, 150);
            this.setLocation(400, 320);
            // 设置回车不换行，回车为按下确定按钮
            KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
            area.getInputMap().put(enter, "none");
            area.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub
                    if (e.getKeyCode() == 10) {
                        yes.doClick();
                    }
                }

            });
            yes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        int number = Integer.parseInt(area.getText().trim());
                        if (area.getText().charAt(0) != '-') {
                            if (number >= 0 && number <= 20) {
                                if (Player.round % 2 == 1) {
                                    showDetail(
                                            (Player.player1.getLocation() + number) % 38,
                                            Main.frame.getMapPanel()
                                                    .getHouses(), Main.frame
                                                    .getMapPanel()
                                                    .getPresents(), Main.frame
                                                    .getMapPanel().getBanks(),
                                            Main.frame.getMapPanel()
                                                    .getLottery(), Main.frame
                                                    .getMapPanel()
                                                    .getSendTicket());
                                } else {
                                    showDetail(
                                            (Player.player2.getLocation() + number) % 38,
                                            Main.frame.getMapPanel()
                                                    .getHouses(), Main.frame
                                                    .getMapPanel()
                                                    .getPresents(), Main.frame
                                                    .getMapPanel().getBanks(),
                                            Main.frame.getMapPanel()
                                                    .getLottery(), Main.frame
                                                    .getMapPanel()
                                                    .getSendTicket());
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "距离太远，无法查看！");
                            }
                        } else if (area.getText().charAt(0) == '-') {
                            if (number >= -20 && number < 0) {
                                number = -number;
                                if (Player.round % 2 == 1) {
                                    int position = (Player.player1
                                            .getLocation() - number) % 38;
                                    if (position < 0) {
                                        position = position + 38;
                                    }
                                    showDetail(position, Main.frame
                                                    .getMapPanel().getHouses(),
                                            Main.frame.getMapPanel()
                                                    .getPresents(), Main.frame
                                                    .getMapPanel().getBanks(),
                                            Main.frame.getMapPanel()
                                                    .getLottery(), Main.frame
                                                    .getMapPanel()
                                                    .getSendTicket());
                                } else {
                                    int position = (Player.player2
                                            .getLocation() - number) % 38;
                                    if (position < 0) {
                                        position = position + 38;
                                    }
                                    showDetail(position, Main.frame
                                                    .getMapPanel().getHouses(),
                                            Main.frame.getMapPanel()
                                                    .getPresents(), Main.frame
                                                    .getMapPanel().getBanks(),
                                            Main.frame.getMapPanel()
                                                    .getLottery(), Main.frame
                                                    .getMapPanel()
                                                    .getSendTicket());
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "距离太远，无法查看！");
                            }
                        }
                    } catch (Exception e1) {
                        //
                        JOptionPane.showMessageDialog(null, "输入错误，请正确输入！");
                        // e1.printStackTrace();
                    }

                }
            });
        }

        public void showDetail(int x, House[] houses, Present[] presents,
                               Bank[] banks, Lottery[] lottery, SendTicket[] tickets) {
            for (int i = 0; i < houses.length; i++) {
                if (x == houses[i].getID()) {
                    if (houses[i].getOwner() != null) {
                        JOptionPane.showMessageDialog(null, "类型：房产" + "\n"
                                + "名称:" + houses[i].getName() + "\n" + "初始价格:"
                                + houses[i].getOriPrice() + "\n" + "当前等级:"
                                + houses[i].getLevel() + "\n" + "拥有者:"
                                + houses[i].getOwner().getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "类型：房产" + "\n"
                                + "名称:" + houses[i].getName() + "\n" + "初始价格:"
                                + houses[i].getOriPrice() + "\n" + "当前等级:"
                                + houses[i].getLevel() + "\n" + "拥有者:"
                                + "<可供出售状态>");
                    }
                }
            }
            for (int i = 0; i < banks.length; i++) {
                if (x == banks[i].getID()) {
                    if (x == banks[i].getID()) {
                        JOptionPane.showMessageDialog(null, "类型：银行" + "\n"
                                + "名称:" + banks[i].getName());
                    }
                }
            }
            for (int i = 0; i < presents.length; i++) {
                if (x == presents[i].getID()) {
                    JOptionPane.showMessageDialog(null, "类型：赠送道具点" + "\n"
                            + "名称:" + presents[i].getName());
                }
            }
            for (int i = 0; i < lottery.length; i++) {
                if (x == lottery[i].getID()) {
                    JOptionPane.showMessageDialog(null, "类型：股市" + "\n"
                            + "状态：正在建设中⋯⋯");
                }
            }
            for (int i = 0; i < tickets.length; i++) {
                if (x == tickets[i].getID()) {
                    JOptionPane.showMessageDialog(null, "类型：赠送券点" + "\n"
                            + "名称:" + tickets[i].getName());
                }
            }

        }
    }
}

class Tool extends JMenu {
    JRadioButton[] buttons;
    ShowTools show;
    JMenuItem useTool = new JMenuItem("使用道具");
    int x;

    public Tool() {
        this.setText("道具");
        this.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        useTool.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        add(useTool);
        useTool.addActionListener(new Listener());
    }

    class ShowTools extends JDialog {
        public ShowTools() {
            this.setLocation(400, 250);
            this.setSize(400, 200);
            this.setLayout(new GridLayout(4, 1));
            if (Player.round % 2 == 1) {
                Card[] cards = Player.player1.getCards();
                buttons = new JRadioButton[Player.player1.getCardList().size()];
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i] = new JRadioButton(Player.player1.getCardList()
                            .get(i).getName()
                            + "＊"
                            + Player.player1.getCardList().get(i).getQuality());
                    add(buttons[i]);
                    buttons[i].addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < buttons.length; i++) {
                                if (buttons[i].isSelected() == true) {
                                    x = Player.player1.getCardList().get(i)
                                            .getID();
                                    Player.player1.useTool(x);
                                    buttons[i].setSelected(false);
                                }

                            }
                        }

                    });
                }
            } else {
                Card[] cards = Player.player2.getCards();
                buttons = new JRadioButton[Player.player2.getCardList().size()];
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i] = new JRadioButton(Player.player2.getCardList()
                            .get(i).getName()
                            + "＊"
                            + Player.player2.getCardList().get(i).getQuality());
                    add(buttons[i]);
                    buttons[i].addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < buttons.length; i++) {
                                if (buttons[i].isSelected() == true) {
                                    x = Player.player2.getCardList().get(i)
                                            .getID();
                                    Player.player2.useTool(x);

                                }
                                buttons[i].setSelected(false);
                            }
                        }

                    });
                }
            }
        }

    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == useTool) {
                show = new ShowTools();
                show.setVisible(true);
            }
        }
    }
}

class Stock extends JMenu {
    JMenuItem stock = new JMenuItem("股票");

    public Stock() {
        this.setText("股票");
        this.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        stock.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        add(stock);
        stock.addActionListener(new Listener());
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "正在建设中⋯⋯");
        }
    }
}
