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
						.contentType(MediaType.APPLICATION_JSON))
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
		String username = "prueba@gmail.com";
		String password = "1";
		String body = "{\"username\":\"" + username + "\", \"password\":\""
				+ password + "\"}";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON))
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
						.contentType(MediaType.APPLICATION_JSON))
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
		String username = "prueba6@gmail.com";
		String password = "123456";
		String body = "{\"username\":\"" + username + "\", \"password\":\""
				+ password + "\"}";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON))
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
	@Test
	void userCanNOTDeleteProduct() throws Exception {
		String username = "prueba7@gmail.com";
		String password = "123456";
		String body = "{\"username\":\"" + username + "\", \"password\":\""
				+ password + "\"}";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String response = result.getResponse().getContentAsString();
		response = response.replace("{\"token\":\"","");

		String token = response.replace("\"}", "");

		String id = "5";
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
		mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete/"+ id)
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " +token))
				.andExpect(status().is4xxClientError());
	}
	@Test
	void userCannotCreateOrUpdateOrDeleteUsers() throws Exception {
		String username = "prueba8@gmail.com";
		String password = "123456";
		String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
		MvcResult registerResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String registerResponse = registerResult.getResponse().getContentAsString();
		String token = registerResponse.replace("{\"token\":\"", "").replace("\"}", "");

		mockMvc.perform(MockMvcRequestBuilders.get("/users/get")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().is4xxClientError());

		String id = "6";
		String username2 = "prueba";
		String password2 = "1";
		String role = "ADMIN";
		String bodyPost = "{\"id\":\"" + id +
				"\", \"username\":\"" + username2 +
				"\", \"password\":\"" + password2 +
				"\", \"role\":\"" + role +
				"\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(bodyPost)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().is4xxClientError());

		mockMvc.perform(MockMvcRequestBuilders.delete("/users/delete/1")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void AdminCanCreateAndUpdateAndDeleteUsers() throws Exception {
		String username = "admin@gmail.com";
		String password = "1";
		String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
		MvcResult registerResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String registerResponse = registerResult.getResponse().getContentAsString();
		String token = registerResponse.replace("{\"token\":\"", "").replace("\"}", "");

		mockMvc.perform(MockMvcRequestBuilders.get("/users/get")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();

		String id = "6";
		String username2 = "prueba";
		String password2 = "1";
		String role = "ADMIN";
		String bodyPost = "{\"id\":\"" + id +
				"\", \"username\":\"" + username2 +
				"\", \"password\":\"" + password2 +
				"\", \"role\":\"" + role +
				"\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(bodyPost)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();

		mockMvc.perform(MockMvcRequestBuilders.delete("/users/delete/6")
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk()).andReturn();
	}
	//Con admin si puede eliminar producto
	@Test
	void AdminCanDeleteProduct() throws Exception {
		String username = "admin@gmail.com";
		String password = "1";
		String body = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
		MvcResult registerResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String registerResponse = registerResult.getResponse().getContentAsString();
		String token = registerResponse.replace("{\"token\":\"", "").replace("\"}", "");

		String id = "5";
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
		mockMvc.perform(MockMvcRequestBuilders.delete("/products/delete/"+ id)
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization", "Bearer " +token))
				.andExpect(status().isOk()).andReturn();
	}
}
