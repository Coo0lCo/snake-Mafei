package 贪吃蛇.主程序;

import 贪吃蛇.gamepanel.gamepanle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class starGame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("DQ快点好起来吧！！~");
        jFrame.setBackground(Color.white);
        Container container=jFrame.getContentPane();
        // 窗口是否可视
        jFrame.setVisible(true);
        //窗口大小不可变
        jFrame.setResizable(false);
        //窗口初始化大小
        jFrame.setBounds(10,10,900,725);
        gamepanle gp=new gamepanle();
        //获取键盘监听
        jFrame.addKeyListener(gp);
        // 获取焦点
        jFrame.setFocusable(true);
        // 将gamepanle装入容器中
        container.add(gp);

    }
}
