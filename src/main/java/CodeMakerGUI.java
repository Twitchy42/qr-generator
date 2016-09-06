import java.awt.EventQueue;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;



import javax.swing.JButton;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;

import org.imgscalr.Scalr;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;

import java.awt.Component;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SpinnerNumberModel;


public class CodeMakerGUI {

	private JFrame frmQrCodeGenerator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CodeMakerGUI window = new CodeMakerGUI();
					window.frmQrCodeGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CodeMakerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQrCodeGenerator = new JFrame();
		frmQrCodeGenerator.getContentPane().setBackground(SystemColor.activeCaption);
		frmQrCodeGenerator.setFont(new Font("Courier New", Font.BOLD, 14));
		frmQrCodeGenerator.setTitle("QR Code Generator");
		frmQrCodeGenerator.getContentPane().setMinimumSize(new Dimension(1000, 1000));
		
		
		
		BufferedImage image = null;
		try{
		image = ImageIO.read(getClass().getResource("resources/InitialImage.jpg"));
		int h = image.getHeight();
		int w = image.getWidth();
		image = Scalr.resize(image, Scalr.Method.BALANCED, w/2, h/2);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		frmQrCodeGenerator.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Starting Code");
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD, 14));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setBounds(10, 11, 136, 14);
		frmQrCodeGenerator.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Print");
		btnNewButton_1.setFont(new Font("Courier New", Font.BOLD, 14));
		btnNewButton_1.setBounds(322, 68, 84, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PDFHandler pdfh = new PDFHandler();
				pdfh.printPDF();
			}
		});
		
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Courier New", Font.BOLD, 14));
		comboBox.setBounds(10, 29, 69, 20);
		comboBox.addItem("B");
		comboBox.addItem("CA");
		comboBox.addItem("CK");
		comboBox.addItem("CO");
		comboBox.addItem("G");
		comboBox.addItem("I");
		comboBox.addItem("R");
		comboBox.addItem("S");
		comboBox.addItem("T");
		comboBox.addItem("V");
		comboBox.addItem("W");
		frmQrCodeGenerator.getContentPane().add(comboBox);
		
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setFont(new Font("Courier New", Font.BOLD, 14));
		spinner.setBounds(77, 29, 69, 20);
		frmQrCodeGenerator.getContentPane().add(spinner);
		
		JButton btnNewButton = new JButton("Generate PDF");
		
		btnNewButton.setFont(new Font("Courier New", Font.BOLD, 14));
		btnNewButton.setBounds(10, 68, 136, 23);
		frmQrCodeGenerator.getContentPane().add(btnNewButton);
		frmQrCodeGenerator.getContentPane().add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(null);
		panel.setBounds(10, 102, 396, 549);
		frmQrCodeGenerator.getContentPane().add(panel);
		
		final JLabel lblNewLabel_1 = new JLabel(new ImageIcon(image));
		panel.add(lblNewLabel_1);
		frmQrCodeGenerator.setBounds(100, 100, 432, 700);
		frmQrCodeGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BufferedImage im;
				PDFHandler pdfh = new PDFHandler();
				pdfh.makePDF(comboBox.getSelectedItem().toString(), (Integer) spinner.getValue());
				pdfh.previewImage("NewPDF.pdf", "PreviewImage");
				try {
					im = ImageIO.read(new FileInputStream("PreviewImage1.jpg"));
					int h = im.getHeight();
					int w = im.getWidth();
					im = Scalr.resize(im, Scalr.Method.BALANCED, w/2, h/2);
					lblNewLabel_1.setIcon(new ImageIcon(im));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

	}
}
