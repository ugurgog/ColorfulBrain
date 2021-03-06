package uren.com.colorfulbrains.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;

import io.fabric.sdk.android.Fabric;
import uren.com.colorfulbrains.Models.LoginUser;
import uren.com.colorfulbrains.Models.User;
import uren.com.colorfulbrains.R;
import uren.com.colorfulbrains.utils.CommonUtils;
import uren.com.colorfulbrains.utils.ShapeUtil;
import uren.com.colorfulbrains.utils.dialogBoxUtil.DialogBoxUtil;
import uren.com.colorfulbrains.utils.dialogBoxUtil.Interfaces.InfoDialogBoxCallback;

import static uren.com.colorfulbrains.Constants.CustomConstants.LOGIN_USER;

public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener {

    RelativeLayout registerLayout;
    EditText nameET;
    EditText emailET;
    EditText passwordET;
    Button btnRegister;

    //Local
    LoginUser newLoginUser;
    String name;
    String userEmail;
    String userPassword;
    ProgressDialog progressDialog;

    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Fabric.with(this, new Crashlytics());
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

        init();
        setShapes();
        //BitmapConversion.setBlurBitmap(RegisterActivity.this, registerLayout,
        //        R.drawable.login_background, 0.2f, 20.5f, null);
    }

    public void setShapes(){
        nameET.setBackground(ShapeUtil.getShape(getResources().getColor(R.color.transparent),
                getResources().getColor(R.color.White), GradientDrawable.RECTANGLE, 20, 4));
        emailET.setBackground(ShapeUtil.getShape(getResources().getColor(R.color.transparent),
                getResources().getColor(R.color.White), GradientDrawable.RECTANGLE, 20, 4));
        passwordET.setBackground(ShapeUtil.getShape(getResources().getColor(R.color.transparent),
                getResources().getColor(R.color.White), GradientDrawable.RECTANGLE, 20, 4));
        btnRegister.setBackground(ShapeUtil.getShape(getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.White), GradientDrawable.RECTANGLE, 20, 4));
    }

    private void init() {
        registerLayout = findViewById(R.id.registerLayout);
        nameET = findViewById(R.id.input_name);
        emailET = findViewById(R.id.input_email);
        passwordET = findViewById(R.id.input_password);
        btnRegister = findViewById(R.id.btnRegister);
        registerLayout.setOnClickListener(this);
        nameET.setOnClickListener(this);
        emailET.setOnClickListener(this);
        passwordET.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {

        if (v == btnRegister) {
            if (checkNetworkConnection())
                btnRegisterClicked();
        }
    }

    public boolean checkNetworkConnection() {
        if (!CommonUtils.isNetworkConnected(RegisterActivity.this)) {
            CommonUtils.connectionErrSnackbarShow(registerLayout, RegisterActivity.this);
            return false;
        } else
            return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    Objects.requireNonNull(imm).hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /*****************************CLICK EVENTS******************************/

    private void btnRegisterClicked() {

        progressDialog.setMessage(this.getString(R.string.REGISTERING_USER));
        progressDialog.show();

        name = nameET.getText().toString();
        userEmail = emailET.getText().toString();
        userPassword = passwordET.getText().toString();

        //validation controls
        if (!checkValidation(name, userEmail, userPassword)) {
            return;
        }

        createUser(name, userEmail, userPassword);

    }

    private boolean checkValidation(String name, String email, String password) {

        //username validation
        if (!Validation.getInstance().isValidUserName(this, name)) {
            progressDialog.dismiss();
            DialogBoxUtil.showInfoDialogBox(RegisterActivity.this,
                    Validation.getInstance().getErrorMessage(), null, new InfoDialogBoxCallback() {
                        @Override
                        public void okClick() {

                        }
                    });

            return false;
        }

        //email validation
        if (!Validation.getInstance().isValidEmail(this, email)) {
            progressDialog.dismiss();
            DialogBoxUtil.showInfoDialogBox(RegisterActivity.this,
                    Validation.getInstance().getErrorMessage(), null, new InfoDialogBoxCallback() {
                        @Override
                        public void okClick() {

                        }
                    });
            return false;
        }

        //password validation
        if (!Validation.getInstance().isValidPassword(this, password)) {
            //Toast.makeText(this, Validation.getInstance().getErrorMessage() , Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

            DialogBoxUtil.showInfoDialogBox(RegisterActivity.this,
                    Validation.getInstance().getErrorMessage(), null, new InfoDialogBoxCallback() {
                        @Override
                        public void okClick() {

                        }
                    });

            return false;
        }

        return true;
    }

    private void createUser(final String userName, final String userEmail, final String userPassword) {

        final Context context = this;

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Log.i("Info", "CreateUser : Success");
                            progressDialog.dismiss();
                            setUserInfo(userEmail);
                            addUserToSystem(userName, userEmail);

                            //startAppIntroPage();
                            //startMainPage();
                        } else {
                            progressDialog.dismiss();
                            Log.i("Info", "CreateUser : Fail");
                            try {
                                throw Objects.requireNonNull(task.getException());
                            } catch (FirebaseAuthUserCollisionException e) {
                                DialogBoxUtil.showInfoDialogBox(RegisterActivity.this,
                                        context.getString(R.string.COLLISION_EXCEPTION), null, new InfoDialogBoxCallback() {
                                            @Override
                                            public void okClick() {

                                            }
                                        });

                            } catch (Exception e) {
                                DialogBoxUtil.showInfoDialogBox(RegisterActivity.this,
                                        context.getString(R.string.UNKNOWN_ERROR) + "(" + e.toString() + ")", null, new InfoDialogBoxCallback() {
                                            @Override
                                            public void okClick() {

                                            }
                                        });
                            }
                        }
                    }
                });
    }

    private void addUserToSystem( String username, String email){

        User user = new User();
        user.setId(mAuth.getCurrentUser().getUid());
        user.setEmail(email);
        user.setAdmin(false);

        startAppIntroPage();


        /*UserDBHelper.addUser(user, new OnCompleteCallback() {
            @Override
            public void OnCompleted() {
                startAppIntroPage();
            }

            @Override
            public void OnFailed(String message) {

            }
        });*/
    }

    private void setUserInfo(String userEmail) {

        newLoginUser = new LoginUser();
        newLoginUser.setEmail(userEmail);
        newLoginUser.setUserId(Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
    }

    public void startAppIntroPage() {
        Intent intent = new Intent(RegisterActivity.this, AppIntroductionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(LOGIN_USER, newLoginUser);
        startActivity(intent);
    }
}
