package br.com.json.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonReaderGeneric {

    public URL url;

    public String getUrl() {
        return this.url == null?null:this.url.toString();
    }
    
    public void setUrlJson(String host,int port,String path) {
        try {
            this.url = new URL("http",host, port, path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJsonObjectFromServer(Map<String, String> parameters, String specificService) throws IOException, JSONException {

        return readJsonFromUrl(buildJsonGetRequest(parameters, specificService));
    }

    public String buildJsonGetRequest(Map<String, String> parameters, String specificService) {
        StringBuilder strbuilder = new StringBuilder();

        try {
            strbuilder.append(url.toURI()).append(specificService).append("?");

            for (String key : parameters.keySet()) {
                strbuilder.append(key).append("=").append(parameters.get(key)).append("&");
            }
            return strbuilder.toString().substring(0, strbuilder.toString().lastIndexOf("&")).replaceAll("\\s", "%20");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "invalid url parsing";
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        InputStream is = null;

        try {
            is = new URL(url).openStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;

        } catch (IOException e) {
            throw new IOException("Não foi possível estabelecer conexão com o API");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public JSONObject buildJsonObject(Map<String, String> parameters) throws IOException, JSONException {
        JSONObject json = new JSONObject();

        for (String key : parameters.keySet()) {
            json.put(key, parameters.get(key));
        }
        return json;
    }
}
