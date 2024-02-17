package org.Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;

public class UtilFunctions {

    public static String jsonExtract(final Response responseObject, final String keyValue){
        final String responseString=responseObject.asString();
        final JsonPath jsonPathoBJECT=new JsonPath(responseString);
        return jsonPathoBJECT.get(keyValue).toString();

    }

    public  static List jsonExtractList(final Response responseObject, final String path){
        final String responseString=responseObject.asString();
        final JsonPath jsonPathoBJECT=new JsonPath(responseString);
        List<HashMap<String , Object>> keylist=jsonPathoBJECT.getList(path);
        return keylist;

    }
}
