/*
 * This file has been auto-generated by ProviderOne
 *
 * Copyright (C) 2011 GroupMe, Inc.
 */
package com.groupme.providerone.sample.database.autogen;

import java.util.ArrayList;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.groupme.providerone.sample.database.SampleDatabase;
import com.groupme.providerone.sample.database.autogen.util.PlatformDatabaseUtils;
import com.groupme.providerone.sample.database.autogen.util.SelectionBuilder;
import com.groupme.providerone.sample.database.tables.MyTableInfo;
import com.groupme.providerone.sample.database.tables.MyViewInfo;


public abstract class BaseSampleProvider extends ContentProvider {

    public static final String PATH_COUNT = "/count";
    public static final String PATH_SUM = "/sum";
    public static final String PATH_LOOKUP = "/lookup/*";
    public static final String PATH_ID = "/id/*";
	public static final String PATH_VACUUM = "vacuum";

    public static final String RAW_PATH_COUNT = "count";
    public static final String RAW_PATH_SUM = "sum";
    public static final String RAW_PATH_LOOKUP = "lookup";
    public static final String RAW_PATH_ID = "id";

	public static final int VACUUM = 0xffff;
	public static final int MY_TABLE = 0xfffe;
	public static final int MY_TABLE_COUNT = 0xfffd;
	public static final int MY_TABLE_SUM = 0xfffc;
	public static final int MY_TABLE_ID = 0xfffb;
	public static final int MY_TABLE_LOOKUP = 0xfffa;
	public static final int MY_VIEW = 0xfff9;
	public static final int MY_VIEW_COUNT = 0xfff8;
	public static final int MY_VIEW_SUM = 0xfff7;
	public static final int MY_VIEW_ID = 0xfff6;
	public static final int MY_VIEW_LOOKUP = 0xfff5;

    private static Uri sBaseContentUri = null;
    private static Context sApplicationContext = null;

    public static Context getAppContext() {
        return sApplicationContext;
    }

    public static String getContentAuthority() {
        return "com.groupme.providerone.sample";
    }

    public static Uri getBaseContentUri() {
        if (sBaseContentUri == null)
            sBaseContentUri = Uri.parse("content://" + getContentAuthority());
        return sBaseContentUri;
    }

    protected SampleDatabase mDatabase;
    private UriMatcher mUriMatcher = null;

