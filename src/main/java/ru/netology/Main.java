package ru.netology;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=hHd8xggaPPF4biMn9ncLKKjeRqVlDgLIz0XiR9LS";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            ApiNasa apiNasa = mapper.readValue(response.getEntity().getContent(),
                    new TypeReference<>() {
                    });
            String[] splitUrl = apiNasa.getUrl().split("/");
            String fileName = splitUrl[splitUrl.length - 1];
            HttpGet request2 = new HttpGet(apiNasa.getHdurl());

            try (CloseableHttpResponse newResp = httpClient.execute(request2);
                 FileOutputStream fos = new FileOutputStream(new File(fileName))) {
                byte[] bytes = newResp.getEntity().getContent().readAllBytes();
                fos.write(bytes);
            }
        }
        httpClient.close();
    }
}