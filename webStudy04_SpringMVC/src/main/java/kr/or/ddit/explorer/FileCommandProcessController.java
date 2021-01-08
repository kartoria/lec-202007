package kr.or.ddit.explorer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FileCommandProcessController{
	
	@FunctionalInterface
	public static interface FileCommandProcessor{
		public boolean process(File src, File destFoler) throws IOException;
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
			if(COPY.fileCommand(src, destFolder)) {
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
	
	@RequestMapping(value="/server/fileCommand.do", method=RequestMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmdParam = req.getParameter("command");
		String srcParam = req.getParameter("srcFile");
		String destParam = req.getParameter("destFolder");
		int status = 200;
		
		ServletContext application = req.getServletContext();
		
		status = validate(cmdParam, srcParam, destParam, application);
		
		if(status!=200) {
			resp.sendError(status);
			return null;
		}
		
		File src = new File(application.getRealPath(srcParam));
		File destFolder = new File(application.getRealPath(destParam));
		FileCommand command = FileCommand.valueOf(cmdParam);
		boolean result = command.fileCommand(src, destFolder); 
		
		String message = result?"SUCCESS":"FAIL";
		
		resp.setContentType("text/plain;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			out.print(message);
		}
		return null;
	}

	private int validate(String cmdParam, String srcParam, String destParam, ServletContext application ) {
		int status = 200;
		FileCommand command = null;
		try {
			command = FileCommand.valueOf(cmdParam);
		}catch (Exception e) {
			status = 400;
		}
		
		if(status==200) {
			if(StringUtils.isBlank(srcParam)) {
				status = 400;
			}else {
				File srcFile = new File(application.getRealPath(srcParam));
				if(!srcFile.exists() || !srcFile.isFile()) status = 400;  
			}
			switch (command) {
			case COPY:
			case MOVE:			
				if(StringUtils.isBlank(destParam)) {
					status = 400;
				}else {
					File destFolder = new File(application.getRealPath(destParam));
					if(!destFolder.exists() || !destFolder.isDirectory()) status = 400;
				}
				break;
			}
		}
		
		return status;
	}
}