    protected abstract void buildPriorityCustomUriMatcher(UriMatcher matcher, String authority);
    protected abstract void buildSecondaryCustomUriMatcher(UriMatcher matcher, String authority);
    protected abstract String getCustomType(Uri uri, int match);
	protected abstract void onNotityChanges(ContentResolver contentResolver, Uri uri, int match);
    protected abstract Integer delete(Uri uri, String selection, String[] selectionArgs, int match);
    protected abstract Uri insert(Uri uri, ContentValues values, int match);
    protected abstract Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, int match);
    protected abstract Integer update(Uri uri, ContentValues values, String selection, String[] selectionArgs, int match);
    protected abstract boolean buildSimpleSelection(Uri uri, int match, SelectionBuilder builder);
    protected abstract int getCustomUpdateAlgorithm(Uri uri, int match);

    @Override
    public boolean onCreate() {
        sApplicationContext = getContext().getApplicationContext();
        mDatabase = new SampleDatabase(getContext());
        return false;
    }

    protected void buildUriMatcher(UriMatcher matcher) {
        final String authority = getContentAuthority();

        buildPriorityCustomUriMatcher(matcher, authority);
		matcher.addURI(authority, PATH_VACUUM, VACUUM);
		matcher.addURI(authority, MyTableInfo.PATH, MY_TABLE);
		matcher.addURI(authority, MyTableInfo.PATH + PATH_COUNT, MY_TABLE_COUNT);
		matcher.addURI(authority, MyTableInfo.PATH + PATH_SUM, MY_TABLE_SUM);
		matcher.addURI(authority, MyTableInfo.PATH + PATH_ID, MY_TABLE_ID);
		matcher.addURI(authority, MyTableInfo.PATH + PATH_LOOKUP, MY_TABLE_LOOKUP);
		matcher.addURI(authority, MyViewInfo.PATH, MY_VIEW);
		matcher.addURI(authority, MyViewInfo.PATH + PATH_COUNT, MY_VIEW_COUNT);
		matcher.addURI(authority, MyViewInfo.PATH + PATH_SUM, MY_VIEW_SUM);
		matcher.addURI(authority, MyViewInfo.PATH + PATH_ID, MY_VIEW_ID);
		matcher.addURI(authority, MyViewInfo.PATH + PATH_LOOKUP, MY_VIEW_LOOKUP);

        buildSecondaryCustomUriMatcher(matcher, authority);
    }

    protected UriMatcher getUriMatcher() {
        if (mUriMatcher == null) {
            mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            buildUriMatcher(mUriMatcher);
        }
        return mUriMatcher;
    }

    @Override
    public String getType(Uri uri) {
        final int match = getUriMatcher().match(uri);
        String result = getCustomType(uri, match);
        if (result != null)
            return result;

        switch(match) {
			case VACUUM:
				return null;
			case MY_TABLE:
			case MY_TABLE_COUNT:
			case MY_TABLE_SUM:
				return MyTableInfo.CONTENT_TYPE;
			case MY_TABLE_ID:
			case MY_TABLE_LOOKUP:
				return MyTableInfo.CONTENT_ITEM_TYPE;
			case MY_VIEW:
			case MY_VIEW_COUNT:
			case MY_VIEW_SUM:
				return MyViewInfo.CONTENT_TYPE;
			case MY_VIEW_ID:
			case MY_VIEW_LOOKUP:
				return MyViewInfo.CONTENT_ITEM_TYPE;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        return applyBatch(operations, true);
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations, boolean withTransaction) throws OperationApplicationException {
        
        if (!withTransaction)
            return super.applyBatch(operations);
        
        ContentProviderResult[] result = null;
        SQLiteDatabase db = mDatabase.getWritableDatabase();
        db.beginTransaction();
        try {
            result =  super.applyBatch(operations);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return result;
    }

    private void notifyUri(Uri notifyUri, int match) {
    	notifyUri(null, notifyUri, match);
    }
    
    private void notifyUri(ContentResolver resolver, Uri notifyUri, int match) {
   		if (resolver == null)
   			resolver = getAppContext().getContentResolver();
   		resolver.notifyChange(notifyUri, null);
   		onNotityChanges(resolver, notifyUri, match);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int match = getUriMatcher().match(uri);
        Integer result = delete(uri, selection, selectionArgs, match);
        if (result != null)
            return result.intValue();

		switch(match) {
			case MY_TABLE_COUNT:
			case MY_TABLE_SUM:
				throw new UnsupportedOperationException("Can't delete using a sum or count uri. (MyTable)");
			case MY_VIEW_COUNT:
			case MY_VIEW_SUM:
				throw new UnsupportedOperationException("Can't delete using a sum or count uri. (MyView)");
			case MY_VIEW:
			case MY_VIEW_ID:
			case MY_VIEW_LOOKUP:
				throw new UnsupportedOperationException("Can't delete from a sqlite view. (MyView)");

		}

        final SelectionBuilder builder = buildSimpleSelection(uri, match);
        int delResult = builder.where(selection, selectionArgs).delete(mDatabase.getWritableDatabase());
        notifyUri(uri, match);
		return delResult;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = getUriMatcher().match(uri);
        Uri result = insert(uri, values, match);
        if (result != null)
            return result;

        PlatformDatabaseUtils db = new PlatformDatabaseUtils(mDatabase);
        switch(match) {
			case MY_TABLE: {
				long id = db.insertWithOnConflict(MyTableInfo.TABLE_NAME, null, values, MyTableInfo.INSERT_ALGORITHM);
				Uri newUri = MyTableInfo.buildIdLookupUri(id);
				notifyUri(newUri, match);
				return newUri;
			}

            default:
                throw new UnsupportedOperationException("Invalid uri for insert: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final int match = getUriMatcher().match(uri);
        Cursor result = query(uri, projection, selection, selectionArgs, sortOrder, match);
        if (result != null)
            return result;


        final SelectionBuilder builder = buildSimpleSelection(uri, match).where(selection, selectionArgs);
        switch(match) {
			case MY_TABLE_COUNT:
			case MY_VIEW_COUNT:
				return builder.query(mDatabase.getReadableDatabase(), new String[] {"count(*) as my_count"}, null);

			case MY_TABLE_SUM:
			case MY_VIEW_SUM:
				return builder.query(mDatabase.getReadableDatabase(), new String[] {"sum(" + projection[0] + ") as my_sum"}, null);

            default:
                return builder.query(mDatabase.getReadableDatabase(), projection, sortOrder);
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = getUriMatcher().match(uri);
        Integer result = update(uri, values, selection, selectionArgs, match);
        if (result != null)
            return result;

		switch(match) {
			case MY_TABLE_COUNT:
			case MY_TABLE_SUM:
				throw new UnsupportedOperationException("Can't update using a sum or count uri. (MyTable)");
			case MY_VIEW_COUNT:
			case MY_VIEW_SUM:
				throw new UnsupportedOperationException("Can't update using a sum or count uri. (MyView)");
			case MY_VIEW:
			case MY_VIEW_ID:
			case MY_VIEW_LOOKUP:
				throw new UnsupportedOperationException("Can't update a sqlite view. (MyView)");

		}
		
		if (match == VACUUM) {
			mDatabase.getWritableDatabase().execSQL("VACUUM;");
			notifyUri(uri, match);
			return 1;
		}

        final SelectionBuilder builder = buildSimpleSelection(uri, match);
        int algorithm = SQLiteDatabase.CONFLICT_FAIL;
        switch(match) {
			case MY_TABLE:
			case MY_TABLE_ID:
			case MY_TABLE_LOOKUP:
				algorithm = MyTableInfo.UPDATE_ALGORITHM;
				break;
			case MY_VIEW:
			case MY_VIEW_ID:
			case MY_VIEW_LOOKUP:
				algorithm = MyViewInfo.UPDATE_ALGORITHM;
				break;

			default:
				algorithm = getCustomUpdateAlgorithm(uri, match);
				if (algorithm == -1)
				    algorithm = SQLiteDatabase.CONFLICT_FAIL;
        }
        int updateResult = builder.where(selection, selectionArgs).updateWithOnConflict(mDatabase, values, algorithm);
        notifyUri(uri, match);
        return updateResult;
    }

    private SelectionBuilder buildSimpleSelection(Uri uri, int match) {
        final SelectionBuilder builder = new SelectionBuilder();

        if (buildSimpleSelection(uri, match, builder))
            return builder;

        switch(match) {
			case MY_TABLE:
			case MY_TABLE_COUNT:
			case MY_TABLE_SUM:
				return builder.table(MyTableInfo.TABLE_NAME);
			case MY_TABLE_ID:
				builder.where(MyTableInfo.Columns._ID + "=?", uri.getLastPathSegment());
				return builder.table(MyTableInfo.TABLE_NAME);
			case MY_TABLE_LOOKUP:
				builder.where(MyTableInfo.Columns.MY_STRING + "=?", uri.getLastPathSegment());
				return builder.table(MyTableInfo.TABLE_NAME);
			case MY_VIEW:
			case MY_VIEW_COUNT:
			case MY_VIEW_SUM:
				return builder.table(MyViewInfo.TABLE_NAME);
			case MY_VIEW_ID:
				builder.where(MyViewInfo.Columns._ID + "=?", uri.getLastPathSegment());
				return builder.table(MyViewInfo.TABLE_NAME);
			case MY_VIEW_LOOKUP:
				builder.where(MyViewInfo.Columns.MY_STRING + "=?", uri.getLastPathSegment());
				return builder.table(MyViewInfo.TABLE_NAME);

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }
}
