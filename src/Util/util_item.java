package Util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import Dao.item.item_implement;
import Model.item_management;



public class util_item {
	public static void main(String[] args) {
		
	}
	
	/*
	public static void create_img_folder() {
		Path img_folder_path = Paths.get("./item_img");
		if(!Files.exists(img_folder_path)) {
			File folder_path = new File("./item_img");
			folder_path.mkdirs();
		}	
	}
	*/
	
	public static void item_table_display(JTable item_table) {
		Object[] col_name = new Object[] {"商品序號", "商品名稱", "商品價格", "商品圖片"};
		Object[][] item_list_display = null;
		List<item_management> item_list = new item_implement().read_item();
		if(item_list.size() != 0) {
			item_list_display = new Object[item_list.size()][4];
			for(int i = 0; i < item_list.size();i++) {
				if(item_list.get(i).getItem_pic_path() != null && item_list.get(i).getDisplay_sequence() != 0) {
					item_list_display[i] = new Object[] {item_list.get(i).getDisplay_sequence(),
														item_list.get(i).getItem_name(), 
														item_list.get(i).getItem_price(),
														"V"};
				}else {
					item_list_display[i] = new Object[] {"未上架",
														item_list.get(i).getItem_name(), 
														item_list.get(i).getItem_price(),
														"V"};	
				}
			}
		} else {
			item_list_display = new Object[0][4];
		}
		
		DefaultTableModel dtm = new DefaultTableModel(item_list_display, col_name);
		item_table.setModel(dtm);
	}
	
	public class ImageFilter extends FileFilter{
		public final static String JPEG = "jpeg";
		public final static String JPG = "jpg";
		public final static String GIF = "gif";
		public final static String TIFF = "tiff";
		public final static String TIF = "tif";
		public final static String PNG = "png";

		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if(f.isDirectory()) {
				return true;
			}
			
			String ext = null;
			int i = f.getName().lastIndexOf('.');
			ext = f.getName().substring(i + 1).toLowerCase();
			
			if(ext != null) {
				if(ext.equals(JPEG) ||
					ext.equals(JPG)||
					ext.equals(GIF)||
					ext.equals(TIFF)||
					ext.equals(TIF)||
					ext.equals(PNG)) {
					return true;
				} else {
					return false;
				}
				
			}
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "所有圖片";
		}
		
	}
	
	public class FolderFilter extends FileFilter{
		@Override
		public boolean accept(File f) {
			return f.isDirectory();
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "所有資料夾";
		}
		
	}
	
	public class DocFilter extends FileFilter{
		public final static String TXT = "txt";
		public final static String CSV = "csv";
		public final static String XLS = "xls";
		public final static String XLSX = "xlsx";

		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if(f.isDirectory()) {
				return true;
			}
			
			String ext = null;
			int i = f.getName().lastIndexOf('.');
			ext = f.getName().substring(i + 1).toLowerCase();
			
			if(ext != null) {
				if(ext.equals(TXT) ||
					ext.equals(CSV)||
					ext.equals(XLS)||
					ext.equals(XLSX)) {
					return true;
				} else {
					return false;
				}
				
			}
			return false;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "所有可匯入格式";
		}
		
	}
	
	public static void copy_img_to_src(String img_path, String dest_path, String img_name) {
		File item_pic_og = new File(img_path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(item_pic_og);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int op_width = (img.getWidth() * 250) / img.getHeight();
		int op_height = 250;
		BufferedImage empty_img = new BufferedImage(op_width, op_height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D graphics = empty_img.createGraphics();
		graphics.drawImage(img, 0, 0, op_width, op_height, null);
		graphics.dispose();
		File item_pic = new File(dest_path + "/" + img_name + ".png");
		try {
			ImageIO.write(empty_img, "png", item_pic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Object[]> read_txt_csv(String doc_path){
		FileReader doc = null;
		List<Object[]> item_list = null;
		try {
			doc = new FileReader(doc_path);
			BufferedReader doc_reader = new BufferedReader(doc);
			String row_data = doc_reader.readLine();
			item_list =  new ArrayList<Object []>();
			
			while(row_data != null) {
				String[] row_split = row_data.split(",");
				Object[] item_data = null;
				try {
					item_data = new Object[] {row_split[0], Integer.parseInt(row_split[1]), row_split[2]};
				} catch(NumberFormatException e) {
					item_data = new Object[] {row_split[0], row_split[1], row_split[2]};
				}
				item_list.add(item_data);
				row_data = doc_reader.readLine();
			}
			doc_reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item_list;
	}
	
	public static boolean item_list_p_checker(List<Object[]> item_list) {
		if(item_list == null) {
			return false;
		}
		for(Object[] item_data:item_list){
			 if(!item_data[1].getClass().getName().equals("java.lang.Integer")) {
				 return false;
			 }
		}
		return true;
		
	}
	
	public static void delete_item_img(String img_path) {
		File img_2bd = new File(img_path);
		img_2bd.delete();
	}
	
	public static int func_check(JTable item_table) {
		int func_check = 0;
		int launched = 0;
		int stopped = 0;
		int[] row_idx_list = item_table.getSelectedRows();
		for(int row_idx: row_idx_list) {
			if(item_table.getValueAt(row_idx, 0) instanceof Integer) {
				launched++;
			} else {
				stopped++;
			}
		}
		if(launched == 0 && stopped == 0) {
			func_check = 0;
		} else if(launched == 1 && stopped == 0) {
			func_check = 1;
		} else if(launched == 0 && stopped == 1) {
			func_check = 2;
		} else if(launched > 0 && stopped > 0) {
			func_check = 3;
		} else if(launched > 1 && stopped == 0) {
			func_check = 4;
		} else if(launched == 0 && stopped > 1) {
			func_check = 5;
		}
		
		return func_check;
	}
	
	public static boolean img_checker(String img_path) {
		Boolean isImg = false;
		String ext = img_path.split("\\.")[img_path.split("\\.").length - 1];
		if(!ext.equals("jpg") && !ext.equals("jpeg") && ext.equals("tiff") &&
							!ext.equals("tif") && !ext.equals("gif") && !ext.equals("png")) {
			return isImg;
		} else {
			isImg = true;
			return isImg;
		}
	}
	public static boolean ds_checker(JTextField item_ds) {
		boolean isDigit = false;
		if(item_ds.getText().equals("未上架")) {
			isDigit = true;
		} else if(!item_ds.getText().chars().allMatch(Character::isDigit)) {
			isDigit = false;
		} else if(Integer.parseInt(item_ds.getText()) > new item_implement().get_max_sequence() - 1) {
			isDigit =  false;
		} else {
			isDigit = true;
		}
		return isDigit;
	}
	public static boolean p_checker(JTextField item_ds) {
		boolean isDigit = false;
		if(!item_ds.getText().chars().allMatch(Character::isDigit)) {
			isDigit = false;
		} else {
			isDigit = true;
		}
		return isDigit;
	}
	
}
