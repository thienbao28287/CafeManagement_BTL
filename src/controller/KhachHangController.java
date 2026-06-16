package controller;

import model.KhachHang;
import service.IKhachHangService;
import service.KhachHangServiceImpl;
import exception.*;
import util.Validator;
import view.KhachHangPanel;
import javax.swing.*;

public class KhachHangController {
    private final KhachHangPanel view;
    private final IKhachHangService service = new KhachHangServiceImpl();
    public KhachHangController(KhachHangPanel view) {
        this.view = view;
    }
    // 3. Khởi tạo sự kiện (Event Handling)
    public void initEvents() {
        view.getBtnLuu().addActionListener(e -> handleSave());
        view.getBtnXoa().addActionListener(e -> deleteKH());
        view.getBtnLamMoi().addActionListener(e -> {
            view.clearForm();
            view.getTablePanel().getTxtTimKiem().setText("");
            loadData();
        });

        // Sự kiện tìm kiếm (rẽ nhánh khi người dùng gõ hoặc nhấn nút)
        view.getTablePanel().getBtnSearch().addActionListener(e -> {
            searchKH(view.getTablePanel().getTxtTimKiem().getText());
        });
    }

    // 4. LOGIC XỬ LÝ CHÍNH
    private void handleSave() {
        try {
            // Luồng rẽ nhánh: Kiểm tra dữ liệu trước khi lưu
            Validator.validateKhachHang(
                view.getTxtTen().getText(), 
                view.getTxtSdt().getText(),
                view.getTxtDiaChi().getText()
            );

            // Rẽ nhánh: Cập nhật nếu mã không thể chỉnh sửa, Thêm mới nếu ngược lại
            if (!view.getTxtMa().isEditable()) updateKH();
            else addKH();
            
        } catch (InvalidInputException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void addKH() {
        try {
            service.add(collectDataFromView());
            finalizeAction("Thêm khách hàng thành công!");
        } catch (DuplicateException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }

    public void updateKH() {
        try {
            service.update(collectDataFromView());
            finalizeAction("Cập nhật thành công!");
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }

    public void deleteKH() {
        try {
            String ma = view.getTxtMa().getText();
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn khách hàng cần xóa!");
                return;
            }
            // Rẽ nhánh xác nhận xóa
            if (JOptionPane.showConfirmDialog(view, "Xóa khách hàng này?") == JOptionPane.YES_OPTION) {
                service.delete(ma);
                finalizeAction("Đã xóa thành công!");
            }
        } catch (NotFoundException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }

    // 5. XỬ LÝ DỮ LIỆU & GIAO DIỆN
    public void searchKH(String key) {
        view.getTableModel().setRowCount(0);
        for (KhachHang kh : service.search(key)) {
            view.getTableModel().addRow(new Object[]{
                kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSoDienThoai(), kh.getDiaChi()
            });
        }
    }

    public void loadData() { searchKH(""); }

    private void finalizeAction(String message) {
        loadData();
        view.clearForm();
        JOptionPane.showMessageDialog(view, message);
    }

    private void handleUnexpectedError(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(view, "Có lỗi xảy ra: " + e.getMessage());
    }

    private KhachHang collectDataFromView() {
        return new KhachHang(
            view.getTxtMa().getText(),
            view.getTxtTen().getText(),
            view.getTxtSdt().getText(),
            view.getTxtDiaChi().getText()
        );
    }
    
}