package com.luwuna.ravyapi;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExtensiveUserInfo {
   private JSONObject obj;
    private JSONObject trust;
    public ExtensiveUserInfo(JSONObject obj){
        this.obj = obj;
        trust = (JSONObject) obj.get("trust");
    }

    public boolean isBanned(){
       JSONArray a = ((JSONArray) obj.get("bans"));
        return !a.isEmpty();
    }

    public int getTrustLevel(){
       return Integer.valueOf(trust.get("level").toString());
    }
    public String getTrustLabel(){
        return (String) trust.get("label");
    }

    /**
     * Checks whether the user has bans
     * @return null if the user is not banned, List<BanEntry> if the user is banned
     */
    public List<BanEntry> getBanEntries(){
        if(!isBanned()) return null;
        List<BanEntry> entry = new ArrayList<>();
        JSONArray arr = (JSONArray) obj.get("bans");
        arr.forEach(a ->{
            entry.add(new BanEntry((JSONObject) a ));
        });
        return entry;
    }

}
