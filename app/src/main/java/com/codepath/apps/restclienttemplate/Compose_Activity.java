package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class Compose_Activity extends AppCompatActivity {

    Context context;
    EditText e;
    TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_);
        e = (EditText) findViewById(R.id.etEditText);
        counter = (TextView) findViewById (R.id.tvCounter);
        context = this;
        e.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                counter.setText(String.valueOf(280-s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



        public void onClick(View v) {
            TwitterApp.getRestClient(this).sendTweet(e.getText().toString(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        Tweet tweet = Tweet.fromJSON(response);
                        Intent data = new Intent();
                        data.putExtra("tweet", Parcels.wrap(tweet));
                        setResult(RESULT_OK, data);
                        finish();
                    } catch (JSONException e) {
                        Log.i("dsklnsef", "efjkhdf");
                    }
                }
            });


        }






}
