package uren.com.colorfulbrains.utils.dialogBoxUtil;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;
import uren.com.colorfulbrains.R;
import uren.com.colorfulbrains.utils.dialogBoxUtil.Interfaces.GifDialogListener;


public class GifDialogBox {
    int gifImageResource;

    private GifDialogBox(GifDialogBox.Builder builder) {
        String title = builder.title;
        String message = builder.message;
        Activity activity = builder.activity;
        GifDialogListener pListener = builder.pListener;
        GifDialogListener nListener = builder.nListener;
        int pBtnColor = builder.pBtnColor;
        int nBtnColor = builder.nBtnColor;
        int pBtnVisibleType = builder.pBtnVisibleType;
        int nBtnVisibleType = builder.nBtnVisibleType;
        int titleVisibleType = builder.titleVisibleType;
        String positiveBtnText = builder.positiveBtnText;
        String negativeBtnText = builder.negativeBtnText;
        this.gifImageResource = builder.gifImageResource;
        boolean cancel = builder.cancel;
        long durationTime = builder.durationTime;
    }

    public static class Builder {
        private String title;
        private String message;
        private String positiveBtnText;
        private String negativeBtnText;
        private int pBtnColor;
        private int nBtnColor;
        private int pBtnVisibleType;
        private int nBtnVisibleType;
        private int titleVisibleType;
        private Activity activity;
        private GifDialogListener pListener;
        private GifDialogListener nListener;
        private boolean cancel;
        int gifImageResource;
        private long durationTime;

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public GifDialogBox.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public GifDialogBox.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public GifDialogBox.Builder setPositiveBtnText(String positiveBtnText) {
            this.positiveBtnText = positiveBtnText;
            return this;
        }

        public GifDialogBox.Builder setPositiveBtnBackground(int pBtnColor) {
            this.pBtnColor = pBtnColor;
            return this;
        }

        public GifDialogBox.Builder setNegativeBtnText(String negativeBtnText) {
            this.negativeBtnText = negativeBtnText;
            return this;
        }

        public GifDialogBox.Builder setNegativeBtnBackground(int nBtnColor) {
            this.nBtnColor = nBtnColor;
            return this;
        }

        public GifDialogBox.Builder setPositiveBtnVisibility(int visibleType) {
            this.pBtnVisibleType = visibleType;
            return this;
        }

        public GifDialogBox.Builder setNegativeBtnVisibility(int visibleType) {
            this.nBtnVisibleType = visibleType;
            return this;
        }

        public GifDialogBox.Builder setTitleVisibility(int visibleType) {
            this.titleVisibleType = visibleType;
            return this;
        }

        public GifDialogBox.Builder setDurationTime(long durationTime) {
            this.durationTime = durationTime;
            return this;
        }

        public GifDialogBox.Builder OnPositiveClicked(GifDialogListener pListener) {
            this.pListener = pListener;
            return this;
        }

        public GifDialogBox.Builder OnNegativeClicked(GifDialogListener nListener) {
            this.nListener = nListener;
            return this;
        }

        public GifDialogBox.Builder isCancellable(boolean cancel) {
            this.cancel = cancel;
            return this;
        }

        public GifDialogBox.Builder setGifResource(int gifImageResource) {
            this.gifImageResource = gifImageResource;
            return this;
        }

        public GifDialogBox build() {
            final Dialog dialog = new Dialog(this.activity);
            dialog.requestWindowFeature(1);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
            dialog.setCancelable(this.cancel);
            dialog.setContentView(R.layout.layout_gif_dialog_box);
            TextView title1 = dialog.findViewById(R.id.title);
            TextView message1 = dialog.findViewById(R.id.message);
            Button nBtn = dialog.findViewById(R.id.negativeBtn);
            Button pBtn = dialog.findViewById(R.id.positiveBtn);
            GifImageView gifImageView = dialog.findViewById(R.id.gifImageView);

            gifImageView.setImageResource(this.gifImageResource);
            nBtn.setVisibility(nBtnVisibleType);
            pBtn.setVisibility(pBtnVisibleType);
            title1.setText(this.title);
            message1.setText(this.message);

            if (this.positiveBtnText != null) {
                pBtn.setText(this.positiveBtnText);
            }

            if (this.negativeBtnText != null) {
                nBtn.setText(this.negativeBtnText);
            }

            if (this.pListener != null) {
                pBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.pListener.OnClick();
                        dialog.dismiss();
                    }
                });
            } else {
                pBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }

            if (this.nListener != null) {
                nBtn.setVisibility(View.VISIBLE);
                nBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.nListener.OnClick();
                        dialog.dismiss();
                    }
                });
            }

            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (this.durationTime > 0) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog.isShowing())
                            dialog.dismiss();
                    }
                }, this.durationTime);
            }
            return new GifDialogBox(this);
        }
    }
}
