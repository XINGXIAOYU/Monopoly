/**
 * Created by xingxiaoyu on 16/6/2.
 */
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class House extends Position implements Upgradeable, Sellable {
    private Player owner;
    private int originalPrice;
    private int level;
    private int currentValue;
    private Mark mark;
    private boolean select;

    public House(int id, String name, int originalPrice, int level, int type,
                 Player owner) {
        super(id, name, type);
        this.originalPrice = originalPrice;
        this.level = level;
        this.owner = owner;
        this.setOpaque(false);
        mark = new Mark();
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void addLevel() {
        this.level++;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int x) {
        this.level = x;
    }

    public int getPrice() {
        currentValue = originalPrice * (level + 1);
        return currentValue;
    }

    public int getOriPrice() {
        return originalPrice;
    }

    public Mark getMark() {
        return mark;
    }

    @Override
    public boolean canBeSold() {
        if (this.owner == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canBeUpgraded(Player player) {
        if (this.owner == player && this.level < 5) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getSelected() {
        return select;
    }

    public void setSelected(boolean x) {
        this.select = x;
    }
}

// 土地的标记类
class Mark extends JPanel {
    private URL ownerURL;
    private ImageIcon ownerIma;
    private JLabel ownerLabel;

    public Mark() {
        this.ownerURL = getClass().getResource("image/noowner0.png");
        this.ownerIma = new ImageIcon(ownerURL);
        this.ownerLabel = new JLabel(ownerIma);
        ownerLabel.setSize(ownerIma.getIconWidth(),
                ownerIma.getIconHeight() + 5);
        this.add(ownerLabel);
        this.setOpaque(false);
    }

    public void setOwnerURL(URL x) {
        this.ownerURL = x;
    }

    public URL getOwnerURL() {
        return ownerURL;
    }

    public void setOwnerIma(ImageIcon x) {
        this.ownerIma = x;
    }

    public ImageIcon getOwnerIma() {
        return ownerIma;
    }

    public JLabel getOwnerLabel() {
        return ownerLabel;
    }

    public void changeIcon(House house) {
        if (house.getOwner() == null) {
            if (house.getLevel() == 0) {
                ownerURL = getClass().getResource("image/noowner0.png");
                ownerIma = new ImageIcon(ownerURL);

            } else if (house.getLevel() == 1) {
                ownerURL = getClass().getResource("image/noowner1.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 2) {
                ownerURL = getClass().getResource("image/noowner2.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 3) {
                ownerURL = getClass().getResource("image/noowner3.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 4) {
                ownerURL = getClass().getResource("image/noowner4.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 5) {
                ownerURL = getClass().getResource("image/noowner5.png");
                ownerIma = new ImageIcon(ownerURL);

            }
        } else if (house.getOwner().getName().equals(Player.player1.getName())) {
            if (house.getLevel() == 0) {
                ownerURL = getClass().getResource("image/ownerA0.png");
                ownerIma = new ImageIcon(ownerURL);
                System.out.print("ok");
            } else if (house.getLevel() == 1) {
                ownerURL = getClass().getResource("image/ownerA1.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 2) {
                ownerURL = getClass().getResource("image/ownerA2.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 3) {
                ownerURL = getClass().getResource("image/ownerA3.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 4) {
                ownerURL = getClass().getResource("image/ownerA4.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 5) {
                ownerURL = getClass().getResource("image/ownerA5.png");
                ownerIma = new ImageIcon(ownerURL);
            }
        } else if (house.getOwner().getName().equals(Player.player2.getName())) {
            if (house.getLevel() == 0) {
                ownerURL = getClass().getResource("image/ownerB0.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 1) {
                ownerURL = getClass().getResource("image/ownerB1.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 2) {
                ownerURL = getClass().getResource("image/ownerB2.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 3) {
                ownerURL = getClass().getResource("image/ownerB3.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 4) {
                ownerURL = getClass().getResource("image/ownerB4.png");
                ownerIma = new ImageIcon(ownerURL);
            } else if (house.getLevel() == 5) {
                ownerURL = getClass().getResource("image/ownerB5.png");
                ownerIma = new ImageIcon(ownerURL);
            }
        }
        ownerLabel.setIcon(ownerIma);
    }
}

