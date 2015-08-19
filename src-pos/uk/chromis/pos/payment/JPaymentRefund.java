//    Chromis POS  - The New Face of Open Source POS
//    Copyright (c) 2015 uniCenta
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

import uk.chromis.pos.customers.CustomerInfoExt;
import uk.chromis.pos.forms.AppLocal;
import java.awt.Component;

/**
 *
 *   
 */
public class JPaymentRefund extends javax.swing.JPanel implements JPaymentInterface {
    
    private final JPaymentNotifier m_notifier;
    private double m_dTotal;
    
    private final String m_sName;
    
    /** Creates new form JPaymentChequeRefund
     * @param notifier
     * @param sName */
    public JPaymentRefund(JPaymentNotifier notifier, String sName) {
        
        m_notifier = notifier;
        m_sName = sName;
        
        initComponents();
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
        
        m_notifier.setStatus(true, true);
    }
    
    /**
     *
     * @return
     */
    @Override
    public PaymentInfo executePayment() {
        return new PaymentInfoTicket(m_dTotal, m_sName);
    }

    /**
     *
     * @return
     */
    @Override
    public Component getComponent() {
        return this;
    } 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setText(AppLocal.getIntString("message.paymentcashneg")); // NOI18N
        add(jLabel1);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    
}
