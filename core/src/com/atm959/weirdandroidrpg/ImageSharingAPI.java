package com.atm959.weirdandroidrpg;

import com.badlogic.gdx.graphics.Pixmap;

/**
 * Created by atm959 on 8/13/2022.
 */

//The image sharing API interface
//Different platforms can implement this function in different ways
public interface ImageSharingAPI {
	public void shareImage(String text, Pixmap image); //Share the image and accompanying text
}
