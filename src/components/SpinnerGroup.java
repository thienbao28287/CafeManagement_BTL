package components;

import javax.swing.*;
import java.awt.*;
import util.UIFactory; // Import UIFactory để đồng bộ thiết kế

public class SpinnerGroup extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSpinner spinner;

    public SpinnerGroup(String labelText, int min, int max, int value, int step) {
        setOpaque(false);
        // Thiết lập khoảng cách giống với InputGroup
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        // Cấu hình Label đồng bộ
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(new Color(75, 85, 99));
        label.setPreferredSize(new Dimension(100, 32));

        // Khởi tạo JSpinner số lượng
        SpinnerModel model = new SpinnerNumberModel(value, min, max, step);
        spinner = new JSpinner(model);
        spinner.setFont(new Font("SansSerif", Font.PLAIN, 14));
        spinner.setPreferredSize(new Dimension(100, 32)); 

        // --- ĐỒNG NHẤT BORDER VỚI FLATLAF TẠI ĐÂY ---
        // Ép hiệu ứng bo góc nhẹ (roundRect) giống hệt TextField và ComboBox của hệ thống
        spinner.putClientProperty("JComponent.roundRect", true);
        
        // Nếu FlatLaf của bạn yêu cầu ép border từ phần Editor chỉnh số bên trong:
        if (spinner.getEditor() instanceof JSpinner.DefaultEditor) {
            JFormattedTextField textField = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
            textField.setBorder(BorderFactory.createEmptyBorder()); // Xóa border thừa của editor để lấy border bọc ngoài của Spinner
        }

        add(label);
        add(spinner);
    }

    // Hàm bổ sung để lấy giá trị số hiện tại
    public JSpinner getSpinner() {
        return spinner;
    }

    public int getValue() {
        return (Integer) spinner.getValue();
    }
}