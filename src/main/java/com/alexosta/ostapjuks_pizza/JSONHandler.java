package com.alexosta.ostapjuks_pizza;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHandler {
    private JSONObject readJSONFromFile(String filePath) throws IOException, ParseException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(content);
    }

    public JSONArray getProductsArray(JSONObject jsonObject) {
        return (JSONArray) jsonObject.get("products");
    }
}
