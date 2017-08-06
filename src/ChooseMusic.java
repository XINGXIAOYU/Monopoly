/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChooseMusic extends JFrame {
    private int count ;
    private JLabel m1 = new JLabel("ここにいるよ");
    private JLabel m2 = new JLabel("旅の途中");
    private JLabel m3 = new JLabel("青鸟");
    private JLabel m4 = new JLabel("夕颜");
    private JLabel m5 = new JLabel("I will be there");

    private JLabel g = new JLabel("BACK");
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel p3 = new JPanel();
    private JPanel p4 = new JPanel();
    private JPanel p5 = new JPanel();
    private JPanel p6 = new JPanel();

    public ChooseMusic() {
        m1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        m2.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        m3.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        m4.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        m5.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        g.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        this.setLayout(null);
        this.setTitle("Monopoly*Music");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        URL background = this.getClass().getResource("image/musicground.jpg");// 设置游戏背景颜色,放在层面板中
        ImageIcon icon = new ImageIcon(background);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        this.setBounds(0, 0, label.getWidth(), label.getHeight());
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel jp = (JPanel) this.getContentPane();// 内容面板设置为透明
        jp.setOpaque(false);
        p1.setBounds(850, 100, 200, 50);
        p2.setBounds(850, 200, 200, 50);
        p3.setBounds(850, 300, 200, 50);
        p4.setBounds(850, 600, 200, 50);
        p5.setBounds(850, 400, 200, 50);
        p6.setBounds(850, 500, 200, 50);
        p1.add(m1);
        p2.add(m2);
        p3.add(m3);
        p5.add(m4);
        p6.add(m5);
        p4.add(g);
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        p5.setOpaque(false);
        p6.setOpaque(false);

        m1.addMouseListener(new Listener());
        m2.addMouseListener(new Listener());
        m3.addMouseListener(new Listener());
        m4.addMouseListener(new Listener());
        m5.addMouseListener(new Listener());

        g.addMouseListener(new Listener());

        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private class Listener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() == m1) {
                Main.music.play("music1.wav");
                Main.music.setOn(true);
                setCount(1);
                Main.frame3.dispose();
            } else if (e.getSource() == m2) {
                Main.music.play("music2.wav");
                Main.music.setOn(true);
                setCount(2);
                Main.frame3.dispose();
            } else if (e.getSource() == m3) {
                Main.music.play("music3.wav");
                Main.music.setOn(true);
                setCount(3);
                Main.frame3.dispose();
            } else if (e.getSource() == m4) {
                Main.music.play("music4.wav");
                Main.music.setOn(true);
                setCount(4);
                Main.frame3.dispose();
            } else if (e.getSource() == m5) {
                Main.music.play("music5.wav");
                Main.music.setOn(true);
                setCount(5);
                Main.frame3.dispose();
            } else if (e.getSource() == g) {
                Main.frame3.dispose();
            }
        }
    }
}

