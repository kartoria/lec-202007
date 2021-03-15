package kr.or.ddit.commons.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

public class BaseController {
	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
	
	protected void printInfo(String varName, Object var) {
		LOGGER.info("☆★☆★☆ {} >>> {} ☆★☆★☆", varName, var);
	}
	
	protected void printError(String varName, Object var) {
		LOGGER.error("☆★☆★☆ {} >>> {} ☆★☆★☆", varName, var);
	}
	
	protected NotyMessageVO notyErrorMessage() {
		return NotyMessageVO.builder("오류가 발생했습니다...").layout(NotyLayout.topCenter).type(NotyType.error).build();
	}
	
	protected NotyMessageVO notyInsertSuccessMessage() {
		return NotyMessageVO.builder("등록이 완료되었습니다!").layout(NotyLayout.topCenter).type(NotyType.success).build();
	}
	
	protected NotyMessageVO notyUpdateSuccessMessage() {
		return NotyMessageVO.builder("수정이 완료되었습니다!").layout(NotyLayout.topCenter).type(NotyType.info).build();
	}
	protected NotyMessageVO notyDeleteSuccessMessage() {
		return NotyMessageVO.builder("삭제가 완료되었습니다!").layout(NotyLayout.topCenter).type(NotyType.info).build();
	}
	
	protected NotyMessageVO notyAccessDeniedMessage() {
		return NotyMessageVO.builder("권한이 없습니다!").layout(NotyLayout.topCenter).type(NotyType.warning).build();
	}
	
	protected NotyMessageVO notyScheduleDeniedMessage(String scheduleName) {
		return NotyMessageVO.builder(scheduleName +"기간이 아닙니다!").layout(NotyLayout.topCenter).type(NotyType.warning).build();
	}
	
	protected String pageTitleWithSubName(String subName ,String title) {
		return subName + " : " + title;
	}
}
