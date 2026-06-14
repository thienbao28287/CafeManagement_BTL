package controller;

import model.NhanVien;
import service.INhanVienService;
import service.NhanVienServiceImpl;
import exception.*;
import util.Validator;
import util.CurrencyUtil;
import view.NhanVienPanel;
import javax.swing.*;

public class NhanVienController {

    private final NhanVienPanel view;
    private final INhanVienService service = new NhanVienServiceImpl();

    public NhanVienController(NhanVienPanel view) { 
        this.view = view; 
    }

    public void initEvents() {
        view.getBtnLuu().addActionListener(e -> handleSave());
        view.getBtnXoa().addActionListener(e -> deleteNV());
        view.getBtnLamMoi().addActionListener(e -> {
            view.clearForm();
            view.getTablePanel().getTxtTimKiem().setText(""); // Reset cả ô tìm kiếm
            loadData();
        });
    }

    // --- LOGIC XỬ LÝ CHÍNH ---

    private void handleSave() {
        try {
            // Validator kiểm tra dữ liệu đầu vào
            Validator.validateNhanVien(
                view.getTxtTen().getText(), view.getTxtSdt().getText(), 
                view.getTxtChucVu().getText(), view.getTxtLuong().getText()
            );

            if (!view.getTxtMa().isEditable()) updateNV();
            else addNV();
        } catch (InvalidInputException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        }
    }

    public void addNV() {
        try {
            service.add(collectDataFromView());
            finalizeAction("Thêm thành công!");
        } catch (DuplicateException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }

    public void updateNV() {
        try {
            service.update(collectDataFromView());
            finalizeAction("Cập nhật thành công!");
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }

    public void deleteNV() {
        try {
            String ma = view.getTxtMa().getText();
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên cần xóa!");
                return;
            }
            if (JOptionPane.showConfirmDialog(view, "Xóa nhân viên này?") == JOptionPane.YES_OPTION) {
                service.delete(ma);
                finalizeAction("Đã xóa thành công!");
            }
        } catch (NotFoundException e) { 
            JOptionPane.showMessageDialog(view, e.getMessage()); 
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }

    // --- XỬ LÝ DỮ LIỆU & GIAO DIỆN ---

    public void searchNV(String key) {
        view.getTableModel().setRowCount(0);
        for (NhanVien nv : service.search(key)) {
            view.getTableModel().addRow(new Object[]{
                nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSoDienThoai(), 
                nv.getEmail(), nv.getChucVu(), CurrencyUtil.formatCurrency(nv.getLuong()), 
                nv.getGioiTinh(), nv.getDiaChi()
            });
        }
    }

    public void loadData() { searchNV(""); }

    private void finalizeAction(String message) {
        loadData();
        view.clearForm();
        JOptionPane.showMessageDialog(view, message);
    }

    private void handleUnexpectedError(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(view, "Có lỗi xảy ra: " + e.getMessage());
    }

    private NhanVien collectDataFromView() {
        double luong = 0;
        try {
            luong = CurrencyUtil.parseCurrency(view.getTxtLuong().getText());
        } catch (Exception ignored) {}
        
        return new NhanVien(
            view.getTxtMa().getText(), view.getTxtTen().getText(), view.getTxtSdt().getText(),
            view.getTxtEmail().getText(), view.getTxtChucVu().getText(),
            (String) view.getCbGioiTinh().getSelectedItem(), view.getTxtDiaChi().getText(),
            luong
        );
    }
}