
import com.mysql.jdbc.Connection;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
public class UserPosts_Panel extends javax.swing.JPanel {

    /**
     * Creates new form UserPosts_Panel
     */
    public static String Pusername;
    public static String Ppost;
    public static String Pdate;
    
    public UserPosts_Panel(String user_name, String post, String date) {
        
        initComponents();
        
        Pusername=user_name;
        Ppost=post;
        Pdate=date;
        
        lbl_date.setText(date);
        txta_Post_text.setText(post);
        
        Integer commentNumber = 0;
        Integer likeNumber = 0;
        
        //Display Comment and CommentNumber
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javagram_db","root","");
            String sql="SELECT user_name1, comment_text FROM tbl_comments WHERE user_name2 = ? AND post = ? AND post_date = ? ORDER BY comment_date DESC";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, Pusername);
            ps.setString(2, Ppost);
            ps.setString(3, Pdate);
            ResultSet rs = ps.executeQuery();
            
            while(jTable1.getRowCount()>0){
                ((DefaultTableModel)jTable1.getModel()).removeRow(0);
            }
            
            while(rs.next()){
                commentNumber++;
                lbl_cm_number.setText(commentNumber.toString());
                ((DefaultTableModel)jTable1.getModel()).addRow(new Object[]{rs.getString("user_name1"),rs.getString("comment_text")});
            }
             
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        //LikeNumber
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javagram_db","root","");
            String sql="SELECT * FROM tbl_like_post WHERE user_name2 = ? AND post = ? AND like_date = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, Pusername);
            ps.setString(2, Ppost);
            ps.setString(3, Pdate);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                likeNumber++;
                lbl_like_number.setText(likeNumber.toString());
            }
            connect.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        //Display image
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javagram_db","root","");
            String sql="SELECT * FROM tbl_post WHERE user_name = ? AND post_txt = ? AND date = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, Pusername);
            ps.setString(2, Ppost);
            ps.setString(3, Pdate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                InputStream getImage = rs.getBinaryStream("post_pic");
                BufferedImage im = ImageIO.read(getImage);
                Image scaleImage = im.getScaledInstance(329, 210, WIDTH);
                ImageIcon icon = new ImageIcon(scaleImage);
                lbl_image.setIcon(icon);
            }
            connect.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
                
       //Check like
       try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javagram_db","root","");
            String sql="SELECT * FROM tbl_like_post WHERE user_name1 = ? AND user_name2 = ? AND post = ? AND like_date=?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, Login_Panel.user_name);
            ps.setString(2, Pusername);
            ps.setString(3, Ppost);
            ps.setString(4, Pdate);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                btn_like.setSelected(true);
            }
            else{
                btn_like.setSelected(false);
            }

       }
       catch(Exception e){
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
        jLabel1 = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txta_Post_text = new javax.swing.JTextArea();
        lbl_image = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_like_number = new javax.swing.JLabel();
        btn_like = new javax.swing.JToggleButton();
        btn_send_cm = new javax.swing.JButton();
        txt_comment = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbl_cm_number = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(198, 217, 230));
        setForeground(new java.awt.Color(9, 0, 49));
        setToolTipText("");

        btn_back.setText("Back to User's Profile");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(9, 0, 49));
        jLabel1.setText("Sharing Date : ");

        lbl_date.setForeground(new java.awt.Color(9, 0, 49));
        lbl_date.setText("Date");

        txta_Post_text.setEditable(false);
        txta_Post_text.setColumns(20);
        txta_Post_text.setLineWrap(true);
        txta_Post_text.setRows(5);
        jScrollPane1.setViewportView(txta_Post_text);

        lbl_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture.png"))); // NOI18N
        lbl_image.setPreferredSize(new java.awt.Dimension(227, 117));

        jLabel2.setForeground(new java.awt.Color(9, 0, 49));
        jLabel2.setText("Likes");
        jLabel2.setToolTipText("");

        lbl_like_number.setForeground(new java.awt.Color(9, 0, 49));
        lbl_like_number.setText("0");
        lbl_like_number.setToolTipText("");

        btn_like.setIcon(new javax.swing.ImageIcon(getClass().getResource("/like24.png"))); // NOI18N
        btn_like.setToolTipText("Like this Post");
        btn_like.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_likeActionPerformed(evt);
            }
        });

        btn_send_cm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cm.png"))); // NOI18N
        btn_send_cm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_send_cmActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(9, 0, 49));
        jLabel4.setText("Commnets");
        jLabel4.setToolTipText("");

        lbl_cm_number.setForeground(new java.awt.Color(9, 0, 49));
        lbl_cm_number.setText("0");
        lbl_cm_number.setToolTipText("");

        jTable1.setBackground(new java.awt.Color(255, 248, 218));
        jTable1.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        jTable1.setForeground(new java.awt.Color(9, 0, 49));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sender", "Comment"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(24);
        jScrollPane2.setViewportView(jTable1);

        jLabel5.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(9, 0, 49));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/send_message.png"))); // NOI18N
        jLabel5.setText("Java Gram");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_date))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btn_back)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_comment, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2))
                                        .addGap(42, 42, 42)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_cm_number, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_like_number, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_send_cm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_like, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_date))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_like_number)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_cm_number)
                            .addComponent(jLabel4)))
                    .addComponent(btn_like, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_send_cm)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txt_comment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_back)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        User_Panel.user_Posts_JFrame.setVisible(false);
        Search_Panel.user_JFrame.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_likeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_likeActionPerformed
        
        if(btn_like.isSelected()){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javagram_db","root","");
                String sql="INSERT INTO tbl_like_post VALUES(?, ?, ?, ?)";
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, Login_Panel.user_name);
                ps.setString(2, Pusername);
                ps.setString(3, Ppost);
                ps.setString(4, Pdate);
                ps.executeUpdate();

                
                JFrame frame = create_JFrame(new UserPosts_Panel(Pusername, Ppost, Pdate), "JavaGram(Posts)",  400, 670);
                frame.setVisible(true);
                User_Panel.user_Posts_JFrame.setVisible(false);
                
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else{
             try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javagram_db","root","");
                String sql="Delete FROM tbl_like_post WHERE user_name1 = ? AND user_name2 = ? AND post = ? AND like_date = ?";
                PreparedStatement ps = connect.prepareStatement(sql);
                ps.setString(1, Login_Panel.user_name);
                ps.setString(2, Pusername);
                ps.setString(3, Ppost);
                ps.setString(4, Pdate);
                ps.executeUpdate();

                
                JFrame frame = create_JFrame(new UserPosts_Panel(Pusername, Ppost, Pdate), "JavaGram(Posts)",  400, 670);
                frame.setVisible(true);
                User_Panel.user_Posts_JFrame.setVisible(false);
                User_Panel.user_Posts_JFrame.show(false);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }//GEN-LAST:event_btn_likeActionPerformed

    private void btn_send_cmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_send_cmActionPerformed
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/javagram_db","root","");
            String sql="INSERT INTO tbl_comments (user_name1, user_name2, post, post_date, comment_text) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, Login_Panel.user_name);
            ps.setString(2, Pusername);
            ps.setString(3, Ppost);
            ps.setString(4, Pdate);
            ps.setString(5, txt_comment.getText());
            ps.executeUpdate();
            
            
            JFrame frame = create_JFrame(new UserPosts_Panel(Pusername, Ppost, Pdate), "JavaGram(Posts)",  400, 670);
            frame.setVisible(true);
            User_Panel.user_Posts_JFrame.setVisible(false);
            User_Panel.user_Posts_JFrame.show(false);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btn_send_cmActionPerformed


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
    private javax.swing.JToggleButton btn_like;
    private javax.swing.JButton btn_send_cm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_cm_number;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JLabel lbl_like_number;
    private javax.swing.JTextField txt_comment;
    private javax.swing.JTextArea txta_Post_text;
    // End of variables declaration//GEN-END:variables
}
