
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zanis
 */
public class Direct_Message_Panel extends javax.swing.JPanel {

    /**
     * Creates new form Direct_Message_Panel
     */
    public Direct_Message_Panel() {
        initComponents();
        //Connect to data base:
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javagram_db", "root", "");
            String sql = "SELECT user_name1, message_txt, date  FROM tbl_private_messages WHERE user_name2 = ? ORDER BY date DESC ";
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1,Login_Panel.user_name);
            ResultSet rs = ps.executeQuery();
            
            //aya tbl khali hast ya kheyr
            int i = 0;
            while(tbl_direct_message.getRowCount() > 0){
                ((DefaultTableModel)tbl_direct_message.getModel()).removeRow(0);
            }
            
            while(rs.next()){
                ((DefaultTableModel)tbl_direct_message.getModel()).addRow(new Object[] {rs.getString("user_name1"), rs.getString("message_txt"), rs.getString("date") });
            }
            conn.close();
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_direct_message = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(198, 217, 230));
        setToolTipText("");

        btn_back.setText("Back to Profile");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        tbl_direct_message.setBackground(new java.awt.Color(255, 248, 218));
        tbl_direct_message.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        tbl_direct_message.setForeground(new java.awt.Color(9, 0, 49));
        tbl_direct_message.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "From", "Message", "Date and Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_direct_message.setRowHeight(24);
        tbl_direct_message.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_direct_messageMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_direct_message);

        jLabel4.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(9, 0, 49));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/send_message.png"))); // NOI18N
        jLabel4.setText("Java Gram");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back)
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_back)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        //Profile_Panel.Direct_Message_JFrame.setVisible(false);
        Login_Panel.Profile_Frame.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void tbl_direct_messageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_direct_messageMouseClicked
        DefaultTableModel model = (DefaultTableModel)tbl_direct_message.getModel();
        int selectedRow = tbl_direct_message.getSelectedRow();
        String receiver = model.getValueAt(selectedRow, 0).toString();
        
        JFrame frame = create_JFrame(new SendMessage_Panel(Login_Panel.user_name, receiver), "Send Message", 300, 318);
        frame.setVisible(true);
        
    }//GEN-LAST:event_tbl_direct_messageMouseClicked


    public static JFrame create_JFrame(JPanel panel, String title, int width, int height){
        
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);  
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        
        return frame;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_direct_message;
    // End of variables declaration//GEN-END:variables
}
