package com.luwuna.ravyapi;


import org.json.JSONArray;
import org.json.JSONObject;

public class BanEntry {
    JSONObject obj;
    public BanEntry(JSONObject obj){
        this.obj = obj;
    }
    public boolean isBanned(){
        System.out.println(obj.get("bans"));
       JSONArray a = ((JSONArray) obj.get("bans"));
        if(a.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
    public String getReason(){
        return obj.get("reason").toString();
    }
    public String getReasonKey(){
        return obj.get("reasonKey").toString();
    }
    public String getModerator(){
        return obj.get("moderator").toString();
    }
    public String getProvider(){
        return obj.get("provider").toString();
    }
}
