package com.factor;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: arjit.gupta
 * Date: 15/02/14
 * Time: 10:36 AM
 */
public class FactorClientApplication {


    public static void main(String [] argv){
        if(argv ==null || argv[0] == null){
            System.out.println("server name not provided please input <host>:<port>");
            return;
        }
        String serverUrl = argv[0];
        serverUrl = getUrl(serverUrl);
        if(argv[1] == null){
            System.out.println("number not given in input");
            return;
        }

        String  number= argv[1];
        String result="";
        try{
            result  = getClient().resource(serverUrl).path("/calculatefactor").type(MediaType.APPLICATION_JSON)
                    .post(String.class, getRequestEntity(number));
        }catch (Exception e){
            System.out.println("for number " + number +" got Exception" + e.getCause());
        }
        System.out.println(result);
    }

    private static String getRequestEntity(String number) {
        return "{\"number\": "+number+"}";
    }

    private static String getUrl(String serverUrl) {
        return "http://"+serverUrl+"/factor";
    }

    private static Client getClient(){
        DefaultClientConfig cc = new DefaultClientConfig();
        cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        return Client.create(cc);
    }

}
