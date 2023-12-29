package com.bitboxer2.SpringBootAPI;

import com.bitboxer2.SpringBootAPI.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ProductController productController;

	@Test
	void notAuthenticatedUserCantGetProducts() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/get")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is3xxRedirection());
	}
	@Test
	void desactiveProduct() throws Exception {
		String username = "prueba3@gmail.com";
		String password = "123456";
		String body = "{\"username\":\"" + username + "\", \"password\":\""
				+ password + "\"}";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON)
						.header("Accept", "*/*")
						.header("Accept-Encoding", "gzip, deflate, br")
						.header("Connection", "keep-alive"))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		response = response.replace("{\"token\":\"","");
		String token = response.replace("\"}", "");
		String id = "1";
		String description = "prueba2";
		String bodyPost = "{\"productId\":\"" + id + "\", \"description\":\""
				+ description + "\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/products/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(bodyPost)
						.header("Authorization", "Bearer " +token))
				.andExpect(status().isOk()).andReturn();
		String reason = "Es un Test";
		mockMvc.perform(MockMvcRequestBuilders.put("/products/desactivate/"+id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(reason)
						.header("Authorization", "Bearer " +token))
				.andExpect(status().isOk()).andReturn();
	}
	@Test
	void loginExistentUser() throws Exception {
		String username = "prueba2@gmail.com";
		String password = "123456";
		String body = "{\"username\":\"" + username + "\", \"password\":\""
				+ password + "\"}";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON)
						.header("Accept", "*/*")
						.header("Accept-Encoding", "gzip, deflate, br")
						.header("Connection", "keep-alive"))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		response = response.replace("{\"token\":\"","");
		String token = response.replace("\"}", "");
		mockMvc.perform(MockMvcRequestBuilders.get("/products/get")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " +token))
				.andExpect(status().isOk());
	}
	@Test
	void newUserRegisterCanGetTokenAndAuthentication() throws Exception {
		String username = "prueba2@gmail.com";
		String password = "123456";
		String body = "{\"username\":\"" + username + "\", \"password\":\""
				+ password + "\"}";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON)
						.header("Accept", "*/*")
						.header("Accept-Encoding", "gzip, deflate, br")
						.header("Connection", "keep-alive"))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		response = response.replace("{\"token\":\"","");
		String token = response.replace("\"}", "");
		mockMvc.perform(MockMvcRequestBuilders.get("/products/get")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " +token))
				.andExpect(status().isOk());
	}
	@Test
	void createProducts() throws Exception {
		String username = "prueba@gmail.com";
		String password = "123456";
		String body = "{\"username\":\"" + username + "\", \"password\":\""
				+ password + "\"}";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON)
						.header("Accept", "*/*")
						.header("Accept-Encoding", "gzip, deflate, br")
						.header("Connection", "keep-alive"))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		response = response.replace("{\"token\":\"","");
		String token = response.replace("\"}", "");
		String id = "0";
		String description = "prueba";
		String bodyPost = "{\"productId\":\"" + id + "\", \"description\":\""
				+ description + "\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/products/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(bodyPost)
						.header("Authorization", "Bearer " +token))
				.andExpect(status().isOk()).andReturn();
		MvcResult result2 = mockMvc.perform(MockMvcRequestBuilders.get("/products/get")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " +token))
				.andExpect(status().isOk()).andReturn();
		String response2 = result2.getResponse().getContentAsString();
		assert  response2.length() > 2;
	}

}
