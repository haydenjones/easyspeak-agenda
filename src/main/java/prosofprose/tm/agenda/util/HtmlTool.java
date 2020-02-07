package prosofprose.tm.agenda.util;

//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;

public class HtmlTool {
    static void close(final InputStream stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        }
        catch (final IOException e) {
        }
    }

    // --- Constants and Variables
    // --- Constructor and Initialization Methods

    private HtmlTool() {
        super();
    }

    // --- Core and Helper Methods
    // --- Getter and Setter Methods

    public static String getHtml(final String httpUrl) throws IOException {
    	return null;
    }
    /*
    public static String getHtml(final String httpUrl) throws IOException {
        final CloseableHttpClient httpclient = HttpClients.createDefault();

        final HttpGet httpget = new HttpGet(httpUrl);
        httpget.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        CloseableHttpResponse response = null;
        InputStream instream = null;

        try {
            response = httpclient.execute(httpget);

            final HttpEntity entity = response.getEntity();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            final byte[] oneByte = new byte[1];

            if (entity != null) {
                instream = entity.getContent();

                int available = instream.available();
                while (available > 0) {
                    if (available == 1) {
                        final int read = instream.read(oneByte);
                        if (read > 0) {
                            baos.write(oneByte, 0, read);
                        }
                    }
                    else {
                        final byte[] bytes = new byte[available];
                        final int read = instream.read(bytes);
                        baos.write(bytes, 0, read);
                    }

                    available = instream.available();
                }

            }

            return new String(baos.toByteArray());
        }
        finally {
            close(instream);
            response.close();
        }
    }
*/
    // --- Delegate and Convenience Methods
    // --- Miscellaneous Methods
}
