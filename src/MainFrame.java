/**
 * Created by xingxiaoyu on 16/6/2.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class MainFrame extends JFrame {
    private Map mapPanel;
    private Information info;
    private Menu menu;
    private JLabel tips;

    public Map getMapPanel() {
        return mapPanel;
    }

    public void setTips(String x) {
        tips.setText(x);
    }

    public Menu getMenu() {
        return menu;
    }

    public Information getInfo(){
        return info;
    }

    public MainFrame() {
        this.setTitle("Monopoly");
        URL tubiao = this.getClass().getResource("image/tubiao.png");// 设置界面图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(tubiao));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "是否需要保存", "",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (Player.round % 2 == 1) {
                        Player.player1.save();
                    } else {
                        Player.player2.save();
                    }
                }
                Main.frame.close();
            }
        });

        menu = new Menu();// 导入菜单
        this.add(menu, BorderLayout.NORTH);
        URL background = this.getClass().getResource("image/background.jpg");// 设置游戏背景颜色,放在层面板中
        ImageIcon icon = new ImageIcon(background);
        JLabel label = new JLabel(icon);
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        this.setBounds(0, 0, label.getWidth(), label.getHeight());
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel jp = (JPanel) this.getContentPane();// 内容面板设置为透明
        jp.setOpaque(false);
        // 地图面板
        mapPanel = new Map();
        this.add(mapPanel, BorderLayout.CENTER);
        mapPanel.setOpaque(false);
        // 信息面板
        info = new Information();
        this.add(info, BorderLayout.EAST);
        info.setOpaque(false);
        // 提示栏
        tips = new JLabel("现在是" + Player.player1.getName() + "的操作时间");
        this.add(tips, BorderLayout.SOUTH);

    }

    public void close() {
        this.dispose();
    }

}

