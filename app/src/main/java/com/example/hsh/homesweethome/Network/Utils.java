package com.example.hsh.homesweethome.Network;

public class Utils {

    private Utils() {}

    public static APIService getAPIService() {

        return RetrofitClient.getClient(Constants.HSH_BASE_URL).create(APIService.class);
    }
}