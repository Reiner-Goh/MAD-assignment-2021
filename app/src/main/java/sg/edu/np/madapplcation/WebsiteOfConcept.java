package sg.edu.np.madapplcation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteOfConcept extends AppCompatActivity {


    private WebView webView;
    private String concept;
    public String GoToURL(String Concept) {
        if (Concept.equals("Introduction to Android")) { // cant use '==' as '==' for java, c++ etc compares pointers (aka addresses whr the string is stored) which will nvr be the same. Equals method compares Strictly string
            return "https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-1-build-your-first-app/1-0-c-introduction-to-android/1-0-c-introduction-to-android.html";
        }
        else if (Concept.equals( "Introduction to java")) {
            return "https://www.geeksforgeeks.org/introduction-to-java/#:~:text=JAVA%20was%20developed%20by%20James,is%20a%20simple%20programming%20language.&text=Java%20is%20a%20class%2Dbased,few%20implementation%20dependencies%20as%20possible.";
        }
        else if (Concept.equals("Basic of activity")) {
            return "https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-2-activities-and-intents/2-1-c-activities-and-intents/2-1-c-activities-and-intents.html";
        }
        else if (Concept.equals("Android Activity Lifecycle")) {
            return "https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-1-get-started/lesson-2-activities-and-intents/2-2-c-activity-lifecycle-and-state/2-2-c-activity-lifecycle-and-state.html";
        }
        else if (Concept.equals("Visual and Audio")) {
            return "https://developer.android.com/guide/topics/media/mediaplayer";
        }
        else if (Concept.equals("Event Handling")) {
            return "https://www.tutorialspoint.com/android/android_event_handling.htm#:~:text=Events%20are%20a%20useful%20way,%2Dout%20(FIFO)%20basis.";
        }
        else if (Concept.equals("RecyclerView")) {
            return "https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-2-user-experience/lesson-4-user-interaction/4-5-c-recyclerview/4-5-c-recyclerview.html";
        }
        else if (Concept.equals("Designing of Mobile User Experience")) {
            return "https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-2-user-experience/lesson-6-testing-your-ui/6-1-c-ui-testing/6-1-c-ui-testing.html";
        }
        else if (Concept.equals("Data and File Storage")) {
            return "https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-4-saving-user-data/lesson-9-preferences-and-settings/9-0-c-data-storage/9-0-c-data-storage.html";
        }
        else {
            return "https://developer.android.com/studio/publish";
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_of_concept);
        Intent receiveddata = getIntent();
        concept = receiveddata.getStringExtra("Concept");
        Log.v("Websiteactivity", concept);
        Log.v("Test", String.valueOf("Introduction to Android" == concept));
        Log.v("Test", "--" + concept + "--");
        webView = findViewById(R.id.webView);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setUserAgentString("Chrome/56.0.0 Mobile");
        webView.loadUrl(GoToURL(concept));
        Log.v("Websiteactivity", GoToURL(concept));
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}