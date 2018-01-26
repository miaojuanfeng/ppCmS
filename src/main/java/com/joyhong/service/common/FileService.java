package com.joyhong.service.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 重命名文件
	 * @param path
	 * @param oldname
	 * @param newname
	 * @return
	 */
	public int renameFile(String oldPath, String newPath, String oldname, String newname){
		try{
	        File oldfile = new File(oldPath + "/" + oldname); 
	        File newfile = new File(newPath + "/" + newname); 
	        if( !oldfile.exists() ){
	            return ConstantService.statusCode_901;
	        }
	        if( newfile.exists() ){
	            return ConstantService.statusCode_902; 
	        }else{ 
	            oldfile.renameTo(newfile);
	            Runtime.getRuntime().exec("chmod 644 " + newPath + "/" + newname);
	        }
		}catch(Exception e){
			logger.info(e.getMessage());
		}
        return 0;
    }
	
	/**
	 * facebook与twitter同步url图片视频到本地服务器
	 * @param url
	 * @param filePath
	 * @param method
	 */
	public void saveUrlAs(String url, String filePath, String fileName){   
	     FileOutputStream fileOut = null;  
	     HttpURLConnection conn = null;  
	     InputStream inputStream = null;  
	     try {
	    	 if (!filePath.endsWith("/")) {  
	             filePath += "/";
	         }
	    	 File file = new File(filePath);
	    	 if(!file.exists()){
	    		 file .mkdir();
	    	 }
	    	 
	         URL httpUrl=new URL(url);  
	         conn=(HttpURLConnection) httpUrl.openConnection();  
	         conn.setRequestMethod("GET");  
	         conn.setDoInput(true);    
	         conn.setDoOutput(true);   
	         conn.setUseCaches(false);  
	         conn.connect();  
	         inputStream=conn.getInputStream();  
	         BufferedInputStream bis = new BufferedInputStream(inputStream);
	         
	         fileOut = new FileOutputStream(filePath+fileName);  
	         BufferedOutputStream bos = new BufferedOutputStream(fileOut);  
	           
	         byte[] buf = new byte[4096];
	         int length = bis.read(buf);
	         while(length != -1)  
	         {  
	             bos.write(buf, 0, length);  
	             length = bis.read(buf);  
	         }
	         bos.close();
	         bis.close();
	         conn.disconnect();
	    } catch (Exception e)  {
	    	logger.info(e.getMessage());
	    }
	 }
	
	/**
	 * 保存facebook和twitter监听到的消息到文件中
	 * @param fileName
	 * @param postdata
	 */
	public void savePostData(String fileName, String postdata){
		FileWriter fw = null;
	    try {
	    	//如果文件存在，则追加内容；如果文件不存在，则创建文件
	    	File f=new File(fileName);
	      	fw = new FileWriter(f, true);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    PrintWriter pw = new PrintWriter(fw);
	    pw.println(postdata);
	    pw.flush();
	    try {
			fw.flush();
			pw.close();
			fw.close();
	    } catch (IOException e) {
	    	logger.info(e.getMessage());
	    }
	}
}
