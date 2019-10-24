package com.top.wisdom.net.Dowload;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import com.top.wisdom.net.Callback.IRequest;
import com.top.wisdom.net.Callback.ISuccess;
import com.top.wisdom.utils.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * 作者：ProZoom
 * 时间：2018/10/19
 * 描述：异步保存文件
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private IRequest iRequest;
    private ISuccess iSuccess;

    private Context mContext;


    SaveFileTask(Context mContext, IRequest iRequest, ISuccess iSuccess) {
        this.mContext = mContext;
        this.iRequest = iRequest;
        this.iSuccess = iSuccess;
    }

    @Override
    protected File doInBackground(Object[] objects) {
        //下载路径
        String download_dir = (String) objects[0];
        //扩展名，后缀
        String extension = (String) objects[1];
        //文件名
        final String name = (String) objects[3];
        //响应体
        final ResponseBody body = (ResponseBody) objects[2];

        final InputStream is = body.byteStream();

        if (download_dir == null) {
            download_dir= Environment.getDownloadCacheDirectory().getAbsolutePath();
        }
        if (extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, download_dir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, download_dir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (iSuccess != null) {
            iSuccess.onSuccess(file.getPath());
        }
        if (iRequest != null) {
            iRequest.onRequestEnd();
        }
        autoInstallApk(mContext, file);
    }


    /**
     * 自动安装
     * @param context
     * @param file
     */
    private void autoInstallApk(Context context, File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }
}
