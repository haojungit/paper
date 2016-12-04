package net.dqsy.papermg.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SnowCheckCode extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0L);

        response.setContentType("image/jpeg");
        int width = 100;
        int height = 35;
        BufferedImage image = new BufferedImage(width, height,
                1);
        Graphics g = image.getGraphics();

        File bgImgFile = new File(request.getRealPath("images/checkCode_bg.jpg"));
        Image src = ImageIO.read(bgImgFile);
        g.drawImage(src, 0, 0, width, height, null);

        Random random = new Random();
        g.setFont(new Font("宋体", 0, 16));
        g.setColor(new Color(255, 255, 255));
        String sRand = "";
        Font mFont = new Font("宋体", 2, 26);
        g.setFont(mFont);
        g.setColor(new Color(255, 255, 255));

        int itmp = 0;
        for (int i = 0; i < 4; i++) {
            if (random.nextInt(2) == 1)
                itmp = random.nextInt(26) + 65;
            else {
                itmp = random.nextInt(9) + 49;
            }
            char ctmp = (char) itmp;
            sRand = sRand + String.valueOf(ctmp);

            Graphics2D g2d_word = (Graphics2D) g;
            AffineTransform trans = new AffineTransform();

            float scaleSize = random.nextFloat() + 0.5F;
            if ((scaleSize < 0.8D) || (scaleSize > 1.1F)) {
                scaleSize = 1.0F;
            }
            trans.scale(scaleSize, scaleSize);
            g2d_word.setTransform(trans);

            g.drawString(String.valueOf(ctmp), width / 6 * i + 23, random.nextInt(height / 2) + 20);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("randCheckCode", sRand);
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    public void destroy() {
        super.destroy();
    }

    public void init()
            throws ServletException {
    }
}