package com.flexionmobile.codingchallenge.solution;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.google.gson.Gson;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.ArrayList;
import java.util.List;


/**
 * http communication code from: http://www.mastertheboss.com/java/top-solutions-for-java-http-clients/
 */
public class IntegrationImpl implements Integration {

    //HTTP link not secure better to use HTTPS
    private final String targetUrl = "http://sandbox.flexionmobile.com/javachallenge/rest";

    @Override
    public Purchase buy(String var1){
        String path = "/developer/Willinger%20Zsolt/buy/"+var1;

        String entity = this.restPostCommunication(targetUrl+path);
        Gson gson = new Gson();
        if (entity != null){
            return gson.fromJson(entity, PurchaseImpl.class);
        }else {
            return null;
        }
    }

    @Override
    public List<Purchase> getPurchases(){
        String path = "/developer/Willinger%20Zsolt/all";

        String entity = this.restGetCommunication(targetUrl+path);
        if (entity == null){
            return null;
        }
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            Purchases purchases = objectMapper.readValue(entity, Purchases.class);
            return new ArrayList<>(purchases.getPurchases());

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void consume(Purchase var1){
        String path = "/developer/Willinger%20Zsolt/consume/"+var1.getId();
        this.restPostCommunication(targetUrl+path);
    }

    public String restPostCommunication(String finalUrl){
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(finalUrl);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(httpPost);
            String entity = "";
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                if (response.getEntity() != null){
                    entity = EntityUtils.toString(response.getEntity());
                }
            } else {
                entity = null;
            }
            client.close();
            return entity;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public String restGetCommunication(String finalUrl){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(finalUrl);



            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    return EntityUtils.toString(response.getEntity());
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            httpclient.close();
            return responseBody;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
