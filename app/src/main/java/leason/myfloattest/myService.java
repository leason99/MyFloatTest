package leason.myfloattest;
import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import jp.co.recruit_lifestyle.android.floatingview.FloatingViewListener;
import jp.co.recruit_lifestyle.android.floatingview.FloatingViewManager;

import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * Created by leason on 2016/4/8.
 */
public class myService extends Service implements FloatingViewListener {
    FloatingViewManager mFloatingViewManager;
    PopupWindow pwindo;
    Button btn;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final LayoutInflater inflater = LayoutInflater.from(this);

        final  View floatview= inflater.inflate(R.layout.floathead, null);

       // final ImageView iconView = (ImageView)floatview.findViewById(R.id.imageView);
        final ImageView iconView =new ImageView(this);
        iconView.setImageResource(R.mipmap.ic_launcher);
        //final View test = floatview.findViewById(R.id.test);
        iconView.setOnClickListener(
                new View.OnClickListener() {
                    //    iconView.setOnClickListener(
                    //    new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LayoutInflater layoutInflater = (LayoutInflater) getApplication().getSystemService(LAYOUT_INFLATER_SERVICE);
                        //  View popupView = layoutInflater.inflate(R.layout.popup, null);
                        View popupView = layoutInflater.inflate(R.layout.popup, null);
                         btn =new Button(getApplication());
                        pwindo = new PopupWindow(btn);
                        pwindo.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                        pwindo.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        pwindo.showAsDropDown(iconView,100,-100);
                        pwindo.update();
                        pwindo.isShowing();
                    }
                }
        );

        final DisplayMetrics metrics = new DisplayMetrics();
        mFloatingViewManager = new FloatingViewManager(this, this);
        mFloatingViewManager.setFixedTrashIconImage(R.drawable.ic_trash_fixed);
        mFloatingViewManager.setActionTrashIconImage(R.drawable.ic_trash_action);
        final FloatingViewManager.Options options = new FloatingViewManager.Options();
        options.shape = FloatingViewManager.SHAPE_CIRCLE;
        options.overMargin = (int) (16 * metrics.density);
     //   WindowManager winma= (WindowManager) getSystemService(WINDOW_SERVICE);
      //  winma.addView(floatview);
        mFloatingViewManager.addViewToWindow(iconView, options);
          return 1;
    }

    @Override
    public void onFinishFloatingView() {
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
