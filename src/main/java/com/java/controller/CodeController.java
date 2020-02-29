package com.java.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.util.CpachaUtil;

/**
 * 系统操作类控制器
 * 
 * @author llq
 *
 */
@Controller
@RequestMapping("/system")
public class CodeController {

	/**
	 * 本系统所有的验证码均采用此方法
	 * 
	 * @param vcodeLen
	 * @param width
	 * @param height
	 * @param          cpachaType:用来区别验证码的类型，传入字符串
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/get_cpacha", method = RequestMethod.GET)
	public void generateCpacha(@RequestParam(name = "vl", required = false, defaultValue = "4") Integer vcodeLen,
			@RequestParam(name = "w", required = false, defaultValue = "160") Integer width,
			@RequestParam(name = "h", required = false, defaultValue = "340") Integer height,
			@RequestParam(name = "type", required = true, defaultValue = "loginCpacha") String cpachaType,
			HttpServletRequest request, HttpServletResponse response) {
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute(cpachaType, generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
