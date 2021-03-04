package com.yestae.user.manage.modular.privilege.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yestae.user.common.exception.GunsException;
import com.yestae.user.common.exception.GunsExceptionEnum;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.util.FileUtil;

@Controller
@RequestMapping("/imageServer")
public class ImageServerController {
	
	@Resource
	UcConstant ucConstant;
	
	@RequestMapping("/**")
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
		String path = ucConstant.getImageDir() + request.getRequestURI().substring(request.getRequestURI().indexOf("/imageServer") + 12);
        
		OutputStream out = null;
		try {
			byte[] bytes = FileUtil.toByteArray(path);
			if(bytes != null){
				
				out = response.getOutputStream();
				out.write(bytes);
				out.flush();
			}
		} catch (IOException e) {
			throw new GunsException(GunsExceptionEnum.FILE_NOT_FOUND);
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					throw new GunsException(GunsExceptionEnum.FILE_NOT_FOUND);
				}
			}
		}
    }
	
}
