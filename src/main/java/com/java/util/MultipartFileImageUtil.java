package com.java.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileImageUtil {

	/**
	 * 文件上传(1,2,position,refaultImgName) position:上传图片到项目中路径位置 refaultImgName：默认图片名
	 * 返回图片的路径
	 * 
	 * @param file
	 * @param request
	 * @param position
	 * @param refaultImgName
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String File(MultipartFile file, HttpServletRequest request, String position, String refaultImgName)
			throws IllegalStateException, IOException {

		if (!file.isEmpty()) {
			String savePath = request.getSession().getServletContext().getRealPath(position);
//			System.out.println("上传图片到项目中路径" + savePath);
			/* 获得文件名字 */
			String fileName = file.getOriginalFilename();
			System.out.println("文件名" + fileName);
			File dir = new File(savePath, fileName);
			if (!dir.exists()) {// 文件是否存在
				dir.mkdirs();// 创建此抽象路径名指定的目录，包括创建必需但不存在的父目录。
			}
			file.transferTo(dir);// MultipartFile自带的解析方法，存入位置
			if (fileName.indexOf(".") > -1) {
				String[] buff = fileName.split("\\.");
				System.out.println(fileName);
				System.out.println(buff[0]);
				System.out.println(buff[1]);
				String format = buff[1].toLowerCase();// 获得文件格式
				if (format.equals("jpg") || format.equals("jpeg") || format.equals("gif") || format.equals("png")
						|| format.equals("bmp")) {// 判断是否符合图片格式
					return position + "/" + fileName;// 存入数据库的位置
				}
			}
		}
		return position + "/" + refaultImgName;// 默认存入数据库位置
	}

}
