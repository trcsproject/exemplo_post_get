package br.com.json.reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import org.jboss.logging.Logger;

public class ClasseDaInterface extends JsonReader implements InterfaceDaClasse {

    private final Logger log = Logger.getLogger(ClasseDaInterface.class);

    private String host = "localhost:80";
    private String servicePath = "/post_get";

    @Override
    public boolean teste() {

        this.host = this.host == null ? "localhost:80" : this.host;
        this.servicePath = this.servicePath == null ? "" : this.servicePath;

        String[] hostSplit = this.host.split(":");
        String hostName = hostSplit[0];
        String portNumber = !"".equals(hostSplit[1]) && hostSplit[1] != null ? hostSplit[1] : "80";
        setUrlJson(hostName, Integer.parseInt(portNumber), this.servicePath);

        Map<String, String> map = new HashMap<>();

        map.put("tiago", "teste");
        map.put("json", "2");
        map.put("sucesso", "true");
        JSONObject json = null;

        try {
            //Trabalhar com Json Recebido
            json = super.getJsonObjectFromServer(map, "/index.php");
            if (json.getBoolean("sucesso")) {
                return true;
            } else {
                throw new RuntimeException("Falha");
            }
        } catch (IOException e) {
            log.error("Erro ao conectar");
        } catch (JSONException e) {
            System.err.println("Error: Json received: " + json.toString());
            return false;
        }
        return true;
    }

}
