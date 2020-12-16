package kr.or.ddit.explorer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/server/fileCommand.do")
public class FileCommandProcessServlet extends HttpServlet{
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@FunctionalInterface
	public static interface FileCommandProcessor{
		public boolean process(File src, File destFolder) throws IOException;
	}
	public static enum FileCommand{
		COPY((src, destFolder)->{
			FileUtils.copyFileToDirectory(src, destFolder);
			return true;
		}),
		DELETE((src, destFolder)->{
			return FileUtils.deleteQuietly(src);
		}),
		MOVE((src, destFolder)->{
			boolean result = false;
			if(COPY.fileCommand(src, destFolder)){
				result = DELETE.fileCommand(src, destFolder);
			}
			return result;
		});
		
		private FileCommandProcessor processor;
		FileCommand(FileCommandProcessor processor){
			this.processor = processor;
		}
		public boolean fileCommand(File src, File destFolder) throws IOException {
			return processor.process(src, destFolder);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comParam = (String) req.getParameter("command");
		String srcParam = (String) req.getParameter("srcFile");
		String destParam = (String) req.getParameter("destFolder");
		int status = 200;
		status = validate(comParam, srcParam, destParam);
		
		if(status != 200) {
			resp.sendError(status);
			return;
		}
		File src = new File(application.getRealPath(srcParam));
		File destFolder = null;
		if(!comParam.equals("DELETE")) {
			destFolder = new File(application.getRealPath(destParam));
		}
		FileCommand command = FileCommand.valueOf(comParam);
		boolean result = command.fileCommand(src, destFolder);
		String msg = result ? "SUCCESS" : "FAIL";
		resp.setContentType("text/plain;charset=UTF-8");
		try(PrintWriter out = resp.getWriter()){
			out.print(msg);
		}
		
	}

	private int validate(String cmdParam, String srcParam, String destParam) {
		int status = 200;
		FileCommand command = null;
		try {
			command = FileCommand.valueOf(cmdParam);
		}catch(Exception e) {
			status = 400;
		}
		if(status == 200) {
			File srcFile = new File(application.getRealPath(srcParam));
			if(!srcFile.exists() || !srcFile.isFile()) status = 400;
			if(StringUtils.isBlank(srcParam)) status = 400;
			switch(command) {
			case COPY:
			case MOVE:
				if(StringUtils.isBlank(destParam)) status = 400;
				else {
					File destFolder = new File(application.getRealPath(destParam));
					if(!destFolder.exists() || !destFolder.isDirectory()) status = 400;
				}
				break;
			}
		}
		return status;
	}

}
