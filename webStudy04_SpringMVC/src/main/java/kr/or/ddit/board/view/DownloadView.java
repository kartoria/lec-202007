package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.enumpkg.Browser;
import kr.or.ddit.vo.AttatchVO;

public class DownloadView extends AbstractView {

	@Value("#{appInfo.boardFiles}")
	private File saveFolder;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		AttatchVO attatch = (AttatchVO) model.get("attatch");
		String browser = req.getHeader("User-Agent");
		String savename = attatch.getAtt_savename();
		String filename = attatch.getAtt_filename();
		if(Browser.TRIDENT.equals(browser)) {
			filename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
		}else {
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
			System.out.println("===============>"+filename);
		}
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		File saveFile = new File(saveFolder, savename);
		resp.setContentType("application/octet-stream");
		try(
			OutputStream os = resp.getOutputStream();	
		){
			FileUtils.copyFile(saveFile, os);
		}
	}

}
