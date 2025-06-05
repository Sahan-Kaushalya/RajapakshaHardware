/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.other;

import model.InvoiceItem;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import model.MySQL;
import java.io.InputStream;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperExportManager;

/**
 *
 * @author Sahan Kaushalya (Sync-x.lk)
 */
public class Invoice extends javax.swing.JFrame {

    HashMap<String, InvoiceItem> invoiceItemMap = new HashMap<>();
    HashMap<String, String> paymentMethodMap = new HashMap<>();

    private void loadPaymentMethods() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `payment_method`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                paymentMethodMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadInvoiceItems() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        total = 0;

        for (InvoiceItem invoiceItem : invoiceItemMap.values()) {
            Vector<String> vector = new Vector<>();
            vector.add(invoiceItem.getStockID());
            vector.add(invoiceItem.getBrand());
            vector.add(invoiceItem.getName());
            vector.add(invoiceItem.getQty());
            vector.add(invoiceItem.getSellingPrice());
            vector.add(invoiceItem.getMfd());
            vector.add(invoiceItem.getExp());

            double itemTotal = Double.parseDouble(invoiceItem.getQty()) * Double.parseDouble(invoiceItem.getSellingPrice());
            total += itemTotal;
            vector.add(String.valueOf(itemTotal));

            model.addRow(vector);
        }

        totalField.setText(String.valueOf(total));

