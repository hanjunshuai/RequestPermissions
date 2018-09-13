package zny.hjs.com.testrxpermissions;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_request)
    Button btnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_request)
    public void onViewClicked() {
        //这个请求事件我写在点击事件里面，
        //点击button之后RxPermissions会为我们申请运行时权限
        RxPermissions.getInstance(MainActivity.this)
                .request(Manifest.permission.READ_CALENDAR)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {//true表示获取权限成功（注意这里在android6.0以下默认为true）
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "获取成功");
                        } else {
                            Log.i("permissions", Manifest.permission.READ_CALENDAR + "：" + "获取失败");
                        }
                    }
                });

    }
}
