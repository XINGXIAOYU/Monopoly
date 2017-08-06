/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Present extends Position {
    private URL url = getClass().getResource("image/gift.png");
    private ImageIcon image = new ImageIcon(url);
    public Present(int id, String name, int type) {
        super(id, name, type);
        JLabel label = new JLabel(image);
        label.setSize(image.getIconWidth(), image.getIconHeight());
        this.setOpaque(false);
        this.add(label);
    }
}

