package uren.com.colorfulbrains.utils.dataModelUtil;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import uren.com.colorfulbrains.Models.User;
import uren.com.colorfulbrains.R;
import uren.com.colorfulbrains.utils.CommonUtils;
import uren.com.colorfulbrains.utils.ShapeUtil;

import static uren.com.colorfulbrains.Constants.CustomConstants.CHAR_AMPERSAND;

public class UserDataUtil {

    public static void setNameOrUserName(String name, TextView textView) {
        int nameMaxLen = 25;

        if (name != null && !name.isEmpty()) {
            if (name.length() > nameMaxLen)
                textView.setText(name.trim().substring(0, nameMaxLen) + "...");
            else
                textView.setText(name);
        } else
            textView.setVisibility(View.GONE);
    }

    public static String getNameOrUsername(String name, String username) {
        int nameMaxLen = 25;

        if (name != null && !name.isEmpty()) {
            if (name.length() > nameMaxLen)
                return name.trim().substring(0, nameMaxLen) + "...";
            else
                return name;
        } else if (username != null && !username.isEmpty()) {
            if (username.length() > nameMaxLen)
                return CHAR_AMPERSAND + username.trim().substring(0, nameMaxLen) + "...";
            else
                return CHAR_AMPERSAND + username;
        } else
            return "unknown";
    }

    public static String getNameOrUsernameFromUser(User user) {
        int nameMaxLen = 25;

        if (user == null) return "unknown";

        if (user.getName() != null && !user.getName().isEmpty()) {
            if (user.getName().length() > nameMaxLen)
                return user.getName().trim().substring(0, nameMaxLen) + "...";
            else
                return user.getName();
        }else
            return "unknown";
    }

    public static void setName(String name, TextView nameTextView) {
        int nameMaxLen = 25;
        if (name != null && nameTextView != null && !name.isEmpty()) {
            nameTextView.setVisibility(View.VISIBLE);
            if (name.length() > nameMaxLen)
                nameTextView.setText(name.trim().substring(0, nameMaxLen) + "...");
            else
                nameTextView.setText(name);
        } else if (nameTextView != null)
            nameTextView.setVisibility(View.GONE);
    }

    public static String getShortenUserName(String name) {
        StringBuilder returnValue = new StringBuilder();
        if (name != null && !name.trim().isEmpty()) {
            String[] seperatedName = name.trim().split(" ");
            for (String word : seperatedName) {
                if (returnValue.length() < 3)
                    returnValue.append(word.substring(0, 1).toUpperCase());
            }
        }

        return returnValue.toString();
    }

    public static String getUsernameFromNameWhenLoginWithGoogle(String name) {
        StringBuilder returnValue = new StringBuilder();
        if (name != null && !name.trim().isEmpty()) {
            String[] seperatedName = name.trim().split(" ");
            for (String word : seperatedName) {
                returnValue.append(word);
            }
        }

        return returnValue.toString();
    }

    public static int setProfilePicture(Context context, String url, String name, TextView shortNameTv,
                                        ImageView profilePicImgView, boolean circleColorVal) {
        if (context == null) return 0;

        boolean picExist = false;
        if (url != null && !url.trim().isEmpty()) {
            shortNameTv.setVisibility(View.GONE);
            Glide.with(context)
                    .load(url)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profilePicImgView);
            picExist = true;
            //profilePicImgView.setPadding(1, 1, 1, 1); // degerler asagidaki imageShape strokeWidth ile aynı tutulmalı
        } else {
            if (name != null && !name.trim().isEmpty()) {
                shortNameTv.setVisibility(View.VISIBLE);
                shortNameTv.setText(UserDataUtil.getShortenUserName(name));
                profilePicImgView.setImageDrawable(null);
            } else {
                shortNameTv.setVisibility(View.GONE);
                Glide.with(context)
                        .load(R.drawable.ic_person_white_24dp)
                        .apply(RequestOptions.circleCropTransform())
                        .into(profilePicImgView);
            }
        }

        GradientDrawable imageShape;
        int colorCode = CommonUtils.getDarkRandomColor(context);

        if (circleColorVal) {
            if (picExist) {
                imageShape = ShapeUtil.getShape(context.getResources().getColor(R.color.White),
                        context.getResources().getColor(R.color.DodgerBlue),
                        GradientDrawable.OVAL, 50, 3);
            } else {
                imageShape = ShapeUtil.getShape(context.getResources().getColor(R.color.DodgerBlue),
                        context.getResources().getColor(R.color.DodgerBlue),
                        GradientDrawable.OVAL, 50, 3);
            }
        } else
            imageShape = ShapeUtil.getShape(context.getResources().getColor(colorCode),
                    context.getResources().getColor(R.color.White),
                    GradientDrawable.OVAL, 50, 3);

        profilePicImgView.setBackground(imageShape);

        if (!picExist)
            return colorCode;
        else return 0;
    }

    public static void updateInviteButton(Context context, Button displayButton, Boolean isHideKeyboard) {
        if (isHideKeyboard != null && isHideKeyboard)
            CommonUtils.hideKeyBoard(context);

        GradientDrawable buttonShape;
        displayButton.setText(context.getResources().getString(R.string.invite));
        displayButton.setTextColor(context.getResources().getColor(R.color.Coral));
        buttonShape = ShapeUtil.getShape(context.getResources().getColor(R.color.White),
                context.getResources().getColor(R.color.Coral), GradientDrawable.RECTANGLE, 15, 3);

        displayButton.setBackground(buttonShape);
    }
}
