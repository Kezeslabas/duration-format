package com.example.duration_format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class DurationFormatControllerTestBase {

	@Autowired
	private MockMvc mockMvc;

	public abstract String requestMapping();

    public ResultActions doGet(String url, Map<String, String> params) throws Exception {
		return this.mockMvc.perform(MockMvcRequestBuilders.get(requestMapping() + "/" + url)
				.params(MultiValueMap.fromSingleValue(params)));
	}
}
