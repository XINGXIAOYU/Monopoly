/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class Position extends JPanel {
    protected int id;
    protected String name;
    protected int type;
    public static final int HOUSE = 0;// 房屋
    public static final int BANK = 1;// 银行
    public static final int GET_PROP = 2;// 取得道具
    public static final int GET_TICKET = 3;// 取得点券
    public static final int LOTTERY = 4;// 股票
    protected Timer time;

    public Position(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.setBorder(new LineBorder(Color.pink, 1));

    }

    public Position() {

    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (Player.player1.getLocation() == id) {
            Player.player1.getIcon().paintIcon(this, g, 0, 0);
        }

        if (Player.player2.getLocation() == id) {
            Player.player2.getIcon().paintIcon(this, g, 0, 0);
        }
    }

    public void showPlayer() {
        repaint();
    }
}

