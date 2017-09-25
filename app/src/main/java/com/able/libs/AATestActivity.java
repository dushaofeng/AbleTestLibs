package com.able.libs;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.able.libs.dbFlow.UserModel;
import com.able.libs.dbFlow.UserModel_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.List;

/**
 * Created by Able on 2017/9/18.
 */
@EActivity(R.layout.aa_layout)
public class AATestActivity extends AppCompatActivity {
    private String TAG = AATestActivity.class.getName();

    @AfterViews
    void afterViews() {

    }

    @Click
    void setDb() {
        Log.d(TAG, "setDb");
        UserModel userModel = new UserModel();
        userModel.id = Long.valueOf(1);
        userModel.name = "dushaofeng";
        userModel.sex = 1;
        userModel.age = 1;
        userModel.insert();

        userModel = new UserModel();
        userModel.id = Long.valueOf(1);
        userModel.name = "dushaofeng1";
        userModel.sex = 1;
        userModel.age = 2;
        userModel.insert();

        userModel = new UserModel();
        userModel.id = Long.valueOf(1);
        userModel.name = "dushaofeng2";
        userModel.sex = 1;
        userModel.age = 2;
        userModel.insert();

    }

    @Click
    void getDb() {
        Log.d(TAG, "getDb");
        List<UserModel> users = SQLite.select()
                .from(UserModel.class)
                .where(UserModel_Table.age.greaterThan(1))
                .queryList();
        Log.d(TAG, "users count:" + users.size());

    }
}
