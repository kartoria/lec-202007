package kr.or.ddit.board.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.inject.Inject;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.CustomWebAppConfiguration;



@RunWith(SpringRunner.class)
@CustomWebAppConfiguration
public class BoardRetrieveControllerTest {
	@Inject
	WebApplicationContext context;
	MockMvc mockMvc;

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/board/boardList.do"))
				.andExpect(model().attributeExists("pagingVO"))
				.andExpect(view().name("board/boardList"))
				.andDo(log())
				.andReturn();
	}

	@Test
	public void testBoardView() throws Exception {
		mockMvc.perform(get("/board/81"))
				.andExpect(model().attributeExists("searchVO"))
				.andExpect(view().name("board/81"))
				.andDo(log())
				.andReturn();
	}

	@Test
	public void testRecommend() {
		fail("Not yet implemented");
	}

}
