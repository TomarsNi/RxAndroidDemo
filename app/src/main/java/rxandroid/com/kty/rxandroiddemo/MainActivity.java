package rxandroid.com.kty.rxandroiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //简单使用
    public void gotoSimpleModule(View view) {
        startActivity(new Intent(this, SimpleActivity.class));
    }

    //更多使用
    public void gotoMoreModule(View view) {
        startActivity(new Intent(this, MoreActivity.class));
    }

    //Lambda使用
    public void gotoLambdaModule(View view) {
    }

    //网络使用
    public void gotoNetworkModule(View view) {
        startActivity(new Intent(this, NetworkActivity.class));
    }

    //线程安全
    public void gotoSafeModule(View view) {
    }

    //RxBinding
    public void gotoBindingModule(View view) {
        startActivity(new Intent(this, BindingActivity.class));

    }
}
