package rxandroid.com.kty.rxandroiddemo.networks;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * TODO:
 * Created by 倪彬彬 on 2017/3/9.
 */
public interface GitHubService {
    String ENDPOINT = "https://api.github.com";

    //获取个人信息
    @GET("/users/{user}")
    Observable<UserListAdapter.GitHubUser> getUserData(@Path("user") String user);

    //获取库
    @GET("/users/{user}/repos")
    Observable<RepoListAdapter.GitHubRepo[]> getRepoData(@Path("user") String user);
}
