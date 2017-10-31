package br.com.json.reader;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public final class ClasseSemInterfaceJson extends JsonReader {

    private void connecta(String hostName, Integer portNumber, String servicePath) {
        setUrlJson(hostName, portNumber, servicePath);
    }

    public JSONObject requestGet(String hostName, Integer portNumber, String servicePath) {

        String urlRequest = hostName + ":" + portNumber + servicePath;
        JSONObject json = null;

        try {

            connecta(hostName, portNumber, servicePath);
            json = JsonReader.readJsonFromUrl(urlRequest);

        } catch (IOException | JSONException e) {
            System.out.println("Falha na requisição, verifique URL");
            System.out.println(e.getMessage());
        }

        return json;
    }

}
