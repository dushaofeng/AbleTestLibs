package com.able.libs;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by du.shaofeng on 2017/11/30.
 */
@EActivity(R.layout.easypermission_activity_layout)
public class EasyPermissionTestActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    private static final int PERMISSION_SD_WRITE_READ = 1;

    @Click
    void permissionCheck() {
        Log.d("ssss", "permissionCheck ");
        methodRequiresTwoPermission();
    }

    /*
    检查权限
     */
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Log.d("ssss", "methodRequiresTwoPermission 已经拥有权限，尽享操作");
        } else {
            Log.d("ssss", "methodRequiresTwoPermission 没有权限，正在提交申请");
            EasyPermissions.requestPermissions(this, "阿杜需要申请权限读写SD，请批准？", PERMISSION_SD_WRITE_READ, perms);
        }
    }

    /*
    EasyPermissions需要
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    /*
    权限申请成功
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("ssss", "权限申请成功 onPermissionsDenied:" + requestCode + ":" + perms.size());
    }

    /*
    权限申请失败
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("ssss", "权限申请失败 onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle("我们需要一些权限来继续操作")
                    .setRationale("这些权限主要用于...但是您已经选择了永久拒绝，接下来会打开应用中心，请开放这些权限。")
                    .build()
                    .show();
        }
    }

    /*
    权限申请失败后提示用户
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Toast.makeText(this, "一些权限您没有同意，因此功能可能受到限制", Toast.LENGTH_SHORT).show();
        }
    }
}
