package com.luwuna.rayviapi;


import static com.luwuna.rayviapi.Types.EntryType.USER;

public class tmp {
    public static void main(String[] args){
       RavyAPI api = new RavyAPI("NDU1Nzc3NjAzNzA2NDIxMjQ5.03d2e6392cf27152ce0d021adcc43c389a1eb03e70059d56ecbe8df745892dc0");
       System.out.println(api.getScopes());
       api.getBans("", USER);
    }
}
