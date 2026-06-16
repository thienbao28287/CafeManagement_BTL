package util;

import exception.*;
import javax.swing.*;
import java.awt.Component;

public class ExceptionHandler {
    public static void handle(Component parent, Exception e) {
        String title = "Thông báo";
        String message = e.getMessage();

        if (e instanceof DatabaseException) {
            title = "Lỗi kết nối dữ liệu";
            message = "Hệ thống không thể truy cập Database: " + e.getMessage();
        } else if (e instanceof DuplicateException) {
            title = "Dữ liệu trùng lặp";
            message = "Dữ liệu này đã tồn tại: " + e.getMessage();
        } else if (e instanceof NotFoundException) {
            title = "Không tìm thấy dữ liệu";
        } else if (e instanceof InvalidInputException) {
            title = "Dữ liệu không hợp lệ";
        } else if (e instanceof DatHangException) {
            title = "Lỗi đặt hàng";
        } else if (e instanceof HoaDonException) {
            title = "Lỗi hóa đơn";
        } else if (e instanceof AppException) {
            title = "Lỗi ứng dụng";
        } else {
            // Trường hợp lỗi không nằm trong các exception tùy chỉnh của bạn
            title = "Lỗi hệ thống";
            message = "Có lỗi xảy ra: " + (message == null ? "Không xác định" : message);
        }
        
        JOptionPane.showMessageDialog(parent, message, title, JOptionPane.WARNING_MESSAGE);
    }
}