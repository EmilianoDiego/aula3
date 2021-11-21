package Analisador;
//interface do programa analisador lexico

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.io.StringReader;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class InterfaceAnalisador {

	private JFrame frame;
    public String expr ="";
    public static String [] tokens = new String[10000];
    public static int tamanho=0;
	public int contar = 0;
	AnalisadorLexico lexical = new AnalisadorLexico(new StringReader(expr));

    //contador para controle do analisadoLexico
    public int contador(int contador){
		if(contador >= tamanho){
        initialize();
			return -1;
		}else{
			return 0;
		}
	}

    //metodo main
	public static void main(String[] args) {
		tokens[0] = "";
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        	System.err.println(ex);
        } catch (InstantiationException ex) {
        	System.err.println(ex);
        } catch (IllegalAccessException ex) {
        	System.err.println(ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        	System.err.println(ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceAnalisador window = new InterfaceAnalisador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }


    //criar inicializacao da classe
	public InterfaceAnalisador() {
		initialize();
	}

	// inicializacao do frame da classe
	private void initialize() {


		frame = new JFrame();
		frame.setTitle("ANALISADOR LEXICO");
		frame.setBounds(100, 100, 536, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground( new Color(128, 128, 128));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(271, 33, 226, 279);
		frame.getContentPane().add(scrollPane);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(30, 33, 206, 279);
		frame.getContentPane().add(scrollPane2);
		
		JLabel lblNome = new JLabel("CODE");
		lblNome.setBackground(new Color(0, 0, 0));
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(20, 11, 117, 22);
		frame.getContentPane().add(lblNome);
		
		/*JTextPane textLeitura = new JTextPane();
		textLeitura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textLeitura.setBackground(SystemColor.activeCaption);
		textLeitura.setBounds(240, 33, 240, 279);
		frame.getContentPane().add(textLeitura);
        textLeitura.setText(tokens);*/

		DefaultListModel lista = new DefaultListModel();
		JList list = new JList(lista);
		list.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list.setBackground(new Color(0, 0, 0));
		list.setForeground(new Color(0, 255, 0));
		scrollPane.setViewportView(list);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(0, 255, 0));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setBackground(new Color(0, 0, 0));
		textArea.setBounds(10, 33, 205, 279);
		scrollPane2.setViewportView(textArea);
		String inicio = "int valor  2 \n if 2 + 2 \n iniciar programa \n then  \n sair do programa";
		textArea.setText(inicio);
		
		
		JLabel lblToken = new JLabel("TOKEN");
		lblToken.setForeground(new Color(0, 0, 0));
		lblToken.setBackground(SystemColor.window);
		lblToken.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblToken.setBounds(271, 15, 46, 14);
		frame.getContentPane().add(lblToken);
		
		JButton btnSair = new JButton("SAIR");
		btnSair.setBackground(new Color(128, 0, 0));
		btnSair.setForeground(Color.GRAY);
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSair.setBounds(375, 335, 89, 36);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();			}
		});
		frame.getContentPane().add(btnSair);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setForeground(new Color(0, 0, 0));
		btnClear.setBackground(new Color(240, 248, 255));
		btnClear.setBounds(218, 335, 89, 36);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//textLeitura.setText("");
				tokens[0] ="";
                textArea.setText("");
				lexical.contadorToken = 0;
				lista.clear();
			}
		});
		frame.getContentPane().add(btnClear);
		
		JButton btnAnalyse = new JButton("ANALYSE");
		btnAnalyse.setForeground(Color.BLACK);
		btnAnalyse.setBackground(new Color(0, 255, 0));
		btnAnalyse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lista.clear();
				    expr = ""+textArea.getText();
					tamanho = expr.length();
					AnalisadorLexico lexical = new AnalisadorLexico(new StringReader(expr));	
                    lexical.yylex();
					contar = lexical.contadorToken;
					System.out.println(contar);
					for(int i=0; i <= contar; i++){
						tokens[i] = lexical.tokensLexema[i];
						System.out.println(tokens[i]);
						lista.addElement(tokens[i]);
					}
					//tokens[] = lexical.tokens;
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAnalyse.setBounds(28, 335, 89, 36);
		frame.getContentPane().add(btnAnalyse);

	}
    

}
