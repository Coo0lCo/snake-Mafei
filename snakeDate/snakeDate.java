package 贪吃蛇.snakeDate;

import javax.swing.*;
import java.net.URL;

public class snakeDate {
    //导入游戏的广告栏
    public static URL biaoti=snakeDate.class.getResource("图形资源/广告栏.png");
    public static URL youtoubu=snakeDate.class.getResource("图形资源/右头部.png");
    public static URL shenti01=snakeDate.class.getResource("图形资源/身体01.png");
    public static URL shangtoubu=snakeDate.class.getResource("图形资源/上头部.png");
    public static URL xiatoubu=snakeDate.class.getResource("图形资源/下头部.png");
    public static URL zuotoubu=snakeDate.class.getResource("图形资源/左头部.png");
    public static URL food=snakeDate.class.getResource("图形资源/food.png");

    public static ImageIcon biaotiicon=new ImageIcon(biaoti);

    public static ImageIcon youtoubuicon=new ImageIcon(youtoubu);
    public static ImageIcon shangtoubuicon=new ImageIcon(shangtoubu);
    public static ImageIcon xiatoubuicon=new ImageIcon(xiatoubu);
    public static ImageIcon zuotoubuicon=new ImageIcon(zuotoubu);

    public static ImageIcon shenti01icon=new ImageIcon(shenti01);
    public static ImageIcon foooricon=new ImageIcon(food);
}
