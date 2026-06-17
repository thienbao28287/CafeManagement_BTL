package controller;

import model.BanAn;
import service.IBanAnService;
import service.BanAnServiceImpl;
import exception.*;
import view.BanAnPanel;

import javax.swing.*;

public class BanAnController {

    private final BanAnPanel view;
    private final IBanAnService service = new BanAnServiceImpl();

    public BanAnController(BanAnPanel view) {
        this.view = view;
    }

    public void initEvents() {

        view.getBtnLuu().addActionListener(e -> handleSave());
        
        view.getBtnXoa().addActionListener(e -> deleteBan());

        view.getBtnLamMoi().addActionListener(e -> {
            view.clearForm();
            view.getTablePanel().getTxtTimKiem().setText("");
            loadData();
        });

        // Sự kiện tìm kiếm
        view.getTablePanel().getBtnSearch().addActionListener(e -> {
            searchBan(view.getTablePanel().getTxtTimKiem().getText().trim());
        });

        // Tìm kiếm khi nhấn Enter trong ô tìm kiếm
        view.getTablePanel().getTxtTimKiem().addActionListener(e -> {
            searchBan(view.getTablePanel().getTxtTimKiem().getText().trim());
        });
    }

    // ======================
    // XỬ LÝ THÊM / SỬA
    // ======================

    private void handleSave() {

        try {

            if (view.getTxtMa().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        view,
                        "Mã bàn không được để trống!"
                );
                return;
            }

            if (view.getTxtSoGhe().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                        view,
                        "Số ghế không được để trống!"
                );
                return;
            }

            try {
                Integer.parseInt(view.getTxtSoGhe().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        view,
                        "Số ghế phải là số nguyên!"
                );
                return;
            }

            if (!view.getTxtMa().isEditable()) {
                updateBan();
            } else {
                addBan();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    view,
                    e.getMessage()
            );
        }
    }

    public void addBan() {

        try {

            service.add(collectDataFromView());

            finalizeAction(
                    "Thêm bàn ăn thành công!"
            );

        } catch (DuplicateException e) {

            JOptionPane.showMessageDialog(
                    view,
                    e.getMessage()
            );

        } catch (Exception e) {

            handleUnexpectedError(e);
        }
    }

    public void updateBan() {

        try {

            service.update(
collectDataFromView()
            );

            finalizeAction(
                    "Cập nhật bàn ăn thành công!"
            );

        } catch (Exception e) {

            handleUnexpectedError(e);
        }
    }

    public void deleteBan() {

        try {

            String ma =
                    view.getTxtMa().getText();

            if (ma.isEmpty()) {

                JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng chọn bàn ăn cần xóa!"
                );

                return;
            }

            int confirm =
                    JOptionPane.showConfirmDialog(
                            view,
                            "Bạn có chắc muốn xóa bàn ăn này?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirm ==
                    JOptionPane.YES_OPTION) {

                service.delete(ma);

                finalizeAction(
                        "Đã xóa thành công!"
                );
            }

        } catch (NotFoundException e) {

            JOptionPane.showMessageDialog(
                    view,
                    e.getMessage()
            );

        } catch (Exception e) {

            handleUnexpectedError(e);
        }
    }

    // ======================
    // TÌM KIẾM
    // ======================

    public void searchBan(String key) {

        view.getTableModel()
                .setRowCount(0);

        for (BanAn ban :
                service.search(key)) {

            view.getTableModel()
                    .addRow(new Object[]{

                            ban.getMaBanAn(),
                            ban.getSoGhe(),
                            ban.getTrangThai(),
                            ban.getViTri()
                    });
        }
    }

    public void loadData() {
        searchBan("");
        updateDashboard();
    }

    // ======================
    // HỖ TRỢ
    // ======================

    private void finalizeAction(
            String message) {

        loadData();

        view.clearForm();

        JOptionPane.showMessageDialog(
                view,
                message
        );
    }

    private void handleUnexpectedError(
            Exception e) {

        e.printStackTrace();

        JOptionPane.showMessageDialog(
                view,
                "Có lỗi xảy ra: "
                        + e.getMessage()
        );
    }

    private BanAn collectDataFromView() {

        return new BanAn(

                view.getTxtMa().getText(),

                Integer.parseInt(
                        view.getTxtSoGhe()
                                .getText()
                ),

                (String) view.getCbTrangThai()
                        .getSelectedItem(),

                view.getTxtViTri()
                        .getText()
        );
    }
private void updateDashboard() {

    int tong = 0;
    int trong = 0;
    int dangDung = 0;
    int daDat = 0;

    for (BanAn ban : service.getAll()) {

        tong++;

        switch (ban.getTrangThai()) {

            case "Trống":
                trong++;
                break;

            case "Đang dùng":
                dangDung++;
                break;

            case "Đã đặt":
                daDat++;
                break;
        }
    }

    view.getDashboardPanel().updateDashboard(
            tong,
            trong,
            dangDung,
            daDat
    );
}
}