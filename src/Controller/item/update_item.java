package Controller.item;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import Controller.member.member_lnr;
import Dao.item.item_implement;
import Model.item_management;
import Util.util_item;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

public class update_item extends JFrame {

	private JPanel contentPane;
	static item_management selected_item;
	private JTextField item_d_s_tf;
	private JTextField item_name_tf;
	private JTextField item_p_tf;
	private JTextField item_pic_path_tf;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					update_item frame = new update_item();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public update_item() {
		Image img = new ImageIcon(member_lnr.class.getResource("/surfing_i.png")).getImage();
		setIconImage(img);
		setResizable(false);
		setVisible(true);
		requestFocusInWindow(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(125, 300, 400, 250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel update_item_lbl = new JLabel("修改商品資料");
		update_item_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		update_item_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		update_item_lbl.setBounds(150, 10, 100, 20);
		contentPane.add(update_item_lbl);
		
		JLabel item_d_s = new JLabel("商品序號");
		item_d_s.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_d_s.setBounds(23, 50, 61, 20);
		contentPane.add(item_d_s);
		
		JLabel item_n_lbl = new JLabel("商品名稱");
		item_n_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_n_lbl.setBounds(23, 80, 61, 20);
		contentPane.add(item_n_lbl);
		
		JLabel item_p_lbl = new JLabel("商品價格");
		item_p_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_p_lbl.setBounds(23, 110, 61, 20);
		contentPane.add(item_p_lbl);
		
		JLabel item_pic_p_lbl = new JLabel("商品圖片");
		item_pic_p_lbl.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		item_pic_p_lbl.setBounds(23, 140, 61, 20);
		contentPane.add(item_pic_p_lbl);
		
		item_d_s_tf = new JTextField();
		item_d_s_tf.setBounds(90, 48, 200, 26);
		if(selected_item.getDisplay_sequence() == 0) {
			item_d_s_tf.setText("未上架");
			item_d_s_tf.setEditable(false);
		}else {
			item_d_s_tf.setText(selected_item.getDisplay_sequence() + "");
		}
		item_d_s_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(item_d_s_tf);
		item_d_s_tf.setColumns(10);
		
		item_name_tf = new JTextField();
		item_name_tf.setBounds(90, 78, 200, 26);
		item_name_tf.setText(selected_item.getItem_name());
		item_name_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(item_name_tf);
		item_name_tf.setColumns(10);
		
		item_p_tf = new JTextField();
		item_p_tf.setBounds(90, 108, 200, 26);
		item_p_tf.setText(selected_item.getItem_price() + "");
		item_p_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(item_p_tf);
		item_p_tf.setColumns(10);
		
		item_pic_path_tf = new JTextField();
		item_pic_path_tf.setBounds(90, 138, 200, 26);
		item_pic_path_tf.setText(selected_item.getItem_pic_path());
		item_pic_path_tf.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		contentPane.add(item_pic_path_tf);
		item_pic_path_tf.setColumns(10);
		
		JButton change_pic_btn = new JButton("更換圖片");
		change_pic_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		change_pic_btn.setBounds(290, 138, 100, 29);
		contentPane.add(change_pic_btn);
		
		JButton cfm_btn = new JButton("確定");
		cfm_btn.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 13));
		cfm_btn.setBounds(141, 180, 117, 29);
		contentPane.add(cfm_btn);
		
		//更換圖片
		change_pic_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String current_dir = System.getProperty("user.dir");
				JFileChooser file = new JFileChooser(current_dir);
				FileFilter img_ff = new util_item().new ImageFilter();
				file.addChoosableFileFilter(img_ff);
				file.setAcceptAllFileFilterUsed(false);
				int result = file.showOpenDialog(null);
				if(result == JFileChooser.APPROVE_OPTION) {
					String path = file.getSelectedFile().getPath();
					item_pic_path_tf.setText(path);
				}
			}
		});
		
		//確認修改
		cfm_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item_implement sql_func = new item_implement();
				if(!util_item.ds_checker(item_d_s_tf)) {
					JOptionPane.showMessageDialog(null, "請輸入正確的商品序號");
					return;
				}
				if(!util_item.p_checker(item_p_tf)) {
					JOptionPane.showMessageDialog(null, "請輸入合理的商品價格");
					return;
				}
				if(!util_item.img_checker(item_pic_path_tf.getText())) {
					JOptionPane.showMessageDialog(null, "請確認檔案格式是否正確");
					return;
				}
				if(item_d_s_tf.getText().equals("未上架") && item_pic_path_tf.getText().equals(selected_item.getItem_pic_path())) {
					String og_pic = item_pic_path_tf.getText();
					String[] og_pic_split = item_pic_path_tf.getText().split("/");
					int end_idx = og_pic.length() - og_pic_split[og_pic_split.length - 1].length() - 1;
					String dest_path = 
							item_pic_path_tf.getText().substring(0, end_idx) + "/img_" + item_name_tf.getText() + ".png";
					//
					sql_func.update_item_n_p(item_name_tf.getText(), Integer.parseInt(item_p_tf.getText()), dest_path
							,selected_item.getItem_name(), selected_item.getItem_price(), og_pic);
					try {
						Files.move(Paths.get(og_pic), Paths.get(dest_path),  java.nio.file.StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if(item_d_s_tf.getText().equals("未上架") && !item_pic_path_tf.getText().equals(selected_item.getItem_pic_path())) {
					util_item.delete_item_img(selected_item.getItem_pic_path());
					String og_pic = item_pic_path_tf.getText();
					String[] og_pic_split = item_pic_path_tf.getText().split("/");
					int end_idx = og_pic.length() - og_pic_split[og_pic_split.length - 1].length() - 1;
					String dest_path = 
							item_pic_path_tf.getText().substring(0, end_idx);
					String img_src = item_pic_path_tf.getText();
					String img_name = "img_" + item_name_tf.getText();
					util_item.copy_img_to_src(img_src, dest_path, img_name);
					sql_func.update_item_n_p_p(
							item_name_tf.getText(), Integer.parseInt(item_p_tf.getText()), dest_path + "/" + img_name + ".png"
									,selected_item.getItem_name(), selected_item.getItem_price(), selected_item.getItem_pic_path());
				} else if(Integer.parseInt(item_d_s_tf.getText()) < sql_func.get_max_sequence() &&
						 item_pic_path_tf.getText().equals(selected_item.getItem_pic_path())) {
					int og_d_s = selected_item.getDisplay_sequence();
					int new_d_s = Integer.parseInt(item_d_s_tf.getText());
					int temp = sql_func.get_max_sequence();
					sql_func.set_new_sequence(og_d_s, temp);
					sql_func.set_new_sequence(new_d_s, og_d_s);
					sql_func.set_new_sequence(temp, new_d_s);
					//
					String og_pic = item_pic_path_tf.getText();
					String[] og_pic_split = item_pic_path_tf.getText().split("/");
					int end_idx = og_pic.length() - og_pic_split[og_pic_split.length - 1].length() - 1;
					String dest_path = 
							item_pic_path_tf.getText().substring(0, end_idx) + "/img_" + item_name_tf.getText() + ".png";
					//
					sql_func.update_item_n_p(item_name_tf.getText(), Integer.parseInt(item_p_tf.getText()), dest_path
							,selected_item.getItem_name(), selected_item.getItem_price(), og_pic);
					try {
						Files.move(Paths.get(og_pic), Paths.get(dest_path),  java.nio.file.StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else if(Integer.parseInt(item_d_s_tf.getText()) < sql_func.get_max_sequence() &&
						 !item_pic_path_tf.getText().equals(selected_item.getItem_pic_path())) {
					int og_d_s = selected_item.getDisplay_sequence();
					int new_d_s = Integer.parseInt(item_d_s_tf.getText());
					int temp = sql_func.get_max_sequence();
					sql_func.set_new_sequence(og_d_s, temp);
					sql_func.set_new_sequence(new_d_s, og_d_s);
					sql_func.set_new_sequence(temp, new_d_s);
					util_item.delete_item_img(selected_item.getItem_pic_path());
					String og_pic = item_pic_path_tf.getText();
					String[] og_pic_split = item_pic_path_tf.getText().split("/");
					int end_idx = og_pic.length() - og_pic_split[og_pic_split.length - 1].length() - 1;
					String dest_path = 
							item_pic_path_tf.getText().substring(0, end_idx);
					String img_src = item_pic_path_tf.getText();
					String img_name = "img_" + item_name_tf.getText();
					util_item.copy_img_to_src(img_src, dest_path, img_name);
					sql_func.update_item_n_p_p(
							item_name_tf.getText(), Integer.parseInt(item_p_tf.getText()), dest_path + "/" + img_name + ".png"
									,selected_item.getItem_name(), selected_item.getItem_price(), selected_item.getItem_pic_path());
				}
				dispose();
				util_item.item_table_display(create_item.item_table);
				create_item.item_table.repaint();
				create_item.item_table.clearSelection();
				int[] row_idx_list = create_item.item_table.getSelectedRows();
				for(int row_idx:row_idx_list) {
					if(row_idx <= create_item.item_table.getRowCount() - 1) {
						create_item.item_table.addRowSelectionInterval(row_idx, row_idx);
					} else {
						continue;
					}
				}
			}
		});
	}
	
	public static void get_selected_item_info(Integer item_ds) {
		selected_item = new item_implement().read_item_update(item_ds);
	}
	
	public static void get_selected_item_info_null(String item_name, Integer item_price) {
		selected_item = new item_implement().read_item_update_null(item_name, item_price);
	}
}
