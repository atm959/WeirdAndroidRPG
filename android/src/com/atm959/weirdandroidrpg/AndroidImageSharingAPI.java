package com.atm959.weirdandroidrpg;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import androidx.core.content.FileProvider;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;

import java.util.List;

/**
 * Created by atm959 on 8/13/2022.
 */
public class AndroidImageSharingAPI implements ImageSharingAPI {
	public Context context;

	public AndroidImageSharingAPI(Context appContext){
		context = appContext;
	}

	@Override
	public void shareImage(String text, Pixmap image) {
		FileHandle imageHandle = Gdx.files.external("./weirdAndroidRPGSharingImage.png");
		PixmapIO.writePNG(imageHandle, image);
		Uri imageURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileprovider", imageHandle.file().getAbsoluteFile());

		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("image/png");
		intent.putExtra(Intent.EXTRA_TEXT, text);
		intent.putExtra(Intent.EXTRA_STREAM, imageURI);

		Intent chooser = Intent.createChooser(intent, "Share using...");
		List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY);

		for (ResolveInfo resolveInfo : resInfoList) {
			String packageName = resolveInfo.activityInfo.packageName;
			context.grantUriPermission(packageName, imageURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
		}
		context.startActivity(chooser);
	}
}
