
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author M.Taghizadeh
 */

public class JavaGram {
    public static JFrame login_frmae;
    public static void main(String[] args) {
        splash();
        login();
    }
    
    public static void splash(){
        Splash_Panel splash = new Splash_Panel();
        JFrame splash_frame = create_JFrame(splash, "", 778, 599, 1);
        splash_frame.setVisible(true);
        
        try {
            for (int i = 0; i < 100; i++) {
                if(i == 50) splash.lbl_logo2.setText("Gram");
                Thread.sleep(52);
                splash.prog.setValue(i);
                splash.bar.setText(Integer.toString(i) + "%");
            }

        } catch (InterruptedException e) {}

        splash_frame.setVisible(false);
    }
    public static void login(){
        login_frmae = create_JFrame(new Login_Panel(), "Java Gram (Login Page)", 350, 500, 0);
        login_frmae.setVisible(true);
    }
    
    public static JFrame create_JFrame(JPanel panel, String title, int width, int height, int isSplash){
        
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(panel); 
       
        if(isSplash==0){
            frame.setUndecorated(false);
        }   
        else 
            frame.setUndecorated(true);
        
        return frame;
    }
}