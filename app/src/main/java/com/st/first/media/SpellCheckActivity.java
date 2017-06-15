package com.st.first.media;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.st.first.R;

import java.util.Locale;

public class SpellCheckActivity extends Activity
        implements SpellCheckerSession.SpellCheckerSessionListener {

    Button b1;
    TextView tv1;
    EditText ed1;
    private SpellCheckerSession mScs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_check);

        b1=(Button)findViewById(R.id.buttonSuggestions);
        tv1=(TextView)findViewById(R.id.textSuggestions);

        ed1=(EditText)findViewById(R.id.editText);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScs.getSentenceSuggestions(new TextInfo[] { new TextInfo(ed1.getText().toString())}, 10);
            }
        });
    }

    public void onResume() {
        super.onResume();
        final TextServicesManager tsm = (TextServicesManager)
                getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE);

        mScs =    tsm.newSpellCheckerSession(null, Locale.ENGLISH,this,true);
    }

    public void onPause() {
        super.onPause();
        if (mScs != null) {
            mScs.close();
        }
    }


    public void onGet(final SuggestionsInfo[] suggestions) {

    }


    @Override
    public void onGetSuggestions(SuggestionsInfo[] suggestionsInfos) {

    }

    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] suggestions) {

        final StringBuffer sb = new StringBuffer("");

        Log.i("Test", String.valueOf(suggestions.length));

        for(SentenceSuggestionsInfo suggestion : suggestions){
            int n = suggestion.getSuggestionsCount();

            for(int i=0; i < n; i++){
                int m = suggestion.getSuggestionsInfoAt(i).getSuggestionsCount();
                Log.i("Test", "Count : "+ String.valueOf(m));
                for(int k=0; k < m; k++) {
                    sb.append(suggestion.getSuggestionsInfoAt(i).getSuggestionAt(k))
                            .append("\n");
                }
                sb.append("\n");
            }
        }

        runOnUiThread(new Runnable() {
            public void run() {
                tv1.setText(sb.toString());
            }
        });

    }
}

