package com.chicken.review.login.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;






@Controller
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
   

	/*
	 * @RequestMapping(value="/login") public String login(Model model, String
	 * error, String logout) {
	 * 
	 * ServletRequestAttributes attr = (ServletRequestAttributes)
	 * RequestContextHolder.currentRequestAttributes(); HttpServletRequest request =
	 * attr.getRequest(); HttpServletResponse response = attr.getResponse();
	 * 
	 * Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication();
	 * 
	 * 
	 * if(authentication != null) {
	 * 
	 * if(!authentication.getPrincipal().equals("anonymousUser")) return
	 * "redirect:/"; }
	 * 
	 * 
	 * 
	 * return "login/login"; }
	 */

    
    @RequestMapping("/imgTest")
    public String listAttach(HttpSession session,HttpServletResponse response, HttpServletRequest request) throws Exception {
    	
    	String referer 	= request.getParameter("referer");
    	String model	= request.getParameter("model");
          
    	response.setContentType( "image/gif" );
    	
    	Cookie[] cookies = request.getCookies();
    	String cookieReferer = null; 
    	String cookieModel = null;
    	
    	if(cookies!= null && cookies.length > 0){ 
    		cookieReferer = getCookie(request, "referer");
    		cookieModel	= getCookie(request, "model");
    	}
    	
     
    	Cookie cookie1 = new Cookie("test", "lge");
    	cookie1.setMaxAge(60*60);
    	response.addCookie(cookie1);
//    	ResponseCookie cookie = ResponseCookie.from("sameSiteCookie", "sameSiteCookieValue").sameSite("None").secure(true).path("/").build();
//    	response.addHeader("Set-Cookie", cookie.toString());
    	
    	if (referer != null) {
//    		Cookie cookie2 = new Cookie("referer", referer);
//    		cookie2.setMaxAge(60*60);
//    		response.addCookie(cookie2);
    		
    		if (cookieReferer != null) {
    		
    			String[] referers = cookieReferer.split("$");
        		
        		if (!Arrays.asList(referers).contains(referer)) {
        			cookieReferer = cookieReferer + "$" + referer;
        		}
        		
    		}
    		else {
    			cookieReferer = referer;
    		}
    		    		
    		
    		ResponseCookie cookie2 = ResponseCookie.from("referer", cookieReferer).sameSite("None").secure(true).path("/").maxAge(60*60).build();
        	response.addHeader("Set-Cookie", cookie2.toString());
    	}
    	
    	if (model != null) {
//    		Cookie cookie3 = new Cookie("model", model);
//    		cookie3.setMaxAge(60*60);
//    		response.addCookie(cookie3);
    		
    		if (cookieModel != null) {
        		
    			String[] models = cookieModel.split("$");
        		
        		if (!Arrays.asList(models).contains(model)) {
        			cookieModel = cookieModel + "$" + model;
        		} 
        		
    		}
    		else {
    			cookieModel = model;
    		}
    		
    		
    		
    		ResponseCookie cookie3 = ResponseCookie.from("model", cookieModel).sameSite("None").secure(true).path("/").maxAge(60*60).build();
        	response.addHeader("Set-Cookie", cookie3.toString());
    	}    	
     
    	ServletOutputStream bout = response.getOutputStream();
     
    	String imgpath = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+ File.separator +"img" + File.separator + "img1.jpg";
     
//     String[] exts = {".bmp", ".jpg", ".gif", ".png", ".jpeg"};

//     String imgpath = "img1.jpg";
     
	     InputStream inputStream = null;
	     ServletOutputStream outputStream = null;
     
	     try {
	         inputStream = new URL("https://www.lge.co.kr/kr/main/main/assets/byme_main_pc.jpg").openStream();
	         outputStream = response.getOutputStream();
	         
	         int length;
	         byte[] buffer = new byte[12288]; // 12K
	         while ((length = inputStream.read(buffer)) != -1) {
	             outputStream.write(buffer, 0, length);
	         }
	         
	     } catch (Exception e) {
	         e.printStackTrace();
	         
	     } finally {
	         try {
	             if (outputStream != null) {
	                 outputStream.close();
	             }
	         } catch (NullPointerException e) {
	         } catch (Exception e) {
	         }
	         
	         try {
	             if (inputStream != null) {
	                 inputStream.close();
	             }
	         } catch (NullPointerException e) {
	         } catch (Exception e) {
	         }
	     }
     
//     FileInputStream f = new FileInputStream("D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\review\\WEB-INF\\classes\\static\\img");
		/*
		 * URL url = new
		 * URL("https://www.lge.co.kr/kr/main/main/assets/byme_main_pc.jpg");
		 * 
		 * 
		 * int length;
		 * 
		 * byte[] buffer = new byte[10];
		 * 
		 * while ( ( length = f.read( buffer ) ) != -1 ) {
		 * 
		 * bout.write( buffer, 0, length );
		 * 
		 * }
		 */
     
	     return null;
     
    }
    
    /** * getCookie * * @return cookie * @throws Exception */ 
    public static String getCookie(HttpServletRequest request, String key) throws Exception { 
    	
    	Cookie[] cookies = request.getCookies(); 
    	
    	if(key == null) return null; 
    	
    	String value = ""; 
    	
    	if(cookies != null){ 
    		for(int i=0;i<cookies.length;i++){ 
    			if(key.equals(cookies[i].getName())){ 
    				value = java.net.URLDecoder.decode(cookies[i].getValue(), "UTF-8"); 
    				break; 
    			} 
    		} 
    	} 
    	
    	return value; 
    }
    
   
}
