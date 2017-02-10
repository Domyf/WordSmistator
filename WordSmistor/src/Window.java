
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Domenico
 */
public class Window extends javax.swing.JFrame {
    
    private String easyFileName = "easywords";
    private String mediumFileName = "mediumwords";
    private String hardFileName = "hardwords";
    private String userName = "";
    private PrintWriter easyFile, mediumFile, hardFile;
    private BufferedReader wordListReader;
    private File wordList;
    private long wordListIndex;
    private boolean wordListOpened;
    
    public Window() {
        initComponents();
        setLocationRelativeTo(null);
        userName = getUserName();
        initCounters();
        
        wordListOpened = false;
        easyFile = openWordFile(easyFileName);
        mediumFile = openWordFile(mediumFileName);
        hardFile = openWordFile(hardFileName);
        
    }
    
    private String getUserName() {
        File f = new File("Data");
        if (!f.exists())
            f.mkdir();
        
        StringTokenizer stk = new StringTokenizer(f.getAbsolutePath(), "\\");
        while (!stk.nextToken().equals("Users")){}
        
        return stk.nextToken();
    }
    
    /**Apre il file passato per argomento. Se non è presente o se presente ma vuoto, lo crea inserendo il comando INSERT*/
    private PrintWriter openWordFile(String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Data\\"+userName+"\\"+fileName + ".txt"));
            if(reader.readLine().isEmpty())
                return createNewFile(fileName);
            return new PrintWriter(new FileWriter("Data\\"+userName+"\\"+fileName + ".txt", true), true);
        }catch(FileNotFoundException ex){
            return createNewFile(fileName);
        }catch(IOException ex){
            return null;
        }
    }
    /**Crea un nuovo file con il nome passato per argomento inserendo il comando INSERT*/
    private PrintWriter createNewFile(String fileName){
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Data\\"+userName+"\\"+fileName + ".txt"));
            //writer.println("INSERT INTO " + fileName + "(ID, Word, Meaning, Category) VALUES");
            writer.flush();
            return writer;
        } catch (IOException ex) {
            return null;
        }
    }
    
    private void saveCounters(){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("Data\\"+userName+"\\save"));
            writer.println(easyWords.getText()); writer.println(mediumWords.getText()); writer.println(hardWords.getText()); writer.println(""+wordListIndex);
            writer.flush();
            writer.close();
        }catch(IOException ex){
            System.out.println("Non riesco a salvare il salvataggio.\n"+ex.getMessage());
        }
    }
    
    /***/
    private void initCounters() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("Data\\"+userName+"\\save"));
            easyWords.setText(bufferedReader.readLine()); 
            mediumWords.setText(bufferedReader.readLine());
            hardWords.setText(bufferedReader.readLine());
            //wordIndex = Long.parseLong(bufferedReader.readLine());
            wordListIndex = Long.parseLong(bufferedReader.readLine());
            bufferedReader.close();
        }catch(Exception ex){
            System.out.println("Non riesco ad inizializzare i contatori.\n"+ex.getMessage());
            new File("Data\\"+userName).mkdir();
            try {
                PrintWriter writer = new PrintWriter(new FileWriter("Data\\"+userName+"\\save"));
                for (int i=0; i<4; i++)
                    writer.println("0");
                writer.flush();
            } catch (IOException e) {
                
            }
        }
    }
    
    private String loadWordListData(){
        String temp = null;
        try {
            wordListReader = new BufferedReader(new FileReader(wordList.getAbsolutePath()));
            temp = wordListReader.readLine();
            for (int i=0; i<wordListIndex; i++)
                temp = wordListReader.readLine();
        } catch(IOException ex){
            System.out.println("Errore nel caricare la riga "+wordListIndex+" da "+wordList.getAbsolutePath()+"\n"+ex.getMessage());
        }
        return temp;
    }
    
    private String nextRow() {
        try {
            wordListIndex++;
            return wordListReader.readLine();
        } catch (IOException ex) {
            wordListIndex--;
            System.out.println("Errore nel leggere la riga successiva da "+wordList.getAbsolutePath()+"\n"+ex.getMessage());
        }
        return null;
    }
    
    private void setUI(String str) {
        StringTokenizer stk = new StringTokenizer(str, ",");
        txtWord.setText(stk.nextToken());
        txtMeaning.setText(stk.nextToken());
        numeroLettere.setText("" + txtMeaning.getText().length());
        if (txtMeaning.getText().length() >= 150) {
            numeroLettere.setForeground(Color.red);
            jLabelNumLet.setForeground(Color.red);
        } else {
            numeroLettere.setForeground(Color.black);
            jLabelNumLet.setForeground(Color.black);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        easyWords = new javax.swing.JLabel();
        mediumWords = new javax.swing.JLabel();
        hardWords = new javax.swing.JLabel();
        btnLoad = new javax.swing.JButton();
        btnEasy = new javax.swing.JButton();
        btnHard = new javax.swing.JButton();
        btnMedium = new javax.swing.JButton();
        txtWord = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelNumLet = new javax.swing.JLabel();
        numeroLettere = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMeaning = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WordSmistor 2000");
        setResizable(false);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Medie");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Difficili");

        easyWords.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        easyWords.setText("0");

        mediumWords.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mediumWords.setText("0");

        hardWords.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hardWords.setText("0");

        btnLoad.setText("Apri...");
        btnLoad.setFocusable(false);
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnEasy.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnEasy.setText("Facile");
        btnEasy.setFocusable(false);
        btnEasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEasyActionPerformed(evt);
            }
        });

        btnHard.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnHard.setText("Difficile");
        btnHard.setFocusable(false);
        btnHard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHardActionPerformed(evt);
            }
        });

        btnMedium.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnMedium.setText("Media");
        btnMedium.setFocusable(false);
        btnMedium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMediumActionPerformed(evt);
            }
        });

        txtWord.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtWord.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtWord.setText("Parola");
        txtWord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtWordMouseClicked(evt);
            }
        });

        btnNext.setText("Scarta");
        btnNext.setFocusable(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnNew.setText("Nuova");
        btnNew.setFocusable(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel1.setText("WordSmistor 2000 Developed by HDev");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Facili");

        jLabelNumLet.setText("Numero lettere:");

        txtMeaning.setColumns(20);
        txtMeaning.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMeaning.setLineWrap(true);
        txtMeaning.setRows(2);
        txtMeaning.setText("Definizione");
        txtMeaning.setWrapStyleWord(true);
        txtMeaning.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMeaningKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMeaningKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtMeaning);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelNumLet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroLettere, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(easyWords, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(mediumWords, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hardWords, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLoad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNew))
                            .addComponent(txtWord, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnEasy, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(btnHard, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)))
                        .addGap(10, 10, 10))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext)
                        .addComponent(btnLoad)
                        .addComponent(btnNew))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(easyWords)
                    .addComponent(hardWords)
                    .addComponent(mediumWords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(txtWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(numeroLettere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNumLet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEasy, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHard, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtMeaning.setFocusable(true);
        txtWord.setFocusable(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEasyActionPerformed
        String word = txtWord.getText();
        String meaning = txtMeaning.getText();
        if(!word.isEmpty() && !meaning.isEmpty()){
            if(!word.equals("Parola") && !meaning.equals("Definizione")){
                int count = Integer.parseInt(easyWords.getText());
                if(count > 0)
                    easyFile.append(System.getProperty("line.separator"));
//                    easyFile.append("," + System.getProperty("line.separator"));
//                  easyFile.append("(\"" + (count+1) + "\",\"" + word + "\",\"" + meaning + "\",\"" + "\")");
//                  easyFile.flush();
//                  easyWords.setText("" + ++count);
                  easyFile.append(word + "," + meaning);
                  easyFile.flush();
                  easyWords.setText("" + ++count);
                if (wordListOpened) {
                    String temp = nextRow();
                    if (temp != null) {
                        setUI(temp);
                    } else {
                        txtWord.setText("Parola");
                        txtMeaning.setText("Definizione");
                        JOptionPane.showMessageDialog(this, "Sono finite le parole!");
                        wordListOpened = false;
                        wordListIndex = -1;
                        System.out.println("Fine del file!");
                    }
                } else {
                    txtWord.setText("Parola");
                    txtMeaning.setText("Definizione");
                }
                saveCounters();
            }
        }
    }//GEN-LAST:event_btnEasyActionPerformed

    private void btnMediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMediumActionPerformed
        String word = txtWord.getText();
        String meaning = txtMeaning.getText();
        if(!word.isEmpty() && !meaning.isEmpty()){
            if(!word.equals("Parola") && !meaning.equals("Definizione")){
                int count = Integer.parseInt(mediumWords.getText()); 
                if(count > 0)
                    mediumFile.append(System.getProperty("line.separator"));
//                    mediumFile.append("," + System.getProperty("line.separator"));
//                mediumFile.append("(\"" + (count+1) + "\",\"" + word + "\",\"" + meaning + "\"," + "\"\")");
//                mediumFile.flush();
                  mediumFile.append(word + "," + meaning);
                  mediumFile.flush();
                mediumWords.setText("" + ++count);
                if (wordListOpened) {
                    String temp = nextRow();
                    if (temp != null) {
                        setUI(temp);
                    } else {
                        txtWord.setText("Parola");
                        txtMeaning.setText("Definizione");
                        JOptionPane.showMessageDialog(this, "Sono finite le parole!");
                        wordListOpened = false;
                        wordListIndex = -1;
                        System.out.println("Fine del file!");
                    }
                } else {
                    txtWord.setText("Parola");
                    txtMeaning.setText("Definizione");
                }
                saveCounters();
            }
        }
    }//GEN-LAST:event_btnMediumActionPerformed

    private void btnHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHardActionPerformed
        String word = txtWord.getText();
        String meaning = txtMeaning.getText();
        if(!word.isEmpty() && !meaning.isEmpty()){
            if(!word.equals("Parola") && !meaning.equals("Definizione")){
                int count = Integer.parseInt(hardWords.getText());
                if(count > 0)
                    hardFile.append(System.getProperty("line.separator"));
//                    hardFile.append("," + System.getProperty("line.separator"));
//                hardFile.append("(\"" + (count+1) + "\",\"" + word + "\",\"" + meaning + "\"," + "\"\")");
//                hardFile.flush();
                hardFile.append(word + "," + meaning);
                hardFile.flush();
                hardWords.setText("" + ++count);
                if (wordListOpened) {
                    String temp = nextRow();
                    if (temp != null) {
                        setUI(temp);
                    } else {
                        txtWord.setText("Parola");
                        txtMeaning.setText("Definizione");
                        JOptionPane.showMessageDialog(this, "Sono finite le parole!");
                        wordListOpened = false;
                        wordListIndex = -1;
                        System.out.println("Fine del file!");
                    }
                } else {
                    txtWord.setText("Parola");
                    txtMeaning.setText("Definizione");
                }
                saveCounters();
            }
        }
    }//GEN-LAST:event_btnHardActionPerformed

    private void txtWordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtWordMouseClicked
        
    }//GEN-LAST:event_txtWordMouseClicked

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        if (wordListIndex == -1) {
            JOptionPane.showMessageDialog(this, "Sono finite le parole!");
        } else {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                wordList = fileChooser.getSelectedFile();
                String temp = loadWordListData();
                if (!temp.isEmpty()) {
                    setUI(temp);
                    wordListOpened = true;
                }
            }
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (wordListOpened)
            setUI(nextRow());
    }//GEN-LAST:event_btnNextActionPerformed

    private void txtMeaningKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMeaningKeyTyped
        numeroLettere.setText("" + txtMeaning.getText().length());
        if (txtMeaning.getText().length() >= 150) {
            numeroLettere.setForeground(Color.red);
            jLabelNumLet.setForeground(Color.red);
        } else {
            numeroLettere.setForeground(Color.black);
            jLabelNumLet.setForeground(Color.black);
        }
    }//GEN-LAST:event_txtMeaningKeyTyped

    private void txtMeaningKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMeaningKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMeaningKeyReleased
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEasy;
    private javax.swing.JButton btnHard;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnMedium;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JLabel easyWords;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel hardWords;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelNumLet;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mediumWords;
    private javax.swing.JLabel numeroLettere;
    private javax.swing.JTextArea txtMeaning;
    private javax.swing.JTextField txtWord;
    // End of variables declaration//GEN-END:variables
}
