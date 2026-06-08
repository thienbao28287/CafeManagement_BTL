package view;

import components.FormPanel;
import components.HeaderPanel;
import components.InputGroup;
import util.UIFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BanAnPanel extends JPanel {

    private JTextField txtMaSoGhe;
    private JTextField txtViTri;
    private JComboBox<String> cbTrangThai;

    private JComboBox<String> cbTang;
    private JPanel gridPanel;

    public BanAnPanel() {

        setLayout(new BorderLayout(0, 16));
        setOpaque(false);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        add(
            new HeaderPanel(
                "🪑 Bàn ăn",
                "Quản lý bàn ăn",
                new Color(30, 41, 59),
                new Color(51, 65, 85),
                new Color(71, 85, 105)
            ),
            BorderLayout.NORTH
        );

        txtMaSoGhe = UIFactory.createTextField();
        txtViTri = UIFactory.createTextField();

        cbTrangThai = UIFactory.createComboBox(
            new String[]{
                "Trống",
                "Đang sử dụng",
                "Đã đặt trước"
            }
        );

        Component[] banAnInputs = {
            new InputGroup("Mã bàn:", txtMaSoGhe),
            new InputGroup("Trạng thái:", cbTrangThai),
            new InputGroup("Vị trí:", txtViTri)
        };

        ImageIcon leftIcon = util.ImageUtil.getScaledIcon(
            getClass(),
            "/img/leftNV.png",
            220,
            220
        );

        ImageIcon rightIcon = util.ImageUtil.getScaledIcon(
            getClass(),
            "/img/rightNV.png",
            220,
            220
        );

        FormPanel formPanel = new FormPanel(
            "Thông tin bàn ăn",
            banAnInputs,
            leftIcon,
            rightIcon
        );

        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setOpaque(false);

        content.add(createDashboardPanel(), BorderLayout.CENTER);
        content.add(formPanel, BorderLayout.SOUTH);

        add(content, BorderLayout.CENTER);
    }

    private JPanel createDashboardPanel() {

        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setOpaque(false);

        panel.add(createStatsPanel(), BorderLayout.WEST);
        panel.add(createTableMapPanel(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createStatsPanel() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        panel.setPreferredSize(new Dimension(220, 0));
        panel.setLayout(new GridLayout(4, 1, 0, 12));

        panel.add(createStatCard("Tổng bàn", "36"));
        panel.add(createStatCard("Trống", "12"));
        panel.add(createStatCard("Đang dùng", "12"));
        panel.add(createStatCard("Đặt trước", "12"));

        return panel;
    }

    private JPanel createStatCard(String title, String value) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(220, 220, 220)
            ),
            BorderFactory.createEmptyBorder(
                12, 15, 12, 15
            )
        ));

        JLabel lblTitle = new JLabel(title);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(
            new Font(
                "Segoe UI",
                Font.BOLD,
                12
            )
        );

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);

        return card;
    }

    private JPanel createTableMapPanel() {

        JPanel wrapper = new JPanel(
            new BorderLayout(10, 10)
        );

        wrapper.setBackground(Color.WHITE);

        wrapper.setBorder(
            BorderFactory.createTitledBorder(
                "Sơ đồ bàn ăn"
            )
        );

        JPanel topPanel = new JPanel(
            new FlowLayout(
                FlowLayout.LEFT
            )
        );

        topPanel.add(new JLabel("Tầng:"));

        cbTang = new JComboBox<>(
            new String[]{
                "Tầng 1",
                "Tầng 2"
            }
        );

        topPanel.add(cbTang);

        wrapper.add(topPanel, BorderLayout.NORTH);

        gridPanel = new JPanel();
        gridPanel.setOpaque(false);

        gridPanel.setBorder(
            new EmptyBorder(
                15,
                15,
                15,
                15
            )
        );

        wrapper.add(
            gridPanel,
            BorderLayout.CENTER
        );

        loadTables(1);

        cbTang.addActionListener(e -> {

            int tang =
                cbTang.getSelectedIndex() + 1;

            loadTables(tang);
        });

        return wrapper;
    }

private void loadTables(int tang) {

    gridPanel.removeAll();

    // 3 hàng × 6 cột
    gridPanel.setLayout(
        new GridLayout(
            3,
            6,
            12,
            12
        )
    );

    int start = (tang == 1) ? 1 : 19;

    for (int i = 0; i < 18; i++) {

        String maBan =
            String.format(
                "B%02d",
                start + i
            );

        String trangThai;

        switch ((start + i) % 3) {

            case 0:
                trangThai = "TRONG";
                break;

            case 1:
                trangThai = "DANG_DUNG";
                break;

            default:
                trangThai = "DAT_TRUOC";
                break;
        }

        gridPanel.add(
            createTableButton(
                maBan,
                trangThai,
                "Tầng " + tang
            )
        );
    }

    gridPanel.revalidate();
    gridPanel.repaint();
}

private JButton createTableButton(
    String maBan,
    String trangThai,
    String viTri
) {

    JButton btn = new JButton(
        "<html><center>"
        + maBan
        + "<br><font size='2'>4 Ghế</font>"
        + "</center></html>"
    );

    btn.setFocusPainted(false);

    btn.putClientProperty(
        "JButton.arc",
        25
    );

    btn.setMargin(
        new Insets(
            0,
            0,
            0,
            0
        )
    );

    btn.setFont(
        new Font(
            "Segoe UI",
            Font.BOLD,
            18
        )
    );

    btn.setForeground(Color.WHITE);

    switch (trangThai) {

        case "TRONG":
            btn.setBackground(
                new Color(
                    34,
                    197,
                    94
                )
            );
            break;

        case "DANG_DUNG":
            btn.setBackground(
                new Color(
                    239,
                    68,
                    68
                )
            );
            break;

        case "DAT_TRUOC":
            btn.setBackground(
                new Color(
                    245,
                    158,
                    11
                )
            );
            break;
    }

    btn.addActionListener(e -> {

        txtMaSoGhe.setText(maBan);
        txtViTri.setText(viTri);

        switch (trangThai) {

            case "TRONG":
                cbTrangThai.setSelectedItem(
                    "Trống"
                );
                break;

            case "DANG_DUNG":
                cbTrangThai.setSelectedItem(
                    "Đang sử dụng"
                );
                break;

            case "DAT_TRUOC":
                btn.setBackground(
                    new Color(245,158,11)
                );

                btn.setForeground(
                    new Color(40,40,40)
                );
                break;
        }
    });

    return btn;
}
}