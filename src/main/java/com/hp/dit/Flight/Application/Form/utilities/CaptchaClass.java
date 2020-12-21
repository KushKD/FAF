package com.hp.dit.Flight.Application.Form.utilities;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageWriter;
import javax.imageio.ImageWriteParam;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.IIOImage;
import java.io.OutputStream;

public class CaptchaClass extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        char[] chars = "abcdefhjkmnpqrstuvwxy23456789".toCharArray();
        Color textColor = Color.YELLOW;
        Color circleColor = new Color(160, 160, 160);
        Font textFont = new Font("Monospace", Font.HANGING_BASELINE, 24);
        int charsToPrint = 6;
        float horizMargin = 15.0f;
        float imageQuality = 0.95f;
        double rotationRange = 0.7;
        int width = 122;
        int height = 41;

        int circlesToDraw = 4;
        float spaceForLetters = -horizMargin * 2 + width;
        float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);

        if (request.getParameter("width") != null
                && request.getParameter("height") != null) {
            width = Integer.parseInt(request.getParameter("width"));
            height = Integer.parseInt(request.getParameter("height"));
        }
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        spaceForLetters = -horizMargin * 2 + width;
        spacePerChar = spaceForLetters / (charsToPrint - 1.0f);

        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);
        g.setColor(circleColor);
        for (int i = 0; i < circlesToDraw; i++) {
            int circleRadius = (int) (Math.random() * height / 2.0);
            int circleX = (int) (Math.random() * width - circleRadius);
            int circleY = (int) (Math.random() * height - circleRadius);
            g.drawOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
            g.drawString("UPFPO", circleRadius * 2, circleRadius * 2);
        }

        g.setColor(textColor);
        g.setFont(textFont);

        FontMetrics fontMetrics = g.getFontMetrics();
        int maxAdvance = fontMetrics.getMaxAdvance();
        int fontHeight = fontMetrics.getHeight();

        //AffineTransform transform = g.getTransform();

        StringBuffer finalString = new StringBuffer();

        for (int i = 0; i < charsToPrint; i++) {
            //double randomValue = Math.random();
            int randomIndex = (int) Math.round(Math.random()
                    * (chars.length - 1));
            char characterToShow = chars[randomIndex];
            finalString.append(characterToShow);

            // this is a separate canvas used for the character so that
            // we can rotate it independently
            ///int charImageWidth = maxAdvance * 2;
            ///int charImageHeight = fontHeight * 2;
            int charWidth = fontMetrics.charWidth(characterToShow);
            int charDim = Math.max(maxAdvance, fontHeight);
            int halfCharDim = (int) (charDim / 2);

            BufferedImage charImage = new BufferedImage(charDim, charDim,
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D charGraphics = charImage.createGraphics();
            charGraphics.translate(halfCharDim, halfCharDim);
            double angle = (Math.random() - 0.5) * rotationRange;
            charGraphics.transform(AffineTransform.getRotateInstance(angle));
            charGraphics.translate(-halfCharDim, -halfCharDim);
            charGraphics.setColor(textColor);
            charGraphics.setFont(textFont);
            int charX = (int) (0.5 * charDim - 0.5 * charWidth);
            if (characterToShow > 96 && characterToShow < 123)
                charGraphics.setColor(new Color(100, 235, 222));
            else if (characterToShow > 64 && characterToShow < 92)
                charGraphics.setColor(new Color(220, 250, 150));
            else
                charGraphics.setColor(new Color(218, 5, 2));

            charGraphics
                    .drawString(
                            "" + characterToShow,
                            charX,
                            (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics
                                    .getAscent()));

            float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
            int y = (int) ((height - charDim) / 2);
            //System.out.println("x=" + x + " height=" + height + " charDim=" + charDim + " y=" + y + " advance=" + maxAdvance + " fontHeight=" + fontHeight + " ascent=" + fontMetrics.getAscent());
            g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
            charGraphics.dispose();
            charImage.flush();
        }
        g.dispose();
        //Write the image as a jpg

        request.getSession().setAttribute("captcha_security", finalString.toString());
        response.setContentType("image/jpeg");
        Iterator iter = ImageIO.getImageWritersByFormatName("JPG");
        if (iter.hasNext()) {
            OutputStream outs = response.getOutputStream();
            ImageWriter writer = (ImageWriter) iter.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(imageQuality);
            writer.setOutput(ImageIO.createImageOutputStream(outs));
            IIOImage imageIO = new IIOImage(bufferedImage, null, null);
            writer.write(null, imageIO, iwp);
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
