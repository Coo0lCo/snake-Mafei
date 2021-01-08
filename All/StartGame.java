package 贪吃蛇.All;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import java.net.URL;

public class StartGame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("DQ一起来吃东西吧！！~");
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
class snakeDate {
    //导入游戏的广告栏
    public static URL biaoti= 贪吃蛇.snakeDate.snakeDate.class.getResource("图形资源/广告栏.png");
    public static URL youtoubu= 贪吃蛇.snakeDate.snakeDate.class.getResource("图形资源/右头部.png");
    public static URL shenti01= 贪吃蛇.snakeDate.snakeDate.class.getResource("图形资源/身体01.png");
    public static URL shangtoubu= 贪吃蛇.snakeDate.snakeDate.class.getResource("图形资源/上头部.png");
    public static URL xiatoubu= 贪吃蛇.snakeDate.snakeDate.class.getResource("图形资源/下头部.png");
    public static URL zuotoubu= 贪吃蛇.snakeDate.snakeDate.class.getResource("图形资源/左头部.png");
    public static URL food= 贪吃蛇.snakeDate.snakeDate.class.getResource("图形资源/food.png");

    public static ImageIcon biaotiicon=new ImageIcon(biaoti);

    public static ImageIcon youtoubuicon=new ImageIcon(youtoubu);
    public static ImageIcon shangtoubuicon=new ImageIcon(shangtoubu);
    public static ImageIcon xiatoubuicon=new ImageIcon(xiatoubu);
    public static ImageIcon zuotoubuicon=new ImageIcon(zuotoubu);

    public static ImageIcon shenti01icon=new ImageIcon(shenti01);
    public static ImageIcon foooricon=new ImageIcon(food);
}
class guanqia2 extends JPanel implements KeyListener, ActionListener {
    boolean shibai=false;
    int guanqiashu=3;
    Graphics g;
    int xianzhi=13;
    KeyEvent e;
    // 蛇的相关数据  属性    1.节数 2.x坐标 y坐标  3.头的方向
    int length;
    // 存储 每节身体的xy坐标
    int[] snakeX=new int[600];
    int[] snakeY=new int[600];

    // 存储蛇头的方向   “ S X Z Y ”
    String direction;

    // 还要存储游戏的状态  开始 与 停止
    boolean isstart=false;
    // 游戏是否通关
    boolean clearance=false;
    // 接下里需要一个定时器 来刷新画面
    int time=20;
    Timer t=new Timer(time,this); // 100ms执行以下  （以毫秒为单位）
    //食物的坐标 （随机产生）
    int foodX;
    int foodY;
    Random random=new Random();

    // 构造器初始化数据
    public guanqia2(){
        init();
        t.start();
    }
    public void init(){
        // 初始化长度以及每节贪吃蛇身体坐标 的操作
        length=3;
        snakeX[0]= 150; snakeY[0]=100;   //头的位置
        snakeX[1]= 75; snakeY[1]=100;   //第一节身体位置
        snakeX[2]= 100; snakeY[2]=100;  //第二节身体位置
        snakeX[3]=125; snakeY[3]=100;   //第三节身体的位置
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
        direction="Y";
    }

    // 食物的随机产生
    public void foodrandom(){
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
    }

    //当蛇的头部与食物重合的时候代表迟到 食物  （食物要继续随机生成，并且长度+1）
    public boolean eatfoodsth(){
        return snakeX[0] == foodX && snakeY[0] == foodY;
    }

