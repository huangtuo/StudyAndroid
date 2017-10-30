package com.study.ht.studyandroid.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.study.ht.studyandroid.R;
import com.study.ht.studyandroid.base.BaseActivity;
import com.study.ht.studyandroid.flowlayout.activity.CategoryActivity;
import com.study.ht.studyandroid.utils.ToastUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_btn_fullSu)
    Button btnFullS;
    @BindView(R.id.main_btn_flowlayout)
    Button btnFlowlayout;
    @BindView(R.id.main_btn_callPhone)
    Button btnCallPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        setListener();

    }

    private void setListener() {

        btnFullS.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FullScreenActivity.class);
            startActivity(intent);
        });
        btnFlowlayout.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
            startActivity(intent);
        });
        btnCallPhone.setOnClickListener(view -> {
            callPermission(Manifest.permission.CALL_PHONE);
        });
    }

    private void callPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                new AlertDialog.Builder(this).setTitle("提示")
                        .setMessage("获取拨打电话权限")
                        .setPositiveButton("授权", (dialogInterface, i) -> {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, 1001);
                        })
                        .setNegativeButton("放弃", null)
                        .create()
                        .show();
            }else{
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, 1001);
            }
        } else {
            callPhone();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1001) {
            if (permissions[0] == Manifest.permission.CALL_PHONE) {
                callPhone();
            } else {
                ToastUtil.show(this, "未获取到权限");
            }

            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:10086");
        intent.setData(data);
        startActivity(intent);
    }
}
