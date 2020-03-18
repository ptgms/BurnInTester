//TODO: Finish this class for the recyclerview
package com.ptgms.burnintester;

public class addPopUp {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public addPopUp(int ImageResource, String text1, String text2) {
        mImageResource = ImageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
