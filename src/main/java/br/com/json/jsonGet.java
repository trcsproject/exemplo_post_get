package br.com.json;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;
import static org.apache.http.protocol.HTTP.USER_AGENT;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

public class jsonGet {

    public static void main(String[] args) throws JSONException, IOException {

        //Teste send Post
        SendGetComum();

        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("");

        //Teste send Post Json
        SendGetComumJson();

        System.out.println("----------------------------");
        System.out.println("----------------------------");
        System.out.println("Fim do Teste POST (JSON E NORMAL)");
    }

    public static void SendGetComum() {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost/post_get/index.php?json=0&teste=1");

        httpGet.addHeader("User-Agent", USER_AGENT);

        try {

            HttpResponse response = httpClient.execute(httpGet);
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

    private static void SendGetComumJson() {

        //Exemplo String
        String json = "{\"name\":\"myname\",\"age\":\"20\"}";

        //Http Client
        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            
            HttpGet request = new HttpGet("http://localhost/post_get/index.php?json=1&teste=");
            request.addHeader("User-Agent", USER_AGENT);
            request.addHeader("content-type", "application/json");
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

}
