package uren.com.colorfulbrains.management;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import uren.com.colorfulbrains.AssignTaskFragment;
import uren.com.colorfulbrains.management.fragmentControllers.FragNavController;
import uren.com.colorfulbrains.management.fragmentControllers.FragNavTransactionOptions;
import uren.com.colorfulbrains.management.fragmentControllers.FragmentHistory;
import uren.com.colorfulbrains.Models.User;
import uren.com.colorfulbrains.R;
import uren.com.colorfulbrains.evetBusModels.UserBus;
import uren.com.colorfulbrains.utils.AdMobUtils;

import static uren.com.colorfulbrains.Constants.CustomConstants.ANIMATE_DOWN_TO_UP;
import static uren.com.colorfulbrains.Constants.CustomConstants.ANIMATE_LEFT_TO_RIGHT;
import static uren.com.colorfulbrains.Constants.CustomConstants.ANIMATE_RIGHT_TO_LEFT;
import static uren.com.colorfulbrains.Constants.CustomConstants.ANIMATE_UP_TO_DOWN;
import static uren.com.colorfulbrains.management.fragmentControllers.FragNavController.TAB1;
import static uren.com.colorfulbrains.management.fragmentControllers.FragNavController.TAB2;
import static uren.com.colorfulbrains.management.fragmentControllers.FragNavController.TAB3;


