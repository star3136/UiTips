package uitps.com.uitips;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.uitips.LogUtils;
import com.uitips.UiTips;
import com.uitips.view.IWarnDialog;
import com.uitips.view.IWarnView;
import com.uitips.view.common.CustomDialog;

public class MainActivity extends AppCompatActivity {
    ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_show_loading_view:
                UiTips.with(this).warnView().hide();
                UiTips.with(this).loadingView().content("正在加载").show(container);
                break;
            case R.id.btn_show_warn_view:
                UiTips.with(this).loadingView().hide();
                UiTips.with(this).warnView().type(IWarnView.EMPTY_DATA).content("数据为空").show(container);
                break;
            case R.id.btn_show_loading_dialog:
                UiTips.with(this).loadingDialog().content("正在加载").cancelable(false).show();
                break;
            case R.id.btn_show_warn_dialog:
                UiTips.with(this)
                        .warnDialog()
                        .content("这是内容")
                        .title("标题")
                        .okButtonText("确定")
                        .okButtonClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LogUtils.d("确定");
                                UiTips.with(MainActivity.this).warnDialog().hide();
                            }
                        })
                        .cancelButtonText("取消")
                        .onCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                LogUtils.d("取消");
                                UiTips.with(MainActivity.this).warnDialog().hide();
                            }
                        })
                        .style(IWarnDialog.TWO_BUTTON)
                        .show();

                break;
        }
    }
}
