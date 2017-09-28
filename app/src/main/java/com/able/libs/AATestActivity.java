package com.able.libs;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.able.libs.dbFlow.DBFlowDatabase;
import com.able.libs.dbFlow.UserModel;
import com.able.libs.dbFlow.UserModel_Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
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
        userModel.name = "dushaofeng";
        userModel.sex = 1;
        userModel.age = 1;
        userModel.save();

        userModel = new UserModel();
        userModel.name = "dushaofeng1";
        userModel.sex = 1;
        userModel.age = 2;
        userModel.save();

        userModel = new UserModel();
        userModel.name = "dushaofeng2";
        userModel.sex = 1;
        userModel.age = 2;
        userModel.save();

        userModel = new UserModel();
        userModel.name = "dushaofeng2";
        userModel.sex = 1;
        userModel.age = 2;
        userModel.save();

    }

    @Click
    void getDb() {
        Log.d(TAG, "getDb");
        List<UserModel> users = new Select()
                .from(UserModel.class)
                .queryList();
        Log.d(TAG, "users count:" + users.size());
        for (UserModel user : users) {
            Log.d(TAG, "users:" + user.toString());
        }
    }

    @Click
    void delDb() {
        long deletedNumber = SQLite.select()
                .from(UserModel.class)
                .where(UserModel_Table.name.eq("dushaofeng2"))
                .queryList()
                .size();
        SQLite.delete(UserModel.class)
                .where(UserModel_Table.name.eq("dushaofeng2"))
                .execute();
        Log.d(TAG, "delte count:" + deletedNumber);
    }

    @Click
    void updatelDb() {
        SQLite.update(UserModel.class)
                .set(UserModel_Table.age.eq(24), UserModel_Table.sex.eq(99))
                .where(UserModel_Table.name.eq("dushaofeng2"))
                .async()
                .execute();
    }

    /*
    批量插入操作
     */
    @Click
    void insertManyDb() {
        List<UserModel> userModelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserModel userModel = new UserModel();
            userModel.name = "dushaofeng" + i;
            userModel.sex = i;
            userModel.age = i * 10;
            userModelList.add(userModel);
        }
        DatabaseWrapper databaseWrapper = FlowManager.getDatabase(DBFlowDatabase.class).getWritableDatabase();
        //这里可以批量save, insert, or update
        //参考https://agrosner.gitbooks.io/dbflow/content/StoringData.html
        FastStoreModelTransaction
                .insertBuilder(FlowManager.getModelAdapter(UserModel.class))
                .addAll(userModelList)
                .build()
                .execute(databaseWrapper);

    }
}
