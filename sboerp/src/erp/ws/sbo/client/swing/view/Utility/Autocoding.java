package erp.ws.sbo.client.swing.view.Utility;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Autocoding {

	private JFrame frame;
	private JTextField tf_field;
	private JTextField tf_description;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Autocoding window = new Autocoding();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Autocoding() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblDatatype = new JLabel("datatype:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDatatype, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblDatatype, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblDatatype);
		
		JComboBox combo_Datatype = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, combo_Datatype, 4, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, combo_Datatype, 6, SpringLayout.EAST, lblDatatype);
		springLayout.putConstraint(SpringLayout.EAST, combo_Datatype, 38, SpringLayout.EAST, lblDatatype);
		frame.getContentPane().add(combo_Datatype);
		
		JLabel lblField = new JLabel("field:");
		springLayout.putConstraint(SpringLayout.WEST, lblField, 27, SpringLayout.EAST, combo_Datatype);
		springLayout.putConstraint(SpringLayout.SOUTH, lblField, 0, SpringLayout.SOUTH, lblDatatype);
		frame.getContentPane().add(lblField);
		
		tf_field = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, tf_field, -3, SpringLayout.NORTH, lblDatatype);
		springLayout.putConstraint(SpringLayout.WEST, tf_field, 6, SpringLayout.EAST, lblField);
		frame.getContentPane().add(tf_field);
		tf_field.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("description");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, lblDatatype);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 19, SpringLayout.EAST, tf_field);
		frame.getContentPane().add(lblNewLabel);
		
		tf_description = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, tf_description, 0, SpringLayout.NORTH, lblDatatype);
		springLayout.putConstraint(SpringLayout.WEST, tf_description, 17, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(tf_description);
		tf_description.setColumns(10);
	}
}
