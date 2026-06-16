package controller;

import exception.*;
import util.ExceptionHandler;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.ChiTietHoaDon;
import model.HoaDon;
import service.DatHangServiceImpl;
import service.HoaDonServiceImpl;
import service.IDatHangService;
import service.IHoaDonService;
import util.CurrencyUtil;
import view.HoaDonPanel;

public class HoaDonController {

    private final HoaDonPanel view;
    private final IHoaDonService hoaDonService = new HoaDonServiceImpl();
    private final IDatHangService chiTietService = new DatHangServiceImpl();

    public HoaDonController(HoaDonPanel view) {
        this.view = view;
    }

    public void initEvents() {
        removeListeners(view.getTablePanel().getBtnSearch());

        view.getTablePanel().getBtnSearch().addActionListener(e -> searchInvoices(view.getTablePanel().getTxtTimKiem().getText()));
        
        view.getTablePanel().getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && view.getTablePanel().getTable().getSelectedRow() != -1) {
                fillFormFromSelectedRow();
            }
        });
    }

    public void loadData() {
        try {
            List<HoaDon> list = hoaDonService.getAllHoaDon();
            if (list == null) throw new DatabaseException("Không thể kết nối đến máy chủ dữ liệu!");
            
            DefaultTableModel model = (DefaultTableModel) view.getTablePanel().getTable().getModel();
            model.setRowCount(0);
            for (HoaDon hd : list) {
                model.addRow(new Object[]{ 
                    hd.getMaHoaDon(), hd.getMaBanAn(), 
                    CurrencyUtil.formatCurrency(hd.getTongTien()), hd.getTrangThai() 
                });
            }
        } catch (Exception e) {
            ExceptionHandler.handle(view, e);
        }
    }

    private void searchInvoices(String keyword) {
        try {
            DefaultTableModel model = view.getTableModel();
            model.setRowCount(0);
            String key = keyword == null ? "" : keyword.trim().toLowerCase();
            
            List<HoaDon> list = hoaDonService.getAllHoaDon();
            if (list == null) throw new DatabaseException("Không thể tải danh sách hóa đơn!");

            for (HoaDon hoaDon : list) {
                String maHoaDon = safe(hoaDon.getMaHoaDon()).toLowerCase();
                String maBan = safe(hoaDon.getMaBanAn()).toLowerCase();
                String trangThai = safe(hoaDon.getTrangThai()).toLowerCase();
                if (key.isEmpty() || maHoaDon.contains(key) || maBan.contains(key) || trangThai.contains(key)) {
                    model.addRow(new Object[]{
                            hoaDon.getMaHoaDon(),
                            hoaDon.getMaBanAn(),
                            CurrencyUtil.formatCurrency(hoaDon.getTongTien()),
                            hoaDon.getTrangThai()
                    });
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handle(view, e);
        }
    }

    private void fillFormFromSelectedRow() {
        try {
            int row = view.getTablePanel().getTable().getSelectedRow();
            if (row < 0) return;

            String maHoaDon = view.getTablePanel().getTable().getValueAt(row, 0).toString();
            String maBan = view.getTablePanel().getTable().getValueAt(row, 1).toString();
            String tongTien = view.getTablePanel().getTable().getValueAt(row, 2).toString();
            String trangThai = view.getTablePanel().getTable().getValueAt(row, 3).toString();

            List<ChiTietHoaDon> chiTiet = chiTietService.getByHoaDon(maHoaDon);
            view.showInvoiceDetail(maHoaDon, maBan, tongTien, trangThai, chiTiet);
        } catch (Exception e) {
            ExceptionHandler.handle(view, e);
        }
    }

    private void removeListeners(AbstractButton button) {
        for (ActionListener actionListener : button.getActionListeners()) {
            button.removeActionListener(actionListener);
        }
    }
    public void xoaHoaDonSelected() {
        try {
            int row = view.getTablePanel().getTable().getSelectedRow();
            if (row == -1) {
                throw new InvalidInputException("Vui lòng chọn hóa đơn cần xóa từ bảng!");
            }

            String maHoaDon = view.getTablePanel().getTable().getValueAt(row, 0).toString();
            String trangThai = view.getTablePanel().getTable().getValueAt(row, 3).toString();

            // Kiểm tra nghiệp vụ: Không cho xóa hóa đơn đã thanh toán
            if ("Đã thanh toán".equals(trangThai)) {
                throw new HoaDonException("Không thể xóa hóa đơn đã thanh toán!");
            }

            int confirm = JOptionPane.showConfirmDialog(view, 
                    "Bạn có chắc chắn muốn xóa hóa đơn: " + maHoaDon + "?", 
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Giả sử bạn có phương thức delete trong hoaDonService
                // Nếu chưa có, bạn cần thêm nó vào IHoaDonService và HoaDonServiceImpl
                hoaDonService.deleteHoaDon(maHoaDon); 
                
                loadData(); // Tải lại danh sách
                // Reset giao diện chi tiết bên phải
                view.showInvoiceDetail("-", "-", "0 đ", "-", null);
                
                JOptionPane.showMessageDialog(view, "Xóa hóa đơn thành công!");
            }
        } catch (Exception e) {
            ExceptionHandler.handle(view, e);
        }
    }
    private String safe(String value) {
        return value == null ? "" : value;
    }
    
    public void refreshData() {
        loadData();
    }
}