package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView tKnownAs, tPlaceOfOrigin, tIngredients, tDescription;
    Sandwich sandwich;

    String placeOfOrigin, description;
    List<String> knwonName = new ArrayList<>();
    List<String> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        tKnownAs = (TextView) findViewById(R.id.also_known_tv);
        tPlaceOfOrigin = (TextView) findViewById(R.id.origin_tv);
        tIngredients = (TextView) findViewById(R.id.ingredients_tv);
        tDescription = (TextView) findViewById(R.id.description_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        knwonName = sandwich.getAlsoKnownAs();
        ingredients = sandwich.getIngredients();
        placeOfOrigin = sandwich.getPlaceOfOrigin();
        description = sandwich.getDescription();

        populateUI();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        tKnownAs.setText(knwonName.toString());
        tIngredients.setText(ingredients.toString());
        tPlaceOfOrigin.setText(placeOfOrigin);
        tDescription.setText(description);

    }
}
