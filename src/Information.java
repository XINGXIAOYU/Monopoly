/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Information extends JPanel {
    private TimeInfo timeInfo;// 时间信息
    private PlayerInfo playerInfo;// 玩家信息

    public Information() {
        this.setLayout(new BorderLayout(0, 50));
        timeInfo = new TimeInfo();
        playerInfo = new PlayerInfo();
        this.add(timeInfo, BorderLayout.NORTH);
        this.add(playerInfo, BorderLayout.CENTER);
    }

    public TimeInfo getTimeInfo() {
        return timeInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public void changeTimeInfo() {
        timeInfo.changeTime();
        Bank.interest();
    }

    public void changePlayerInfo() {// 一轮结束更换玩家
        playerInfo.changePlayer();
        playerInfo.changeData();
    }

    public void changePlayerInfo2() {// 一轮没有结束，玩家的信息发生变化
        playerInfo.changeData();
    }

}

class TimeInfo extends JPanel {
    private Calendar cal = Calendar.getInstance();
    private Date date = new Date();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private Font font = new Font("", Font.BOLD, 26);
    private JPanel panel = new JPanel();

    public TimeInfo() {
        this.setBorder(new TitledBorder("游戏信息"));
        this.setOpaque(false);
        this.add(getTime());

    }

    public JPanel getTime() {
        label1.setFont(font);
        label2.setFont(font);
        int day = 0;
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        if (Player.round % 2 == 1) {
            day = (Player.round + 1) / 2;

        } else {
            day = Player.round / 2;

        }
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.add(Calendar.DATE, day);
        date = cal.getTime();
        label1.setText(df.format(date));
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 7:
                label2.setText("星期六");
                break;
            case 1:
                label2.setText("星期日");
                break;
            case 2:
                label2.setText("星期一");
                break;
            case 3:
                label2.setText("星期二");
                break;
            case 4:
                label2.setText("星期三");
                break;
            case 5:
                label2.setText("星期四");
                break;
            case 6:
                label2.setText("星期五");
                break;
        }
        panel.add(label1, BorderLayout.NORTH);
        panel.add(label2, BorderLayout.SOUTH);
        return panel;
    }

    public void changeTime() {
        panel = getTime();
    }

    public boolean endOfMonth() {
        if (cal.get(Calendar.DATE) == cal
                .getActualMaximum(Calendar.DAY_OF_MONTH))
            return true;
        else
            return false;
    }

}

class PlayerInfo extends JPanel {
    private Font font;
    private JLabel[] value = new JLabel[5];
    private URL url1;
    private ImageIcon image1;
    private JLabel list;
    private JLabel photo;
    private JLabel name;
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel = new JPanel();

    PlayerInfo() {
        font = new Font("", Font.BOLD, 26);
        url1 = this.getClass().getResource("image/image1.png");
        image1 = new ImageIcon(url1);
        list = new JLabel(image1);
        photo = new JLabel();
        name = new JLabel();
        this.setBorder(new TitledBorder("玩家信息"));
        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        for (int i = 0; i < value.length; i++) {
            value[i] = new JLabel();
            value[i].setFont(font);
        }
        this.add(playerInfo1(), BorderLayout.NORTH);
        this.add(playerInfo2(), BorderLayout.CENTER);
        list.setSize(image1.getIconWidth(), image1.getIconHeight());
        name.setFont(font);
    }

    public JPanel playerInfo1() {
        if (Player.round % 2 == 1) {
            photo.setIcon(Player.player1.getPhoto());
            photo.setSize(Player.player1.getPhoto().getIconWidth(),
                    Player.player1.getPhoto().getIconHeight());
        } else if (Player.round % 2 == 0) {
            photo.setIcon(Player.player2.getPhoto());
            photo.setSize(Player.player2.getPhoto().getIconWidth(),
                    Player.player2.getPhoto().getIconHeight());
        }
        panel1.add(photo);
        panel1.setOpaque(false);
        return panel1;
    }

    public JPanel playerInfo2() {
        if (Player.round % 2 == 1) {
            name.setText("\t" + "\t" + Player.player1.getName());
            value[0].setText("\t" + "\t" + Player.player1.getCash());
            value[1].setText("\t" + "\t" + Player.player1.getDeposit());
            value[2].setText("\t" + "\t" + Player.player1.getHouseValue());
            value[3].setText("\t" + "\t" + Player.player1.getTicket());
            value[4].setText("\t" + "\t" + Player.player1.getProperty());
        } else if (Player.round % 2 == 0) {
            name.setText("\t" + "\t" + Player.player2.getName());
            value[0].setText("\t" + "\t" + Player.player2.getCash());
            value[1].setText("\t" + "\t" + Player.player2.getDeposit());
            value[2].setText("\t" + "\t" + Player.player2.getHouseValue());
            value[3].setText("\t" + "\t" + Player.player2.getTicket());
            value[4].setText("\t" + "\t" + Player.player2.getProperty());
        }
        panel.setLayout(new GridLayout(6, 1));
        panel.setOpaque(false);
        panel.add(name);
        for (int i = 0; i < value.length; i++) {
            panel.add(value[i]);
        }
        panel2.setLayout(new BorderLayout());
        panel2.add(list, BorderLayout.WEST);
        panel2.add(panel, BorderLayout.CENTER);
        panel2.setOpaque(false);
        return panel2;
    }

    public void changePlayer() {
        panel1 = playerInfo1();
    }

    public void changeData() {
        panel2 = playerInfo2();
    }

}

