package com.assignment.imagesearch.model;

import android.os.Parcel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
public class ImageTest {

    public Image image;

    @Before
    public void setUp() throws Exception {
        image = new Image(78789, "description", "id", "link", "title", "type");
    }

    @Test
    public void testGetDatetime() {
        Assert.assertEquals(78789, image.datetime);
    }

    @Test
    public void testSetDatetime() {
        image.setDatetime(98987);
        int dateTime = image.getDatetime();
        Assert.assertEquals(98987, dateTime);
    }

    @Test
    public void testGetDescription() {
        Assert.assertEquals("description", image.getDescription());
    }

    @Test
    public void testImageIsParcelable() {
        Parcel parcel = Parcel.obtain();
        image.writeToParcel(parcel, image.describeContents());
        parcel.setDataPosition(0);
        Image createdFromParcel = (Image) Image.CREATOR.createFromParcel(parcel);
        assertEquals(78789,createdFromParcel.getDatetime());
    }

}