package com.myhd.util;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: VerifyCode
 * <br></br>
 * className: VerifyCode
 * <br></br>
 * packageName: com.myhd.util
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/9/17 16:12
 */
public class VerifyCode {
    public static final ConcurrentHashMap<String, HttpSession> sessionVerifyCode = new ConcurrentHashMap<>();

    private VerifyCode(){}

    public static String getVerifyCode(BufferedImage bufferedImage) {
        String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        // 存生成的字母
        StringBuilder randomStr = new StringBuilder();
        for (int i = 0; i < 5; ++i) {
            // 拿一个数据的位置
            int index = random.nextInt(str.length());
            char letter = str.charAt(index);
            randomStr.append(letter);
        }

        // 2.确定颜色
        // 背景色：底色（0~255）
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Color bgColor = new Color(red, green, blue);
//        System.out.println(red + " " + green + " " + blue);
        // 前景色：人物，文字）
        int red2 = 255 - bgColor.getRed();
        int green2 = 255 - bgColor.getGreen();
        int blue2 = 255 - bgColor.getBlue();
        Color foreColor = new Color(red2, green2, blue2);

        Graphics graphics = bufferedImage.getGraphics();

        // 3.3 画纸涂满底色
        graphics.setColor(bgColor);
        graphics.fillRect(0, 0, 100, 30);

        // 3.4 画笔换成前景色
        graphics.setColor(foreColor);
        graphics.setFont(new Font("黑体", Font.BOLD, 26));
        graphics.drawString(randomStr.toString(), 10, 28);

        // 画50个噪点
        for (int i = 0; i < 50; ++i) {
            graphics.setColor(Color.white);
            int x = random.nextInt(80);
            int y = random.nextInt(30);
            graphics.fillRect(x, y, 1, 1);
        }

        return randomStr.toString();
    }

}
