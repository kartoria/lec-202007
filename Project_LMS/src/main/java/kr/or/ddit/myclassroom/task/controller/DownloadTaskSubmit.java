package kr.or.ddit.myclassroom.task.controller;

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
import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;

public class DownloadTaskSubmit extends AbstractView{

	@Value("#{appInfo.boardFiles}")
	private File saveFolder;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// AttachVO 가져오기
		TaskSubmitVO taskSb = (TaskSubmitVO) model.get("taskSb");
		String browser = request.getHeader("User-Agent");
		// 저장이름, 파일 이름 가져오기
		String savename = taskSb.getAttSavename();
		String filename = taskSb.getAttFilename();
		
		// 브라우저마다 처리하는 방식이 다를 수 있음
		if(Browser.TRIDENT.equals(browser)) {
			// utf-8로 인코딩해주기
			filename = URLEncoder.encode(filename, "UTF-8");
		}else {
			// 비 ms 계열
			byte[] bytes = filename.getBytes();
			// 무조건 byte로 쪼갠 후 무작위로 더해서 문자열 하나로 만든다.
			filename = new String(bytes, "ISO-8859-1");
		}
		// header에 저장
		response.setHeader("Content-Disposition", "attachment;filename=\"" +filename + "\"");
		File saveFile = new File(saveFolder, savename);
		// 이진데이터 이니까 브라우저는 실행하지 않고 다운로드로 진행
		response.setContentType("application/octet-stream");
		try(
			OutputStream os = response.getOutputStream();	
		){
			FileUtils.copyFile(saveFile, os);
		}
	}
	
}
