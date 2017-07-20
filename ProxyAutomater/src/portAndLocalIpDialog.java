import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;

public class portAndLocalIpDialog extends JDialog {
	private JTextField portField;
	private JTextField localIpField;
	private JButton okBtn;
	private JButton cancelBtn;
	private Controller controller;
	private List<String> list;
	private List<String> portandLocalIpList;
	private JCheckBox multipleIpBox;
	private IpWhiteListDialog ipWhiteListDialog;
	private PortandLocalIpListener listen;
		public portAndLocalIpDialog(JFrame parent) {
			super(parent,"Input your LOCAL MACHINE IP and PORT",null);
			portField = new JTextField(15);
			localIpField = new JTextField(15);
			okBtn = new JButton("OK");
			controller = new Controller();
			portandLocalIpList = new ArrayList<String>();
			multipleIpBox = new JCheckBox();
			ipWhiteListDialog = new IpWhiteListDialog(parent);
			okBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					portandLocalIpList.clear();
					String port = portField.getText();
					String ip = localIpField.getText();
					List<String> additionalIp = ipWhiteListDialog.geAdditionalIps();
					portandLocalIpList.add(port);
					portandLocalIpList.add(ip);
					System.out.println("additionalIp is " + additionalIp);
					
					if(list == null) {
						JOptionPane.showMessageDialog(portAndLocalIpDialog.this, "You did not set any IPs in the VPS!", "NO IPs", JOptionPane.ERROR_MESSAGE);
					} else if(additionalIp.size() == 0) {
						controller.squidAssignmentScript(port,ip,list);
					} else {
						controller.squidAssignmentScript2(port,ip,list,additionalIp);
					}
					
					if(listen != null) {
						listen.PortEventOccured(portandLocalIpList,additionalIp);
					}
					setVisible(false);
				}	
			});

			cancelBtn = new JButton("Cancel");
			cancelBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
				
			});
			multipleIpBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ipWhiteListDialog.setVisible(true);
				}
				
			});
			layoutComponents();
			setSize(300,200);
		}
		private void layoutComponents() {
			JPanel fieldPanel = new JPanel();
			JPanel buttonPanel = new JPanel();
			
			//setting up gc//
			fieldPanel.setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx=0;
			gc.gridy=0;
			gc.weightx = 0;
			gc.weighty =0;
			gc.anchor = GridBagConstraints.FIRST_LINE_END;
			gc.fill = GridBagConstraints.NONE;
			gc.insets = new Insets (5,0,0,5);
			fieldPanel.add(new JLabel("IP:"),gc);
			
			gc.gridx++;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.insets = new Insets(0,0,0,5);
			fieldPanel.add(localIpField,gc);
			
			gc.gridx = 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.FIRST_LINE_END;
			gc.fill = GridBagConstraints.NONE;
			
			gc.insets = new Insets(5,0,0,5);
			fieldPanel.add(new JLabel("Port:"), gc);
			gc.gridx++;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.insets = new Insets(0,0,0,5);
			fieldPanel.add(portField,gc);
			
			gc.gridx = 0;
			gc.gridy++;
			gc.anchor = GridBagConstraints.FIRST_LINE_END;
			gc.fill = GridBagConstraints.NONE;
			
			gc.insets = new Insets(5,0,0,5);
			fieldPanel.add(new JLabel("Multiple IPs?"), gc);
			gc.gridx++;
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.insets = new Insets(0,0,0,5);
			fieldPanel.add(multipleIpBox,gc);
			
			
			//for buttonPanel//
			
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPanel.add(okBtn);
			buttonPanel.add(cancelBtn);
			
			// for this//
			
			this.setLayout(new BorderLayout());
			this.add(fieldPanel,BorderLayout.CENTER);
			this.add(buttonPanel,BorderLayout.SOUTH);
			
		}
		public List<String> getList() {
			return list;
		}
		public void setList(List<String> squidList) {
			this.list = squidList;
		}
		public void setPortandLocalIpListener(PortandLocalIpListener portandLocalIpListener) {
			this.listen = portandLocalIpListener;
			
		}
}
