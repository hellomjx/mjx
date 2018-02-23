package mjx.children.joy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mjx.children.joy.view.WowSplashView;
import mjx.children.joy.view.WowView;

public class HomeActivity extends Activity {
    private WowSplashView mWowSplashView;
    private WowView mWowView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mWowSplashView = (WowSplashView) findViewById(R.id.wowSplash);
        mWowView = (WowView) findViewById(R.id.wowView);
        mWowSplashView.startAnimate();
        mWowSplashView.setOnEndListener(new WowSplashView.OnEndListener() {
            @Override
            public void onEnd(WowSplashView wowSplashView) {
                mWowSplashView.setVisibility(View.GONE);
                mWowView.setVisibility(View.VISIBLE);
                mWowView.startAnimate(wowSplashView.getDrawingCache());

            }
        });
    }


}
