package rxandroid.com.kty.rxandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rxandroid.com.kty.rxandroiddemo.networks.NetworkWrapper;
import rxandroid.com.kty.rxandroiddemo.networks.UserListAdapter;

public class NetworkActivity extends AppCompatActivity {

    @Bind(R.id.network_rv_list)
    RecyclerView mRvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);

        //设置Layout管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);

        //设置adapter
        UserListAdapter adapter = new UserListAdapter(this::gotoDetailPage);
        NetworkWrapper.getUsersInto(adapter);
        mRvList.setAdapter(adapter);


    }

    public interface UserClickCallBack {
        void onItemClick(String name);
    }

    // 跳转到库详情页面
    private void gotoDetailPage(String name) {
        startActivity(NetworkDetailActivity.from(NetworkActivity.this, name));
    }
}
