/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SendTicket extends Position{
    private URL url = getClass().getResource("image/ticket.png");
    private ImageIcon image = new ImageIcon(url);
    private int randomTicket;
    public SendTicket(int id,String name,int type){
        super(id,name,type);
        JLabel label = new JLabel(image);
        label.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        this.setOpaque(false);
        this.add(label);
        this.setBorder(null);
        this.setLayout(null);
    }
    public int sendTicket(){
        randomTicket = (int)(Math.random()*100+1);
        return randomTicket;
    }
}
