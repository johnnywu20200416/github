package tw.leonchen.jspproject.uplaod;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig(location="c:/temp/files")
@WebServlet("/TestUploadFileServletEx1.action")
public class TestUploadFileServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		processSingleFileUpload(request, response);
		processMultipleFileUpload(request, response);
	}

	private void processMultipleFileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Collection<Part> parts = request.getParts();
		for(Part part: parts) { //每一個 Part 型別的  part: 在 parts裡面
			//for-loop 裡面的動作同 單一上傳
			String headerValue = part.getHeader("Content-Disposition");
			
			String fileName = headerValue.substring(headerValue.indexOf("filename=\"")+10, headerValue.lastIndexOf("\""));		
			
			fileName = getParseFileName(fileName);
			
			out.write("headerValue:" + headerValue + "<br/>");
			out.write("fileName:" + fileName + "<br/>");
			
			if(!"".equals(fileName) && part.getContentType()!=null) {
				part.write(fileName);				
			}
		}
		
		out.write("Multiple Files Upload Success !!<br/>");
		
		out.close();
	}

	private void processSingleFileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		
		Part part = request.getPart("multimedia");
		String headerValue = part.getHeader("Content-Disposition");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
																//因為 filename=" 共10個字元
		String fileName = headerValue.substring(headerValue.indexOf("filename=\"")+10, headerValue.lastIndexOf("\""));
		
		fileName = getParseFileName(fileName);
		
		out.write("headerValue:" + headerValue + "<br/>");
		out.write("fileName:" + fileName + "<br/>");  //call getParseFileName 之前：  fileName = C:\temp\test\image01.jpg
													//我們要的檔名 只有 image01.jpg 這個
													//chrome可以正常抓取(image01.jpg)，但是IE會抓到整個路徑(C:\temp\test\image01.jpg)
													//所以，用 function：getParseFileName(fileName) 處理
		
		if(!"".equals(fileName) && part.getContentType()!=null) { //! "".equals(fileName)：fileName若不是 ""，表示檔案名存在
																	//part.getContentType()!=null
			part.write(fileName); //將檔案存入電腦
			out.write("File Upload Success !!<br/>");
		}
		
		out.close();
	}

	private String getParseFileName(String pathName) {
		LinkedList<String> path = new LinkedList<String>(); //宣告動態陣列，型別為String，用來儲存分拆後的資料
		StringTokenizer st1 = new StringTokenizer(pathName, "\\"); //要用 "\" 分拆pathName，注意 \\ 要用兩個
																	//StringTokenizer 是 方法
		
		while(st1.hasMoreTokens()) { //如果還有東西
			path.add(st1.nextToken()); //把它用 add 加入 動態陣列 (path)裡面
		}
		
		return path.getLast(); //我們要的檔名 ： image01.jpg 會在路徑的最後面，也就是 path裡面的最後一個位置 
	}

	
}
