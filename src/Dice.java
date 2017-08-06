/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Dice extends JPanel {
    private URL[] urls;
    private Timer timer;
    private ImageIcon[] images; // 用于动画的图标数组
    private int currentImage = 0; // 当前图像编号
    private int delay = 100; // 图像切换延迟
    private int time = 0;// 多少时间停下

    public Dice() {
        timer = new Timer(delay, new TimerListener());
        this.addMouseListener(new MyMouseListener());
        images = new ImageIcon[6];
        urls = new URL[6];
        for (int i = 0; i < images.length; i++) {
            urls[i] = getClass().getResource("image/dice" + (i + 1) + ".png");
            images[i] = new ImageIcon(urls[i]);
        }
        this.setOpaque(false);
    }

    protected void paintComponent(Graphics g) { // 重载组件绘制方法
        super.paintComponent(g);
        images[currentImage].paintIcon(this, g, 0, 0);

    }

    public int getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(int x) {
        currentImage = x;
        repaint();
    }

    private class MyMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            timer.start();
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentImage = (int) (Math.random() * 6);// 更改当前图像编号
            // currentImage = 0;
            time = time + delay;
            repaint();
            if (time == 1000) {
                timer.stop();// 扔完骰子，玩家前进
                if (Player.round % 2 == 1) {
                    if (Player.player1.getUseDiceCard() == true) {
                        currentImage = Player.player1.getControlDice()
                                .getDiceImage();
                        repaint();
                    }
                    Player.player1.goForward();
                } else {
                    if (Player.player2.getUseDiceCard() == true) {
                        currentImage = Player.player2.getControlDice()
                                .getDiceImage();
                        repaint();
                    }
                    Player.player2.goForward();
                }
                time = 0;
            }

        }
    }
}

