package Controller.item;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import Controller.member.admin_funcs;
import Controller.member.member_lnr;
import Controller.order.create_order;
import Dao.item.item_implement;
import Dao.order.order_implement;
import Util.util_item;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;

public class create_item extends JFrame {
	
	private static String member_acc;
	private JPanel contentPane;
	private JTextField item_name_tf;
	private JTextField item_p_tf;
	private JTextField item_pic_tf;
	private JTextField batch_import_tf;
	private JTextField item_pic_path_tf;
	static JTable item_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					create_item frame = new create_item();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public static void setMember_acc(String member_acc) {
		create_item.member_acc = member_acc;
	}
	
	public static String getMember_acc() {
		return create_item.member_acc;
	}
	
	public create_item() {
		Image img = new ImageIcon(member_lnr.class.getResource("/surfing_i.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setVisible(true);
		requestFocusInWindow(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel item_pic_lbl = new JLabel("商品圖片");
		item_pic_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_pic_lbl.setBounds(20, 10, 61, 30);
		contentPane.add(item_pic_lbl);
		
		JLabel item_pic_path = new JLabel("儲存路徑");
		item_pic_path.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_pic_path.setBounds(20, 45, 61, 30);
		contentPane.add(item_pic_path);
		
		JLabel item_name_lbl = new JLabel("商品名稱");
		item_name_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_name_lbl.setBounds(20, 80, 61, 30);
		contentPane.add(item_name_lbl);
		
		JLabel item_p_lbl = new JLabel("商品價格");
		item_p_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_p_lbl.setBounds(20, 115, 61, 30);
		contentPane.add(item_p_lbl);
		
		JLabel batch_import_lbl = new JLabel("批量匯入");
		batch_import_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		batch_import_lbl.setBounds(20, 150, 61, 30);
		contentPane.add(batch_import_lbl);
		
		item_pic_tf = new JTextField();
		item_pic_tf.setBounds(90, 10, 130, 30);
		contentPane.add(item_pic_tf);
		item_pic_tf.setColumns(10);
		
		item_pic_path_tf = new JTextField();
		item_pic_path_tf.setBounds(90, 45, 130, 30);
		contentPane.add(item_pic_path_tf);
		item_pic_path_tf.setColumns(10);
		
		item_name_tf = new JTextField();
		item_name_tf.setBounds(90, 80, 130, 30);
		contentPane.add(item_name_tf);
		item_name_tf.setColumns(10);
		
		item_p_tf = new JTextField();
		item_p_tf.setBounds(90, 115, 130, 30);
		contentPane.add(item_p_tf);
		item_p_tf.setColumns(10);
		
		batch_import_tf = new JTextField();
		batch_import_tf.setBounds(90, 150, 130, 30);
		contentPane.add(batch_import_tf);
		batch_import_tf.setColumns(10);
		
		item_table = new JTable();
		util_item.item_table_display(item_table);
		item_table.setBounds(20, 200, 300, 470);
		item_table.setDefaultEditor(Object.class, null);
		item_table.setRowSelectionAllowed(true);
		item_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		contentPane.add(item_table);
		
		JScrollPane scrollPane = new JScrollPane(item_table);
		scrollPane.setBounds(20, 205, 300, 460);
		contentPane.add(scrollPane);
		
		JButton item_pic_fc_btn = new JButton("選擇檔案");
		item_pic_fc_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_pic_fc_btn.setBounds(230, 10, 100, 30);
		contentPane.add(item_pic_fc_btn);
		
		JButton item_pic_path_fc_btn = new JButton("選擇路徑");
		item_pic_path_fc_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_pic_path_fc_btn.setBounds(230, 45, 100, 30);
		contentPane.add(item_pic_path_fc_btn);
		
		JButton create_item_btn = new JButton("新增");
		create_item_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		create_item_btn.setBounds(340, 10, 90, 30);
		contentPane.add(create_item_btn);
		
		JButton bi_fc_btn = new JButton("選擇檔案");
		bi_fc_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		bi_fc_btn.setBounds(230, 150, 100, 30);
		contentPane.add(bi_fc_btn);
		
		JButton bi_cfm_btn = new JButton("新增");
		bi_cfm_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		bi_cfm_btn.setBounds(340, 150, 90, 30);
		contentPane.add(bi_cfm_btn);
		
		JButton launch_item_btn = new JButton("上架");
		launch_item_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		launch_item_btn.setBounds(340, 205, 90, 30);
		contentPane.add(launch_item_btn);
	
		JButton stop_item_btn = new JButton("下架");
		stop_item_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		stop_item_btn.setBounds(340, 240, 90, 30);
		contentPane.add(stop_item_btn);
		
		JButton update_item_btn = new JButton("修改");
		update_item_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		update_item_btn.setBounds(340, 275, 90, 30);
		contentPane.add(update_item_btn);
		
		JButton delete_item_btn = new JButton("刪除");
		delete_item_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		delete_item_btn.setBounds(340, 310, 90, 30);
		contentPane.add(delete_item_btn);
		
		JButton mv_to_top_btn = new JButton("置頂");
		mv_to_top_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		mv_to_top_btn.setBounds(340, 345, 90, 30);
		contentPane.add(mv_to_top_btn);
		
		JButton mv_up_btn = new JButton("上移");
		mv_up_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		mv_up_btn.setBounds(340, 380, 90, 30);
		contentPane.add(mv_up_btn);
		
		JButton mv_down_btn = new JButton("下移");
		mv_down_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		mv_down_btn.setBounds(340, 415, 90, 30);
		contentPane.add(mv_down_btn);
		
		JButton mv_to_btm_btn = new JButton("置底");
		mv_to_btm_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		mv_to_btm_btn.setBounds(340, 450, 90, 30);
		contentPane.add(mv_to_btm_btn);
		
		JButton pv_od_pg = new JButton("預覽頁面");
		pv_od_pg.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		pv_od_pg.setBounds(340, 605, 90, 30);
		contentPane.add(pv_od_pg);
		
		JButton mv_to_last_pg = new JButton("回前頁");
		mv_to_last_pg.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		mv_to_last_pg.setBounds(340, 640, 90, 30);
		contentPane.add(mv_to_last_pg);
		
		//選擇圖片
		item_pic_fc_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				FileFilter img_ff = new util_item().new ImageFilter();
				file.addChoosableFileFilter(img_ff);
				file.setAcceptAllFileFilterUsed(false);
				int result = file.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					String path = file.getSelectedFile().getPath();
					item_pic_tf.setText(path);
				}
			}
		});
		
