package rxandroid.com.kty.rxandroiddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * TODO:
 * Created by 倪彬彬 on 2017/3/7.
 */
public class MoreActivity extends AppCompatActivity {
    @Bind(R.id.more_tv)
    TextView mTvText;

    final String[] mManyWords = {"Hello", "I", "am", "your", "friend", "Spike"};
    final List<String> mManyWordList = Arrays.asList(mManyWords);

    // Action类似订阅者, 设置TextView--貌似和回调那个一个意思啊
    private Action1<String> mTextViewAction = new Action1<String>() {
        @Override
        public void call(String s) {
            mTvText.setText(s);
        }
    };

    private Action1<String> mToastAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(MoreActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };

    //设置映射函数
    private Func1<List<String>, Observable<String>> mOneLetterFunc = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call(List<String> strings) {
            return Observable.from(strings);//映射字符串
        }
    };

    //设置大写字母
    private Func1<String, String> mUpperLetterFunc = new Func1<String, String>() {
        @Override
        public String call(String s) {
            return s.toUpperCase();
        }
    };

    //连接字符串
    private Func2<String, String, String> mMergeStringFunc = new Func2<String, String, String>() {
        @Override
        public String call(String s, String s2) {
            return String.format("%s %s", s, s2);//空格连接字符串
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);


        // 添加字符串, 省略Action的其他方法, 只使用一个onNext.
        Observable<String> obShow = Observable.just("Hello, I am your friend, Spike!");

        // 先映射, 再设置TextView
        obShow.observeOn(AndroidSchedulers.mainThread()).map(mUpperLetterFunc).subscribe(mTextViewAction);

        //单独显示数组中的每个元素
        Observable<String> obMap = Observable.from(mManyWordList);

        // 映射之后分发
        obMap.observeOn(AndroidSchedulers.mainThread()).map(mUpperLetterFunc).subscribe(mToastAction);


        // 优化过的代码, 直接获取数组, 再分发, 再合并, 再显示toast, Toast顺次执行.
        Observable.just(mManyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(mOneLetterFunc)//flatMap把数组转换为单独分发, Func1内部使用from拆分数组.
                .reduce(mMergeStringFunc)//reduce把单独分发数据集中到一起, 再统一分发, 使用Func2.
                .subscribe(mToastAction);

    }
}
