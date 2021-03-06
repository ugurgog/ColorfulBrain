package uren.com.colorfulbrains;


import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseAuth;

import io.fabric.sdk.android.Fabric;
import uren.com.colorfulbrains.login.LoginActivity;
import uren.com.colorfulbrains.management.NextActivity;
import uren.com.colorfulbrains.Models.User;
import uren.com.colorfulbrains.utils.AnimationUtil;
import uren.com.colorfulbrains.utils.CommonUtils;
import uren.com.colorfulbrains.utils.ShapeUtil;


public class MainActivity extends AppCompatActivity {

    RelativeLayout mainActLayout;
    ImageView appIconImgv;
    SwipeRefreshLayout refresh_layout;
    Button tryAgainButton;
    TextView networkTryDesc;

    private FirebaseAuth firebaseAuth;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());
        CommonUtils.hideKeyBoard(this);
        initVariables();


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else
            fillUserInfo();
    }

    private void initVariables() {
        mainActLayout = findViewById(R.id.mainActLayout);
        refresh_layout = findViewById(R.id.refresh_layout);
        appIconImgv = findViewById(R.id.appIconImgv);
        tryAgainButton = findViewById(R.id.tryAgainButton);
        networkTryDesc = findViewById(R.id.networkTryDesc);
        AnimationUtil.blink(MainActivity.this, appIconImgv);

        tryAgainButton.setBackground(ShapeUtil.getShape(MainActivity.this.getResources().getColor(R.color.DodgerBlue),
                MainActivity.this.getResources().getColor(R.color.White), GradientDrawable.RECTANGLE, 50, 2));

        setPullToRefresh();
        addListeners();
    }

    private void addListeners() {
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProcess();
            }
        });
    }

    private void setPullToRefresh() {
        refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loginProcess();
            }
        });
    }

    public void fillUserInfo() {
        loginProcess();
    }

    public void loginProcess() {
        if (!CommonUtils.isNetworkConnected(MainActivity.this)) {
            tryAgainButton.setVisibility(View.VISIBLE);
            networkTryDesc.setVisibility(View.VISIBLE);
            CommonUtils.connectionErrSnackbarShow(mainActLayout, MainActivity.this);
            refresh_layout.setRefreshing(false);
        } else {
            tryAgainButton.setVisibility(View.GONE);
            networkTryDesc.setVisibility(View.GONE);
            startLoginProcess();
        }
    }

    public void startLoginProcess() {

        startActivity(new Intent(MainActivity.this, NextActivity.class));

        /*UserDBHelper.getUser(firebaseAuth.getCurrentUser().getUid(), new CompleteCallback() {
            @Override
            public void onComplete(Object object) {
                user = (User) object;

                if (user != null) {
                    EventBus.getDefault().postSticky(new UserBus(user));
                    refresh_layout.setRefreshing(false);
                    updateDeviceTokenForFCM();
                    startActivity(new Intent(MainActivity.this, NextActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailed(String message) {
                CommonUtils.showToastShort(MainActivity.this, message);
                refresh_layout.setRefreshing(false);
            }
        });*/
    }

    public void updateDeviceTokenForFCM() {
        /*TokenDBHelper.updateTokenSigninValue(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid(), CHAR_E,
                new OnCompleteCallback() {
                    @Override
                    public void OnCompleted() {
                        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                            @Override
                            public void onSuccess(InstanceIdResult instanceIdResult) {
                                String deviceToken = instanceIdResult.getToken();
                                TokenDBHelper.sendTokenToServer(deviceToken, Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid());
                            }
                        });
                    }

                    @Override
                    public void OnFailed(String message) {

                    }
                });*/
    }
}
