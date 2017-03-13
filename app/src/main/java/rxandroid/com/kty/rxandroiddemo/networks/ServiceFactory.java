package rxandroid.com.kty.rxandroiddemo.networks;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * TODO:工厂模式
 * Created by 倪彬彬 on 2017/3/9.
 */
public class ServiceFactory {
    public static <T> T createServiceFrom(final Class<T> serviceClass, String endpoint) {

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//Rx适配器
                .addConverterFactory(GsonConverterFactory.create())//Gson转换器
                .build();
        return adapter.create(serviceClass);

    }


}