		//新增商品
		create_item_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String img_path = item_pic_tf.getText();
				String ext = img_path.split("\\.")[img_path.split("\\.").length - 1];
				if(item_pic_tf.getText().equals("") ||
					item_name_tf.getText().equals("")||
					item_p_tf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "請填入完整資訊");
				} else if(!ext.equals("jpg") && !ext.equals("jpeg") && ext.equals("tiff") &&
							!ext.equals("tif") && !ext.equals("gif") && !ext.equals("png")) {
					JOptionPane.showMessageDialog(null, "請輸入圖檔路徑");
				} else if(!new File(item_pic_path_tf.getText()).isDirectory() || item_pic_path_tf.getText().equals("")){
					JOptionPane.showMessageDialog(null, "請輸入正確的儲存路徑");
				} else {
					String item_pic_name = "img_" + item_name_tf.getText();
					util_item.copy_img_to_src(item_pic_tf.getText(), item_pic_path_tf.getText(), item_pic_name);
					String img_src = item_pic_path_tf.getText() + "/" + item_pic_name + ".png";
					new item_implement().create_item(item_name_tf.getText(), Integer.parseInt(item_p_tf.getText()), img_src);
					item_pic_tf.setText("");
					item_name_tf.setText("");
					item_p_tf.setText("");
					util_item.item_table_display(item_table);
					item_table.repaint();
				}
			}
		});
		
		//選擇圖片存儲路徑
		item_pic_path_fc_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				FileFilter dir_ff = new util_item().new FolderFilter();
				/*
				JPopupMenu popup_menu = new JPopupMenu("pm_jfc");
				JMenuItem create_file = new JMenuItem("新建文件夾");
				popup_menu.add(create_file);
				file.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						if(e.isMetaDown()) {
							popup_menu.show(file, e.getX(), e.getY());
						}
					}
				});
				*/
				file.addChoosableFileFilter(dir_ff);
				file.setAcceptAllFileFilterUsed(false);
				file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = file.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					String path = file.getSelectedFile().getPath();
					item_pic_path_tf.setText(path);
				}
			}
		});
		
		//選擇文件
		bi_fc_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser(Paths.get("").toAbsolutePath().toString());
				FileFilter doc_ff = new util_item().new DocFilter();
				file.addChoosableFileFilter(doc_ff);
				file.setAcceptAllFileFilterUsed(false);
				int result = file.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					String path = file.getSelectedFile().getPath();
					batch_import_tf.setText(path);
				}
			}
		});
		
		//批量新增商品
		bi_cfm_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(batch_import_tf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "請填入完整資訊");
					return;
				}
				String doc_path =  batch_import_tf.getText();
				String ext = doc_path.substring(doc_path.length() - 3 ,doc_path.length());
				if(ext.equals("txt") || ext.equals("csv") && new File(item_pic_path_tf.getText()).isDirectory()) {
					 List<Object[]> item_list = util_item.read_txt_csv(doc_path);
					 if(util_item.item_list_p_checker(item_list)) {
						 item_list.stream().forEach((item_data) -> {
							 String item_pic_name = "img_" + (String)item_data[0];
							 util_item.copy_img_to_src((String)item_data[2], item_pic_path_tf.getText(), item_pic_name);
							 String img_src = item_pic_path_tf.getText() + "/" + item_pic_name + ".png";
							 new item_implement().create_item((String)item_data[0], (int)item_data[1], img_src);
						 });
						 batch_import_tf.setText("");
						 util_item.item_table_display(item_table);
						 item_table.repaint();
					
					 } else {
						 JOptionPane.showMessageDialog(null, "請確認檔案格式是否正確");
					 }
				} else if(!new File(item_pic_path_tf.getText()).isDirectory() || item_pic_path_tf.getText().equals("")){
						JOptionPane.showMessageDialog(null, "請輸入正確的儲存路徑");
				} else {
					JOptionPane.showMessageDialog(null, "僅接受以下檔案格式\n.txt/.csv/.xls/.xlsx");
				}
				
			}
		});
		
		//按鈕判斷
		item_table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent event) {
				int func_check = util_item.func_check(item_table);
				if(func_check == 0) {
					launch_item_btn.setEnabled(false);
					stop_item_btn.setEnabled(false);
					update_item_btn.setEnabled(false);
					delete_item_btn.setEnabled(false);
					mv_to_top_btn.setEnabled(false);
					mv_up_btn.setEnabled(false);
					mv_down_btn.setEnabled(false);
					mv_to_btm_btn.setEnabled(false);
				} else if(func_check == 1) {
					launch_item_btn.setEnabled(false);
					stop_item_btn.setEnabled(true);
					update_item_btn.setEnabled(true);
					delete_item_btn.setEnabled(true);
					mv_to_top_btn.setEnabled(true);
					mv_up_btn.setEnabled(true);
					mv_down_btn.setEnabled(true);
					mv_to_btm_btn.setEnabled(true);
				} else if(func_check == 2) {
					launch_item_btn.setEnabled(true);
					stop_item_btn.setEnabled(false);
					update_item_btn.setEnabled(true);
					delete_item_btn.setEnabled(true);
					mv_to_top_btn.setEnabled(false);
					mv_up_btn.setEnabled(false);
					mv_down_btn.setEnabled(false);
					mv_to_btm_btn.setEnabled(false);
				} else if(func_check == 3) {
					launch_item_btn.setEnabled(false);
					stop_item_btn.setEnabled(false);
					update_item_btn.setEnabled(false);
					delete_item_btn.setEnabled(true);
					mv_to_top_btn.setEnabled(false);
					mv_up_btn.setEnabled(false);
					mv_down_btn.setEnabled(false);
					mv_to_btm_btn.setEnabled(false);
				} else if(func_check == 4) {
					launch_item_btn.setEnabled(false);
					stop_item_btn.setEnabled(true);
					update_item_btn.setEnabled(false);
					delete_item_btn.setEnabled(true);
					mv_to_top_btn.setEnabled(true);
					mv_up_btn.setEnabled(true);
					mv_down_btn.setEnabled(true);
					mv_to_btm_btn.setEnabled(true);
				} else if(func_check == 5) {
					launch_item_btn.setEnabled(true);
					stop_item_btn.setEnabled(false);
					update_item_btn.setEnabled(false);
					delete_item_btn.setEnabled(true);
					mv_to_top_btn.setEnabled(false);
					mv_up_btn.setEnabled(false);
					mv_down_btn.setEnabled(false);
					mv_to_btm_btn.setEnabled(false);
				}
			}
		});
		
		//商品上架
		launch_item_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] row_idx_list = item_table.getSelectedRows();
				for(int row_dix: row_idx_list) {
					if(item_table.getValueAt(row_dix, 0).equals("未上架")) {
						String item_name = (String) item_table.getValueAt(row_dix, 1);
						int item_price = (int) item_table.getValueAt(row_dix, 2);
						new item_implement().set_sequence(item_name, item_price);
					}
				}
				util_item.item_table_display(item_table);
				item_table.repaint();
				item_table.clearSelection();
				for(int row_idx:row_idx_list) {
					if(row_idx <= item_table.getRowCount() - 1) {
						item_table.addRowSelectionInterval(row_idx, row_idx);
					} else {
						continue;
					}
				}
			}
		});
		
		//商品下架
		stop_item_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] row_idx_list = item_table.getSelectedRows();
				int row_nn_count = new item_implement().get_sequence() - 1;
				List<Integer> row_nn_list = new ArrayList<Integer>();
				for(int row_idx = 0; row_idx < row_nn_count; row_idx++) {
					row_nn_list.add(row_idx + 1);
				}
				
				List<Integer> row_selected_list = new ArrayList<Integer>();
				for(int row_idx: row_idx_list) {
					row_selected_list.add(row_idx + 1);
				}
				for(int row_idx = row_nn_list.size() - 1; row_idx >= 0; row_idx--) {
					if(row_selected_list.contains(row_nn_list.get(row_idx))) {
						row_nn_list.remove(row_idx);
					}
				}
				for(int row_idx: row_idx_list) {
					if(!item_table.getValueAt(row_idx, 0).equals("未上架")) {
						String item_name = (String) item_table.getValueAt(row_idx, 1);
						int item_price = (int) item_table.getValueAt(row_idx, 2);
						new item_implement().stop_item(item_name, item_price);
					} 
				}
				
				for(int row_idx = 0; row_idx < row_nn_list.size(); row_idx++) {
					int og_d_s = row_nn_list.get(row_idx);
					int new_d_s = row_idx + 1;
					new item_implement().set_new_sequence(og_d_s, new_d_s);
				}
				
				util_item.item_table_display(item_table);
				item_table.repaint();
				item_table.clearSelection();
				for(int row_idx:row_idx_list) {
					if(row_idx <= item_table.getRowCount() - 1) {
						item_table.addRowSelectionInterval(row_idx, row_idx);
					} else {
						continue;
					}
				}
			}
		});
		
		//修改商品資訊
		update_item_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected_row = item_table.getSelectedRow();
				if(item_table.getValueAt(selected_row, 0) instanceof Integer) {
					update_item.get_selected_item_info((int) item_table.getValueAt(selected_row, 0));
				} else {
					update_item.get_selected_item_info_null(
							(String) item_table.getValueAt(selected_row, 1), 
							(int) item_table.getValueAt(selected_row, 2));
				}
				update_item ui_pg = new update_item();
				ui_pg.setVisible(true);
			}
		});
		
		//刪除商品
		delete_item_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] row_idx_list = item_table.getSelectedRows();
				int row_nn_count = new item_implement().get_sequence() - 1;
				List<Integer> row_nn_list = new ArrayList<Integer>();
				for(int row_idx = 0; row_idx < row_nn_count; row_idx++) {
					row_nn_list.add(row_idx + 1);
				}
				
				List<Integer> row_selected_list = new ArrayList<Integer>();
				for(int row_idx: row_idx_list) {
					row_selected_list.add(row_idx + 1);
				}
				for(int row_idx = row_nn_list.size() - 1; row_idx >= 0; row_idx--) {
					if(row_selected_list.contains(row_nn_list.get(row_idx))) {
						row_nn_list.remove(row_idx);
					}
				}
				for(int row_idx:row_idx_list) {
					if(item_table.getValueAt(row_idx, 0) instanceof Integer){
						int display_sequence = (int) item_table.getValueAt(row_idx, 0);
						String item_name = (String) item_table.getValueAt(row_idx, 1);
						int item_price = (int) item_table.getValueAt(row_idx, 2);
						String img_path = new item_implement().get_pic_src(item_name, item_price);
						new item_implement().delete_nonstop_item(display_sequence);
						util_item.delete_item_img(img_path);
					} else {
						String item_name = (String) item_table.getValueAt(row_idx, 1);
						int item_price = (int) item_table.getValueAt(row_idx, 2);
						String img_path = new item_implement().get_pic_src(item_name, item_price);
						new item_implement().delete_stop_item(item_name, item_price);
						util_item.delete_item_img(img_path);
					}
				}

				for(int row_idx = 0; row_idx < row_nn_list.size(); row_idx++) {
					int og_d_s = row_nn_list.get(row_idx);
					int new_d_s = row_idx + 1;
					new item_implement().set_new_sequence(og_d_s, new_d_s);
				}
				
				util_item.item_table_display(item_table);
				item_table.repaint();
				item_table.clearSelection();
				for(int row_idx:row_idx_list) {
					if(row_idx <= item_table.getRowCount() - 1) {
						item_table.addRowSelectionInterval(row_idx, row_idx);
					} else {
						continue;
					}
				}
			}
		});
		
		//上移一格
		mv_up_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] row_idx_list = item_table.getSelectedRows();
				for(int row_idx: row_idx_list) {
					int og_d_s = (int) item_table.getValueAt(row_idx, 0);
					if(og_d_s != 1) {
						int temp = new item_implement().get_max_sequence();
						int new_d_s = og_d_s - 1;
						item_implement sql_func = new item_implement();
						sql_func.set_new_sequence(og_d_s, temp);
						sql_func.set_new_sequence(new_d_s, og_d_s);
						sql_func.set_new_sequence(temp, new_d_s);
					} else {
						JOptionPane.showMessageDialog(null, "序號1的商品無法上移");
					}
				}
				util_item.item_table_display(item_table);
				item_table.repaint();
				item_table.clearSelection();
				for(int row_idx:row_idx_list) {
					if(row_idx <= item_table.getRowCount() - 1) {
						item_table.addRowSelectionInterval(row_idx, row_idx);
					} else {
						continue;
					}
				}
			}
		});
		
		//下移一格
		mv_down_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] row_idx_list = item_table.getSelectedRows();
				for(int row_idx: row_idx_list) {
					item_implement sql_func = new item_implement();
					int og_d_s = (int) item_table.getValueAt(row_idx, 0);
					int temp = sql_func.get_max_sequence();
					if(og_d_s != sql_func.get_sequence() - 1) {
						int new_d_s = og_d_s + 1;
						sql_func.set_new_sequence(og_d_s, temp);
						sql_func.set_new_sequence(new_d_s, og_d_s);
						sql_func.set_new_sequence(temp, new_d_s);
					} else {
						JOptionPane.showMessageDialog(null, "序號最大的商品無法下移");
					}
				}
				util_item.item_table_display(item_table);
				item_table.repaint();
				item_table.clearSelection();
				for(int row_idx:row_idx_list) {
					if(row_idx <= item_table.getRowCount() - 1) {
						item_table.addRowSelectionInterval(row_idx, row_idx);
					} else {
						continue;
					}
				}
			}
		});
		
		//置頂
		mv_to_top_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item_implement sql_func = new item_implement();
				int[] row_idx_list = item_table.getSelectedRows();
				int row_nn_count = sql_func.get_sequence() - 1;
				List<Integer> row_nn_list = new ArrayList<Integer>();
				for(int row_idx = 0; row_idx < row_nn_count; row_idx++) {
					row_nn_list.add(row_idx + 1);
				}
				
				List<Integer> row_selected_list = new ArrayList<Integer>();
				for(int row_idx: row_idx_list) {
					row_selected_list.add(row_idx + 1);
				}
				
				for(int row_idx = row_nn_list.size() - 1; row_idx >= 0; row_idx--) {
					if(row_selected_list.contains(row_nn_list.get(row_idx))) {
						row_nn_list.remove(row_idx);
					}
				}
				
				int temp_ns = sql_func.get_max_sequence();
				for(int row_idx = 0; row_idx < row_nn_list.size(); row_idx++) {
					int og_d_s = row_nn_list.get(row_idx);
					sql_func.set_new_sequence(og_d_s, temp_ns);
					temp_ns++;
				}
				
				int temp_s = sql_func.get_max_sequence();
				for(int row_idx = 0; row_idx < row_idx_list.length; row_idx++) {
					int og_d_s = (int) item_table.getValueAt(row_idx_list[row_idx], 0);
					if(og_d_s != 1) {
						int new_d_s = row_idx + 1;
						sql_func.set_new_sequence(og_d_s, temp_s);
						sql_func.set_new_sequence(new_d_s, og_d_s);
						sql_func.set_new_sequence(temp_s, new_d_s);
						temp_s++;
					} else {
						JOptionPane.showMessageDialog(null, "序號1的商品無法上移");
					}
				}
				
				int new_nn_d_s = row_idx_list.length + 1;
				for(int row_idx = 0; row_idx < row_nn_list.size(); row_idx++) {
					int og_d_s = temp_ns - row_nn_list.size() + row_idx;
					sql_func.set_new_sequence(og_d_s, new_nn_d_s);
					new_nn_d_s++;
					
				}
				util_item.item_table_display(item_table);
				item_table.repaint();
				item_table.clearSelection();
				for(int row_idx:row_idx_list) {
					if(row_idx <= item_table.getRowCount() - 1) {
						item_table.addRowSelectionInterval(row_idx, row_idx);
					} else {
						continue;
					}
				}
			}
		});
		
		//置底
		mv_to_btm_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item_implement sql_func = new item_implement();
				int[] row_idx_list = item_table.getSelectedRows();
				int row_nn_count = sql_func.get_sequence() - 1;
				List<Integer> row_nn_list = new ArrayList<Integer>();
				for(int row_idx = 0; row_idx < row_nn_count; row_idx++) {
					row_nn_list.add(row_idx + 1);
				}
				
				List<Integer> row_selected_list = new ArrayList<Integer>();
				for(int row_idx: row_idx_list) {
					row_selected_list.add(row_idx + 1);
				}
				
				for(int row_idx = row_nn_list.size() - 1; row_idx >= 0; row_idx--) {
					if(row_selected_list.contains(row_nn_list.get(row_idx))) {
						row_nn_list.remove(row_idx);
					}
				}
				
				int temp_ns = sql_func.get_max_sequence();
				for(int row_idx = 0; row_idx < row_idx_list.length; row_idx++) {
					int og_d_s = row_idx_list[row_idx] + 1;
					if(og_d_s == temp_ns - 1) {
						JOptionPane.showMessageDialog(null, "序號最大的商品無法下移");
					}
					sql_func.set_new_sequence(og_d_s, temp_ns);
					temp_ns++;
				}
				
				int temp_s = sql_func.get_max_sequence();
				for(int row_idx = 0; row_idx < row_nn_list.size(); row_idx++) {
					int og_d_s = row_nn_list.get(row_idx);
					int new_nn_d_s = row_idx + 1;
					sql_func.set_new_sequence(og_d_s, temp_s);
					sql_func.set_new_sequence(new_nn_d_s, og_d_s);
					sql_func.set_new_sequence(temp_s, new_nn_d_s);
					temp_s++;
				}
			
				int new_d_s = row_nn_list.size() + 1;
				for(int row_idx = 0;row_idx < row_idx_list.length; row_idx++) {
					int og_d_s = temp_ns - row_idx_list.length + row_idx;
					sql_func.set_new_sequence(og_d_s, new_d_s);
					new_d_s++;
					
				}

				util_item.item_table_display(item_table);
				item_table.repaint();
				item_table.clearSelection();
				for(int row_idx:row_idx_list) {
					if(row_idx <= item_table.getRowCount() - 1) {
						item_table.addRowSelectionInterval(row_idx, row_idx);
					} else {
						continue;
					}
				}
				
			}
		});
		
		//回功能選單
		mv_to_last_pg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin_funcs.setMember_acc(member_acc);
				admin_funcs af = new admin_funcs();
				af.setVisible(true);
				dispose();
			}
		});
		
		//預覽頁面
		pv_od_pg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new order_implement().add_item();
				create_order.setMember_acc(member_acc);
				create_order co = new create_order();
				co.setVisible(true);
				dispose();
			}
		});
	}
	
}
