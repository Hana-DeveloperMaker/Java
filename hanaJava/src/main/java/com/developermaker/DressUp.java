package com.developermaker;

import com.developermaker.entity.Result;
import com.developermaker.entity.ScoreType;
import com.developermaker.entity.User;
import com.developermaker.utils.JsonUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class DressUp extends JFrame {

    private JLabel basePersonLabel, hairLabel, topLabel, bottomLabel, shoesLabel;
    private Map<String, Point> positionMap = new HashMap<>();
    private final Map<String, String> currentWearing = new HashMap<>();

    private final String[] categories = {"hair", "top", "bottom", "shoes"};
    private final int[] itemCounts = {3, 4, 4, 4};
    private int currentCategoryIndex = 0;

    private JPanel thumbnailPanel;
    private JLabel categoryLabel;

    private final User user;

    private void saveCurrentOutfitImage() {
        int width = 450;
        int height = 600;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();

        ImageIcon baseIcon = (ImageIcon) basePersonLabel.getIcon();
        if (baseIcon != null) {
            g2.drawImage(baseIcon.getImage(), basePersonLabel.getX(), basePersonLabel.getY(), null);
        }

        String[] drawOrder = {"hair", "top", "bottom", "shoes"};
        for (String category : drawOrder) {
            String itemKey = currentWearing.get(category);
            if (itemKey != null) {
                String path = "src/main/resources/" + itemKey + ".png";
                ImageIcon icon = new ImageIcon(path);
                Point pos = positionMap.getOrDefault(itemKey, new Point(0, 0));
                g2.drawImage(icon.getImage(), pos.x, pos.y, null);
            }
        }

        g2.dispose();

        try {
            File output = new File("src/main/resources/outfit.png");
            ImageIO.write(image, "png", output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public DressUp(User user) {
        this.user = user;
        setTitle("\uD83D\uDD2E옷을 입혀볼까");
        setSize(600, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        initPositionMap();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 450, 600);
        add(layeredPane);

        ImageIcon baseIcon = new ImageIcon("src/main/resources/person.png");
        basePersonLabel = new JLabel(baseIcon);
        basePersonLabel.setBounds(40, 30, baseIcon.getIconWidth(), baseIcon.getIconHeight());
        layeredPane.add(basePersonLabel, Integer.valueOf(-100));

        hairLabel = new JLabel(); hairLabel.setBounds(0, 0, 0, 0); layeredPane.add(hairLabel, 10);
        topLabel = new JLabel(); topLabel.setBounds(0, 0, 0, 0); layeredPane.add(topLabel, 20);
        bottomLabel = new JLabel(); bottomLabel.setBounds(0, 0, 0, 0); layeredPane.add(bottomLabel, 30);
        shoesLabel = new JLabel(); shoesLabel.setBounds(0, 0, 0, 0); layeredPane.add(shoesLabel, 40);

        ImageIcon resetIcon = new ImageIcon("src/main/resources/btn_reset.png");
        Image scaled = resetIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton resetButton = new JButton(new ImageIcon(scaled));
        resetButton.setBounds(130, 530, 30, 30);
        resetButton.setContentAreaFilled(false);
        resetButton.setBorderPainted(false);
        resetButton.setFocusPainted(false);
        resetButton.setToolTipText("Reset");
        resetButton.addActionListener(e -> resetClothes());
        add(resetButton);

        ImageIcon doneIcon = new ImageIcon("src/main/resources/btn_done.png");
        Image doneScaled = doneIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton doneButton = new JButton(new ImageIcon(doneScaled));
        doneButton.setBounds(180, 530, 30, 30);
        doneButton.setContentAreaFilled(false);
        doneButton.setBorderPainted(false);
        doneButton.setFocusPainted(false);
        doneButton.setToolTipText("Done");
        doneButton.addActionListener(e -> handleDoneAction());
        add(doneButton);

        thumbnailPanel = new JPanel();
        thumbnailPanel.setLayout(new BoxLayout(thumbnailPanel, BoxLayout.Y_AXIS));
        thumbnailPanel.setBounds(410, 80, 130, 480);
        add(thumbnailPanel);

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBounds(0, 600, 600, 40);
        add(controlPanel);

        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");

        categoryLabel = new JLabel("", SwingConstants.CENTER);
        controlPanel.add(categoryLabel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(prevButton);
        controlPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(nextButton);
        controlPanel.add(rightPanel, BorderLayout.EAST);

        prevButton.addActionListener(e -> {
            if (currentCategoryIndex > 0) {
                currentCategoryIndex--;
                updateThumbnails();
            }
        });

        nextButton.addActionListener(e -> {
            if (currentCategoryIndex < categories.length - 1) {
                currentCategoryIndex++;
                updateThumbnails();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                handleDoneAction();
            }
        });

        updateThumbnails();
        setVisible(true);
    }

    private void updateThumbnails() {
        thumbnailPanel.removeAll();
        String category = categories[currentCategoryIndex];
        int itemCount = itemCounts[currentCategoryIndex];

        categoryLabel.setText(category.toUpperCase());

        for (int i = 1; i <= itemCount; i++) {
            String key = category + i;
            String path = "src/main/resources/" + key + ".png";

            ImageIcon icon = new ImageIcon(path);
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JButton thumbButton = new JButton(new ImageIcon(scaledImage));

            thumbButton.setPreferredSize(new Dimension(100, 100));
            thumbButton.setMaximumSize(new Dimension(100, 100));
            thumbButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            thumbButton.setBorder(BorderFactory.createEmptyBorder());

            thumbButton.addActionListener((ActionEvent e) -> {
                switch (category) {
                    case "hair" -> updateLayer(hairLabel, key, path, category);
                    case "top" -> updateLayer(topLabel, key, path, category);
                    case "bottom" -> updateLayer(bottomLabel, key, path, category);
                    case "shoes" -> updateLayer(shoesLabel, key, path, category);
                }
            });

            thumbnailPanel.add(Box.createVerticalStrut(5));
            thumbnailPanel.add(thumbButton);
        }

        thumbnailPanel.revalidate();
        thumbnailPanel.repaint();
    }

    private void updateLayer(JLabel label, String key, String imagePath, String category) {
        String current = currentWearing.get(category);
        if (key.equals(current)) {
            label.setIcon(null);
            label.setBounds(0, 0, 0, 0);
            currentWearing.remove(category);
        } else {
            ImageIcon icon = new ImageIcon(imagePath);
            label.setIcon(icon);
            Point pos = positionMap.getOrDefault(key, new Point(0, 0));
            label.setBounds(pos.x, pos.y, icon.getIconWidth(), icon.getIconHeight());
            currentWearing.put(category, key);
        }
    }

    private void resetClothes() {
        hairLabel.setIcon(null); hairLabel.setBounds(0, 0, 0, 0);
        topLabel.setIcon(null); topLabel.setBounds(0, 0, 0, 0);
        bottomLabel.setIcon(null); bottomLabel.setBounds(0, 0, 0, 0);
        shoesLabel.setIcon(null); shoesLabel.setBounds(0, 0, 0, 0);
        currentWearing.clear();
    }

    private void initPositionMap() {
        positionMap.put("hair1", new Point(41, -8));
        positionMap.put("hair2", new Point(35, 30));
        positionMap.put("hair3", new Point(59, 35));
        positionMap.put("top1", new Point(57, 190));
        positionMap.put("top4", new Point(55, 173));
        positionMap.put("top3", new Point(80, 180));
        positionMap.put("top2", new Point(57, 190));
        positionMap.put("bottom1", new Point(49, 233));
        positionMap.put("bottom4", new Point(83, 290));
        positionMap.put("bottom3", new Point(55, 250));
        positionMap.put("bottom2", new Point(65, 284));
        positionMap.put("shoes1", new Point(65, 450));
        positionMap.put("shoes2", new Point(70, 430));
        positionMap.put("shoes3", new Point(46, 370));
        positionMap.put("shoes4", new Point(55, 430));
    }

    private void handleDoneAction() {
        String hair = currentWearing.getOrDefault("hair", "none");
        String top = currentWearing.get("top");
        String bottom = currentWearing.get("bottom");
        String shoes = currentWearing.get("shoes");

        saveCurrentOutfitImage();

        String message = "옷입히기";
        Map<ScoreType, Integer> scoreMap = new EnumMap<>(ScoreType.class);

        if (top != null && bottom != null && shoes != null) {
            if (top.equals("top2") && bottom.equals("bottom3") && shoes.equals("shoes4")) {
                message = "정장";
                scoreMap.put(ScoreType.WITH_CUSTOMER, 5);
                scoreMap.put(ScoreType.EXCELLENCE, 5);
            } else if (top.equals("top4") && bottom.equals("bottom1") && shoes.equals("shoes1")) {
                message = "추리닝";
                scoreMap.put(ScoreType.OPENNESS, 5);
            }
        }

        Result resultObj = new Result("outfit", message, scoreMap);
        user.updateScores(resultObj);
        try {
            JsonUtil.setUserScore(user, resultObj);
            JsonUtil.saveUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int result;

        if (top == null || bottom == null || shoes == null) {
            result = 0;
        } else {
            int hairGroup = (hair.equals("hair1") || hair.equals("hair3")) ? 1 : 2;
            int topGroup = (top.equals("top1") || top.equals("top2")) ? 1 : 2;
            int bottomGroup = (bottom.equals("bottom1") || bottom.equals("bottom2")) ? 1 : 2;
            int shoesGroup = (shoes.equals("shoes1") || shoes.equals("shoes2")) ? 1 : 2;

            result = switch (hairGroup) {
                case 1 -> switch (topGroup) {
                    case 1 -> switch (bottomGroup) {
                        case 1 -> (shoesGroup == 1) ? 4 : 3;
                        case 2 -> (shoesGroup == 1) ? 2 : 1;
                        default -> 0;
                    };
                    case 2 -> switch (bottomGroup) {
                        case 1 -> (shoesGroup == 1) ? 8 : 7;
                        case 2 -> (shoesGroup == 1) ? 6 : 5;
                        default -> 0;
                    };
                    default -> 0;
                };
                case 2 -> switch (topGroup) {
                    case 1 -> switch (bottomGroup) {
                        case 1 -> (shoesGroup == 1) ? 12 : 11;
                        case 2 -> (shoesGroup == 1) ? 10 : 9;
                        default -> 0;
                    };
                    case 2 -> switch (bottomGroup) {
                        case 1 -> (shoesGroup == 1) ? 16 : 15;
                        case 2 -> (shoesGroup == 1) ? 14 : 13;
                        default -> 0;
                    };
                    default -> 0;
                };
                default -> 0;
            };
        }

        user.setDressCode(result);
        try {
            JsonUtil.saveUser(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dispose();
    }
}
