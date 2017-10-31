package br.com.json;

import br.com.models.Contact;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class jsonPost {

    public static void main(String[] args) throws JSONException, IOException {

        //Teste send Post
        SendPostComum();

        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("");

        //Teste send Post Json
        SendPostComumJson();

        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("Fim do Teste POST (JSON E NORMAL)");

    }

    public static void SendPostComum() {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost/post_get/index.php");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", "10"));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity respEntity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == 200 && respEntity != null) {
                //Retorno
                String content = EntityUtils.toString(respEntity);
                System.out.println(content);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void SendPostComumJson() {
        String strFinal = "";

        //Exemplo String
        String json = "{\"name\":\"myname\",\"age\":\"20\"}";
//        strFinal = strFinal+json;

        //Exemplo add Map
        Map<String, String> map = new HashMap();
        map.put("nome", "tiago");
        map.put("id", "10");

        JSONObject jobj = new JSONObject(map);
        strFinal = strFinal + jobj.toString();

        //Exemplo add Objeto
        Contact c = new Contact();
        c.setId(10L);
        c.setNome("Marcos");

        JSONObject jobmodel = new JSONObject(c);
//        strFinal = strFinal+jobmodel.toString();

        //Http Client
        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost("http://localhost/post_get/index.php");
            StringEntity params = new StringEntity(strFinal);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            HttpEntity respEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == 200 && respEntity != null) {
                //Retorno
                String content = EntityUtils.toString(respEntity);
                System.out.println(content);
            }

        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

    }

    /*
    BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

	StringBuffer result = new StringBuffer();
	String line = "";
	while ((line = rd.readLine()) != null) {
		result.append(line);
	}

	// System.out.println(result.toString());

     */
}
