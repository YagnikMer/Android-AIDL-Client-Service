package com.worldmer.aidlexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.worldmer.server.CharService;
import com.worldmer.server.MathService;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    CharService mCharService;
    MathService mMathService;
    EditText edt1, edt2;
    TextView tvAnswer;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        init();

    }

    public void bindView() {
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        tvAnswer = (TextView) findViewById(R.id.tvanswer);
    }

    public void init() {
        Intent mathIntent = new Intent("android.intent.action.MATH_MESSAGE");
        bindService(createExplicitFromImplicitIntent(this, mathIntent), mMathServiceConnection, Context.BIND_AUTO_CREATE);

        Intent charIntent = new Intent("android.intent.action.CHAR_MESSAGE");
        bindService(createExplicitFromImplicitIntent(this, charIntent), mCharServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public boolean isValid(EditText view) {
        boolean status = false;
        try {
            if (null != view) {
                if (view.getText().toString().trim().length() > 0) {
                    status = true;
                }
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    public void getAnswer(View view) {
        int mAnswer = 0;
        if (isValid(edt1) && isValid(edt2)) {
            num1 = Integer.parseInt(edt1.getText().toString());
            num2 = Integer.parseInt(edt2.getText().toString());
            try {
                switch (view.getTag().toString()) {
                    case "add":
                        mAnswer = mMathService.add(num1, num2);
                        break;
                    case "sub":
                        mAnswer = mMathService.sub(num1, num2);
                        break;
                    case "mul":
                        mAnswer = mMathService.mul(num1, num2);
                        break;
                    case "div":
                        mAnswer = mMathService.div(num1, num2);
                        break;
                    case "mod":
                        mAnswer = mMathService.mod(num1, num2);
                        break;
                }
                tvAnswer.setText(String.valueOf(mAnswer));
                Toast.makeText(getApplicationContext(),mCharService.getCharFromNumber(mAnswer),Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                tvAnswer.setText("Error");
            }
        } else {
            tvAnswer.setText("Error");
        }
    }

    ServiceConnection mMathServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMathService = MathService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mMathService = null;
        }
    };

    ServiceConnection mCharServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mCharService = CharService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mCharService = null;
        }
    };

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
