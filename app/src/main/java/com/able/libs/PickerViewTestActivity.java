package com.able.libs;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.model.IPickerViewData;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;

/**
 * Created by du.shaofeng on 2018/2/7.
 */
@EActivity(R.layout.picker_activity_layout)
public class PickerViewTestActivity extends AppCompatActivity {
    private OptionsPickerView pvOptions;
    private ArrayList<PickerBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    @AfterViews
    void onAfterViews() {
        initOptionPicker();
    }

    @Click
    void show_picker() {
        pvOptions.show();
    }

    private void initOptionPicker() {
        options1Items.add(new PickerBean(0, "生活"));
        options1Items.add(new PickerBean(1, "工作"));
        options1Items.add(new PickerBean(2, "学习"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("生活1");
        options2Items_01.add("生活2");
        options2Items_01.add("生活3");
        options2Items_01.add("生活4");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("工作1");
        options2Items_02.add("工作2");
        options2Items_02.add("工作3");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("学习1");
        options2Items_03.add("学习2");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);


        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).labelName + "--" + options2Items.get(options1).get(options2);
                Toast.makeText(PickerViewTestActivity.this, tx, Toast.LENGTH_LONG).show();
            }
        })
                .setTitleText("选择类别")
                .setContentTextSize(18)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.YELLOW)
                .setSubmitColor(Color.YELLOW)
                .setTextColorCenter(Color.LTGRAY)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();

        pvOptions.setPicker(options1Items, options2Items);//二级选择器
    }

    class PickerBean implements IPickerViewData {
        long labelId;
        String labelName;

        PickerBean(int id, String name) {
            labelId = id;
            labelName = name;
        }

        @Override
        public String getPickerViewText() {
            return labelName;
        }
    }
}
