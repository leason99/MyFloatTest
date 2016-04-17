package leason.myfloattest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  static   MainActivity instance;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;
        final boolean canShow = showChatHead(context);

        if (!canShow) {
            // シンプルなFloatingViewの表示許可設定
            @SuppressLint("InlinedApi")
            final Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
            startActivityForResult(intent, 100);
        }

         btn=(Button) findViewById(R.id.button3);

        btn.setOnClickListener(
                new Button.OnClickListener() {
                    //    iconView.setOnClickListener(
                    //    new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                        //  View popupView = layoutInflater.inflate(R.layout.popup, null);
                        View popupView = layoutInflater.inflate(R.layout.popup, null);
                      PopupWindow  pwindo = new PopupWindow(popupView);
                        pwindo.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                        pwindo.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

                        pwindo.showAsDropDown(btn);
                        pwindo.update();
                        pwindo.isShowing();
                    }
                });



        }

    private boolean showChatHead(Context context) {
        // API22以下かチェック
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            context.startService(new Intent(context, myService.class));
            return true;
        }

        // 他のアプリの上に表示できるかチェック
        if (Settings.canDrawOverlays(context)) {
            context.startService(new Intent(context, myService.class));
            return true;
        }

        return false;
    }


    }

