package com.luwuna.ravyapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RavyAPI{
    /**
     * Creates the basic API object.
     * @param RavyToken: the token to login with
     * @throws IllegalArgumentException if the token is not a Ravy token
     *
     */
    Request r;
    OkHttpClient c = new OkHttpClient();
    String token;
    public RavyAPI(@NotNull String RavyToken){
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

    /**
     * Gets the bans of a user. Returns a BanEntry object
     * Use .getBans()
     */
    public ExtensiveUserInfo getBanInfo(String id){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id + "/bans")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            obj = new JSONObject(res.body().string());
            return new ExtensiveUserInfo(obj);
        }catch (Exception e){
            e.printStackTrace();
            res = null;
            obj = null;
        }
        return null;
    }

    public String getGuildBans(){
        String reason;
        return null;
    }

    /**
     *
     * @param id
     * @return pronouns if successful, null if no access
     */
    public String getPronouns(@NotNull String id){
        r = new Request.Builder()
                .url("https://ravy.org/api/v1/users/" + id + "/pronouns")
                .header("Authorization", token)
                .get().build();
        Response res;
        JSONObject obj ;
        try {
            res = c.newCall(r).execute();
            obj = new JSONObject(res.body().string());
            return obj.get("pronouns").toString();
        }catch (Exception e){
            res = null;
            obj = null;
        }
        return null;
    }
    public String getTrust(@NotNull String id){
        return null;
    }
    public String  getWhiteList(@NotNull String id){
        return null;
    }
    public String getReputation(@NotNull String id){
        return null;
    }
    public boolean isSentinelVerified(@NotNull String id){
        return false;
    }

}
