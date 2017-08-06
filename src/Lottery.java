/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Lottery extends Position{
    private URL url = getClass().getResource("image/lottery.png");
    private ImageIcon image = new ImageIcon(url);
    public Lottery(int id,String name,int type){
        super(id,name,type);
        JLabel label = new JLabel(image);
        label.setSize(image.getIconWidth(), image.getIconHeight());
        this.setOpaque(false);
        this.add(label);
    }
}
