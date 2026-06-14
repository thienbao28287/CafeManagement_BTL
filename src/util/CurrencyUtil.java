package util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    // Định dạng số thành chuỗi tiền tệ (Ví dụ: 1000000 -> 1,000,000 VND)
    public static String formatCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }

    // Chuyển chuỗi nhập liệu (có thể có dấu phẩy/chấm) thành số thực
    // Ví dụ người dùng nhập "1.000.000" -> trả về 1000000.0
    public static double parseCurrency(String amountStr) throws NumberFormatException {
        // Loại bỏ mọi ký tự không phải số (trừ dấu chấm thập phân nếu có)
        String cleanString = amountStr.replaceAll("[^\\d.]", "");
        return Double.parseDouble(cleanString);
    }
}