/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swingGUI;

import common.DBUtils;
import hotelmanager.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.swing.SwingWorker;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.derby.jdbc.ClientDataSource40;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;


public class HotelManagerFrame extends javax.swing.JFrame {
    
    
    public static final ResourceBundle DEFAULT_VALUES = ResourceBundle.getBundle("swingGUI.DefaultValues");
    public static final ResourceBundle LANGUAGE = ResourceBundle.getBundle("swingGUI.General", Locale.forLanguageTag(""));
    private ResourceBundle general = LANGUAGE;
    private DataSource ds;
    private static final HotelManagerImpl hotelManager = new HotelManagerImpl();
    private static final PersonManagerImpl personManager = new PersonManagerImpl();
    private static final RoomManagerImpl roomManager = new RoomManagerImpl();
    private static RoomListModel roomListModel;
    private static RoomListModel roomUpdateRemoveListModel;
    private static PersonListModel personListModel;
    private static PersonListModel personCheckListModel;
    
    public static HotelManagerImpl getHotelManager()
    {
	return hotelManager;
    }
    
    public static RoomManagerImpl getRoomManager()
    {
	return roomManager;
    }
    
    public static PersonManagerImpl getPersonManager()
    {
	return personManager;
    }
    
    public static RoomListModel getRoomListModel()
    {
	return roomListModel;
    }
    
    public static PersonListModel getPersonListModel()
    {
	return personListModel;
    }
    
    public static RoomListModel getRoomUpdateRemoveListModel()
    {
	return roomUpdateRemoveListModel;
    }
    
    public static PersonListModel getPersonCheckListModel()
    {
	return personCheckListModel;
    }
    
    /**
     * Creates new form HotelManagerFrame
     */
    public HotelManagerFrame() throws SQLException {
	setUp();
        initComponents();
    }
    
    /*private static DataSource prepareDataSource() throws SQLException {
        BasicDataSource ds = new BasicDataSource();
        //we will use in memory database
        ds.setUrl("jdbc:derby:memory:roommgr-tests;create=true");
        return ds;
    }*/
    
    private static DataSource prepareDataSource(){
        ClientDataSource40 ds = new ClientDataSource40();
        ds.setServerName(DEFAULT_VALUES.getString("DBServerName"));
        ds.setPortNumber(Integer.parseInt(DEFAULT_VALUES.getString("DBPortNumber")));
        ds.setDatabaseName(DEFAULT_VALUES.getString("DBName"));
        ds.setUser(DEFAULT_VALUES.getString("DBUsername"));
        ds.setPassword(DEFAULT_VALUES.getString("DBPassword"));
        return ds;
    }
    
    @Before
    public void setUp() throws SQLException {
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                    ds = prepareDataSource();
                    hotelManager.setDataSource(ds);
                    personManager.setDataSource(ds);
                    roomManager.setDataSource(ds);
                    return true;
            }
 
