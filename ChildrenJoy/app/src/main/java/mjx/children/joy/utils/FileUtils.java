package mjx.children.joy.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
	private String SDPATH;
	
	public String getSDPATH() {
		return SDPATH;
	}
	
	public FileUtils() {
		SDPATH = Environment.getExternalStorageDirectory() + File.separator;
	}
	
	/**
	 * 在SD卡上创建文件
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public File createSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		file.createNewFile();
		return file;
	}
	
	/**
	 * 在SD卡上创建目录
	 * @param dirName
	 * @return
	 * @throws IOException
	 */
	public File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		dir.mkdirs();
		return dir;
	}
	
	/**
	 * 判断SD卡上文件是否存在
	 */
	public boolean isFileExist(String fileName){
		File file = new File(SDPATH + fileName);
		return file.exists();
	}
	
	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String path, String fileName, InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			createSDDir(path);
			file = createSDFile(path + fileName);
//			file = new File(path+fileName);

			output = new FileOutputStream(file);
			byte[] buffer = new byte[4 * 1024];
			int readsize = 0;
			while((readsize = input.read(buffer)) > 0) {
				output.write(buffer, 0, readsize);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}


	// 在sdcard卡上创建文件
	public File createFilePath(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		file.createNewFile();
		return file;
	}






	/**
	 * 将一个inputstream里面的数据写入SD卡中 第一个参数为目录名 第二个参数为文件名
	 */
	public File write2SDFromInput(String path, InputStream inputstream) {
		File file = null;
		OutputStream output = null;
		try {
			file = createSDFile(path);
			output = new FileOutputStream(file);
// 4k为单位，每4K写一次
			byte buffer[] = new byte[4 * 1024];
			int temp = 0;
			while ((temp = inputstream.read(buffer)) != -1) {
// 获取指定信,防止写入没用的信息
				output.write(buffer, 0, temp);
			}
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		return file;
	}


	/**
	 * 把下载的bundle压缩包写入
	 */
	public File writeForBundle(String path, InputStream inputstream){
		File file = null;
		OutputStream output = null;
		try {
//            file = createSDFile(path);
			file = new File(path);

			output = new FileOutputStream(file);
// 4k为单位，每4K写一次
			byte buffer[] = new byte[4 * 1024];
			int temp = 0;
			while ((temp = inputstream.read(buffer)) != -1) {
// 获取指定信,防止写入没用的信息
				output.write(buffer, 0, temp);
			}
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
}