public class NextActivity extends FragmentActivity implements
        BaseFragment.FragmentNavigation,
        FragNavController.TransactionListener,
        FragNavController.RootFragmentListener {

    public static Activity thisActivity;

    public static FrameLayout contentFrame;
    public LinearLayout profilePageMainLayout;
    public TabLayout bottomTabLayout;

    public LinearLayout tabMainLayout;

    private LinearLayout linearLayoutOne ;
    private LinearLayout linearLayout2 ;
    private LinearLayout linearLayout3;

    public String ANIMATION_TAG;

    public FragNavTransactionOptions transactionOptions;
    public AssignTaskFragment assignTaskFragment;

    private int[] mTabIconsSelected = {
            R.drawable.ic_my_tasks_white_24dp,
            R.drawable.ic_send_white_24dp,
            R.drawable.ic_person_white_24dp};

    public String[] TABS;

    private FragNavController mNavController;

    private FragmentHistory fragmentHistory;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Fabric.with(this, new Crashlytics());
        thisActivity = this;

        initValues();

        fragmentHistory = new FragmentHistory();

        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.content_frame)
                .transactionListener(this)
                .rootFragmentListener(this, TABS.length)
                .build();

        switchTab(0);
        updateTabSelection(0);

        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabSelectionControl(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mNavController.clearStack();
                tabSelectionControl(tab);
            }
        });
    }

    public void tabSelectionControl(TabLayout.Tab tab) {
        fragmentHistory.push(tab.getPosition());
        switchAndUpdateTabSelection(tab.getPosition());
    }

    private void initValues() {
        ButterKnife.bind(this);
        bottomTabLayout = findViewById(R.id.tablayout);
        profilePageMainLayout = findViewById(R.id.profilePageMainLayout);
        contentFrame = findViewById(R.id.content_frame);
        tabMainLayout = findViewById(R.id.tabMainLayout);
        adView = findViewById(R.id.adView);
        TABS = getResources().getStringArray(R.array.tab_name);

        //setStatusBarTransparent();
        initTab();
        MobileAds.initialize(NextActivity.this, getResources().getString(R.string.ADMOB_APP_ID));
        AdMobUtils.loadBannerAd(adView);
    }

    private void initTab() {
        if (bottomTabLayout != null) {
            for (int i = 0; i < TABS.length; i++) {
                bottomTabLayout.addTab(bottomTabLayout.newTab());
                TabLayout.Tab tab = bottomTabLayout.getTabAt(i);
            }
        }

        View headerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.custom_tab, null, false);

        linearLayoutOne = (LinearLayout) headerView.findViewById(R.id.ll);
        linearLayout2 = (LinearLayout) headerView.findViewById(R.id.ll2);
        linearLayout3 = (LinearLayout) headerView.findViewById(R.id.ll3);

        bottomTabLayout.getTabAt(0).setCustomView(linearLayoutOne);
        bottomTabLayout.getTabAt(1).setCustomView(linearLayout2);
        bottomTabLayout.getTabAt(2).setCustomView(linearLayout3);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true)
    public void customEventReceived(UserBus userBus){
        User user = userBus.getUser();
    }

    public void switchTab(int position) {
        mNavController.switchTab(position);
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {

        if (!mNavController.isRootFragment()) {
            setTransactionOption();
            mNavController.popFragment(transactionOptions);
        } else {

            if (fragmentHistory.isEmpty()) {
                super.onBackPressed();
            } else {

                if (fragmentHistory.getStackSize() > 1) {

                    int position = fragmentHistory.popPrevious();
                    switchAndUpdateTabSelection(position);
                } else {
                    switchAndUpdateTabSelection(0);
                    fragmentHistory.emptyStack();
                }
            }
        }
    }

    public void switchAndUpdateTabSelection(int position) {
        switchTab(position);
        updateTabSelection(position);
    }

    private void setTransactionOption() {
        if (transactionOptions == null) {
            transactionOptions = FragNavTransactionOptions.newBuilder().build();
        }

        if (ANIMATION_TAG != null) {
            switch (ANIMATION_TAG) {
                case ANIMATE_RIGHT_TO_LEFT:
                    transactionOptions.enterAnimation = R.anim.slide_from_right;
                    transactionOptions.exitAnimation = R.anim.slide_to_left;
                    transactionOptions.popEnterAnimation = R.anim.slide_from_left;
                    transactionOptions.popExitAnimation = R.anim.slide_to_right;
                    break;
                case ANIMATE_LEFT_TO_RIGHT:
                    transactionOptions.enterAnimation = R.anim.slide_from_left;
                    transactionOptions.exitAnimation = R.anim.slide_to_right;
                    transactionOptions.popEnterAnimation = R.anim.slide_from_right;
                    transactionOptions.popExitAnimation = R.anim.slide_to_left;
                    break;
                case ANIMATE_DOWN_TO_UP:
                    transactionOptions.enterAnimation = R.anim.slide_from_down;
                    transactionOptions.exitAnimation = R.anim.slide_to_up;
                    transactionOptions.popEnterAnimation = R.anim.slide_from_up;
                    transactionOptions.popExitAnimation = R.anim.slide_to_down;
                    break;
                case ANIMATE_UP_TO_DOWN:
                    transactionOptions.enterAnimation = R.anim.slide_from_up;
                    transactionOptions.exitAnimation = R.anim.slide_to_down;
                    transactionOptions.popEnterAnimation = R.anim.slide_from_down;
                    transactionOptions.popExitAnimation = R.anim.slide_to_up;
                    break;
                default:
                    transactionOptions = null;
            }
        } else
            transactionOptions = null;
    }

    public void updateTabSelection(int currentTab) {

        for (int i = 0; i < TABS.length; i++) {
            TabLayout.Tab selectedTab = bottomTabLayout.getTabAt(i);
            GradientDrawable drawable = (GradientDrawable)selectedTab.getCustomView().getBackground();

            if (currentTab != i)
                drawable.setColor(getResources().getColor(R.color.colorPrimaryDark));
            else {
                selectedTab.getCustomView().startAnimation(AnimationUtils.loadAnimation(NextActivity.thisActivity, R.anim.tab_anim));
                switch (i){
                    case TAB1:
                        drawable.setColor(getResources().getColor(R.color.gplus_color_2)); break;
                    case TAB2:
                        drawable.setColor(getResources().getColor(R.color.gplus_color_3)); break;
                    case TAB3:
                        drawable.setColor(getResources().getColor(R.color.gplus_color_4)); break;
                        default:
                            drawable.setColor(getResources().getColor(R.color.colorPrimaryDark)); break;
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }

    @Override
    public void pushFragment(Fragment fragment, String animationTag) {

        ANIMATION_TAG = animationTag;
        setTransactionOption();

        if (mNavController != null) {
            mNavController.pushFragment(fragment, transactionOptions);
        }
    }

    @Override
    public void onTabTransaction(Fragment fragment, int index) {

    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {

            case TAB1:
                return new AssignTaskFragment();
            case FragNavController.TAB2:
                return new AssignTaskFragment();
            case TAB3:
                return new AssignTaskFragment();

        }
        throw new IllegalStateException("Need to send an index that we know");
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {

    }
}