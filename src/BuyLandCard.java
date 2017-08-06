/**
 * Created by xingxiaoyu on 16/6/2.
 */
import javax.swing.JOptionPane;

public class BuyLandCard extends Card {
    public BuyLandCard(int id, String name, int quality, boolean isUsed) {
        super(id, name, quality, isUsed);
    }

    @Override
    public void use() {
        // TODO Auto-generated method stub
        House[] houses = Main.frame.getMapPanel().getHouses();
        if (Player.round % 2 == 1) {
            boolean x = false;
            for (int i = 0; i < houses.length; i++) {
                if (Player.player1.getLocation() == houses[i].getID()) {
                    x = true;
                }
            }
            for (int i = 0; i < houses.length; i++) {
                if (x == true
                        && Player.player1.getLocation() == houses[i].getID()
                        && houses[i].getOwner() != Player.player1
                        && Player.player1.getCash() >= houses[i].getPrice()) {
                    JOptionPane.showMessageDialog(null,
                            "成功购买" + houses[i].getName());
                    houses[i].setOwner(Player.player1);
                    houses[i].getMark().changeIcon(houses[i]);
                    Player.player1.addHouse();
                    Player.player1.addCash(-(int) (houses[i].getPrice()));
                    Main.frame.setTips(Player.player1.getName() + "使用购地卡，买下"
                            + houses[i].getName());
                    setUsed(true);
                    minusQuality();
                    Main.frame.getMenu().getTool().show.dispose();
                    Player.player1.ressetCardList2();

                    break;
                } else if (x == true
                        && Player.player1.getLocation() == houses[i].getID()
                        && houses[i].getOwner() == Player.player1) {
                    JOptionPane.showMessageDialog(null, "此处已经是你的房屋，无需购买。");
                    break;
                } else if (x == true
                        && Player.player1.getLocation() == houses[i].getID()
                        && houses[i].getOwner() != Player.player1
                        && Player.player1.getCash() < houses[i].getPrice()) {
                    JOptionPane.showMessageDialog(null, "现金不足，购买失败。");
                    break;
                } else if (x == false) {
                    JOptionPane.showMessageDialog(null, "此处不是房屋！");
                    break;
                }
            }
            x = false;
        } else if (Player.round % 2 == 0) {
            boolean x = false;
            for (int i = 0; i < houses.length; i++) {
                System.out.println(Player.player2.getLocation());
                if (Player.player2.getLocation() == houses[i].getID()) {
                    x = true;
                }
            }
            for (int i = 0; i < houses.length; i++) {
                if (x == true
                        && Player.player2.getLocation() == houses[i].getID()
                        && houses[i].getOwner() != Player.player2
                        && Player.player2.getCash() >= houses[i].getPrice()) {
                    JOptionPane.showMessageDialog(null,
                            "成功购买" + houses[i].getName());
                    houses[i].setOwner(Player.player2);
                    houses[i].getMark().changeIcon(houses[i]);
                    Player.player2.addHouse();
                    Player.player2.addCash(-(int) (houses[i].getPrice()));
                    Main.frame.setTips(Player.player2.getName() + "使用购地卡，买下"
                            + houses[i].getName());
                    setUsed(true);
                    minusQuality();
                    Main.frame.getMenu().getTool().show.dispose();
                    Player.player2.ressetCardList2();
                    break;
                } else if (x == true
                        && Player.player2.getLocation() == houses[i].getID()
                        && houses[i].getOwner() == Player.player2) {
                    JOptionPane.showMessageDialog(null, "此处已经是你的房屋，无需购买。");
                    break;
                } else if (x == true
                        && Player.player2.getLocation() == houses[i].getID()
                        && houses[i].getOwner() != Player.player2
                        && Player.player2.getCash() < houses[i].getPrice()) {
                    JOptionPane.showMessageDialog(null, "现金不足，购买失败。");
                    break;
                } else if (x == false) {
                    JOptionPane.showMessageDialog(null, "此处不是房屋！");
                    break;
                }
            }
            x = false;

        }

    }
}