            @Override
            protected void done() {
			    
		List<Room> setUpRooms = roomManager.getAllRooms();
		roomListModel = (RoomListModel) roomManagerList.getModel();
		roomUpdateRemoveListModel = (RoomListModel) updateRemoveRoomList.getModel();

                for (Room s : setUpRooms) {
		    roomListModel.addRoom(s);
		    roomUpdateRemoveListModel.addRoom(s);
                }
		
		List<Person> setUpPersons = personManager.getAllPersons();
                personListModel = (PersonListModel) updateRemovePersonList.getModel();
		personCheckListModel = (PersonListModel) checkPersonList.getModel();
		
                for (Person s : setUpPersons) {
		    personListModel.addPerson(s);
		    personCheckListModel.addPerson(s);
                }
            }
        }.execute();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomManagerList = new javax.swing.JList();
        updatePersonButton = new javax.swing.JRadioButton();
        removePersonButton = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        updateRemovePersonList = new javax.swing.JList();
        updateRoomButton = new javax.swing.JRadioButton();
        removeRoomButton = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        updateRemoveRoomList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        checkPersonList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText(general.getString("addPerson"));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(general.getString("addRoom"));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        roomManagerList.setModel(new swingGUI.RoomListModel());
        roomManagerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomManagerListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(roomManagerList);

        buttonGroup1.add(updatePersonButton);
        updatePersonButton.setSelected(true);
        updatePersonButton.setText(general.getString("updatePerson"));

        buttonGroup1.add(removePersonButton);
        removePersonButton.setText(general.getString("removePerson"));
        removePersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePersonButtonActionPerformed(evt);
            }
        });

        updateRemovePersonList.setModel(new swingGUI.PersonListModel());
        updateRemovePersonList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateRemovePersonListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(updateRemovePersonList);

        buttonGroup2.add(updateRoomButton);
        updateRoomButton.setSelected(true);
        updateRoomButton.setText(general.getString("updateRoom"));

        buttonGroup2.add(removeRoomButton);
        removeRoomButton.setText(general.getString("removeRoom"));

        updateRemoveRoomList.setModel(new swingGUI.RoomListModel());
        updateRemoveRoomList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateRemoveRoomListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(updateRemoveRoomList);

        jLabel1.setText(general.getString("checkInOutRoom"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Hotel Manager -  v 0.0.1");

        checkPersonList.setModel(new swingGUI.PersonListModel());
        checkPersonList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPersonListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(checkPersonList);

        jLabel3.setText(general.getString("checkInOutPerson"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(removeRoomButton)
                                    .addComponent(updateRoomButton)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removePersonButton)
                            .addComponent(updatePersonButton)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(238, 238, 238))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updatePersonButton)
                    .addComponent(updateRoomButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removePersonButton)
                    .addComponent(removeRoomButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
	new AddPerson().setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
	new AddRoom().setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void roomManagerListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomManagerListMouseClicked
	
	    try {
		new CheckIn(roomManagerList.getSelectedValue().toString()).setVisible(true);
	    } catch (IOException ex) {
		Logger.getLogger(HotelManagerFrame.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (SQLException ex) {
		Logger.getLogger(HotelManagerFrame.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }//GEN-LAST:event_roomManagerListMouseClicked

    private void removePersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePersonButtonActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_removePersonButtonActionPerformed

    private void updateRemovePersonListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateRemovePersonListMouseClicked
	if(updateRemovePersonList.getModel().getSize() > 0)
	{
	    if(updatePersonButton.isSelected())
	    {
		new AddPerson(updateRemovePersonList.getSelectedValue().toString()).setVisible(true);
	    }
	    else if(removePersonButton.isSelected())
	    {
		new RemoveItem(true, updateRemovePersonList.getSelectedValue().toString()).setVisible(true);
	    }
	}
    }//GEN-LAST:event_updateRemovePersonListMouseClicked

    private void updateRemoveRoomListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateRemoveRoomListMouseClicked
	if(updateRemoveRoomList.getModel().getSize() > 0)
	{
	    if(updateRoomButton.isSelected())
	    {
		new AddRoom(updateRemoveRoomList.getSelectedValue().toString()).setVisible(true);
	    }
	    else if(removeRoomButton.isSelected())
	    {
		new RemoveItem(false, updateRemoveRoomList.getSelectedValue().toString()).setVisible(true);
	    }
	}
    }//GEN-LAST:event_updateRemoveRoomListMouseClicked

    private void checkPersonListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPersonListMouseClicked
	try {
	    new CheckIn(true, checkPersonList.getSelectedValue().toString()).setVisible(true);
	} catch (IOException | SQLException ex) {
	    Logger.getLogger(HotelManagerFrame.class.getName()).log(Level.SEVERE, null, ex);
	}
    }//GEN-LAST:event_checkPersonListMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/*
	 * Set the Nimbus look and feel
	 */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the
	 * default look and feel. For details see
	 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(HotelManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(HotelManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(HotelManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(HotelManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/*
	 * Create and display the form
	 */
	java.awt.EventQueue.invokeLater(new Runnable() {

	    public void run() {
		try {
		    new HotelManagerFrame().setVisible(true);
		} catch (SQLException ex) {
		    Logger.getLogger(HotelManagerFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JList checkPersonList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JRadioButton removePersonButton;
    private javax.swing.JRadioButton removeRoomButton;
    private javax.swing.JList roomManagerList;
    private javax.swing.JRadioButton updatePersonButton;
    private javax.swing.JList updateRemovePersonList;
    private javax.swing.JList updateRemoveRoomList;
    private javax.swing.JRadioButton updateRoomButton;
    // End of variables declaration//GEN-END:variables
}