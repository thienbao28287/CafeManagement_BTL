package controller;

import model.SanPham;
import service.*;
import exception.*;
import util.*;
import view.SanPhamPanel;
import javax.swing.*;
import java.awt.event.ActionListener; // Import thêm thư viện này

public class SanPhamController {
    private final SanPhamPanel view;
    private final ISanPhamService service = new SanPhamServiceImpl();

    public SanPhamController(SanPhamPanel view) { 
        this.view = view; 
        this.initEvents();
    }

    public void initEvents() {
        // Xóa sạch sự kiện cũ trước khi add mới để tránh trùng lặp
        removeListeners(view.getBtnLuu());
        removeListeners(view.getBtnXoa());
        removeListeners(view.getBtnLamMoi());
        removeListeners(view.getTablePanel().getBtnSearch());

        view.getBtnLuu().addActionListener(e -> handleSave());
        view.getBtnXoa().addActionListener(e -> deleteSP());
        view.getBtnLamMoi().addActionListener(e -> {
            view.clearForm();
            view.getTablePanel().getTxtTimKiem().setText("");
            loadData();
        });

        view.getTablePanel().getBtnSearch().addActionListener(e -> {
            String keyword = view.getTablePanel().getTxtTimKiem().getText();
            searchSP(keyword);
        });
    }

    // Phương thức phụ trợ để xóa listener
    private void removeListeners(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    // ... các phương thức còn lại (searchSP, handleSave, deleteSP, ...) giữ nguyên
    private void searchSP(String key) {
        view.getTableModel().setRowCount(0);
        for (SanPham sp : service.search(key)) {
            view.getTableModel().addRow(new Object[]{
                sp.getMaSanPham(), sp.getTenSanPham(), sp.getLoai(), 
                sp.getSoLuong(), CurrencyUtil.formatCurrency(sp.getGiaBan()), sp.getTrangThai()
            });
        }
    }
    private void handleSave() {
        try {
            Validator.validateSanPham(
                view.getTxtTen().getText(), 
                view.getTxtLoai().getText(), 
                view.getTxtSoLuong().getText(), 
                view.getTxtGiaBan().getText()
            );
            SanPham sp = new SanPham(view.getTxtMa().getText(), view.getTxtTen().getText(), 
                         Integer.parseInt(view.getTxtSoLuong().getText()), view.getTxtLoai().getText(), 
                         Double.parseDouble(view.getTxtGiaBan().getText()), (String)view.getCbTrangThai().getSelectedItem());
            
            if (!view.getTxtMa().isEditable()) service.update(sp);
            else service.add(sp);
            finalizeAction(!view.getTxtMa().isEditable() ? "Cập nhật thành công!" : "Thêm thành công!");
        } catch (InvalidInputException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }
    public void deleteSP() {
        try {
            String ma = view.getTxtMa().getText();
            if (ma.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn sản phẩm cần xóa!");
                return;
            }
            if (JOptionPane.showConfirmDialog(view, "Xóa sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                service.delete(ma);
                finalizeAction("Đã xóa thành công!");
            }
        } catch (NotFoundException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }
    public void loadData() {
        view.getTableModel().setRowCount(0);
        for (SanPham sp : service.getAll()) {
            view.getTableModel().addRow(new Object[]{
                sp.getMaSanPham(), sp.getTenSanPham(), sp.getLoai(), 
                sp.getSoLuong(), CurrencyUtil.formatCurrency(sp.getGiaBan()), sp.getTrangThai()
            });
        }
    }
    private void finalizeAction(String msg) { 
        loadData(); 
        view.clearForm(); 
        JOptionPane.showMessageDialog(view, msg); 
    }
    private void handleUnexpectedError(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(view, "Có lỗi xảy ra: " + e.getMessage());
    }
}