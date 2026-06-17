package controller;

import exception.*;
import util.ExceptionHandler;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.DBConnection;
import model.*;
import repository.*;
import service.*;
import util.*;
import view.DatHangPanel;
import view.HoaDonPanel;
import view.SanPhamPanel;

public class DatHangController {
    private final DatHangPanel view;
    private HoaDonPanel hoaDonPanel;
    private final IDatHangService service = new DatHangServiceImpl();
    private final ISanPhamService sanPhamService = new SanPhamServiceImpl();
    private final IHoaDonService hoaDonService = new HoaDonServiceImpl();
    private final IHoaDonRepository hoaDonRepository = new HoaDonRepositoryImpl();
    private ISanPhamRepository sanPhamRepo = new SanPhamRepositoryImpl();
    private SanPhamPanel sanPhamPanel;
    public DatHangController(DatHangPanel view) {
        this.view = view;
    }

    public void setHoaDonPanel(HoaDonPanel hoaDonPanel) {
        this.hoaDonPanel = hoaDonPanel;
    }

    public void initEvents() {
        removeListeners(view.getBtnThemNuoc());
        removeListeners(view.getBtnThemDoAn());
        removeListeners(view.getBtnLuu());
        removeListeners(view.getBtnXoa());
        removeListeners(view.getBtnLamMoi());
        removeListeners(view.getBtnCapNhat());
        removeListeners(view.getTablePanel().getBtnSearch());

        loadSanPhamOptions();

        view.getBtnThemNuoc().addActionListener(e -> addOrUpdateSelectedItem(view.getCbNuocUong().getSelectedItem(), (Integer) view.getSpSoluongNuoc().getValue()));
        view.getBtnThemDoAn().addActionListener(e -> addOrUpdateSelectedItem(view.getCbDoAn().getSelectedItem(), (Integer) view.getSpSoluongDoAn().getValue()));
        view.getBtnLuu().addActionListener(e -> saveCurrentOrder());
        view.getBtnXoa().addActionListener(e -> deleteSelectedItem());
        view.getBtnLamMoi().addActionListener(e -> { view.clearOrderInputs(); loadData(); });
        view.getBtnCapNhat().addActionListener(e -> updateCurrentOrder());

        view.getTablePanel().getBtnSearch().addActionListener(e -> {
            String keyword = view.getTablePanel().getTxtTimKiem().getText().trim();
            if (keyword.isEmpty()) {
                loadData();
            } else {
                searchBySanPham(keyword);
            }
        });

        view.getTablePanel().getTxtTimKiem().addActionListener(e -> {
            String keyword = view.getTablePanel().getTxtTimKiem().getText().trim();
            if (keyword.isEmpty()) loadData(); else searchBySanPham(keyword);
        });

        view.getTablePanel().getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && view.getTablePanel().getTable().getSelectedRow() != -1) {
                fillFormFromSelectedRow();
            }
        });
    }

    public void loadData() {
        searchOrderItems(view.getTxtMaDatHang().getText().trim());
    }

    public void searchBySanPham(String maSanPham) {
        DefaultTableModel model = (DefaultTableModel) view.getTablePanel().getTable().getModel();
        model.setRowCount(0);
        double tongTien = 0;
        for (ChiTietHoaDon item : service.searchByMaSanPham(maSanPham)) {
            model.addRow(new Object[]{
                item.getMaHoaDon(), item.getMaSanPham(), item.getTenSanPham(),
                item.getSoLuong(), util.CurrencyUtil.formatCurrency(item.getDonGia()),
                util.CurrencyUtil.formatCurrency(item.getThanhTien())
            });
            tongTien += item.getThanhTien();
        }
        view.getLblTongTien().setText("Tổng tiền: " + util.CurrencyUtil.formatCurrency(tongTien));
    }

    public void searchOrderItems(String maHoaDon) {
        DefaultTableModel model = (DefaultTableModel) view.getTablePanel().getTable().getModel();
        model.setRowCount(0);
        if (maHoaDon == null || maHoaDon.isEmpty()) {
            view.getLblTongTien().setText("Tổng tiền: 0 đ");
            return;
        }
        List<ChiTietHoaDon> list = service.getByHoaDon(maHoaDon);
        double tongTien = 0;
        for (ChiTietHoaDon item : list) {
            model.addRow(new Object[]{ 
                item.getMaHoaDon(), item.getMaSanPham(), item.getTenSanPham(), 
                item.getSoLuong(), CurrencyUtil.formatCurrency(item.getDonGia()), 
                CurrencyUtil.formatCurrency(item.getThanhTien()) 
            });
            tongTien += item.getThanhTien();
        }
        view.getLblTongTien().setText("Tổng tiền: " + CurrencyUtil.formatCurrency(tongTien));
    }

    private void checkHoaDonChoPhepSua(HoaDon hd) throws DatHangException {
        if (hd != null && "Đã thanh toán".equals(hd.getTrangThai())) {
            throw new DatHangException("Hóa đơn đã thanh toán, không được phép thay đổi!");
        }
    }

    private void addOrUpdateSelectedItem(Object selectedItem, int soLuong) {
        try {
            if (soLuong <= 0) throw new InvalidInputException("Số lượng phải lớn hơn 0!");
            String maHoaDon = view.getTxtMaDatHang().getText().trim();
            HoaDon hd = hoaDonService.getHoaDonById(maHoaDon);
            if (hd == null) throw new NotFoundException("Vui lòng nhấn LƯU để tạo hóa đơn trước!");
            checkHoaDonChoPhepSua(hd);
            String item = selectedItem == null ? "" : selectedItem.toString();
            if (item.startsWith("-- Chọn")) throw new InvalidInputException("Vui lòng chọn món!");
            String[] parts = item.split(" - ");
            
            // 1. Thực hiện service (đã bao gồm logic trừ kho trong DB)
            service.addOrUpdate(new ChiTietHoaDon(maHoaDon, parts[0].trim(), parts[1].trim(), soLuong, Double.parseDouble(parts[2].replace(",", ""))));
            
            // 2. Cập nhật giao diện Hóa Đơn hiện tại
            capNhatTongTienHoaDon(maHoaDon);
            searchOrderItems(maHoaDon);
            if (hoaDonPanel != null) hoaDonPanel.refreshData();
            
            // --- THÊM PHẦN CẬP NHẬT DANH SÁCH SẢN PHẨM Ở ĐÂY ---
            // Giả sử bạn có biến sanPhamPanel đã được khởi tạo trong controller
            if (this.sanPhamPanel != null) {
                this.sanPhamPanel.refreshData(); // Gọi hàm bạn vừa tạo ở bước 1
            }
            // ---------------------------------------------------
            
        } catch (Exception e) {
            ExceptionHandler.handle(view, e);
        }
    }

    private void deleteSelectedItem() {
        try {
            int row = view.getTablePanel().getTable().getSelectedRow();
            if (row == -1) throw new InvalidInputException("Vui lòng chọn một dòng để xóa!");
            String maHoaDon = view.getTablePanel().getTable().getValueAt(row, 0).toString();
            HoaDon hd = hoaDonService.getHoaDonById(maHoaDon);
            checkHoaDonChoPhepSua(hd);
            String maSanPham = view.getTablePanel().getTable().getValueAt(row, 1).toString();
            service.remove(maHoaDon, maSanPham);
            capNhatTongTienHoaDon(maHoaDon);
            searchOrderItems(maHoaDon);
            if (hoaDonPanel != null) hoaDonPanel.refreshData();
        } catch (Exception e) {
            ExceptionHandler.handle(view, e);
        }
    }

    private void capNhatTongTienHoaDon(String maHoaDon) {
        List<ChiTietHoaDon> list = service.getByHoaDon(maHoaDon);
        double tongTien = list.stream().mapToDouble(ChiTietHoaDon::getThanhTien).sum();
        HoaDon hd = hoaDonRepository.getById(maHoaDon);
        if (hd != null) { 
            hd.setTongTien(tongTien); 
            hoaDonRepository.update(hd); 
        }
        view.getLblTongTien().setText("Tổng tiền: " + CurrencyUtil.formatCurrency(tongTien));
        if (hoaDonPanel != null) hoaDonPanel.refreshData();
    }

    private void ensureInvoiceHeaderExists(String maHoaDon) throws Exception {
        if (hoaDonService.getHoaDonById(maHoaDon) != null) return;
        
        String maBan = view.getTxtMaBan().getText().trim();
        // 1. Lấy mã khách hàng từ ô nhập liệu trong view
        String maKHInput = view.getTxtMaKhachHang().getText().trim();
        
        if (!isBanHopLe(maBan)) throw new NotFoundException("Mã bàn không tồn tại!");
        
        // 2. Nếu trống hoặc là text mặc định thì truyền null (khách vャng lai)
        String maKH = (maKHInput.isEmpty() || maKHInput.equals("Khách Vãng Lai")) ? null : maKHInput;
        
        // 3. null = khách vãng lai, DB đã cho ph麐p MaKhachHang NULL
        hoaDonService.addHoaDon(new HoaDon(maHoaDon, 0, "Đang phục vụ", new java.util.Date(), "NV01", maKH, maBan));
    }

    private boolean isBanHopLe(String maBan) throws DatabaseException {
        String sql = "SELECT COUNT(*) FROM BanAn WHERE MaBanAn = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) { 
            throw new DatabaseException("Lỗi kết nối CSDL: " + e.getMessage()); 
        }
    }

    private void saveCurrentOrder() {
        try {
            String maHoaDon = view.getTxtMaDatHang().getText().trim();
            String maBan = view.getTxtMaBan().getText().trim();
            if (maHoaDon.isEmpty() || maBan.isEmpty()) throw new InvalidInputException("Mã đặt hàng và mã bàn không được để trống!");
            if (hoaDonService.getHoaDonById(maHoaDon) != null) throw new DuplicateException("Hóa đơn đã tồn tại! Vui lòng nhấn Cập nhật.");
            ensureInvoiceHeaderExists(maHoaDon);
            searchOrderItems(maHoaDon); // Hiển thị danh sách món sau khi tạo đơn
            if (hoaDonPanel != null) hoaDonPanel.refreshData();
            JOptionPane.showMessageDialog(view, "Tạo hóa đơn thành công!");
        } catch (Exception e) {
            ExceptionHandler.handle(view, e);
        }
    }

    private void updateCurrentOrder() {
        try {
            String maHoaDon = view.getTxtMaDatHang().getText().trim();
            if (maHoaDon.isEmpty()) throw new InvalidInputException("Vui lòng chọn hoặc nhập mã HĐ!");
            HoaDon hd = hoaDonService.getHoaDonById(maHoaDon);
            if (hd == null) throw new NotFoundException("Không tìm thấy hóa đơn này!");
            hd.setMaBanAn(view.getTxtMaBan().getText().trim());
            hd.setTrangThai(view.getCbTrangThai().getSelectedItem().toString());
            hoaDonRepository.update(hd);
            if (hoaDonPanel != null) hoaDonPanel.refreshData();
            capNhatTongTienHoaDon(maHoaDon);
            JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
        } catch (Exception e) {
            ExceptionHandler.handle(view, e);
        }
    }

    private void loadSanPhamOptions() {
        DefaultComboBoxModel<String> nuocModel = new DefaultComboBoxModel<>(), doAnModel = new DefaultComboBoxModel<>();
        nuocModel.addElement("-- Chọn nước uống --"); doAnModel.addElement("-- Chọn món --");
        for (SanPham sp : sanPhamService.getAll()) {
            String display = sp.getMaSanPham() + " - " + sp.getTenSanPham() + " - " + (long) sp.getGiaBan();
            if (sp.getLoai().toLowerCase().contains("ăn") || sp.getLoai().toLowerCase().contains("bánh")) doAnModel.addElement(display);
            else nuocModel.addElement(display);
        }
        view.setProductOptions(toArray(nuocModel), toArray(doAnModel));
    }

    private String[] toArray(DefaultComboBoxModel<String> model) {
        String[] data = new String[model.getSize()];
        for (int i = 0; i < model.getSize(); i++) data[i] = model.getElementAt(i);
        return data;
    }

    private void fillFormFromSelectedRow() {
        int row = view.getTablePanel().getTable().getSelectedRow();
        view.getTxtMaDatHang().setText(view.getTablePanel().getTable().getValueAt(row, 0).toString());
        view.setSelectedQuantity(Integer.parseInt(view.getTablePanel().getTable().getValueAt(row, 3).toString()));
    }

    private void removeListeners(AbstractButton button) {
        for (ActionListener al : button.getActionListeners()) button.removeActionListener(al);
    }
    public void setSanPhamPanel(SanPhamPanel sanPhamPanel) {
        this.sanPhamPanel = sanPhamPanel;
    }
}