        calculate();
    }

    String email;

    public Invoice(String email) {
        initComponents();
        this.email = email;
        employeeLable.setText(email);
        genarateInvoiceID();
        printInvoiceButton.setEnabled(false);
        loadPaymentMethods();
        this.setIconImage(new ImageIcon(getClass().getResource("/resources/icon/logo.png")).getImage());
    }

    private double total = 0;
    private double discount = 0;
    private double payment = 0;
    private boolean withdawPoints = false;
    private double balance = 0;
    private String paymentMethod = "Select";
    private double newPoints = 0;

    private void calculate() {

        //settings
        if (discountField.getText().isEmpty()) {
            discount = 0;
        } else {
            discount = Double.parseDouble(discountField.getText());
        }

        if (paymentField.getText().isEmpty()) {
            payment = 0;
        } else {
            payment = Double.parseDouble(paymentField.getText());
        }

        total = Double.parseDouble(totalField.getText());

        paymentMethod = String.valueOf(jComboBox1.getSelectedItem());
        //settings

        total -= discount;

        if (paymentMethod.equals("Cash Payment")) {
            //cash
            paymentField.setEditable(true);
            balance = payment - total;

            if (balance < 0) {
                printInvoiceButton.setEnabled(false);
            } else {
                printInvoiceButton.setEnabled(true);
            }

        } else {
            //card
            payment = total;
            balance = 0;
            paymentField.setText(String.valueOf(payment));
            paymentField.setEditable(false);
            printInvoiceButton.setEnabled(true);
        }

        balanceFiled.setText(String.valueOf(balance));

    }

    private void genarateInvoiceID() {
        try {

            String iID = "";

            ResultSet resultset = MySQL.executeSearch("SELECT MAX(`id`) AS max_id FROM `invoice`");

            if (resultset.next()) {
                String maxID = resultset.getString("max_id");
                if (maxID != null) {
                    int ID = Integer.parseInt(maxID.replace("INV_", ""));
                    iID = String.format("INV_%03d", ID + 1);
                    invoiceNumberField.setText(iID);
                } else {
                    iID = "INV_001";
                    invoiceNumberField.setText(iID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Customer mobile  
    public JTextField getjTextField2() {
        return customerMobileField;
    }

    // Customer name
    public JLabel getjLabel6() {
        return jLabel6;
    }

    //stock id
    public JTextField getjTextField3() {
        return jTextField3;
    }

    // Brand Name
    public JLabel getjLabel12() {
        return jLabel12;
    }

    // Product Name
    public JLabel getjLabel15() {
        return jLabel15;
    }

    //Selling Price 
    public JTextField getjTextField5() {
        return jTextField5;
    }

    // MFD
    public JLabel getjLabel17() {
        return jLabel17;
    }

    // EXP
    public JLabel getjLabel19() {
        return jLabel19;
    }

    //QTY
    public JFormattedTextField getjFormattedTextField1() {
        return jFormattedTextField1;
    }

    // Available Qty
    public JLabel getjLabel9() {
        return jLabel9;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        invoiceNumberField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        customerMobileField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        employeeLable = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        totalField = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        paymentField = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        discountField = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        balanceFiled = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        printInvoiceButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INVOICE");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Brand", "Name", "Quantity", "Selling Price", "MFD", "EXP", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INVOICE & BILLING AREA");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setText("Invoice Number :");

        invoiceNumberField.setEditable(false);
        invoiceNumberField.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setText("Customer No :");

        customerMobileField.setEditable(false);
        customerMobileField.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(0, 102, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Select");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel26.setText("Customer Name :");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("CUSTOMER NAME HERE");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setText("Employee Email :");

        employeeLable.setFont(new java.awt.Font("Segoe UI Semibold", 2, 12)); // NOI18N
        employeeLable.setText("EMPLOYEE DETAILS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(customerMobileField, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(invoiceNumberField))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(employeeLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(invoiceNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerMobileField)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(employeeLable))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel7.setText("Stock");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        jButton2.setBackground(new java.awt.Color(0, 102, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Select");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel14.setText("Name");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel15.setText("PRODUCT NAME HERE");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel11.setText("Brand");

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel12.setText("PRODUCT BRAND HERE");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel8.setText("Quantity");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("0");
        jLabel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 1, true));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(26, 26, 26)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel18.setText("EXP");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel16.setText("MFD");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel17.setText("MFD HERE");

        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel19.setText("EXP HERE");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel13.setText("Selling Price");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setBackground(new java.awt.Color(0, 153, 51));
        jButton4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Add To Invoice");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Clear All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));

        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel20.setText("Total");

        totalField.setEditable(false);
        totalField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        totalField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalField.setText("0");
        totalField.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        totalField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                totalFieldKeyPressed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel23.setText("Payment");

        paymentField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        paymentField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        paymentField.setText("0");
        paymentField.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        paymentField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentFieldKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel21.setText("Discount");

        discountField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00%"))));
        discountField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        discountField.setText("0");
        discountField.setToolTipText("");
        discountField.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        discountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountFieldActionPerformed(evt);
            }
        });
        discountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                discountFieldKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel22.setText("Payment Method");

        jComboBox1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel25.setText("Balance");

        balanceFiled.setEditable(false);
        balanceFiled.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        balanceFiled.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        balanceFiled.setText("0");
        balanceFiled.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N

        printInvoiceButton.setBackground(new java.awt.Color(0, 153, 153));
        printInvoiceButton.setFont(new java.awt.Font("Nirmala UI", 1, 20)); // NOI18N
        printInvoiceButton.setForeground(new java.awt.Color(255, 255, 255));
        printInvoiceButton.setText("Print Invoice");
        printInvoiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printInvoiceButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(discountField, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(totalField)
                            .addComponent(paymentField)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(balanceFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(printInvoiceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(totalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(discountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(paymentField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(printInvoiceButton)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CustomerRegistration cr = new CustomerRegistration();
        cr.setVisible(true);
        cr.setInvoice(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        SelectProduct sp = new SelectProduct(this, true);
        sp.setInvoice(this);
        sp.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String stockID = jTextField3.getText();
        String brand = jLabel12.getText();
        String productName = jLabel15.getText();
        String qty = jFormattedTextField1.getText();
        String mfd = jLabel17.getText();
        String exp = jLabel19.getText();
        String sellingPrice = jTextField5.getText();

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setBrand(brand);
        invoiceItem.setExp(exp);
        invoiceItem.setMfd(mfd);
        invoiceItem.setName(productName);
        invoiceItem.setQty(qty);
        invoiceItem.setSellingPrice(sellingPrice);
        invoiceItem.setStockID(stockID);

        if (invoiceItemMap.get(stockID) == null) {
            invoiceItemMap.put(stockID, invoiceItem);
        } else {

            InvoiceItem found = invoiceItemMap.get(stockID);

            int option = JOptionPane.showConfirmDialog(this, "Do you want to Update the Quantity of Product :" + productName, "Message",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {

                found.setQty(String.valueOf(Double.parseDouble(found.getQty()) + Double.parseDouble(qty)));

            }
        }
        loadInvoiceItems();
        clearAll();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void discountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountFieldKeyReleased
        calculate();
    }//GEN-LAST:event_discountFieldKeyReleased

    private void paymentFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentFieldKeyReleased
        calculate();
    }//GEN-LAST:event_paymentFieldKeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        calculate();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void printInvoiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printInvoiceButtonActionPerformed

        try {

            String invoiceID = invoiceNumberField.getText();
            String customerMobile = customerMobileField.getText();
            String employeeEmail = employeeLable.getText();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String paidAmount = paymentField.getText();
            String paymentMethodID = paymentMethodMap.get(String.valueOf(jComboBox1.getSelectedItem()));
            String discount = String.valueOf(discountField.getText());

            //insert to invoice
            MySQL.executeIUD("INSERT INTO `invoice` VALUES('" + invoiceID + "','" + customerMobile + "','" + employeeEmail + "',"
                    + "'" + dateTime + "','" + paidAmount + "','" + paymentMethodID + "','" + discount + "')");
            //insert to invoice

            for (InvoiceItem invoiceItem : invoiceItemMap.values()) {

                //insert to invoiceItem
                MySQL.executeIUD("INSERT INTO `invoice_item`(`stock_id`,`qty`,`invoice_id` )"
                        + "VALUES('" + invoiceItem.getStockID() + "','" + invoiceItem.getQty() + "','" + invoiceID + "')");
                //insert to invoiceItem

                //stock update
                MySQL.executeIUD("UPDATE `stock` SET `qty`=`qty`-'" + invoiceItem.getQty() + "' WHERE `id`='" + invoiceItem.getStockID() + "'");
                //stock update
            }

            //Withdraw Points
            //view or print report
            // String path = "src//reports//invoice.jasper";
            InputStream path = this.getClass().getResourceAsStream("/report/invoice.jasper");
//            
            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", totalField.getText());
            params.put("Parameter2", discountField.getText());
            params.put("Parameter3", String.valueOf(jComboBox1.getSelectedItem()));
            params.put("Parameter4", paymentField.getText());
            params.put("Parameter5", balanceFiled.getText());
            
            params.put("Parameter6", invoiceNumberField.getText());
            params.put("Parameter7", customerMobileField.getText());
            params.put("Parameter8", employeeLable.getText());
            params.put("Parameter9", dateTime);
            
            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);
            
            JasperViewer.viewReport(jasperPrint, false);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "Invoice.pdf");
            clearAll();
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_printInvoiceButtonActionPerformed
    private void reset() {
        jLabel6.setText("CUSTOMER NAME HERE");
        customerMobileField.setText("");
        jComboBox1.setSelectedIndex(0);
        balanceFiled.setText("0.00");
        paymentField.setText("0.00");
        discountField.setText("0.00");
        totalField.setText("0.00");
        ((DefaultTableModel) jTable1.getModel()).setRowCount(0);
        invoiceItemMap.clear();
        jTable1.clearSelection();
        genarateInvoiceID();
        printInvoiceButton.setEnabled(false);
    }

    private void clearAll() {
        jTextField3.setText("");
        jTextField5.setText("");
        jLabel12.setText("PRODUCT BRAND HERE");
        jLabel15.setText("PRODUCT NAME HERE");
        jLabel17.setText("MFD HERE");
        jLabel19.setText("EXP HERE");
        jLabel9.setText("0");
        jFormattedTextField1.setText("");

    }
    private void totalFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalFieldKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        clearAll();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void discountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountFieldActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        FlatMacDarkLaf.setup();
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Invoice().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField balanceFiled;
    private javax.swing.JTextField customerMobileField;
    private javax.swing.JFormattedTextField discountField;
    private javax.swing.JLabel employeeLable;
    private javax.swing.JTextField invoiceNumberField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JFormattedTextField paymentField;
    private javax.swing.JButton printInvoiceButton;
    private javax.swing.JFormattedTextField totalField;
    // End of variables declaration//GEN-END:variables
}
