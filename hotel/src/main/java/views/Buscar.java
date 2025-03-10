package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import br.com.alura.hotel.controller.HospedeController;
import br.com.alura.hotel.controller.ReservaController;
import br.com.alura.hotel.dao.HospedeDao;
import br.com.alura.hotel.dao.ReservaDao;
import br.com.alura.hotel.modelo.Hospede;
import br.com.alura.hotel.modelo.Reserva;
import br.com.alura.hotel.util.JPAUtil;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	private HospedeController hospedeController;
	private ReservaController reservaController;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {

		this.hospedeController = new HospedeController();
		this.reservaController = new ReservaController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table,
				null);
		scroll_table.setVisible(true);

		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair da aplicação?");
				if (opcao == 0) {
					System.exit(0);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Quando o usuário remove o mouse do botão, ele retornará ao estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparTabela();
				preencherTabela();

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tbReservas.getSelectedRow() != -1) {
					editarReserva();
				}
				if (tbHospedes.getSelectedRow() != -1) {
					editarHospede();
				}
				limparTabela();
				preencherTabela();

			}

		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnDeletar = new JPanel();
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				deletar();
				limparTabela();
				preencherTabela();

			}

		});

		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);

		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);

	}

	private void limparTabela() {
		modelo.getDataVector().clear();
		modeloHospedes.getDataVector().clear();
	}

	private void preencherTabela() {

		modelo.setRowCount(0);
		modeloHospedes.setRowCount(0);

		String itemParaBusca = txtBuscar.getText();
		String sobrenome = null;
		Long id = null;

		if (itemParaBusca != null && !itemParaBusca.trim().isEmpty()) {
			if (itemParaBusca.matches("[a-zA-Z]+")) {
				sobrenome = itemParaBusca;
			} else if (itemParaBusca.matches("[0-9]+")) {
				id = Long.valueOf(itemParaBusca);
			}
		}

		if (sobrenome != null) {
			List<Hospede> hospedes = this.hospedeController.buscarPorParametros(null, sobrenome, null, null, null);
			if (!hospedes.isEmpty()) {
				for (Hospede hospede : hospedes) {
					modelo.addRow(new Object[] { hospede.getReserva().getId(), hospede.getReserva().getDataEntranda(),
							hospede.getReserva().getDataSaida(), hospede.getReserva().getValor(),
							hospede.getReserva().getFormaPagamento() });
					modeloHospedes.addRow(new Object[] { hospede.getId(), hospede.getNome(), hospede.getSobrenome(),
							hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(),
							hospede.getReserva().getId() });
				}
			} else {
				JOptionPane.showMessageDialog(null, "Hóspede não encontrado!");
			}

		} else if (id != null) {
			try {
				Reserva reservasPorId = this.reservaController.buscarPorId(id);

				modeloHospedes.addRow(new Object[] { reservasPorId.getHospede().getId(),
						reservasPorId.getHospede().getNome(), reservasPorId.getHospede().getSobrenome(),
						reservasPorId.getHospede().getDataNascimento(), reservasPorId.getHospede().getNacionalidade(),
						reservasPorId.getHospede().getTelefone(), reservasPorId.getId() });

				modelo.addRow(new Object[] { reservasPorId.getId(), reservasPorId.getDataEntranda(),
						reservasPorId.getDataSaida(), reservasPorId.getValor(), reservasPorId.getFormaPagamento() });

			} catch (NullPointerException nullException) {
				JOptionPane.showMessageDialog(null, "ID não encontrado!");
			}

		} else {
			JOptionPane.showMessageDialog(null,
					"Busque pelo id da Reserva ou sobrenome do Hóspede!\nEvite usar acentos!");
		}

	}

	private void deletar() {

		int linhaReservaSelecionada = tbReservas.getSelectedRow();
		int linhaHospedeSelecionada = tbHospedes.getSelectedRow();

		if (linhaReservaSelecionada != -1) {
			Object id = tbReservas.getValueAt(linhaReservaSelecionada, 0);
			Reserva reserva = reservaController.buscarPorId((Long) id);
			Hospede hospede = reserva.getHospede();

			reservaController.remover(reserva);

			modelo.removeRow(linhaReservaSelecionada);
			for (int i = 0; i < tbHospedes.getRowCount(); i++) {
				if (tbHospedes.getValueAt(i, 6) == id) {
					modeloHospedes.removeRow(i);
					JOptionPane.showMessageDialog(null, "Reserva removida com sucesso!");
				}
			}
		}

		if (linhaHospedeSelecionada != -1) {
			Object id = tbHospedes.getValueAt(linhaHospedeSelecionada, 6);
			Hospede hospede = hospedeController.buscarPorIdDaReserva((Long) id);
			Reserva reserva = reservaController.buscarPorId((Long) id);

			reservaController.remover(reserva);

			modeloHospedes.removeRow(linhaHospedeSelecionada);
			for (int i = 0; i < tbReservas.getRowCount(); i++) {
				if (tbReservas.getValueAt(i, 0) == id) {
					modelo.removeRow(i);
					JOptionPane.showMessageDialog(null, "Hóspede removido com sucesso!");
				}
			}
		}

		linhaHospedeSelecionada = -1;
		linhaReservaSelecionada = -1;

		if (linhaHospedeSelecionada == -1 && linhaReservaSelecionada == -1) {
			JOptionPane.showMessageDialog(null, "Selecione alguma linha para Remover!");
		}

	}

	private void editarReserva() {

		int linhaReservaSelecionada = tbReservas.getSelectedRow();
		int colunaReservaSelecionada = tbReservas.getSelectedColumn();
		
		Object idReserva = tbReservas.getValueAt(linhaReservaSelecionada, 0);
		
		if (colunaReservaSelecionada == 0 || colunaReservaSelecionada == 3 || colunaReservaSelecionada == 4) {
			JOptionPane.showMessageDialog(null,
					"A coluna " + tbReservas.getColumnName(colunaReservaSelecionada) + " não pode ser editada.");
			preencherTabela();
		} else if (colunaReservaSelecionada > 0) {
			try {
				Reserva reserva = this.reservaController.buscarPorId((Long) idReserva);
				Object dataEntrada = tbReservas.getValueAt(linhaReservaSelecionada, 1);
				Object dataSaida = tbReservas.getValueAt(linhaReservaSelecionada, 2);

				if (!dataEntrada.equals(reserva.getDataEntranda())) {
					Object data = dataEntrada;
					reserva.setDataEntranda(LocalDate.parse((CharSequence) data));
					Hospede hospede = this.hospedeController.buscarPorIdDaReserva((Long) idReserva);
					hospede.setReserva(reserva);
					this.reservaController.atualizar(reserva);
					this.hospedeController.atualizar(hospede);
				}

				if (!dataSaida.equals(reserva.getDataSaida())) {
					Object data = dataSaida;
					reserva.setDataSaida(LocalDate.parse((CharSequence) data));
					Hospede hospede = this.hospedeController.buscarPorIdDaReserva((Long) idReserva);
					hospede.setReserva(reserva);
					this.reservaController.atualizar(reserva);
					this.hospedeController.atualizar(hospede);
				}

				JOptionPane.showMessageDialog(null, "Reserva editada!");

			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Selecione alguma linha para editar!");
			}
		}

	}
	
	private void editarHospede() {
		
		int linhaHospedeSelecionada = tbHospedes.getSelectedRow();
		int colunaHospedeSelecionada = tbHospedes.getSelectedColumn();
		
		Object idHospede = tbHospedes.getValueAt(linhaHospedeSelecionada, 0);
		
		if (colunaHospedeSelecionada == 0 || colunaHospedeSelecionada == 4 || colunaHospedeSelecionada == 6) {
			JOptionPane.showMessageDialog(null,
					"A coluna " + tbHospedes.getColumnName(colunaHospedeSelecionada) + " não pode ser editada.");
			preencherTabela();
		} else if (colunaHospedeSelecionada > 0) {
			try {
				Hospede hospede = this.hospedeController.buscarPorId((Long) idHospede);
				Object nomeHospede = tbHospedes.getValueAt(linhaHospedeSelecionada, 1);
				Object sobrenomeHospede = tbHospedes.getValueAt(linhaHospedeSelecionada, 2);
				Object dataNascimento = tbHospedes.getValueAt(linhaHospedeSelecionada, 3);
				Object telefoneHospede = tbHospedes.getValueAt(linhaHospedeSelecionada, 5);

				if (!nomeHospede.equals(hospede.getNome())) {
					Object nome = nomeHospede;
					hospede.setNome((String) nome);
					Reserva reserva = this.reservaController.buscarPorIdDoHospede((Long) idHospede);
					reserva.setHospede(hospede);
					this.hospedeController.atualizar(hospede);
					this.reservaController.atualizar(reserva);
				}

				if (!sobrenomeHospede.equals(hospede.getSobrenome())) {
					Object sobrenome = sobrenomeHospede;
					hospede.setSobrenome((String) sobrenome);
					Reserva reserva = this.reservaController.buscarPorIdDoHospede((Long) idHospede);
					reserva.setHospede(hospede);
					this.hospedeController.atualizar(hospede);
					this.reservaController.atualizar(reserva);
				}

				if (!dataNascimento.equals(hospede.getDataNascimento())) {
					Object dataNasc = dataNascimento;
					hospede.setDataNascimento(LocalDate.parse((CharSequence) dataNasc));
					Reserva reserva = this.reservaController.buscarPorIdDoHospede((Long) idHospede);
					reserva.setHospede(hospede);
					this.hospedeController.atualizar(hospede);
					this.reservaController.atualizar(reserva);
				}

				if (!telefoneHospede.equals(hospede.getTelefone())) {
					Object telefone = telefoneHospede;
					hospede.setTelefone((String) telefone);
					;
					Reserva reserva = this.reservaController.buscarPorIdDoHospede((Long) idHospede);
					reserva.setHospede(hospede);
					this.hospedeController.atualizar(hospede);
					this.reservaController.atualizar(reserva);
				}

				JOptionPane.showMessageDialog(null, "Hospede editado!");

			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Selecione alguma linha para editar!");
			}
		}
		
	}

	// Código que permite movimentar a janela pela tela seguindo a posição de "x" e
	// "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}

}
