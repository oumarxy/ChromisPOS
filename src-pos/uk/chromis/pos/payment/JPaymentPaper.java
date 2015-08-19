//    Chromis POS  - The New Face of Open Source POS
//    Copyright (C) 2008-2009 
//    http://www.chromis.co.uk
//
//    This file is part of Chromis POS
//
//     Chromis POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Chromis POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>.


package uk.chromis.pos.payment;

import uk.chromis.format.Formats;
import uk.chromis.pos.customers.CustomerInfoExt;
import uk.chromis.pos.forms.AppConfig;
import uk.chromis.pos.forms.AppLocal;
import uk.chromis.pos.util.RoundUtils;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 *
 *   
 */
public class JPaymentPaper extends javax.swing.JPanel implements JPaymentInterface {
    
    private JPaymentNotifier m_notifier;
    
    private double m_dTicket;
    private double m_dTotal;  
    private Boolean priceWith00;
    
    private String m_sPaper; // "paperin", "paperout"
    // private String m_sCustomer; 
    
    
    /** Creates new form JPaymentTicket
     * @param notifier
     * @param sPaper */
    public JPaymentPaper(JPaymentNotifier notifier, String sPaper) {
        
        m_notifier = notifier;
        m_sPaper = sPaper;
        
        initComponents();
        
        m_jTendered.addPropertyChangeListener("Edition", new RecalculateState());
        m_jTendered.addEditorKeys(m_jKeys);
        
        
 /* added JDL 11.05.13        
        AppConfig m_config =  new AppConfig(new File((System.getProperty("user.home")), AppLocal.APP_ID + ".properties"));        
        m_config.load();        
        priceWith00 =("true".equals(m_config.getProperty("till.pricewith00")));
        if (priceWith00) {
            // use '00' instead of '.'
            m_jKeys.dotIs00(true);
        }
        m_config=null;
*/        
    }
    
    /**
     *
     * @param customerext
     * @param dTotal
     * @param transID
     */
    @Override
    public void activate(CustomerInfoExt customerext, double dTotal, String transID) {
        
        m_dTotal = dTotal;
        
        m_jTendered.reset();
        m_jTendered.activate();
        
        printState();        
    }
    
    /**
     *
     * @return
     */
    @Override
    public Component getComponent() {
        return this;
    }
    
    /**
     *
     * @return
     */
    @Override
    public PaymentInfo executePayment() {

        return new PaymentInfoTicket(m_dTicket, m_sPaper);
    }    
    
    private void printState() {

        Double value = m_jTendered.getDoubleValue();
        if (value == null) {
            m_dTicket = 0.0;
        } else {
            m_dTicket = value;
        } 
        
        m_jMoneyEuros.setText(Formats.CURRENCY.formatValue(new Double(m_dTicket)));
        
        int iCompare = RoundUtils.compare(m_dTicket, m_dTotal);
        
        // it is allowed to pay more
        m_notifier.setStatus(m_dTicket > 0.0, iCompare >= 0);
    }
    
    private class RecalculateState implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            printState();
        }
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        m_jMoneyEuros = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        m_jKeys = new uk.chromis.editor.JEditorKeys();
        jPanel1 = new javax.swing.JPanel();
        m_jTendered = new uk.chromis.editor.JEditorCurrencyPositive();

        setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText(AppLocal.getIntString("Label.InputCash")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel4.add(jLabel1);
        jLabel1.setBounds(10, 4, 100, 30);

        m_jMoneyEuros.setBackground(new java.awt.Color(204, 255, 51));
        m_jMoneyEuros.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        m_jMoneyEuros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        m_jMoneyEuros.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow")), javax.swing.BorderFactory.createEmptyBorder(1, 4, 1, 4)));
        m_jMoneyEuros.setOpaque(true);
        m_jMoneyEuros.setPreferredSize(new java.awt.Dimension(180, 30));
        jPanel4.add(m_jMoneyEuros);
        m_jMoneyEuros.setBounds(120, 4, 180, 30);

        add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.Y_AXIS));
        jPanel12.add(m_jKeys);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel1.setLayout(new java.awt.BorderLayout());

        m_jTendered.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(m_jTendered, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel1);

        jPanel11.add(jPanel12, java.awt.BorderLayout.NORTH);

        add(jPanel11, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel4;
    private uk.chromis.editor.JEditorKeys m_jKeys;
    private javax.swing.JLabel m_jMoneyEuros;
    private uk.chromis.editor.JEditorCurrencyPositive m_jTendered;
    // End of variables declaration//GEN-END:variables
    
}