    // 绘图画笔   （显示写出一个静态的界面）
    @Override
    protected void paintComponent(Graphics g) {

        this.g=g;
        super.paintComponent(g);         //清屏操作
        this.setBackground(Color.white); // 初始化 画板颜色
        // 接下来我们需要将导入的图片资源绘入画板中

        //这里将标题画到 窗口中  也就是当前对象this（指的就是当前面板）
        贪吃蛇.snakeDate.snakeDate.biaotiicon.paintIcon(this,g,25,0);

        //接下来我们要画一个  区域（正方形或者矩形） 作为我们的游戏的区域
        g.fillRect(25,75,850,600);

        //画蛇的头  (方向由案件决定)  上下左右
        if (direction.equals("S")){
            贪吃蛇.snakeDate.snakeDate.shangtoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("X")){
            贪吃蛇.snakeDate.snakeDate.xiatoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction.equals("Z")){
            贪吃蛇.snakeDate.snakeDate.zuotoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("Y")){
            贪吃蛇.snakeDate.snakeDate.youtoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        //画蛇的食物
        贪吃蛇.snakeDate.snakeDate.foooricon.paintIcon(this,g,foodX,foodY);

        //循环 画蛇的身体
        for(int i=1;i<=length;i++){
            贪吃蛇.snakeDate.snakeDate.shenti01icon.paintIcon(this,g,snakeX[i],snakeY[i]);
        }


        if (clearance){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("小天才！恭喜你通关啦！！",50,400);
        }
        if (shibai){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败请按下空格重写开始",300,400);
        }
        if (!isstart && !clearance){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏哟噢",300,400);
        }
    }
    // 键盘监听  监听  空格（游戏的开始与暂停）    wasd按键（上下左右） 小蛇的控制
    @Override
    public void keyPressed(KeyEvent e) {
        this.e=e;
        int keycode=e.getKeyCode();
        if(keycode==KeyEvent.VK_SPACE){
            if (shibai){
                shibai=false;
                init();
            }else{
                isstart=!isstart;
            }
            repaint();
        }
        if (keycode==KeyEvent.VK_S){
            direction="X";
        }else if(keycode==KeyEvent.VK_W){
            direction="S";
        }else if(keycode==KeyEvent.VK_A){
            direction="Z";
        }else if(keycode==KeyEvent.VK_D){
            direction="Y";
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }


    /* 基本原理：
        事件监听   一旦监听到了某个按键 会改变isstart  以及 字符串direction的值  通过他们值的变化去执行不同的操作
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(isstart && !shibai){
            if (length==xianzhi){
                clearance=true;
                repaint();
                return;
            }
            if(direction.equals("Y")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeX[0]=snakeX[0]+25;
                if (snakeX[0]>850){
                    snakeX[0]= 25;
                }
            }else if(direction.equals("Z")){
                if (eatfoodsth()){
                    foodrandom();

                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeX[0]=snakeX[0]-25;
                if (snakeX[0]<25){
                    snakeX[0]=850;
                }
            }else if(direction.equals("X")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeY[0]=snakeY[0]+25;
                if (snakeY[0]>650){
                    snakeY[0]= 75;
                }
            }else if(direction.equals("S")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeY[0]=snakeY[0]-25;
                if (snakeY[0]<75){
                    snakeY[0]= 600;
                }
            }
            for (int i=1;i<=length;i++){
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    shibai=true;
                }
            }
            repaint();
        }
        t.start();
    }
}
class guanqia1 extends JPanel implements KeyListener, ActionListener {
    boolean guanqiajinru=true;
    boolean shibai=false;
    int guanqiashu=2;
    Graphics g;
    int xianzhi=10;
    KeyEvent e;
    // 蛇的相关数据  属性    1.节数 2.x坐标 y坐标  3.头的方向
    int length;
    // 存储 每节身体的xy坐标
    int[] snakeX=new int[600];
    int[] snakeY=new int[600];

    // 存储蛇头的方向   “ S X Z Y ”
    String direction;

    // 还要存储游戏的状态  开始 与 停止
    boolean isstart=false;
    // 游戏是否通关
    boolean clearance=false;
    // 接下里需要一个定时器 来刷新画面
    int time=50;
    Timer t=new Timer(time,this); // 100ms执行以下  （以毫秒为单位）
    //食物的坐标 （随机产生）
    int foodX;
    int foodY;
    Random random=new Random();

    // 构造器初始化数据
    public guanqia1(){
        init();
        t.start();
    }
    public void init(){
        // 初始化长度以及每节贪吃蛇身体坐标 的操作
        length=3;
        snakeX[0]= 150; snakeY[0]=100;   //头的位置
        snakeX[1]= 75; snakeY[1]=100;   //第一节身体位置
        snakeX[2]= 100; snakeY[2]=100;  //第二节身体位置
        snakeX[3]=125; snakeY[3]=100;   //第三节身体的位置
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
        direction="Y";
    }

    // 食物的随机产生
    public void foodrandom(){
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
    }

    //当蛇的头部与食物重合的时候代表迟到 食物  （食物要继续随机生成，并且长度+1）
    public boolean eatfoodsth(){
        return snakeX[0] == foodX && snakeY[0] == foodY;
    }

    // 绘图画笔   （显示写出一个静态的界面）
    @Override
    protected void paintComponent(Graphics g) {

        this.g=g;
        super.paintComponent(g);         //清屏操作
        this.setBackground(Color.white); // 初始化 画板颜色
        // 接下来我们需要将导入的图片资源绘入画板中

        //这里将标题画到 窗口中  也就是当前对象this（指的就是当前面板）
        贪吃蛇.snakeDate.snakeDate.biaotiicon.paintIcon(this,g,25,0);

        //接下来我们要画一个  区域（正方形或者矩形） 作为我们的游戏的区域
        g.fillRect(25,75,850,600);

        //画蛇的头  (方向由案件决定)  上下左右
        if (direction.equals("S")){
            贪吃蛇.snakeDate.snakeDate.shangtoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("X")){
            贪吃蛇.snakeDate.snakeDate.xiatoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction.equals("Z")){
            贪吃蛇.snakeDate.snakeDate.zuotoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("Y")){
            贪吃蛇.snakeDate.snakeDate.youtoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        //画蛇的食物
        贪吃蛇.snakeDate.snakeDate.foooricon.paintIcon(this,g,foodX,foodY);

        //循环 画蛇的身体
        for(int i=1;i<=length;i++){
            贪吃蛇.snakeDate.snakeDate.shenti01icon.paintIcon(this,g,snakeX[i],snakeY[i]);
        }


        if (clearance){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("小天才！恭喜你（按下回车开启下一关）！！",50,400);
            if(e.getKeyCode()==KeyEvent.VK_ENTER && guanqiashu==2){
                guanqiajinru=false;
                JFrame jFrame = new JFrame("DQ一起来吃东西吧！！~");
                jFrame.setBackground(Color.white);
                Container container=jFrame.getContentPane();
                // 窗口是否可视
                jFrame.setVisible(true);
                //窗口大小不可变
                jFrame.setResizable(false);
                //窗口初始化大小
                jFrame.setBounds(200,100,900,725);
                贪吃蛇.guaqnqia2.guanqia2 gp=new 贪吃蛇.guaqnqia2.guanqia2();
                //获取键盘监听
                jFrame.addKeyListener(gp);
                // 获取焦点
                jFrame.setFocusable(true);
                // 将gamepanle装入容器中
                container.add(gp);
            }

        }
        if (shibai){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败请按下空格重写开始",300,400);
        }
        if (!isstart && !clearance){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏哟噢",300,400);
        }
    }
    // 键盘监听  监听  空格（游戏的开始与暂停）    wasd按键（上下左右） 小蛇的控制
    @Override
    public void keyPressed(KeyEvent e) {
        this.e=e;
        int keycode=e.getKeyCode();
        if(keycode==KeyEvent.VK_SPACE){
            if (shibai){
                shibai=false;
                init();
            }else{
                isstart=!isstart;
            }
            repaint();
        }
        if (keycode==KeyEvent.VK_S){
            direction="X";
        }else if(keycode==KeyEvent.VK_W){
            direction="S";
        }else if(keycode==KeyEvent.VK_A){
            direction="Z";
        }else if(keycode==KeyEvent.VK_D){
            direction="Y";
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }


    /* 基本原理：
        事件监听   一旦监听到了某个按键 会改变isstart  以及 字符串direction的值  通过他们值的变化去执行不同的操作
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(isstart && !shibai && guanqiajinru){
            if (length==xianzhi){
                clearance=true;
                repaint();
                return;
            }
            if(direction.equals("Y")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeX[0]=snakeX[0]+25;
                if (snakeX[0]>850){
                    snakeX[0]= 25;
                }
            }else if(direction.equals("Z")){
                if (eatfoodsth()){
                    foodrandom();

                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeX[0]=snakeX[0]-25;
                if (snakeX[0]<25){
                    snakeX[0]=850;
                }
            }else if(direction.equals("X")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeY[0]=snakeY[0]+25;
                if (snakeY[0]>650){
                    snakeY[0]= 75;
                }
            }else if(direction.equals("S")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeY[0]=snakeY[0]-25;
                if (snakeY[0]<75){
                    snakeY[0]= 600;
                }
            }
            for (int i=1;i<=length;i++){
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    shibai=true;
                }
            }
            repaint();
        }
        t.start();
    }
}
class gamepanle extends JPanel implements KeyListener, ActionListener {
    boolean guanqiajinru=true;
    boolean shibai=false;
    int guanqiashu=1;
    Graphics g;
    int xianzhi=7;
    KeyEvent e;
    // 蛇的相关数据  属性    1.节数 2.x坐标 y坐标  3.头的方向
    int length;
    // 存储 每节身体的xy坐标
    int[] snakeX=new int[600];
    int[] snakeY=new int[600];

    // 存储蛇头的方向   “ S X Z Y ”
    String direction;

    // 还要存储游戏的状态  开始 与 停止
    boolean isstart=false;
    // 游戏是否通关
    boolean clearance=false;
    // 接下里需要一个定时器 来刷新画面
    int time=75;
    Timer t=new Timer(time,this); // 100ms执行以下  （以毫秒为单位）
    //食物的坐标 （随机产生）
    int foodX;
    int foodY;
    Random random=new Random();

    // 构造器初始化数据
    public gamepanle(){
        init();
        t.start();
    }
    public void init(){
        // 初始化长度以及每节贪吃蛇身体坐标 的操作
        length=3;
        snakeX[0]= 150; snakeY[0]=100;   //头的位置
        snakeX[1]= 75; snakeY[1]=100;   //第一节身体位置
        snakeX[2]= 100; snakeY[2]=100;  //第二节身体位置
        snakeX[3]=125; snakeY[3]=100;   //第三节身体的位置
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
        direction="Y";
    }

    // 食物的随机产生
    public void foodrandom(){
        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);
    }

    //当蛇的头部与食物重合的时候代表迟到 食物  （食物要继续随机生成，并且长度+1）
    public boolean eatfoodsth(){
        return snakeX[0] == foodX && snakeY[0] == foodY;
    }

    // 绘图画笔   （显示写出一个静态的界面）
    @Override
    protected void paintComponent(Graphics g) {

        this.g=g;
        super.paintComponent(g);         //清屏操作
        this.setBackground(Color.white); // 初始化 画板颜色
        // 接下来我们需要将导入的图片资源绘入画板中

        //这里将标题画到 窗口中  也就是当前对象this（指的就是当前面板）
        贪吃蛇.snakeDate.snakeDate.biaotiicon.paintIcon(this,g,25,0);

        //接下来我们要画一个  区域（正方形或者矩形） 作为我们的游戏的区域
        g.fillRect(25,75,850,600);

        //画蛇的头  (方向由案件决定)  上下左右
        if (direction.equals("S")){
            贪吃蛇.snakeDate.snakeDate.shangtoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("X")){
            贪吃蛇.snakeDate.snakeDate.xiatoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction.equals("Z")){
            贪吃蛇.snakeDate.snakeDate.zuotoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("Y")){
            贪吃蛇.snakeDate.snakeDate.youtoubuicon.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        //画蛇的食物
        贪吃蛇.snakeDate.snakeDate.foooricon.paintIcon(this,g,foodX,foodY);

        //循环 画蛇的身体
        for(int i=1;i<=length;i++){
            贪吃蛇.snakeDate.snakeDate.shenti01icon.paintIcon(this,g,snakeX[i],snakeY[i]);
        }


        if (clearance){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("小天才！恭喜你（按下回车开启下一关）！！",50,400);
            if(e.getKeyCode()==KeyEvent.VK_ENTER && guanqiashu==1){
                guanqiajinru=false;
                JFrame jFrame = new JFrame("DQ一起来吃东西吧！！~");
                jFrame.setBackground(Color.white);
                Container container=jFrame.getContentPane();
                // 窗口是否可视
                jFrame.setVisible(true);
                //窗口大小不可变
                jFrame.setResizable(false);
                //窗口初始化大小
                jFrame.setBounds(70,70,900,725);
                贪吃蛇.guanqia2.guanqia1 gp=new 贪吃蛇.guanqia2.guanqia1();
                //获取键盘监听
                jFrame.addKeyListener(gp);
                // 获取焦点
                jFrame.setFocusable(true);
                // 将gamepanle装入容器中
                container.add(gp);
            }

        }
        if (shibai){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败请按下空格重写开始",300,400);
        }
        if (!isstart && !clearance){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏哟噢",300,400);
        }
    }
    // 键盘监听  监听  空格（游戏的开始与暂停）    wasd按键（上下左右） 小蛇的控制
    @Override
    public void keyPressed(KeyEvent e) {
        this.e=e;
        int keycode=e.getKeyCode();
        if(keycode==KeyEvent.VK_SPACE){
            if (shibai){
                shibai=false;
                init();
            }else{
                isstart=!isstart;
            }
            repaint();
        }
        if (keycode==KeyEvent.VK_S){
            direction="X";
        }else if(keycode==KeyEvent.VK_W){
            direction="S";
        }else if(keycode==KeyEvent.VK_A){
            direction="Z";
        }else if(keycode==KeyEvent.VK_D){
            direction="Y";
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }


    /* 基本原理：
        事件监听   一旦监听到了某个按键 会改变isstart  以及 字符串direction的值  通过他们值的变化去执行不同的操作
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(isstart && !shibai && guanqiajinru){
            if (length==xianzhi){
                clearance=true;
                repaint();
                return;
            }
            if(direction.equals("Y")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeX[0]=snakeX[0]+25;
                if (snakeX[0]>850){
                    snakeX[0]= 25;
                }
            }else if(direction.equals("Z")){
                if (eatfoodsth()){
                    foodrandom();

                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeX[0]=snakeX[0]-25;
                if (snakeX[0]<25){
                    snakeX[0]=850;
                }
            }else if(direction.equals("X")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeY[0]=snakeY[0]+25;
                if (snakeY[0]>650){
                    snakeY[0]= 75;
                }
            }else if(direction.equals("S")){
                if (eatfoodsth()){
                    foodrandom();
                    length++;
                }
                for(int i=length;i!=0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                snakeY[0]=snakeY[0]-25;
                if (snakeY[0]<75){
                    snakeY[0]= 600;
                }
            }
            for (int i=1;i<=length;i++){
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    shibai=true;
                }
            }
            repaint();
        }
        t.start();
    }
}

