package util; // Hoặc package view, tùy bạn quản lý

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageUtil {
    
    // Thêm từ khóa "static" để có thể gọi trực tiếp không cần khởi tạo
    public static ImageIcon getScaledIcon(Class<?> clazz, String resourcePath, int width, int height) {
        URL imgUrl = clazz.getResource(resourcePath);
        if (imgUrl == null) {
            System.err.println("Không tìm thấy ảnh tại đường dẫn: " + resourcePath);
            return null;
        }
        Image srcImg = new ImageIcon(imgUrl).getImage();

        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        
        // Cấu hình khử răng cưa và chống vỡ hình
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }
}