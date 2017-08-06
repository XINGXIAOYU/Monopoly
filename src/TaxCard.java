/**
 * Created by xingxiaoyu on 16/6/2.
 */

import javax.swing.JOptionPane;

public class TaxCard extends Card {
    public TaxCard(int id, String name, int quality, boolean isUsed) {
        super(id, name, quality, isUsed);
    }

    @Override
    public void use() {
        int distance = 0;
        if(Math.abs(Player.player1.getLocation()
                - Player.player2.getLocation())<=19){
            distance =Math.abs(Player.player1.getLocation()
                    - Player.player2.getLocation());
        }else{
            distance =38-Math.abs(Player.player1.getLocation()
                    - Player.player2.getLocation());
        }
        if (distance <= 5) {
            if (Player.round % 2 == 1) {
                JOptionPane.showMessageDialog(null, "成功使用查税卡");
                Player.player2.setDeposit((int) (0.7 * Player.player2
                        .getDeposit()));
                Main.frame.setTips(Player.player1.getName() + "对"
                        + Player.player2.getName() + "使用查税卡，使他强行缴税存款的30%");
                Player.player1.ressetCardList2();
            } else if (Player.round % 2 == 0) {
                JOptionPane.showMessageDialog(null, "成功使用查税卡");
                Player.player1.setDeposit((int) (0.7 * Player.player2
                        .getDeposit()));
                Main.frame.setTips(Player.player2.getName() + "对"
                        + Player.player1.getName() + "使用查税卡，使他强行缴税存款的30%");
                Player.player2.ressetCardList2();
            }
            setUsed(true);
            minusQuality();
            Main.frame.getMenu().getTool().show.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "距离太远，无法使用！");
        }
    }
}

