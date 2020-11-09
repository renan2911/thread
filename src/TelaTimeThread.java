import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaTimeThread extends JDialog{
	
	private JPanel jPanel = new JPanel(new GridBagLayout()); /*Painel de componentes*/
	
	private JLabel descricaoThread1 = new JLabel("Time Thread 1");
	private JTextField mostraTempo1 = new JTextField();
	
	
	private JLabel descricaoThread2 = new JLabel("Time Thread 2");
	private JTextField mostraTempo2 = new JTextField();
	
	private JButton jButtonStart = new JButton("Start");
	private JButton jButtonStop = new JButton("Stop");
	
	/*Primeira Thread*/ 
	private Runnable thread1 = new Runnable() {
		
		@Override
		public void run() {
			while (true) {
				mostraTempo1.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss")
						.format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	/*Segunda Thread*/
	private Runnable thread2 = new Runnable() {
		
		@Override
		public void run() {
			while(true) {
				mostraTempo2.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
						.format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}			
		}
	};
	
	private Thread threadTime1; 
	private Thread threadTime2;
	public TelaTimeThread() {
		setTitle("Minha tela de time com Thread");
		setSize(240,240);
		setLocationRelativeTo(null);
		setResizable(false);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		
		descricaoThread1.setPreferredSize(new Dimension(200,25));
		jPanel.add(descricaoThread1, gridBagConstraints);
		
		mostraTempo1.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy ++;
		mostraTempo1.setEditable(false);
		jPanel.add(mostraTempo1, gridBagConstraints);
		
		descricaoThread2.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(descricaoThread2, gridBagConstraints);
		
		mostraTempo2.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy ++;
		mostraTempo2.setEditable(false);
		jPanel.add(mostraTempo2, gridBagConstraints);
		
		/*Buttons*/
		gridBagConstraints.gridwidth = 1;
		
		jButtonStart.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(jButtonStart, gridBagConstraints);
		
		/*Ação do button*/
		jButtonStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				threadTime1 = new Thread(thread1);
				threadTime1.start();
				
				threadTime2 = new Thread(thread2);
				threadTime2.start();
				
				jButtonStart.setEnabled(false);
				jButtonStop.setEnabled(true);
			}
		});
		
		
		
		jButtonStop.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx ++;
		jPanel.add(jButtonStop, gridBagConstraints);
		
		/*Ação do button*/
		jButtonStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				threadTime1.stop();
				threadTime2.stop();
				
				jButtonStart.setEnabled(true);
				jButtonStop.setEnabled(false);
			}
		});
		
		jButtonStop.setEnabled(false);
		
		add(jPanel, BorderLayout.WEST);
		/*Sempre será o ultimo comando*/
		setVisible(true);/*Torna a tela visível para o usuário*/
	}
}
