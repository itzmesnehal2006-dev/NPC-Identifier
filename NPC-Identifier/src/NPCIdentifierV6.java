import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

public class NPCIdentifierV6 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NPCFrameV6().setVisible(true));
    }
}

class NPCFrameV6 extends JFrame implements ActionListener {
    private JRadioButton[][] options;
    private JButton checkButton, resetButton;
    private JLabel resultLabel, imgLabel;
    private JPanel questionsPanel;

    NPCFrameV6() {
        setTitle("NPC Identifier 🤖 Modern Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Title
        JLabel title = new JLabel("🧠 NPC Identifier 6.0", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        title.setForeground(new Color(50, 50, 180));
        mainPanel.add(title, BorderLayout.NORTH);

        // Questions Panel
        questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBackground(new Color(245, 245, 245));
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] questions = {
            "Do you say 'bro' in every sentence?",
            "Do you quote memes in real-life conversations?",
            "Do you scroll reels until 3 AM?",
            "Do you reply to texts with just emojis?",
            "Do you say 'cringe', 'based', or 'skibidi' unironically?",
            "Do you think 'Touch grass' is a lifestyle?"
        };

        options = new JRadioButton[questions.length][2];

        for (int i = 0; i < questions.length; i++) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10),
                    new LineBorder(Color.LIGHT_GRAY, 1, true)));
            card.setBackground(Color.WHITE);
            card.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.setMaximumSize(new Dimension(520, 80));

            JLabel qLabel = new JLabel((i + 1) + ". " + questions[i]);
            qLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
            card.add(qLabel);

            ButtonGroup group = new ButtonGroup();
            options[i][0] = new JRadioButton("Yes");
            options[i][1] = new JRadioButton("No");
            options[i][0].setBackground(Color.WHITE);
            options[i][1].setBackground(Color.WHITE);
            group.add(options[i][0]);
            group.add(options[i][1]);

            JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            optionPanel.setBackground(Color.WHITE);
            optionPanel.add(options[i][0]);
            optionPanel.add(options[i][1]);
            card.add(optionPanel);

            questionsPanel.add(card);
            questionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JScrollPane scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Result Panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true), "Result"));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setOpaque(true);
        resultPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        checkButton = new JButton("🔍 Check Identity");
        styleButton(checkButton, new Color(100, 200, 255), new Color(80, 180, 255));

        resetButton = new JButton("🔄 Reset");
        styleButton(resetButton, new Color(255, 180, 100), new Color(255, 150, 50));
        resetButton.addActionListener(e -> resetQuiz());

        resultLabel = new JLabel("Your result will appear here 😎", JLabel.CENTER);
        resultLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imgLabel = new JLabel();
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        resultPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultPanel.add(checkButton);
        resultPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        resultPanel.add(resetButton);
        resultPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultPanel.add(resultLabel);
        resultPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        resultPanel.add(imgLabel);

        mainPanel.add(resultPanel, BorderLayout.SOUTH);
        add(mainPanel);

        checkButton.addActionListener(this);
    }

    private void styleButton(JButton button, Color normal, Color hover) {
        button.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(normal);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(normal);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int yesCount = 0;
        for (JRadioButton[] q : options) {
            if (!q[0].isSelected() && !q[1].isSelected()) {
                JOptionPane.showMessageDialog(this, "Please answer all questions!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (q[0].isSelected()) yesCount++;
        }

        Random random = new Random();
        String[] memes;
        if (yesCount >= 4) {
            resultLabel.setText("🤖 You’re 100% an NPC! Touch grass 🌱");
            memes = new String[]{
                "https://i.imgflip.com/6sybvv.jpg",
                "https://i.imgflip.com/8c4evf.jpg",
                "https://i.imgflip.com/89m8v8.jpg"
            };
        } else if (yesCount >= 2) {
            resultLabel.setText("😐 Half NPC detected — still some humanity left.");
            memes = new String[]{
                "https://i.imgflip.com/8c4ezq.jpg",
                "https://i.imgflip.com/8c4f1h.jpg"
            };
        } else {
            resultLabel.setText("🧠 You’re a true human being! Congrats 🎉");
            memes = new String[]{
                "https://i.imgflip.com/89m9ax.jpg",
                "https://i.imgflip.com/8c4f4d.jpg"
            };
        }

        try {
            URL url = new URL(memes[random.nextInt(memes.length)]);
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            imgLabel.setText("Failed to load meme image 😅");
        }
    }

    private void resetQuiz() {
        for (JRadioButton[] q : options) {
            q[0].setSelected(false);
            q[1].setSelected(false);
        }
        resultLabel.setText("Your result will appear here 😎");
        imgLabel.setIcon(null);
        imgLabel.setText("");
    }
}
