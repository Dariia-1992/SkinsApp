package com.hi.skinsapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;

public class GridAutofitLayoutManager extends RecyclerView {

   private GridLayoutManager mManager;
   private int columnWidth = -1;

    public GridAutofitLayoutManager(@NonNull Context context) {
        super(context);
        init(context, null);

    }

    public GridAutofitLayoutManager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GridAutofitLayoutManager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        if (attrs != null){
            int [] attrsArray = {android.R.attr.columnWidth};
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }
        mManager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(mManager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (columnWidth > 0){
            int spanCount = Math.max(1, getMeasuredWidth() / columnWidth);
            mManager.setSpanCount(spanCount);
        }
    }

    /*private int mColumnWidth;
    private boolean mColumnWidthChanged = true;

    public GridAutofitLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        setColumnWidth(checkedColumnWidth(context, spanCount));
    }

    public GridAutofitLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, 1, orientation, reverseLayout);
        setColumnWidth(checkedColumnWidth(context, spanCount));
    }

    private int checkedColumnWidth(Context context, int columnWidth){
        if (columnWidth <= 0){
            columnWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, R.dimen.image_size,
                    context.getResources().getDisplayMetrics());
        }
        return columnWidth;
    }

    public void setColumnWidth(int newColumnWidth){
        if (newColumnWidth > 0 && mColumnWidth != mColumnWidth){
            mColumnWidth = newColumnWidth;
            mColumnWidthChanged = true;
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        int width = getWidth();
        int height = getHeight();
        if (mColumnWidthChanged && mColumnWidth > 0 && width > 0 && height > 0){
            int totalSpace;
            if (getOrientation() == RecyclerView.VERTICAL){
                totalSpace = width - getPaddingRight() - getPaddingLeft();
            }
            else {
                totalSpace = height - getPaddingTop() - getPaddingBottom();
            }
            int spanCount = Math.max(1, totalSpace/mColumnWidth);
            setSpanCount(spanCount);
            mColumnWidthChanged = false;
        }
        super.onLayoutChildren(recycler, state);
    }*/
}
