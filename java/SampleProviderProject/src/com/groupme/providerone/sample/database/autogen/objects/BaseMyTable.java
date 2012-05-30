/*
 * This file has been auto-generated by ProviderOne
 *
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.autogen.objects;

import java.nio.ByteBuffer;
import java.util.Date;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;

import com.groupme.providerone.sample.database.SampleProvider;
import com.groupme.providerone.sample.database.autogen.PersistentObject;
import com.groupme.providerone.sample.database.objects.MyTable;
import com.groupme.providerone.sample.database.tables.MyTableInfo;

public abstract class BaseMyTable extends PersistentObject {


    public static MyTable fromCursor(Cursor cursor, MyTable.ColumnHelper helper) {
        MyTable obj = new MyTable();
        obj.hydrate(cursor, helper);
        return obj;
    }

    public static MyTable fromJson(JSONObject obj) {
        if (obj == null)
            return null;
        MyTable myTable = new MyTable();
        myTable.hydrate(obj);
        return myTable;
    }

    public static int getCount(String selection, String[] selectionArgs) {
        return getSingleIntResult(MyTableInfo.COUNT_URI, null, selection, selectionArgs, null);
    }

    public static int getIntSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleIntResult(MyTableInfo.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static long getLongSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleLongResult(MyTableInfo.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static double getDoubleSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleDoubleResult(MyTableInfo.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static float getFloatSum(String columnName, String selection, String[] selectionArgs) {
        return getSingleFloatResult(MyTableInfo.SUM_URI, new String[] {columnName}, selection, selectionArgs, null);
    }

    public static ArrayList<MyTable> findAllWhere(String selection, String[] selectionArgs, String sortOrder) {
        return findAllWhere(MyTableInfo.ALL_COLUMNS, selection, selectionArgs, sortOrder);
    }

    public static ArrayList<MyTable> findAllWhere(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return findAllWhere(projection == null ? MyTableInfo.ALL_COLUMNS_HELPER : new MyTableInfo.ColumnHelper(projection), selection, selectionArgs, sortOrder);
    }

    public static ArrayList<MyTable> findAllWhere(MyTableInfo.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        return findAllByUri(MyTableInfo.CONTENT_URI, helper, selection, selectionArgs, sortOrder);
    }

    public static MyTable findOneWhere(String selection, String[] selectionArgs) {
        return findOneWhere(MyTableInfo.ALL_COLUMNS, selection, selectionArgs);
    }

    public static MyTable findOneWhere(String[] projection, String selection, String[] selectionArgs) {
        return findOneWhere(projection == null ? MyTableInfo.ALL_COLUMNS_HELPER : new MyTableInfo.ColumnHelper(projection), selection, selectionArgs);
    }

    public static MyTable findOneWhere(MyTableInfo.ColumnHelper helper, String selection, String[] selectionArgs) {
        return findOneByUri(MyTableInfo.CONTENT_URI, helper, selection, selectionArgs, null);
    }

    public static int deleteWhere(String where, String[] selectionArgs) {
        return deleteByUri(MyTableInfo.CONTENT_URI, where, selectionArgs);
    }

    public static MyTable findOneById(long id) {
        return findOneById(id, MyTableInfo.ALL_COLUMNS);
    }

    public static MyTable findOneById(long id, String[] projection) {
        return findOneById(id, projection == null ? MyTableInfo.ALL_COLUMNS_HELPER : new MyTableInfo.ColumnHelper(projection));
    }

    public static MyTable findOneById(long id, MyTableInfo.ColumnHelper helper) {
        return findOneByUri(MyTableInfo.buildIdLookupUri(id), helper, null, null, null);
    }

    public static int deleteOneById(long id) {
        return deleteByUri(MyTableInfo.buildIdLookupUri(id), null, null);
    }


    public static MyTable findOneByMyString(String myString) {
        return findOneByMyString(myString, MyTableInfo.ALL_COLUMNS);
    }
            
    public static MyTable findOneByMyString(String myString, String[] projection) {
        return findOneByMyString(myString, projection == null ? MyTableInfo.ALL_COLUMNS_HELPER : new MyTableInfo.ColumnHelper(projection));
    }
    
    public static MyTable findOneByMyString(String myString, MyTableInfo.ColumnHelper helper) {
        return findOneByUri(MyTableInfo.buildMyStringLookupUri(myString), helper, null, null, null);
    }

    public static int deleteOneByMyString(String myString) {
        return deleteByUri(MyTableInfo.buildMyStringLookupUri(myString), null, null);
    }

    public static MyTable findOneWithMyStringInArray(String myString, ArrayList<MyTable> myTableList) {
		if (myString == null || myTableList == null || myTableList.isEmpty())
            return null;
        for (MyTable myTable : myTableList) {
            if (myTable.mMyStringSet && myTable.mMyString != null && myTable.mMyString.equals(myString))
                return myTable;
        }
        return null;
    }


	public static MyTable findOneWithIdInArray(long id, ArrayList<MyTable> myTableList) {
	    if (myTableList == null || myTableList.isEmpty())
	        return null;
	    for (MyTable myTable : myTableList) {
	        if (myTable.mIdSet && myTable.mId != null && myTable.mId.longValue() == id)
	            return myTable;
	    }
	    return null;
	}

    public static MyTable findOneByUri(Uri uri, MyTableInfo.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        MyTable rtr = null;

        if (helper == null)
            helper = MyTableInfo.ALL_COLUMNS_HELPER;
        
        if (TextUtils.isEmpty(sortOrder))
            sortOrder = MyTableInfo.Columns._ID;

        Cursor c = SampleProvider.getAppContext().getContentResolver().query(
                uri,
                helper.projection,
                selection,
                selectionArgs,
                sortOrder + " LIMIT 1");
        if (c != null) {
            if (c.moveToFirst()) {
                rtr = fromCursor(c, helper);
            }
            c.close();
        }
        return rtr;
    }

    public static ArrayList<MyTable> findAllByUri(Uri uri, MyTableInfo.ColumnHelper helper, String selection, String[] selectionArgs, String sortOrder) {
        if (helper == null)
            helper = MyTableInfo.ALL_COLUMNS_HELPER;

        Cursor c = SampleProvider.getAppContext().getContentResolver().query(uri, helper.projection, selection, selectionArgs, sortOrder);
        ArrayList<MyTable> rtr = new ArrayList<MyTable>();
        if (c != null) {
            while(c.moveToNext()) {
                rtr.add(MyTable.fromCursor(c, helper));
            }
            c.close();
        }
        return rtr;
    }

	protected Long mId = null;
	protected boolean mIdSet = false;
	protected Boolean mMyBoolean = null;
	protected boolean mMyBooleanSet = false;
	protected Double mMyDouble = null;
	protected boolean mMyDoubleSet = false;
	protected Float mMyFloat = null;
	protected boolean mMyFloatSet = false;
	protected Integer mMyInt = null;
	protected boolean mMyIntSet = false;
	protected Long mMyLong = null;
	protected boolean mMyLongSet = false;
	protected Character mMyChar = null;
	protected boolean mMyCharSet = false;
	protected String mMyString = null;
	protected boolean mMyStringSet = false;
	protected ByteBuffer mMyBlob = null;
	protected boolean mMyBlobSet = false;
	protected Long mMyTime = null;
	protected boolean mMyTimeSet = false;


    public BaseMyTable() {
        super();
    }
    public BaseMyTable(Parcel in) {
        readFromParcel(in);
    }

    private void assertColumnHelper(ColumnHelper helper, boolean allowNull) {
        if (helper == null) {
            if (allowNull)
                return;
            else
                throw new IllegalArgumentException("Trying to use a null column helper with MyTable");
        }
        if (!(helper instanceof MyTableInfo.ColumnHelper))
            throw new IllegalArgumentException("Trying to use wrong type of ColumnHelper with MyTable - " + helper.getClass().getName());
    }

    @Override
    protected void hydrate(Cursor c, ColumnHelper helper) {
        assertColumnHelper(helper, false);
        hydrate(c, (MyTableInfo.ColumnHelper)helper);
    }

    protected void hydrate(Cursor c, MyTableInfo.ColumnHelper h) {
        if (h.col__id != -1) {
            mId = c.isNull(h.col__id) ? null : c.getLong(h.col__id);
            mIdSet = true;
        } else {
            mId = null;
            mIdSet = false;
        }
        if (h.col_my_boolean != -1) {
            mMyBoolean = c.isNull(h.col_my_boolean) ? null : (c.getInt(h.col_my_boolean) == 1);
            mMyBooleanSet = true;
        } else {
            mMyBoolean = null;
            mMyBooleanSet = false;
        }
        if (h.col_my_double != -1) {
            mMyDouble = c.isNull(h.col_my_double) ? null : c.getDouble(h.col_my_double);
            mMyDoubleSet = true;
        } else {
            mMyDouble = null;
            mMyDoubleSet = false;
        }
        if (h.col_my_float != -1) {
            mMyFloat = c.isNull(h.col_my_float) ? null : c.getFloat(h.col_my_float);
            mMyFloatSet = true;
        } else {
            mMyFloat = null;
            mMyFloatSet = false;
        }
        if (h.col_my_int != -1) {
            mMyInt = c.isNull(h.col_my_int) ? null : c.getInt(h.col_my_int);
            mMyIntSet = true;
        } else {
            mMyInt = null;
            mMyIntSet = false;
        }
        if (h.col_my_long != -1) {
            mMyLong = c.isNull(h.col_my_long) ? null : c.getLong(h.col_my_long);
            mMyLongSet = true;
        } else {
            mMyLong = null;
            mMyLongSet = false;
        }
        if (h.col_my_char != -1) {
            mMyChar = null;
            if (!c.isNull(h.col_my_char)) {
                String myChar = c.getString(h.col_my_char);
                if (myChar.length() > 0)
                    mMyChar = myChar.charAt(0);
            }
            mMyCharSet = true;
        } else {
            mMyChar = null;
            mMyCharSet = false;
        }
        if (h.col_my_string != -1) {
            mMyString = c.getString(h.col_my_string);
            mMyStringSet = true;
        } else {
            mMyString = null;
            mMyStringSet = false;
        }
        if (h.col_my_blob != -1) {
            mMyBlob = c.isNull(h.col_my_blob) ? null : ByteBuffer.wrap(c.getBlob(h.col_my_blob));
            mMyBlobSet = true;
        } else {
            mMyBlob = null;
            mMyBlobSet = false;
        }
        if (h.col_my_time != -1) {
            mMyTime = c.isNull(h.col_my_time) ? null : c.getLong(h.col_my_time);
            mMyTimeSet = true;
        } else {
            mMyTime = null;
            mMyTimeSet = false;
        }

        mIsNew = false;
    }

	protected void hydrate(JSONObject obj) {
		if (obj == null)
			return;
//_id doesnt get hydrated from json
		if (obj.has(MyTableInfo.Columns.MY_BOOLEAN)) {
		    try {
		        mMyBoolean = obj.getBoolean(MyTableInfo.Columns.MY_BOOLEAN);
		    } catch (JSONException e) {
		        mMyBoolean = false;
		    }
		    mMyBooleanSet = true;
		}
		if (obj.has(MyTableInfo.Columns.MY_DOUBLE)) {
		    try {
		        mMyDouble = obj.getDouble(MyTableInfo.Columns.MY_DOUBLE);
		    } catch (JSONException e) {
		        mMyDouble = null;
		    }
		    mMyDoubleSet = true;
		}
		if (obj.has(MyTableInfo.Columns.MY_FLOAT)) {
		    try {
		        mMyFloat = (float)obj.getDouble(MyTableInfo.Columns.MY_FLOAT);
		    } catch (JSONException e) {
		        mMyFloat = null;
		    }
		    mMyFloatSet = true;
		}
		if (obj.has(MyTableInfo.Columns.MY_INT)) {
		    try {
		        mMyInt = obj.getInt(MyTableInfo.Columns.MY_INT);
		    } catch (JSONException e) {
		        mMyInt = null;
		    }
		    mMyIntSet = true;
		}
		if (obj.has(MyTableInfo.Columns.MY_LONG)) {
		    try {
		        mMyLong = obj.getLong(MyTableInfo.Columns.MY_LONG);
		    } catch (JSONException e) {
		        mMyLong = null;
		    }
		    mMyLongSet = true;
		}
		if (obj.has(MyTableInfo.Columns.MY_CHAR)) {
			String myChar = null;
		    try {
		        myChar = obj.getString(MyTableInfo.Columns.MY_CHAR);
				if (myChar == null || myChar.length() == 0)
					mMyChar = null;
				else
					mMyChar = myChar.charAt(0);
		    } catch (JSONException e) {
		        mMyChar = null;
		    }
		    mMyCharSet = true;
		}
		if (obj.has(MyTableInfo.Columns.MY_STRING)) {
		    try {
		        mMyString = obj.getString(MyTableInfo.Columns.MY_STRING);
		    } catch (JSONException e) {
		        mMyString = null;
		    }
		    mMyStringSet = true;
		}
//Can't hydrate a BLOB from JSON
		if (obj.has(MyTableInfo.Columns.MY_TIME)) {
		    try {
		        mMyTime = obj.getLong(MyTableInfo.Columns.MY_TIME);
		    } catch (JSONException e) {
		        mMyTime = null;
		    }
		    mMyTimeSet = true;
		}

	}

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
		if (mIdSet)
			values.put(MyTableInfo.Columns._ID, mId);
		if (mMyBooleanSet)
			values.put(MyTableInfo.Columns.MY_BOOLEAN, mMyBoolean);
		if (mMyDoubleSet)
			values.put(MyTableInfo.Columns.MY_DOUBLE, mMyDouble);
		if (mMyFloatSet)
			values.put(MyTableInfo.Columns.MY_FLOAT, mMyFloat);
		if (mMyIntSet)
			values.put(MyTableInfo.Columns.MY_INT, mMyInt);
		if (mMyLongSet)
			values.put(MyTableInfo.Columns.MY_LONG, mMyLong);
		if (mMyCharSet)
			values.put(MyTableInfo.Columns.MY_CHAR, mMyChar == null ? null : String.valueOf(mMyChar));
		if (mMyStringSet)
			values.put(MyTableInfo.Columns.MY_STRING, mMyString);
		if (mMyBlobSet)
			values.put(MyTableInfo.Columns.MY_BLOB, mMyBlob == null ? null : mMyBlob.array());
		if (mMyTimeSet)
			values.put(MyTableInfo.Columns.MY_TIME, mMyTime);

        return values;
    }

    @Override
    public JSONObject toJson(ColumnHelper helper) throws JSONException {
        assertColumnHelper(helper, true);
        return toJson((MyTableInfo.ColumnHelper)helper);
    }

    @Override
    public JSONObject toJson(String[] projection) throws JSONException {
        return toJson(projection == null ? MyTableInfo.ALL_COLUMNS_HELPER : new MyTableInfo.ColumnHelper(projection));
    }

    public JSONObject toJson(MyTableInfo.ColumnHelper h) throws JSONException {
        if (h == null)
            h = MyTableInfo.ALL_COLUMNS_HELPER;
        JSONObject rtr = new JSONObject();
		if (mIdSet && h.col__id != -1)
			rtr.put(MyTableInfo.Columns._ID, mId);
		if (mMyBooleanSet && h.col_my_boolean != -1)
			rtr.put(MyTableInfo.Columns.MY_BOOLEAN, mMyBoolean);
		if (mMyDoubleSet && h.col_my_double != -1)
			rtr.put(MyTableInfo.Columns.MY_DOUBLE, mMyDouble);
		if (mMyFloatSet && h.col_my_float != -1)
			rtr.put(MyTableInfo.Columns.MY_FLOAT, mMyFloat.doubleValue());
		if (mMyIntSet && h.col_my_int != -1)
			rtr.put(MyTableInfo.Columns.MY_INT, mMyInt);
		if (mMyLongSet && h.col_my_long != -1)
			rtr.put(MyTableInfo.Columns.MY_LONG, mMyLong);
		if (mMyCharSet && h.col_my_char != -1)
			rtr.put(MyTableInfo.Columns.MY_CHAR, String.valueOf(mMyChar));
		if (mMyStringSet && h.col_my_string != -1)
			rtr.put(MyTableInfo.Columns.MY_STRING, mMyString);
		//Cannot add blobs to Json objects so MyBlob is skipped
		if (mMyTimeSet && h.col_my_time != -1)
			rtr.put(MyTableInfo.Columns.MY_TIME, mMyTime);

        return rtr;
    }

    @Override
    public void save() {
        if (isNew()) {
            Uri result = SampleProvider.getAppContext().getContentResolver().insert(MyTableInfo.CONTENT_URI, toContentValues());
            if (result != null) {
                setId(Long.valueOf(result.getLastPathSegment()));
            }
            mIsNew = false;
        } else {
            if (!mIdSet) {
                throw new IllegalArgumentException("Trying to save an existing persistant object when ID column is not set");
            }
            Uri updateUri = MyTableInfo.buildIdLookupUri(mId);
            SampleProvider.getAppContext().getContentResolver().update(updateUri, toContentValues(), null, null);
        }
    }

    @Override
    public ContentProviderOperation getSaveProviderOperation() {
        ContentProviderOperation op = null;
        if (isNew()) {
            op = ContentProviderOperation.newInsert(MyTableInfo.CONTENT_URI).withValues(toContentValues()).build();
        } else {
            if (!mIdSet) {
                throw new IllegalArgumentException("Trying to save an existing persistant object when ID column is not set");
            }
            Uri updateUri = MyTableInfo.buildIdLookupUri(mId);
            op = ContentProviderOperation.newUpdate(updateUri).withValues(toContentValues()).build();
        }
        return op;
    }

    @Override
    public int delete() {
        if (isNew())
            throw new IllegalArgumentException("Trying to delete a MyTable record that has never been saved");
        if (!mIdSet)
            throw new IllegalArgumentException("Trying to delete a MyTable record that doesnt have its ID column set");

        Uri delUri = MyTableInfo.buildIdLookupUri(mId);
        return SampleProvider.getAppContext().getContentResolver().delete(delUri, null, null);
    }
    
    @Override
    public boolean reload() {
        return reload(MyTableInfo.ALL_COLUMNS_HELPER);
    }

    @Override
    public boolean reload(String[] projection) {
        return reload(projection == null ? MyTableInfo.ALL_COLUMNS_HELPER : new MyTableInfo.ColumnHelper(projection));
    }

    @Override
    public boolean reload(ColumnHelper helper) {
        assertColumnHelper(helper, true);
        return reload((MyTableInfo.ColumnHelper)helper);
    }
    
    public boolean reload(MyTableInfo.ColumnHelper helper) {
        if (isNew() || !mIdSet)
            throw new IllegalArgumentException("Trying to reload a record without an id");
        if (helper == null)
            helper = MyTableInfo.ALL_COLUMNS_HELPER;
        
        boolean result = false;
        
        Cursor c = SampleProvider.getAppContext().getContentResolver().query(
                MyTableInfo.buildIdLookupUri(mId),
                helper.projection,
                null,
                null,
                null);
        if (c != null) {
            if (c.moveToFirst()) {
                hydrate(c, helper);
                result = true;
            }
            c.close();
        }
        return result;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
        mIdSet = true;
        mIsNew = id != null;
    }

    public Boolean getMyBoolean() {
        return mMyBoolean;
    }

    public void setMyBoolean(Boolean myBoolean) {
        mMyBoolean = myBoolean;
        mMyBooleanSet = true;
    }

    public Double getMyDouble() {
        return mMyDouble;
    }

    public void setMyDouble(Double myDouble) {
        mMyDouble = myDouble;
        mMyDoubleSet = true;
    }

    public Float getMyFloat() {
        return mMyFloat;
    }

    public void setMyFloat(Float myFloat) {
        mMyFloat = myFloat;
        mMyFloatSet = true;
    }

    public Integer getMyInt() {
        return mMyInt;
    }

    public void setMyInt(Integer myInt) {
        mMyInt = myInt;
        mMyIntSet = true;
    }

    public Long getMyLong() {
        return mMyLong;
    }

    public void setMyLong(Long myLong) {
        mMyLong = myLong;
        mMyLongSet = true;
    }

    public Character getMyChar() {
        return mMyChar;
    }

    public void setMyChar(Character myChar) {
        mMyChar = myChar;
        mMyCharSet = true;
    }

    public String getMyString() {
        return mMyString;
    }

    public void setMyString(String myString) {
        mMyString = myString;
        mMyStringSet = true;
    }

    public ByteBuffer getMyBlob() {
        return mMyBlob;
    }

    public void setMyBlob(ByteBuffer myBlob) {
        mMyBlob = myBlob;
        mMyBlobSet = true;
    }

   public Long getMyTimeInMillis() {
       return mMyTime;
   }

   public Integer getMyTimeInSeconds() {
       if (mMyTime == null)
           return null;
       return (int)(mMyTime.longValue()/1000);
   }

   public Date getMyTimeAsDate() {
       if (mMyTime == null)
           return null;
       return new Date(mMyTime);
   }

   public void setMyTimeInMillis(Long myTime) {
       mMyTime = myTime;
       mMyTimeSet = true;
   }

   public void setMyTimeInSeconds(Integer myTime) {
       if (myTime == null) {
           mMyTime = null;
           return;
       }
       mMyTime = myTime.longValue() * 1000;
   }

   public void setMyTimeAsDate(Date myTime) {
       if (myTime == null) {
           mMyTime = null;
           return;
       } else {
           mMyTime = myTime.getTime();
       }
   }


    public boolean isIdSet() {
        return mIdSet;
    }
    public boolean isMyBooleanSet() {
        return mMyBooleanSet;
    }
    public boolean isMyDoubleSet() {
        return mMyDoubleSet;
    }
    public boolean isMyFloatSet() {
        return mMyFloatSet;
    }
    public boolean isMyIntSet() {
        return mMyIntSet;
    }
    public boolean isMyLongSet() {
        return mMyLongSet;
    }
    public boolean isMyCharSet() {
        return mMyCharSet;
    }
    public boolean isMyStringSet() {
        return mMyStringSet;
    }
    public boolean isMyBlobSet() {
        return mMyBlobSet;
    }
    public boolean isMyTimeSet() {
        return mMyTimeSet;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

		dest.writeValue(mId);
		dest.writeInt(mIdSet ? 1 : 0);

		dest.writeValue(mMyBoolean);
		dest.writeInt(mMyBooleanSet ? 1 : 0);

		dest.writeValue(mMyDouble);
		dest.writeInt(mMyDoubleSet ? 1 : 0);

		dest.writeValue(mMyFloat);
		dest.writeInt(mMyFloatSet ? 1 : 0);

		dest.writeValue(mMyInt);
		dest.writeInt(mMyIntSet ? 1 : 0);

		dest.writeValue(mMyLong);
		dest.writeInt(mMyLongSet ? 1 : 0);

		dest.writeValue(mMyChar == null ? null : String.valueOf(mMyChar));
		dest.writeInt(mMyCharSet ? 1 : 0);

		dest.writeValue(mMyString);
		dest.writeInt(mMyStringSet ? 1 : 0);

		dest.writeValue(mMyBlob == null ? null : mMyBlob.array());
		dest.writeInt(mMyBlobSet ? 1 : 0);

		dest.writeValue(mMyTime);
		dest.writeInt(mMyTimeSet ? 1 : 0);


    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);

		mId = (Long) in.readValue(Long.class.getClassLoader());
		mIdSet = in.readInt() == 1;

		mMyBoolean = (Boolean) in.readValue(Boolean.class.getClassLoader());
		mMyBooleanSet = in.readInt() == 1;

		mMyDouble = (Double) in.readValue(Double.class.getClassLoader());
		mMyDoubleSet = in.readInt() == 1;

		mMyFloat = (Float) in.readValue(Float.class.getClassLoader());
		mMyFloatSet = in.readInt() == 1;

		mMyInt = (Integer) in.readValue(Integer.class.getClassLoader());
		mMyIntSet = in.readInt() == 1;

		mMyLong = (Long) in.readValue(Long.class.getClassLoader());
		mMyLongSet = in.readInt() == 1;

        String my_char_tmp = in.readString();
        mMyChar = my_char_tmp == null || my_char_tmp.length() <= 0 ? null : my_char_tmp.charAt(0);
		mMyCharSet = in.readInt() == 1;

		mMyString = (String) in.readValue(String.class.getClassLoader());
		mMyStringSet = in.readInt() == 1;

        byte[] my_blob_temp = (byte[]) in.readValue(byte[].class.getClassLoader());
        mMyBlob = my_blob_temp == null ? null : ByteBuffer.wrap(my_blob_temp);
		mMyBlobSet = in.readInt() == 1;

		mMyTime = (Long) in.readValue(Long.class.getClassLoader());
		mMyTimeSet = in.readInt() == 1;


    }

}
