package com.epicest.snippetmanager;

import com.epicest.snippetmanager.gui.*;
import com.epicest.snippetmanager.object.*;
import java.beans.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * The main class in this project, and what controls most of the logic
 *
 * @author Jayar
 */
public class SnippetManager extends javax.swing.JFrame {

 /**
  * The main data of the program
  */
 public ArrayList<Category> categories = new ArrayList<>();
 /**
  * Storage of the tabs in the program
  */
 public ArrayList<SnippetTab> categoryTabs = new ArrayList<>();

 /**
  * Whether lines are numbered in the snippet editor, currently unsupported
  */
 public static boolean lineNumbers = false;
 /**
  * Whether the snippet editor wraps or not
  */
 public static boolean wrap = false;
 /**
  * Whether the snippet editor wraps by word or by character
  */
 public static boolean wrapByWords = true;

 /**
  * Whether the program saves on exit/closing
  */
 public static boolean saveAtClose = true;

 /**
  * Creates new form SnippetManager
  */
 public SnippetManager() {
  initComponents();
  setLocationRelativeTo(null);
  load(LOAD_START);
 }

 /**
  * Will load only category data
  */
 public static int LOAD_NORMAL = 0;
 /**
  * Will load all data including set options
  */
 public static int LOAD_START = 1;

