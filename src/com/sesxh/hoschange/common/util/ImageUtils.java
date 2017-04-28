package com.sesxh.hoschange.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class ImageUtils {
	/**
	 * base64转成流并存入本地
	* @Title: GenerateImage
	* @author Ning 
	* @data:2017年3月25日
	* @return:boolean
	* @throws:
	 */
	public static boolean GenerateImage(String base64Str, String mainFilePath, String filePath, String fileName,String fileType) { // 对字节数组字符串进行Base64解码并生成图片
		if (base64Str == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		File fp = new File(mainFilePath + filePath);
		// 创建目录
		if (!fp.exists()) {
			fp.mkdirs();// 目录不存在的情况下，创建目录。
		}
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(base64Str);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = mainFilePath + filePath + "/" + fileName+"."+fileType;// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 读取文件并转成base64
	* @Title: readFileByBytesToBase64
	* @author Ning 
	* @data:2017年3月25日
	* @return:String
	* @throws:
	 */
	public static String readFileByBytesToBase64(String filePath,String imageType) {
		InputStream in = null;
		byte[] tempbytes = null;
		BASE64Encoder encoder = null;
		String base64Str=null;
		try {
			encoder = new BASE64Encoder();
			in = new FileInputStream(filePath+"."+imageType);
			int size = in.available();
			tempbytes = new byte[size];
			in.read(tempbytes);
			base64Str = encoder.encode(tempbytes);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return base64Str;
	}
}
