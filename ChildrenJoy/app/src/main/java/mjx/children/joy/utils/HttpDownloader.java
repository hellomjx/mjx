package mjx.children.joy.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressLint("NewApi")
public class HttpDownloader {
//	private IDownMusicListener iDownMusicListener;
	private URL url = null;
	/**
	 * 根据URL下载文本文件，返回值是文件内容
	 * @param urlStr
	 * @return text
	 */
	public String download(String urlStr) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
			buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = buffer.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * 返回值 -1：表示下载文件出错，0：表示文件下载成功，1：表示文件已经存在
	 * @param urlStr
	 * @param path
	 * @param fileName
	 * @return
	 */
//	public int downFile(String urlStr, String path, String fileName) {
//		InputStream inputStream = null;
//		try {
//			FileUtils fileUtils = new FileUtils();
////			if (fileUtils.isFileExist(path + fileName)) {
////				return 1;
////			}
//			inputStream = getInputStreamFromUrl(urlStr);
//			File resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);
//			if (resultFile == null) {
//				return -1;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				inputStream.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return 0;
//	}








	/**
	 * 返回值 -1：表示下载文件出错，0：表示文件下载成功，1：表示文件已经存在
	 * @param urlStr
	 * @param path
	 * @param fileName
	 * @return
	 */
	public void downFile(String urlStr, String path, String fileName,IDownMusicListener iDownMusicListener) {
		String readMusicPath = path +fileName;
		InputStream inputStream = null;
		String recodeCode = "0";
		try {
			FileUtils fileUtils = new FileUtils();
			inputStream = getInputStreamFromUrl(urlStr);
//			File resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);
			File resultFile = fileUtils.writeForBundle(path+fileName,inputStream);
			if (resultFile == null) {
				recodeCode = "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			recodeCode = "-1";
		} finally {
			try {
				inputStream.close();
				if("0".equals(recodeCode)){
					if(iDownMusicListener != null){
						iDownMusicListener.downMusicSuccess(readMusicPath);
					}
				}else if("-1".equals(recodeCode)){
					if(iDownMusicListener != null){
						iDownMusicListener.downMusicError("下载音乐失败");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}




	public InputStream getInputStreamFromUrl(String urlStr)
			throws MalformedURLException, IOException {
		url = new URL(urlStr);
		HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
		InputStream inputStream = urlConn.getInputStream();
		return inputStream;
	}
	
	public static void initStrictMode() {
		System.out.println("Build.VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
		if (Build.VERSION.SDK_INT > 10) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork() // 这里可以替换为detectAll()
																			// 就包括了磁盘读写和网络I/O
					.penaltyLog() // 打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
					.build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
					.penaltyLog() // 打印logcat
					.penaltyDeath().build());
		}
	}

//	public void setIDownMusicListener(IDownMusicListener iDownMusicListener){
//		this.iDownMusicListener = iDownMusicListener;
//	}

	public interface IDownMusicListener{
		void downMusicSuccess(String musicPath);
		void downMusicError(String error);
	}
}
