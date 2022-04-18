package io.fission;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import io.fission.Function;
import io.fission.Context;

import java.net.URI;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class HelloWorld implements Function {

	@Override
	public ResponseEntity<?> call(RequestEntity req, Context context) {

		req.getBody();


		Map<String, String> query_pairs = new LinkedHashMap<String, String>();
		URI url = req.getUrl();
		String query = url.getQuery();
		int duration = 0;
		try {
			String[] pairs = query.split("&");
			for (String pair : pairs) {
				int idx = pair.indexOf("=");
				query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
			}
			String durationStr = query_pairs.get("duration");
			duration = Integer.parseInt(durationStr);
			Thread.sleep(duration);
		}catch (Exception e){
			System.out.println(e.toString());
		}

		return ResponseEntity.ok("Hello World!" + query + duration);
	}

}
