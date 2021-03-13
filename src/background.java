
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Win 10
 */
public class background extends javax.swing.JPanel {

    /**
     * Creates new form background
     */
    Column[] c;
    Chim cu;
    boolean play = true;
    MyThread1 t1;
    MyThread2 t2;
    MyThread3 t3;
    Vector<Integer> koTrung = new Vector<>();
    int diem = 0;

    public background() {
        this.setSize(1200, 800);
        init();
        initComponents();
        lbDiem.setText(diem + "");
        lbDiem.setVisible(false);
        lbDiem1.setText("Mark: 0");
        lbDiem1.setVisible(true);
//        this.addKeyListener(new moveUpDown());
//        setFocusable(true);//////// 
        t1 = new MyThread1(play, this);
        t2 = new MyThread2(play);
        t3 = new MyThread3(play);
        t1.start();
        t2.start();
        t3.start();
        
    }

    private class moveUpDown extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                cu.y -= 60;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                cu.y += 17;
            }
        }

    }

    class MyThread1 extends Thread {

        boolean play;
        background x;
        public MyThread1(boolean play, background x) {
            this.play = play;
            this.x=x;
        }

        @Override
        public void run() {
            while (play) {
                if (isGameOver()) {
                    getPlay(false);
                    int ans=JOptionPane.showConfirmDialog(null, "Thua rồi!!!", null, JOptionPane.YES_NO_OPTION);
                    if(ans==JOptionPane.YES_OPTION){
                        getPlay(true);
                        diem=0;
                        lbDiem1.setText("Mark: "+diem);
                        init();
                    }
                    else{
                        t1.stop();
                    }
                    
                }
                for (Column column : c) {
                    column.x -= 10;
                }
                Random rd=new Random();
                for(int g=0; g<c.length; g++){
                    if(c[g].x==0)
                    {
                        int k=100+rd.nextInt(400);
                        if(g<4){
                            c[g].setX(1200);
                            c[g].setHeight(k);
//                            System.out.println("cot: "+c[g].height);
                        }
                        else{
                            c[g].setX(1200);
                            c[g].setY(200+c[g-4].height);
                            c[g].setHeight(800-(200+c[g-4].height));
//                            System.out.println("cot: "+c[g].y);
                        }
                    }
                    
                }
                repaint();//gọi rp là gọi hàm paint() => gọi paintComponent();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyThread2 extends Thread {

        boolean play;

        public MyThread2(boolean play) {
            this.play = play;
        }

        @Override
        public void run() {
            while (play) {
                if (t1.getState() == State.TERMINATED) {
                    t2.stop();
                } else if (t1.getState() == State.WAITING) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(background.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    cu.y += 4;
                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class MyThread3 extends Thread {

        boolean play;

        public MyThread3(boolean play) {
            this.play = play;
        }

        @Override
        public void run() {
            while (play) {
                if (t1.getState() == State.RUNNABLE) {
                    for (Column column : c) {
                        if (column.x == 50) {
                            lbDiem.setVisible(true);
                            diem += 10;
                            lbDiem.setText("+10");
                            lbDiem1.setText("Mark: "+diem);
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            lbDiem.setVisible(false);
                            repaint();
                        }
                    }
                } else if (t1.getState() == State.TERMINATED) 
                    t3.stop();
                else if (t1.getState() == State.WAITING) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(background.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public synchronized void getPlay(boolean a) {
        play = a;
    }

    public synchronized boolean isGameOver() {
        for (Column column : c) {
            if (column.intersects(cu)) {
                return true;
            }
        }
        return false;
    }

    

    void init() {
        c = new Column[8];
//        c[0]=new Column(Color.green, 500, 0, 50, 400, 1);
//        c[1]=new Column(Color.green, 730, 0, 50, 350, 1);
//        c[2]=new Column(Color.green, 960, 0, 50, 450, 1);
//        c[3]=new Column(Color.green, 1190, 0, 50, 250, 1);
//        c[4]=new Column(Color.green, 1450, 0, 50, 450, 1);
//        c[5]=new Column(Color.green, 500, 150+400, 50, this.getHeight()-150-400,0);
//        c[6]=new Column(Color.green, 730, 150+350, 50, this.getHeight()-150-350, 0);
//        c[7]=new Column(Color.green, 960, 150+450, 50, this.getHeight()-150-450, 0);
//        c[8]=new Column(Color.green, 1190, 150+250, 50, this.getHeight()-150-250, 0);
//        c[9]=new Column(Color.green, 1450, 150+450, 50, this.getHeight()-150-450, 0);
        int[] a = {500, 790, 1080, 1370};
        Random rd = new Random();
        for (int i = 0; i < c.length; i++) {
            int k = 100 + rd.nextInt(400);
            if (i < 4) {
                while(koTrung.contains(k)){
                    k = 100 + rd.nextInt(400);
                }
                c[i] = new Column(Color.green, a[i], 0, 50, k, 1);
                koTrung.add(k);
                if(koTrung.size()==200)
                    koTrung.clear();
            } else {
                c[i] = new Column(Color.green, a[i - 4], 200 + c[i - 4].height, 50, this.getHeight() - (200 + c[i - 4].height), 0);
            }
        }
        cu = new Chim("E:\\PRJ321_JavaDesktop\\FlappyBird\\an2.png", 100, 250, 127, 80);//12 17// 227
    }

    @Override
    protected void paintComponent(Graphics g) {//Chỉ vẽ lại component được tạo lại
        super.paintComponent(g); //vẽ panel ban đầu
//        this.setSize(1500, 900);// set theo màn hình dùng window
        String[] arr={};
        ImageIcon icon1 = new ImageIcon("E:\\PRJ321_JavaDesktop\\FlappyBird\\k.jpg");
        ImageIcon icon2 = new ImageIcon("E:\\PRJ321_JavaDesktop\\FlappyBird\\PC.jpg");
        ImageIcon icon3 = new ImageIcon("E:\\PRJ321_JavaDesktop\\FlappyBird\\anime.jpg");
        ImageIcon icon4 = new ImageIcon("E:\\PRJ321_JavaDesktop\\FlappyBird\\n.jpg");
        ImageIcon icon5 = new ImageIcon("E:\\PRJ321_JavaDesktop\\FlappyBird\\way.jpg");
        g.drawImage(icon1.getImage(), 0, 0, null);
        if(diem>=0 && diem <50)
            g.drawImage(icon1.getImage(), 0, 0, null);
        else if(diem>=50 && diem <100)
            g.drawImage(icon2.getImage(), 0, 0, null);
        else if(diem>=100 && diem <150)
            g.drawImage(icon3.getImage(), 0, 0, null);
        else if(diem>=150 && diem <200)
            g.drawImage(icon4.getImage(), 0, 0, null);
        else
            g.drawImage(icon5.getImage(), 0, 0, null);
        for (Column column : c) {
            column.drawColumn(g);
        }
//        Graphics2D g2d= (Graphics2D) g;
        cu.drawChim(g);
//        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbDiem = new javax.swing.JLabel();
        lbDiem1 = new javax.swing.JLabel();

        lbDiem.setFont(new java.awt.Font("UD Digi Kyokasho NK-B", 3, 30)); // NOI18N
        lbDiem.setForeground(new java.awt.Color(0, 255, 51));

        lbDiem1.setFont(new java.awt.Font("UD Digi Kyokasho NK-B", 3, 30)); // NOI18N
        lbDiem1.setForeground(new java.awt.Color(0, 0, 153));
        lbDiem1.setText("Mark: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(446, 446, 446)
                .addComponent(lbDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(630, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbDiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lbDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 530, Short.MAX_VALUE)
                .addComponent(lbDiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbDiem;
    private javax.swing.JLabel lbDiem1;
    // End of variables declaration//GEN-END:variables
}
