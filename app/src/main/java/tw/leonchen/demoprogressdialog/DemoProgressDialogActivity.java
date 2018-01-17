package tw.leonchen.demoprogressdialog;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DemoProgressDialogActivity extends AppCompatActivity {

    private Button btn_dialog;
    private TextView tv_msg;
    private ProgressDialog dialog;
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_progress_dialog);

        tv_msg = (TextView)findViewById(R.id.tv_msg);
        btn_dialog = (Button)findViewById(R.id.btn_dialog);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();
            }
        });
    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(DemoProgressDialogActivity.this);
        //dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setTitle("info:");
        dialog.setMessage("loading, please wait for a while.");
        dialog.setMax(1000);
        dialog.show();

        handler = new MyHandler();
        new MyThread().start();
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                    tv_msg.setText("Mission Completed !!");
                    break;
            }
        }
    }

    private class MyThread extends Thread{
        public void run(){
            for(int i=1;i<=1000;i=i+5){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.setProgress(i);
            }

            handler.sendEmptyMessage(1);
            dialog.dismiss();
        }
    }
}
