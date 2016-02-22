package com.wangrunxiang.andfixdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private Button mBtBug, mBtFix, mBtCastBug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtBug = (Button) findViewById(R.id.bt_bug);
        mBtFix = (Button) findViewById(R.id.bt_fix);
        mBtCastBug = (Button) findViewById(R.id.bt_cast_bug);

        mBtBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 100+(Integer.parseInt(""));
                Toast.makeText(MainActivity.this, "补丁修复成功", Toast.LENGTH_SHORT).show();
            }
        });

        mBtCastBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText view = (EditText) new TextView(MainActivity.this);
                Toast.makeText(MainActivity.this, "补丁修复成功", Toast.LENGTH_SHORT).show();
            }
        });

        mBtFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "准备下载补丁", Toast.LENGTH_SHORT).show();
                OkHttpUtils.get().url("http://daxuelife.qiniudn.com/patch/patch3.apatch").build().execute(new FileCallBack(getFilesDir().getAbsolutePath(), "patch3.apatch") {
                    @Override
                    public void inProgress(float progress) {

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(File response) {
                        Toast.makeText(MainActivity.this, "下载补丁", Toast.LENGTH_SHORT).show();
                        try {
                            ((MainApplication)getApplication()).mPatchManager.addPatch(response.getPath());
                            Toast.makeText(MainActivity.this, "打补丁成功", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "打补丁失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
