/**
 * Created by xingxiaoyu on 16/6/2.
 */
public class Main {
    // 游戏界面
    public static MainFrame frame = new MainFrame();
    // 游戏开始界面
    public static GameStart frame2 = new GameStart();
    // 音乐界面
    public static ChooseMusic frame3 = new ChooseMusic();
    // 音乐播放
    public static PlayMusic music = new PlayMusic();

    public static void main(String[] args) {
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame3.setLocationRelativeTo(null);
    }
}