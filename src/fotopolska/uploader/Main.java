package fotopolska.uploader;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.wikipedia.Wiki;

public final class Main extends javax.swing.JFrame {
    //tech
    private volatile boolean stopRq = false;
    int i = 0;
    
    Wiki w;
    
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
        updateCounter();
        
        /**
         * Scroll fixer
         * @see: http://stackoverflow.com/questions/4045722/how-to-make-jtextpane-autoscroll-only-when-scroll-bar-is-at-bottom-and-scroll-lo
         */
        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            BoundedRangeModel brm = scroll.getVerticalScrollBar().getModel();
            boolean wasAtBottom = true;
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (!brm.getValueIsAdjusting()) {
                if (wasAtBottom)
                    brm.setValue(brm.getMaximum());
                } else
                wasAtBottom = ((brm.getValue() + brm.getExtent()) == brm.getMaximum());
            }
        }); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        bLoadFile = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        bDel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tName = new javax.swing.JTextField();
        tPassword = new javax.swing.JPasswordField();
        tServer = new javax.swing.JTextField();
        bLogin = new javax.swing.JButton();
        counter = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        loger = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        bStart = new javax.swing.JButton();
        bStop = new javax.swing.JButton();
        bStartSingle = new javax.swing.JButton();
        bClearLog = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        list.setModel(model);
        jScrollPane2.setViewportView(list);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"));

        bLoadFile.setText("Ładuj plik");
        bLoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadFileActionPerformed(evt);
            }
        });

        bClear.setText("Wyczyść");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        bDel.setText("Usuń zazn.");
        bDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bLoadFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bLoadFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bClear)
                    .addComponent(bDel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sterowanie"));

        tName.setText("YarluFileBot");

        tServer.setText("commons.wikimedia.org");

        bLogin.setText("Zaloguj");
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tName, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        counter.setText("jLabel1");

        loger.setContentType("text/html");
        loger.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        loger.setText("<html><body style=\"font: 10pt Verdana\"></body>\n</html>\n");
        loger.setMaximumSize(new java.awt.Dimension(2147483647, 300));
        scroll.setViewportView(loger);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Start"));

        bStart.setText("<html><b>Start</b></html>");
        bStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartActionPerformed(evt);
            }
        });

        bStop.setText("Stop");
        bStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStopActionPerformed(evt);
            }
        });

        bStartSingle.setText("Pojedynczo");
        bStartSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStartSingleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bStartSingle)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bStart, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(bStartSingle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bStop, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        bClearLog.setText("Wyczyść log");
        bClearLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearLogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(counter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bClearLog)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(counter, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bClearLog))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartActionPerformed
        stopRq = false;
        i = list.getSelectedIndex();
        if(i == -1) i = 0;
        
        Runnable run = new Runnable() {
            @Override
            public void run() {
                bLoadFile.setEnabled(false);
                bClear.setEnabled(false);
                bDel.setEnabled(false);
                
                for(;i<list.getModel().getSize(); ++i) {
                    if(!stopRq) {
                        String number = (String)model.get(i);
                        uploadFile(number);
                    } else
                        break;
                }
                stopRq();
            }
        };
        Thread t = new Thread(run);
        t.start();
    }//GEN-LAST:event_bStartActionPerformed

    void stopRq() {
        stopRq = true;
        bLoadFile.setEnabled(true);
        bClear.setEnabled(true);
        bDel.setEnabled(true);      
    }
    
    void uploadFile(String number) {
        updateCounter();
        logBold("Wczytuję " + i + "...\n");
        
        if(!number.contains("OK")) {
            try {
                Foto f = new Foto(number);
                if(f.isUploadable()) {
                    try {
                        log(f.toString());
                        int j = JOptionPane.showConfirmDialog(rootPane, f.toString());
                        if(j==0) {
                            w.upload(f.getFileSource(), f.getFileName(), f.getWikiText(), "import from fotopolska.eu");
                            model.set(i, model.get(i) + " - OK");
                        } else if(j==2)
                            stopRq();
                    } catch (LoginException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else 
                    log("Nie nadaje się\n");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void bLoadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadFileActionPerformed
        class Filter extends FileFilter {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                String s = f.getName();
                int i = s.lastIndexOf('.');

                if (i > 0 && i < s.length() - 1)
                    if (s.substring(i + 1).toLowerCase().equals("txt"))
                        return true;
                return false;
            }

            @Override
            public String getDescription() {
                return "Plik tekstowy (*.txt)";
            }
        }
        Filter f = new Filter();
        
        JFileChooser ch = new JFileChooser();
        try {
            ch.setCurrentDirectory(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile());
        } catch (URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        ch.setAcceptAllFileFilterUsed(false);
        ch.setMultiSelectionEnabled(false);
        ch.addChoosableFileFilter(f);

        if (ch.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            if(ch.getSelectedFile().isFile())
                loadList(ch.getSelectedFile());
            else
                JOptionPane.showMessageDialog(rootPane, "Coś nie tak z plikiem.");
        }
    }//GEN-LAST:event_bLoadFileActionPerformed

    void loadList(File f) {
        try(BufferedReader in = new BufferedReader(new FileReader(f))) {
            String str;
            while ((str = in.readLine()) != null) {
                int pos = list.getModel().getSize();
                model.add(pos, str);
            }
        } catch (IOException e) {
        }
        updateCounter();
    }
    
    private void bDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDelActionPerformed
        //@see: http://stackoverflow.com/a/7672689/1418878
        DefaultListModel dlm = (DefaultListModel) list.getModel();

        if(this.list.getSelectedIndices().length > 0) {
            int[] selectedIndices = list.getSelectedIndices();
            for (int j = selectedIndices.length-1; j >=0; j--) {
                dlm.removeElementAt(selectedIndices[j]);
            } 
        } 

        //int[] ints = list.getSelectedIndices();
        //for(int j : ints) model.remove(j);
        updateCounter();
    }//GEN-LAST:event_bDelActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        model.removeAllElements();
        updateCounter();
    }//GEN-LAST:event_bClearActionPerformed

    private void bStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStopActionPerformed
        stopRq();
    }//GEN-LAST:event_bStopActionPerformed

    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed
        w = new Wiki(tServer.getText());
        try {
            w.login(tName.getText(), tPassword.getPassword());
            bLogin.setText("Zalogowano");
            bLogin.setEnabled(false);
        } catch (IOException | FailedLoginException ex) {
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex)
            JOptionPane.showMessageDialog(rootPane, ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_bLoginActionPerformed

    private void bClearLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearLogActionPerformed
        loger.setText("<html><body style=\"font: 10pt Verdana\"></body></html>");
    }//GEN-LAST:event_bClearLogActionPerformed

    private void bStartSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStartSingleActionPerformed
        stopRq = false;
        i = list.getSelectedIndex();
        if(i == -1) i = 0;
        
        Runnable run = new Runnable() {
            @Override
            public void run() {
                bLoadFile.setEnabled(false);
                bClear.setEnabled(false);
                bDel.setEnabled(false);
                
                if(!stopRq) {
                    String number = (String)model.get(i);
                    uploadFile(number);
                    list.setSelectedIndex(++i);
                }
                
                stopRq();
            }
        };
        Thread t = new Thread(run);
        t.start();
    }//GEN-LAST:event_bStartSingleActionPerformed


    public void updateCounter() {
        counter.setText("Element: " + i + " / " + list.getModel().getSize());
    }
    
    public void log(String s) {
        try {
            Document doc = loger.getDocument();
            doc.insertString(doc.getLength(), s, null);
        } catch(BadLocationException exc) {
        }
    }
    
    public void logBold(String s) {
        try {
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setBold(attributes, true);
            Document doc = loger.getDocument();
            doc.insertString(doc.getLength(), s, attributes);
        } catch(BadLocationException exc) {
        }
    }
    
    public void logRed(String s) {
        try {
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setForeground(attributes, Color.red);
            Document doc = loger.getDocument();
            doc.insertString(doc.getLength(), s, attributes);
        } catch(BadLocationException exc) {
        }
    }
    
    public void logGreen(String s) {
        try {
            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setForeground(attributes, new Color(0, 102, 0));
            Document doc = loger.getDocument();
            doc.insertString(doc.getLength(), s, attributes);
        } catch(BadLocationException exc) {
        }
    }
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClear;
    private javax.swing.JButton bClearLog;
    private javax.swing.JButton bDel;
    private javax.swing.JButton bLoadFile;
    private javax.swing.JButton bLogin;
    private javax.swing.JButton bStart;
    private javax.swing.JButton bStartSingle;
    private javax.swing.JButton bStop;
    private javax.swing.JLabel counter;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList list;
    private javax.swing.JTextPane loger;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextField tName;
    private javax.swing.JPasswordField tPassword;
    private javax.swing.JTextField tServer;
    // End of variables declaration//GEN-END:variables
    DefaultListModel model = new DefaultListModel();
}
