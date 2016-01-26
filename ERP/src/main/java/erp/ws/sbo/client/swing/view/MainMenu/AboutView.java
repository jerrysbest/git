package erp.ws.sbo.client.swing.view.MainMenu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class AboutView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane txtpnQserpDevelopedBy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutView dialog = new AboutView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		{
		    txtpnQserpDevelopedBy = new JTextPane();
			txtpnQserpDevelopedBy.setEditable(false);
			txtpnQserpDevelopedBy.setText("QS-ERP Developed by Jerry;\r\nMobile:13381162367;\r\nEmail:Jerrysbest@163.com;" +
					"\r\nVersion:201307081;\r\n���кų��������к���⡢�������������������˲��ԣ����ǳ��غʹ�ӡ��û�п�������������Ӧ�մ���ƾ֤��ӡ����");
			getContentPane().add(txtpnQserpDevelopedBy, BorderLayout.CENTER);
		}
	}

	public JTextPane getTxtpnQserpDevelopedBy() {
		return txtpnQserpDevelopedBy;
	}

	public void setTxtpnQserpDevelopedBy(JTextPane txtpnQserpDevelopedBy) {
		this.txtpnQserpDevelopedBy = txtpnQserpDevelopedBy;
	}

}
