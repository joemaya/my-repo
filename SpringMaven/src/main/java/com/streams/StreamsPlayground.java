package com.streams;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.scheduling.annotation.Async;

public class StreamsPlayground {

	public static void fetchCSV() {
		
		
		Pattern pat =Pattern.compile("\\n");
		CompletableFuture<Stream<String>> csv = asyncHttpClient()
		        .prepareGet("http://api.worldweatheronline.com/premium/v1/past-weather.ashx?q=37.017,-7.933&date=2018-04-01&enddate=2018-04-30&tp=24&format=csv&key=54a4f43fc39c435fa2c143536183004")
		        .execute()
		        .toCompletableFuture()
		        .thenApply(Response::getResponseBody)
		        .thenApply(pat::splitAsStream);
		
	}

	private static AsyncHttpClient asyncHttpClient() {
		AsyncHttpClient asyncHttpClient = (AsyncHttpClient) asyncHttpClient();
		return asyncHttpClient;
	}
	
	public static CompletableFuture<IntStream> getTemperaturesAsync(double lat, double log, LocalDate from, LocalDate to) {
	    AsyncHttpClient asyncHttpClient = asyncHttpClient();
	    CompletableFuture<Stream<String>> csv = asyncHttpClient
	                .prepareGet(String.format(PATH, lat, log, from, to, KEY))
	                .execute()
	                .toCompletableFuture()
	                .thenApply(Response::getResponseBody)
	                .thenApply(NEWLINE::splitAsStream);
	    boolean[] isEven = {true};
	    return csv.thenApply(str -> str
	                .filter(w ->!w.startsWith("#"))     // Filter comments
	                .skip(1)                            // Skip line: Not Available
	                .filter(l -> isEven[0] =!isEven[0]) // Filter Even line
	                .map(line ->COMMA.splitAsStream(line).skip(2).findFirst().get()) // Extract temperature in Celsius
	                .mapToInt(Integer::parseInt));// Convert to Integer
	}
}
