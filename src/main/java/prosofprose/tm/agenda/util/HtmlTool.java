package prosofprose.tm.agenda.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HtmlTool {
	private static TrustManager[] trustAllCerts = new TrustManager[]{
		    new X509TrustManager() {
		        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		            return null;
		        }
		        public void checkClientTrusted(
		            java.security.cert.X509Certificate[] certs, String authType) {
		        }
		        public void checkServerTrusted(
		            java.security.cert.X509Certificate[] certs, String authType) {
		        }
		    }
		};

    // --- Constants and Variables
    // --- Constructor and Initialization Methods

    private HtmlTool() {
        super();
    }

    // --- Core and Helper Methods
    // --- Getter and Setter Methods

    public static String getHtml(final String url) throws IOException {
		final HttpClient client;
		try {
			System.out.println(url);
			if (url.startsWith("https:")) {
				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, trustAllCerts, new SecureRandom());
				client = HttpClient.newBuilder().sslContext(sslContext).build();
			}
			else {
				client = HttpClient.newBuilder().build();
			}
			
			HttpRequest request = HttpRequest.newBuilder(new URI(url)).header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36").GET().build(); // headers("Content-Type", "application/json", "Accept", "application/json").GET().build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			String out = response.body();
			return out;
		} 
		catch (NoSuchAlgorithmException | KeyManagementException | URISyntaxException | InterruptedException e) {
			throw new IOException(e);
		}
		
    }
    
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods
}
