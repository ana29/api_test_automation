package br.com.teste.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Cep {
	private static final String BASE_URL = "https://viacep.com.br/ws/";

	@Test
	public void cepValido() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/58400025/json/");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	public void cepInexistente() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/0000000/json/");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");
	}

	@Test
	public void cepInvalido() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/mmo/json/");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 400);
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 400 Bad Request");
	}

	// TODO: https://viacep.com.br/ws/RS/Gravatai/Barroso/json/ (extra)

}
