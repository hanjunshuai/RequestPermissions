package zny.hjs.com.testrxpermissions;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.tbruyelle.rxpermissions.Permission;
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
                //分别申请多个权限时，使用requestEach
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.name.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            //当ACCESS_FINE_LOCATION权限获取成功时，permission.granted=true
                            Log.i("permissions", Manifest.permission.ACCESS_FINE_LOCATION + "：" + permission.granted);
                        }
                        if (permission.name.equals(Manifest.permission.RECORD_AUDIO)) {
                            //当RECORD_AUDIO 权限获取成功时，permission.granted=true
                            Log.i("permissions", Manifest.permission.RECORD_AUDIO + "：" + permission.granted);
                        }
                        if (permission.name.equals(Manifest.permission.CAMERA)) {
                            //当CAMERA权限获取成功时，permission.granted=true
                            Log.i("permissions", Manifest.permission.CAMERA + "：" + permission.granted);
                        }
                    }
                });

    }
}
