import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.UIManager;
import java.awt.Color;

public class Main extends JFrame {

    private JPanel contentPane;
    private JTextField nameField;
    private JTextArea personnelArea;
    private JTextArea resultArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setTitle("\u5229\u7528\u6570\u7EC4\u968F\u673A\u62BD\u53D6\u5E78\u8FD0\u89C2\u4F17");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 498, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel
                .setBorder(new TitledBorder(
                        null,
                        "\u8F93\u5165\u5728\u573A\u89C2\u4F17\u59D3\u540D\u6309\u56DE\u8F66",
                        TitledBorder.LEADING, TitledBorder.TOP, null,
                        new Color(59, 59, 59)));
        panel.setBounds(10, 10, 174, 242);
        contentPane.add(panel);
        panel.setLayout(new BorderLayout(0, 5));

        nameField = new JTextField();
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                do_textField_keyPressed(e);
            }
        });
        panel.add(nameField, BorderLayout.NORTH);
        nameField.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane);

        personnelArea = new JTextArea();
        personnelArea.setEditable(false);
        scrollPane.setViewportView(personnelArea);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null,
                "\u9009\u53D6\u89C2\u4F17\u4EBA\u5458\uFF1A",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59,
                59)));
        panel_1.setBounds(183, 10, 219, 242);
        contentPane.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_1 = new JScrollPane();
        panel_1.add(scrollPane_1);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        scrollPane_1.setViewportView(resultArea);

        JButton button = new JButton("\u62BD\u53D6");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(407, 164, 63, 25);
        contentPane.add(button);

        JButton button_1 = new JButton("\u9000\u51FA");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_1_actionPerformed(e);
            }
        });
        button_1.setBounds(407, 215, 63, 25);
        contentPane.add(button_1);
    }

    protected void do_textField_keyPressed(KeyEvent e) {
        if (e.getKeyChar() != '\n')// 不是回车字符不做处理
            return;
        String name = nameField.getText();
        if (name.isEmpty())// 如果文本框没有字符串不做处理
            return;
        personnelArea.append(name + "\n");// 把输入人名与回车符添加到人员列表
        nameField.selectAll();// 选择文本框所有字符
    }

    protected void do_button_actionPerformed(ActionEvent e) {
        String perstring = personnelArea.getText();// 获取人员列表文本
        String[] personnelArray = perstring.split("\n{1,}");// 获取人员数组
        int index = (int) (Math.random() * personnelArray.length);// 生成随机数组索引
        // 定义包含格式参数的中奖信息
        String formatArg = "本次抽取观众人员：\n\t%1$s\n恭喜%1$s成为本次观众抽奖的大奖得主。"
                + "\n\n我们将为%1$s颁发过期的酸奶二十箱。";
        // 为中奖信息添加人员参数
        String info = String.format(formatArg, personnelArray[index]);
        resultArea.setText(info);// 在文本域显示中间信息
    }

    protected void do_button_1_actionPerformed(ActionEvent e) {
        dispose();
    }
}
