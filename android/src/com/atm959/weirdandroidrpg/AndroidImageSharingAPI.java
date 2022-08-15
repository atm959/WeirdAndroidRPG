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

//The Android implementation of image sharing
public class AndroidImageSharingAPI implements ImageSharingAPI {
	public Context context; //Certain Android API things need the application context

	//This constructor sets the local instance of the context
	public AndroidImageSharingAPI(Context appContext){
		context = appContext;
	}

	//Share the image and text to another app that supports it such as Discord, Instagram, etc.
	@Override
	public void shareImage(String text, Pixmap image) {
		//Before the image can be shared, it has to be saved to the external app storage
		FileHandle imageHandle = Gdx.files.external("./weirdAndroidRPGSharingImage.png");
		PixmapIO.writePNG(imageHandle, image);

		//Then, using the FileProvider that's defined in AndroidManifest.xml, get the URI of the saved file
		Uri imageURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileprovider", imageHandle.file().getAbsoluteFile());

		//Create the sharing intent
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND); //The action that the app that's shared to should take
		intent.setType("image/png"); //The type of data to send
		intent.putExtra(Intent.EXTRA_TEXT, text); //The text that should accompany the image
		intent.putExtra(Intent.EXTRA_STREAM, imageURI); //The URI of the image

		//Create a chooser for the "share-to" app to be chosen
		Intent chooser = Intent.createChooser(intent, "Share using...");

		//I think this sets the permissions of all the apps that can be chosen so they can freely access the file
		//This is a rare instance of code that I copied from online
		List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY);
		for (ResolveInfo resolveInfo : resInfoList) {
			String packageName = resolveInfo.activityInfo.packageName;
			context.grantUriPermission(packageName, imageURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
		}

		//Finally, start the chooser activity which will let the app to share to be chosen
		context.startActivity(chooser);
	}
}
