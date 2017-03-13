package rxandroid.com.kty.rxandroiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * TODO:
 * Created by 倪彬彬 on 2017/3/7.
 */
public class SimpleActivity extends Activity {
    //发射事件
    private Observable.OnSubscribe mObservableAction = new Observable.OnSubscribe<String>() {

        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello Can I Fuck U");//发送事件
            subscriber.onCompleted();//完成事件
        }
    };

    //接收并修改TextView
    private Subscriber<String> mTextSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            tv.setText(s);
        }
    };
    //接收并显示toast
    private Subscriber<String> mToastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Toast.makeText(SimpleActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };

    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        tv = (TextView) findViewById(R.id.tv);


        Observable<String> observable = Observable.create(mObservableAction);

        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(mTextSubscriber);

        observable.subscribe(mToastSubscriber);
    }


    // 创建字符串
    private String sayMyName() {
        return "Hello, I am your friend, Spike!";
    }
}
