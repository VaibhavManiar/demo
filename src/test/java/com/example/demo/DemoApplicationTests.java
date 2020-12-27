package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	DemoController controller;

	@Test
	void contextLoads() {
	}

	@Test
	void helloAsyncTest() {

	}

	public static void handleASyncResponse(MockMvc mockMvc, String requestBody, String url, ResultMatcher resultMatcher) {
		try {
			MvcResult mvcResult = mockMvc
					.perform(
							post(url)
									.contentType(MediaType.APPLICATION_JSON)
									.content(requestBody))
					.andExpect(request().asyncStarted())
					.andDo(MockMvcResultHandlers.log())
					.andReturn();
			mockMvc.perform(asyncDispatch(mvcResult))
					.andDo(rh -> {
						MockHttpServletResponse response = rh.getResponse();
						System.out.printf("Response : %s%n", response.getContentAsString());
					})
					.andExpect(resultMatcher);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
