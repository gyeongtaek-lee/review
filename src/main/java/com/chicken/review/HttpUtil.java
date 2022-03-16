package com.chicken.review;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class HttpUtil {
	 
    @SuppressWarnings("finally")
	public static String callApi(JsonObject params, String type){
        
    	String result = "";
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        
        try {
            //URL 설정
            URL url = new URL("https://openapi.playauto.io/api/auth");
 
            conn = (HttpURLConnection) url.openConnection();
            
            // type의 경우 POST, GET, PUT, DELETE 가능
            conn.setRequestMethod(type);
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Transfer-Encoding", "chunked");
//            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("x-api-key", "u54ci91cQ2aw7LwUn3NWS9WkIXxeRw7G3aady9Pu");
            conn.setDoOutput(true);
            
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            // JSON 형식의 데이터 셋팅
            JsonObject commands = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            
            params.addProperty("email", "lg_super@playauto.co.kr");
            params.addProperty("password", "Playauto!@");
//            params.addProperty("userNm", "홍길동");
 
//            commands.add("email", params);
            
             // JSON 형식의 데이터 셋팅 끝
            
            // 데이터를 STRING으로 변경
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(params);
                 
            bw.write(params.toString());
            bw.flush();
            bw.close();
            
            // 보내고 결과값 받기
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
             // 응답 데이터
              System.out.println("responseJson :: " + sb.toString());
              
              result = sb.toString();
//                responseJson = new JSONObject(sb.toString());
                
                // 응답 데이터
//                System.out.println("responseJson :: " + responseJson);
            } 
                                    
        } catch (MalformedURLException e) {
        	result = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
        	result = e.getMessage();
            e.printStackTrace();
        } catch (JSONException e) {
        	result = e.getMessage();
            System.out.println("not JSON Format response");
            e.printStackTrace();
        } finally {
        	return result;
        }
    }
    
    
    @SuppressWarnings("finally")
	public static String insertBaseProduct(JsonObject params, String type){
        
    	String result = "";
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        
        try {
            //URL 설정
            URL url = new URL("https://openapi.playauto.io/api/stock/add");
 
            conn = (HttpURLConnection) url.openConnection();
            
            // type의 경우 POST, GET, PUT, DELETE 가능
            conn.setRequestMethod(type);
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Transfer-Encoding", "chunked");
//            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("x-api-key", "u54ci91cQ2aw7LwUn3NWS9WkIXxeRw7G3aady9Pu");
            conn.setRequestProperty("Authorization", "Token eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhdXRoX3R5cGUiOiLstJ3qtITqtIDrpqzsnpAiLCJzb2xfbm8iOjE3ODcxLCJwYV9zb2xfbm8iOm51bGwsInNvbF90eXBlIjoiR01QIiwic29sX3N1Yl90eXBlIjoiVEVTVCIsInNvbF92ZXJzaW9uIjoiVklQIiwic29sX3N0b2NrIjoxLCJ3bV9ubyI6bnVsbCwid2RhdGUiOiIyMDIxLTA3LTE1IDE2OjUxOjAyIiwic29sX3NkYXRlIjoiMjAyMS0xMS0wNCIsInNvbF9lZGF0ZSI6IjIwMjItMTItMzEiLCJsYXN0X3N5bmNfdGltZSI6bnVsbCwiZmlyc3RfcGF5X25vIjpudWxsLCJkb21haW4iOiJwbGF5YXV0by5jby5rciIsImRiX2Nvbm5fbmFtZSI6ImtyX3VzZXIxIiwiYXBpX2tleSI6InU1NGNpOTFjUTJhdzdMd1VuM05XUzlXa0lYeGVSdzdHM2FhZHk5UHUiLCJ0ZXN0ZXJfY29uZmlybV95biI6MCwiZGVwb3Rfbm8iOm51bGwsImViYXlkZXBvdF95biI6bnVsbCwiZGVmYXVsdF90ZW1wbGF0ZV9ncm91cF9ubyI6MjA3MDIsInNvbF9zZXIiOiJvcGVuYXBpLHN1cGVyX3VzZXIiLCJtX25vIjo2NDM3MCwibWRhdGUiOiIyMDIyLTAzLTEwIDE3OjI3OjIzIiwibGFzdF9sb2dpbl90aW1lIjoiMjAyMi0wMy0xMCAxNzoyNzoyMyIsImxhc3RfcHdkX21vZF90aW1lIjoiMjAyMS0wNy0xNSAxNjo1MTowMiIsImxlYXZlX3RpbWUiOm51bGwsInJlc3RfZGF0ZSI6bnVsbCwibGFzdF9sb2dpbl9pcCI6NDYxMDE2MDc0LCJuYW1lIjoiTEcoU1VQRVIpIiwiZW1haWwiOiJsZ19zdXBlckBwbGF5YXV0by5jby5rciIsInB3ZCI6IjEzNjNjYjI1OWZiOTMxNTY0MDJlZWM0NjU2NmJmOTA3IiwibV90eXBlIjoi66eI7Iqk7YSwIiwidHdvZmFjdG9yX3R5cGUiOiJzbXMiLCJtYXNfbV9ubyI6NjQzNzAsImNfbm8iOjU5Njg5LCJjdHJ5X2NkIjoiS1IiLCJ0ZWwiOm51bGwsImh0ZWwiOiIwMTAzNTYzMDgxMyIsImVtYWlsX3luIjowLCJzbXNfeW4iOjAsImRlcHQiOm51bGwsInBvc2kiOm51bGwsInJlZ19hdXRoX2tleSI6bnVsbCwicHdkX2F1dGhfa2V5IjpudWxsLCJtX3N0YXR1cyI6IuygleyDgSIsInByb2ZpbGVfaW1nX3VybCI6IiIsImFkYl9pZCI6bnVsbCwiY29tYmluZV95biI6MSwiY29tcGFueV9uYW1lIjoiTEcg7KCE7J6QIiwiYml6X3JlZ19ubyI6Ijk5OS05OS05OTAwOSIsInppcGNkIjpudWxsLCJhZGRyMSI6bnVsbCwiYWRkcjIiOm51bGwsImNfdGVsIjoiMDEwLTExMTEtMjIyMiIsInVzZXJfaGFzaCI6ImYxOTJjZDA2ZmUzYTY5MDBmYTk1MTAyMzc1ODEwZGFlNzZjZmU4ZWIyY2RlNDBkNDUxYTdiMzBlNWEwNThkZTYiLCJhZG1pbk1vZGUiOmZhbHNlLCJpYXQiOjE2NDc0MTEzOTcsImV4cCI6MTY0NzQ5Nzc5N30.Zo6yKe79QBaLLDRnwosrkrE824DS1FVb3L_Pp319sZxVvfJa09_PEmZZRjRkRwZDVa6Cxkuuo3GQBtsY3MbY0A");
            conn.setDoOutput(true);
            
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            // JSON 형식의 데이터 셋팅
            JsonObject commands = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            
            params.addProperty("prod_name", "테스트 LG DLRUDXOR");
            params.addProperty("delivery_vendor", "28654");
            params.addProperty("maker", "LG전자");
            
            commands.addProperty("sku_cd", "DLRUDXOR.AKOR");
            commands.addProperty("model_no", "DLRUDXOR.AKOR");
            commands.addProperty("model", "DLRUDXOR");
            commands.addProperty("product_price", "100000");
            commands.addProperty("real_stock", "0");
            commands.addProperty("safe_stock", "0");
            commands.addProperty("opt1_type", "없음");
            jsonArray.add(commands);
            
            params.add("opt", jsonArray);

//            params.addProperty("userNm", "홍길동");
 
//            commands.add("email", params);
            
             // JSON 형식의 데이터 셋팅 끝
            
            // 데이터를 STRING으로 변경
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(params);
                 
            bw.write(params.toString());
            bw.flush();
            bw.close();
            
            // 보내고 결과값 받기
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
             // 응답 데이터
              System.out.println("responseJson :: " + sb.toString());
              
              result = sb.toString();
//                responseJson = new JSONObject(sb.toString());
                
                // 응답 데이터
//                System.out.println("responseJson :: " + responseJson);
            } 
                                    
        } catch (MalformedURLException e) {
        	result = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
        	result = e.getMessage();
            e.printStackTrace();
        } catch (JSONException e) {
        	result = e.getMessage();
            System.out.println("not JSON Format response");
            e.printStackTrace();
        } finally {
        	return result;
        }
    }
    
    
    @SuppressWarnings("finally")
	public static String insertBaseProducts(JsonObject params, String type){
        
    	String result = "";
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        
        try {
        	
            //URL 설정
            URL url = new URL("https://openapi.playauto.io/api/stock/add");
 
            conn = (HttpURLConnection) url.openConnection();
            
            // type의 경우 POST, GET, PUT, DELETE 가능
            conn.setRequestMethod(type);
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Transfer-Encoding", "chunked");
//            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("x-api-key", "u54ci91cQ2aw7LwUn3NWS9WkIXxeRw7G3aady9Pu");
            conn.setRequestProperty("Authorization", "Token eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhdXRoX3R5cGUiOiLstJ3qtITqtIDrpqzsnpAiLCJzb2xfbm8iOjE3ODcxLCJwYV9zb2xfbm8iOm51bGwsInNvbF90eXBlIjoiR01QIiwic29sX3N1Yl90eXBlIjoiVEVTVCIsInNvbF92ZXJzaW9uIjoiVklQIiwic29sX3N0b2NrIjoxLCJ3bV9ubyI6bnVsbCwid2RhdGUiOiIyMDIxLTA3LTE1IDE2OjUxOjAyIiwic29sX3NkYXRlIjoiMjAyMS0xMS0wNCIsInNvbF9lZGF0ZSI6IjIwMjItMTItMzEiLCJsYXN0X3N5bmNfdGltZSI6bnVsbCwiZmlyc3RfcGF5X25vIjpudWxsLCJkb21haW4iOiJwbGF5YXV0by5jby5rciIsImRiX2Nvbm5fbmFtZSI6ImtyX3VzZXIxIiwiYXBpX2tleSI6InU1NGNpOTFjUTJhdzdMd1VuM05XUzlXa0lYeGVSdzdHM2FhZHk5UHUiLCJ0ZXN0ZXJfY29uZmlybV95biI6MCwiZGVwb3Rfbm8iOm51bGwsImViYXlkZXBvdF95biI6bnVsbCwiZGVmYXVsdF90ZW1wbGF0ZV9ncm91cF9ubyI6MjA3MDIsInNvbF9zZXIiOiJvcGVuYXBpLHN1cGVyX3VzZXIiLCJtX25vIjo2NDM3MCwibWRhdGUiOiIyMDIyLTAzLTEwIDE3OjI3OjIzIiwibGFzdF9sb2dpbl90aW1lIjoiMjAyMi0wMy0xMCAxNzoyNzoyMyIsImxhc3RfcHdkX21vZF90aW1lIjoiMjAyMS0wNy0xNSAxNjo1MTowMiIsImxlYXZlX3RpbWUiOm51bGwsInJlc3RfZGF0ZSI6bnVsbCwibGFzdF9sb2dpbl9pcCI6NDYxMDE2MDc0LCJuYW1lIjoiTEcoU1VQRVIpIiwiZW1haWwiOiJsZ19zdXBlckBwbGF5YXV0by5jby5rciIsInB3ZCI6IjEzNjNjYjI1OWZiOTMxNTY0MDJlZWM0NjU2NmJmOTA3IiwibV90eXBlIjoi66eI7Iqk7YSwIiwidHdvZmFjdG9yX3R5cGUiOiJzbXMiLCJtYXNfbV9ubyI6NjQzNzAsImNfbm8iOjU5Njg5LCJjdHJ5X2NkIjoiS1IiLCJ0ZWwiOm51bGwsImh0ZWwiOiIwMTAzNTYzMDgxMyIsImVtYWlsX3luIjowLCJzbXNfeW4iOjAsImRlcHQiOm51bGwsInBvc2kiOm51bGwsInJlZ19hdXRoX2tleSI6bnVsbCwicHdkX2F1dGhfa2V5IjpudWxsLCJtX3N0YXR1cyI6IuygleyDgSIsInByb2ZpbGVfaW1nX3VybCI6IiIsImFkYl9pZCI6bnVsbCwiY29tYmluZV95biI6MSwiY29tcGFueV9uYW1lIjoiTEcg7KCE7J6QIiwiYml6X3JlZ19ubyI6Ijk5OS05OS05OTAwOSIsInppcGNkIjpudWxsLCJhZGRyMSI6bnVsbCwiYWRkcjIiOm51bGwsImNfdGVsIjoiMDEwLTExMTEtMjIyMiIsInVzZXJfaGFzaCI6ImYxOTJjZDA2ZmUzYTY5MDBmYTk1MTAyMzc1ODEwZGFlNzZjZmU4ZWIyY2RlNDBkNDUxYTdiMzBlNWEwNThkZTYiLCJhZG1pbk1vZGUiOmZhbHNlLCJpYXQiOjE2NDc0MTEzOTcsImV4cCI6MTY0NzQ5Nzc5N30.Zo6yKe79QBaLLDRnwosrkrE824DS1FVb3L_Pp319sZxVvfJa09_PEmZZRjRkRwZDVa6Cxkuuo3GQBtsY3MbY0A");
            conn.setDoOutput(true);
            
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

//            params.addProperty("userNm", "홍길동");
 
//            commands.add("email", params);
            
             // JSON 형식의 데이터 셋팅 끝
            
            // 데이터를 STRING으로 변경
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(params);
                 
            bw.write(params.toString());
            bw.flush();
            bw.close();
            
            // 보내고 결과값 받기
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
             // 응답 데이터
              System.out.println("responseJson :: " + sb.toString());
              
              result = sb.toString();
//                responseJson = new JSONObject(sb.toString());
                
                // 응답 데이터
//                System.out.println("responseJson :: " + responseJson);
            } 
                                    
        } catch (MalformedURLException e) {
        	result = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
        	result = e.getMessage();
            e.printStackTrace();
        } catch (JSONException e) {
        	result = e.getMessage();
            System.out.println("not JSON Format response");
            e.printStackTrace();
        } finally {
        	return result;
        }
    }
    
}

