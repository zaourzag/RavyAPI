package com.luwuna.rayviapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.luwuna.rayviapi.Types.EntryType;

public class RavyAPI{
    /**
     * Creates the basic API object. A ravy token can be obtained via Aero's server
     * @param RavyToken: the token to login with
     * @throws IllegalArgumentException if the token is not a Ravy token
     *
     */
    Request r;
    OkHttpClient c = new OkHttpClient();
    String token;
    public RavyAPI(@NotNull String RavyToken, String... id){
        token = RavyToken;
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/tokens/@current")
                .header("Authorization", token)
                .get().build();
        try {
           Response res =  c.newCall(r).execute();
           System.out.println(res.code());
           if(res.code() == 401){
               throw new IllegalArgumentException("Invalid token!");
           }
        //   System.out.println(res.body().string());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Gets the scopes that the token has access to in a List<String>. All scopes can be found at https://docs.ravy.org/share/5bc92059-64ef-4d6d-816e-144b78e97d89
     */
    public List<String> getScopes(){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/tokens/@current")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
          res = c.newCall(r).execute();
          obj = new JSONObject(res.body().string());
        }catch (Exception e){
            res = null;
            obj = null;
        }
        JSONArray arr = (JSONArray) obj.get("access");
        List<String> scopes = new ArrayList<>();
        arr.forEach(a ->{
            scopes.add(a.toString());
        });
        return scopes;
    }
    public String getBans(String id, EntryType entryType){
        return null;
    }
    public String getPronouns(String... id){
        return null;
    }
    public String getTrust(String... id){
        return null;
    }
    public String  getWhiteList(String... id){
        return null;
    }
    public String getReputation(String... id){
        return null;
    }
    public boolean isSentinelVerified(String... id){
        return false;
    }

}
