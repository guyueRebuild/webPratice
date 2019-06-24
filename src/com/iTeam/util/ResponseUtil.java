package com.iTeam.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author XieZhiHao
 *
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response,Object obj) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(obj.toString());
		pw.flush();
		pw.close();
	}
}
