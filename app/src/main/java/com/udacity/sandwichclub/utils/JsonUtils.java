package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwichObject = new Sandwich();

        List<String> kownAsList = new ArrayList<String>();
        List<String> ingredientsList = new ArrayList<String>();
        String parsePlaceOfOrigin = "";
        String parseDescription = "";
        String parseImage = "";

        try {
            // main JSON object for all sandwich data
            JSONObject sandwichDetails = new JSONObject(json);

            // getNameDetails to get the name details data such also known as
            JSONObject parseNameDetails = sandwichDetails.getJSONObject("name");
            JSONArray alsoKownAs = parseNameDetails.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKownAs.length(); i++) {
                String theOtherName = alsoKownAs.getString(i);
                kownAsList.add(theOtherName);
            }

            parsePlaceOfOrigin = sandwichDetails.getString("placeOfOrigin");
            parseDescription = sandwichDetails.getString("description");
            parseImage = sandwichDetails.getString("image");

            // getting array ingredients form sandwich data
            JSONArray parseIngedients = sandwichDetails.getJSONArray("ingredients");
            for (int i = 0; i < parseIngedients.length(); i++) {
                String ingredients = parseIngedients.getString(i);
                ingredientsList.add(ingredients);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        sandwichObject.setAlsoKnownAs(kownAsList);
        sandwichObject.setPlaceOfOrigin(parsePlaceOfOrigin);
        sandwichObject.setDescription(parseDescription);
        sandwichObject.setImage(parseImage);
        sandwichObject.setIngredients(ingredientsList);

        return sandwichObject;
    }
}