 /**
  * Loads categories
  *
  * @param loadType will change small things in what happens during the load
  */
 private void load(int loadType) {
  categories.removeAll(categories);
  XMLDecoder decoder = null;
  try {
   decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("save.xml")));
  } catch (Exception e) {
   JOptionPane.showMessageDialog(this, "Error\nMay not have been able to load.\n" + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
  }
  SaveBean saveBean;
  try {
   saveBean = (SaveBean) decoder.readObject();
  } catch (Exception e) {
   saveBean = null;
   JOptionPane.showMessageDialog(this, "Error\nMay not have been able to load.\n" + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
  }
  if (saveBean != null) {
   categories = saveBean.getContent();
   if (loadType == LOAD_START) {
    lineNumbers = saveBean.isLineNumbering();
    wrap = saveBean.isWrapping();
    wrapByWords = saveBean.isWrapByWords();
    saveAtClose = saveBean.isSavingAtClose();
    lineNumberingItem.setSelected(lineNumbers);
    wrapItem.setSelected(wrap);
    wrapWordsItem.setSelected(wrapByWords);
    saveOnCloseItem.setSelected(saveAtClose);
   }
  }
  refreshTabs(REFRESH_NORMAL);
 }

 /**
  * Saves everything
  */
 public static int SAVE_NORMAL = 0;
 /**
  * Saves only options
  */
 public static int SAVE_MINIMAL = 1;

 /**
  * Saves data
  *
  * @param saveType will change small things in what happens during the save
  */
 public void save(int saveType) {
  if (saveType == SAVE_MINIMAL) {
   load(LOAD_NORMAL);
  }
  SaveBean saveBean = new SaveBean();
  saveBean.setContent(categories);
  saveBean.setWrapping(wrap);
  saveBean.setLineNumbering(lineNumbers);
  saveBean.setWrapByWords(wrapByWords);
  saveBean.setSavingAtClose(saveAtClose);
  XMLEncoder encoder = null;
  try {
   encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("save.xml")));
   encoder.writeObject(saveBean);
  } catch (Exception e) {
   JOptionPane.showMessageDialog(this, "Error\nMay not have been able to save.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  } finally {
   encoder.close();
  }
 }

 /**
  * Refreshes the tabs as if there was nothing happening
  */
 public static final int REFRESH_NORMAL = 0;
 /**
  * Refreshes the tabs as if a tab were being renamed
  * <p>
  * Refreshes the tabs as if a tab were being renamed, this will focus the
  * category name text field at the place the user was typing
  */
 public static final int REFRESH_RENAME = 1;
 /**
  * Refreshes the tabs as if a tab were being removed
  */
 public static final int REFRESH_REMOVE = 2;
 /**
  * Refreshes the tabs as if a tab were being added
  */
 public static final int REFRESH_ADD = 3;

 /**
  * Refreshes tabs
  *
  * @param refreshType will change small things in what happens during the
  * refresh
  */
 public void refreshTabs(int refreshType) {
  int selectedTab = languageTabs.getSelectedIndex();

  boolean keepGoing = true;
  while (keepGoing) {
   try {
    languageTabs.removeTabAt(0);
   } catch (Exception e) {
    keepGoing = false;
   }
  }
  for (int i = 0; i < categories.size(); i++) {
   try {
    categoryTabs.set(i, new SnippetTab(this, i));
   } catch (IndexOutOfBoundsException ioobe) {
    categoryTabs.add(i, new SnippetTab(this, i));
   }
   languageTabs.addTab(categories.get(i).getName(), categoryTabs.get(i));
  }
  switch (refreshType) {
   case REFRESH_RENAME:
    languageTabs.setSelectedIndex(selectedTab);
    categoryTabs.get(selectedTab).tabNameTextField.requestFocus();
    categoryTabs.get(selectedTab).tabNameTextField.setCaretPosition(SnippetTab.categoryNameCaretPosition);
    break;
   case REFRESH_REMOVE:
    try {
     languageTabs.setSelectedIndex(selectedTab - 1);
    } catch (Exception e) {
     //Do Nothing
    }
    break;
   case REFRESH_ADD:
    try {
     languageTabs.setSelectedIndex(languageTabs.getTabCount() - 1);
    } catch (Exception e) {
     //Do Nothing
    }
    break;
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

  languageTabs = new javax.swing.JTabbedPane();
  menuBar = new javax.swing.JMenuBar();
  fileMenu = new javax.swing.JMenu();
  reloadItem = new javax.swing.JMenuItem();
  saveItem = new javax.swing.JMenuItem();
  separatorFile1 = new javax.swing.JPopupMenu.Separator();
  optionsMenu = new javax.swing.JMenu();
  lineNumberingItem = new javax.swing.JCheckBoxMenuItem();
  separatorOptions1 = new javax.swing.JPopupMenu.Separator();
  wrapItem = new javax.swing.JCheckBoxMenuItem();
  wrapWordsItem = new javax.swing.JCheckBoxMenuItem();
  separatorOptions2 = new javax.swing.JPopupMenu.Separator();
  saveOnCloseItem = new javax.swing.JCheckBoxMenuItem();
  separatorFile2 = new javax.swing.JPopupMenu.Separator();
  aboutItem = new javax.swing.JMenuItem();
  editMenu = new javax.swing.JMenu();
  addTabItem = new javax.swing.JMenuItem();
  removeTabItem = new javax.swing.JMenuItem();

  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
  setTitle("Pixelcraft Snippet Manager");
  setMinimumSize(new java.awt.Dimension(400, 275));
  addWindowListener(new java.awt.event.WindowAdapter() {
   public void windowClosing(java.awt.event.WindowEvent evt) {
    formWindowClosing(evt);
   }
  });

  fileMenu.setText("File");

  reloadItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
  reloadItem.setText("Reload");
  reloadItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    reloadItemActionPerformed(evt);
   }
  });
  fileMenu.add(reloadItem);

  saveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
  saveItem.setText("Save");
  saveItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    saveItemActionPerformed(evt);
   }
  });
  fileMenu.add(saveItem);
  fileMenu.add(separatorFile1);

  optionsMenu.setText("Options");

  lineNumberingItem.setText("Line Numbering");
  lineNumberingItem.setEnabled(false);
  lineNumberingItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    lineNumberingItemActionPerformed(evt);
   }
  });
  optionsMenu.add(lineNumberingItem);
  optionsMenu.add(separatorOptions1);

  wrapItem.setText("Wrap");
  wrapItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    wrapItemActionPerformed(evt);
   }
  });
  optionsMenu.add(wrapItem);

  wrapWordsItem.setSelected(true);
  wrapWordsItem.setText("Wrap by Word");
  wrapWordsItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    wrapWordsItemActionPerformed(evt);
   }
  });
  optionsMenu.add(wrapWordsItem);
  optionsMenu.add(separatorOptions2);

  saveOnCloseItem.setSelected(true);
  saveOnCloseItem.setText("Save On Close");
  saveOnCloseItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    saveOnCloseItemActionPerformed(evt);
   }
  });
  optionsMenu.add(saveOnCloseItem);

  fileMenu.add(optionsMenu);
  fileMenu.add(separatorFile2);

  aboutItem.setText("About Application");
  aboutItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    aboutItemActionPerformed(evt);
   }
  });
  fileMenu.add(aboutItem);

  menuBar.add(fileMenu);

  editMenu.setText("Edit");

  addTabItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
  addTabItem.setText("Add Tab");
  addTabItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    addTabItemActionPerformed(evt);
   }
  });
  editMenu.add(addTabItem);

  removeTabItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
  removeTabItem.setText("Remove Tab");
  removeTabItem.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    removeTabItemActionPerformed(evt);
   }
  });
  editMenu.add(removeTabItem);

  menuBar.add(editMenu);

  setJMenuBar(menuBar);

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(languageTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
  );
  layout.setVerticalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(languageTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
  );

  pack();
 }// </editor-fold>//GEN-END:initComponents

 /**
  * Saves normally when pressed
  */
 private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveItemActionPerformed
  save(SAVE_NORMAL);
 }//GEN-LAST:event_saveItemActionPerformed

 /**
  * Saves on close
  * <p>
  * Saves options on close, will use outdated categories if saveOnExit == false
  * otherwise saves normally
  */
 private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
  if (saveAtClose) {
   save(SAVE_NORMAL);
  } else {
   save(SAVE_MINIMAL);
  }
 }//GEN-LAST:event_formWindowClosing

 /**
  * Reloads categories
  */
 private void reloadItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadItemActionPerformed
  int reply = JOptionPane.showConfirmDialog(this, "Reloading will remove any unsaved changes\nProceed?", "Are you sure?", JOptionPane.YES_NO_OPTION);
  if (reply == JOptionPane.YES_OPTION) {
   load(LOAD_NORMAL);
  }
 }//GEN-LAST:event_reloadItemActionPerformed

 /**
  * Opens an about window
  */
 private void aboutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutItemActionPerformed
  new AboutFrame(this).setVisible(true);
 }//GEN-LAST:event_aboutItemActionPerformed

 /**
  * Removes a category
  */
 private void removeTabItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTabItemActionPerformed
  categories.remove(languageTabs.getSelectedIndex());
  refreshTabs(REFRESH_REMOVE);
 }//GEN-LAST:event_removeTabItemActionPerformed

 /**
  * Adds a category
  */
 private void addTabItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTabItemActionPerformed
  Category lang = new Category();
  categories.add(lang);
  refreshTabs(REFRESH_ADD);
 }//GEN-LAST:event_addTabItemActionPerformed

 /**
  * Sets whether or not to wrap lines in the snippet editor, then refreshes tabs
  */
 private void wrapItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrapItemActionPerformed
  wrap = wrapItem.isSelected();
  refreshTabs(REFRESH_NORMAL);
 }//GEN-LAST:event_wrapItemActionPerformed

 /**
  * Sets whether or not to number lines in the snippet editor, then refreshes
  * tabs
  */
 private void lineNumberingItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineNumberingItemActionPerformed
  lineNumbers = lineNumberingItem.isSelected();
  refreshTabs(REFRESH_NORMAL);
 }//GEN-LAST:event_lineNumberingItemActionPerformed

 /**
  * Sets whether or not to wrap lines by word in the snippet editor, then
  * refreshes tabs
  */
 private void wrapWordsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wrapWordsItemActionPerformed
  wrapByWords = wrapWordsItem.isSelected();
  refreshTabs(REFRESH_NORMAL);
 }//GEN-LAST:event_wrapWordsItemActionPerformed

 /**
  * Sets whether or not to save categories on close
  */
 private void saveOnCloseItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOnCloseItemActionPerformed
  saveAtClose = saveOnCloseItem.isSelected();
  refreshTabs(REFRESH_NORMAL);
 }//GEN-LAST:event_saveOnCloseItemActionPerformed

 /**
  * Starts the program
  *
  * @param args the command line arguments
  */
 public static void main(String args[]) {
  /* Set the Windows look and feel */
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
   java.util.logging.Logger.getLogger(SnippetManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  } catch (InstantiationException ex) {
   java.util.logging.Logger.getLogger(SnippetManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  } catch (IllegalAccessException ex) {
   java.util.logging.Logger.getLogger(SnippetManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  } catch (javax.swing.UnsupportedLookAndFeelException ex) {
   java.util.logging.Logger.getLogger(SnippetManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
  }
  //</editor-fold>

  /* Create and display the form */
  java.awt.EventQueue.invokeLater(new Runnable() {
   public void run() {
    new SnippetManager().setVisible(true);
   }
  });
 }

 // Variables declaration - do not modify//GEN-BEGIN:variables
 private javax.swing.JMenuItem aboutItem;
 private javax.swing.JMenuItem addTabItem;
 private javax.swing.JMenu editMenu;
 private javax.swing.JMenu fileMenu;
 private javax.swing.JTabbedPane languageTabs;
 private javax.swing.JCheckBoxMenuItem lineNumberingItem;
 private javax.swing.JMenuBar menuBar;
 private javax.swing.JMenu optionsMenu;
 private javax.swing.JMenuItem reloadItem;
 private javax.swing.JMenuItem removeTabItem;
 private javax.swing.JMenuItem saveItem;
 private javax.swing.JCheckBoxMenuItem saveOnCloseItem;
 private javax.swing.JPopupMenu.Separator separatorFile1;
 private javax.swing.JPopupMenu.Separator separatorFile2;
 private javax.swing.JPopupMenu.Separator separatorOptions1;
 private javax.swing.JPopupMenu.Separator separatorOptions2;
 private javax.swing.JCheckBoxMenuItem wrapItem;
 private javax.swing.JCheckBoxMenuItem wrapWordsItem;
 // End of variables declaration//GEN-END:variables
}
