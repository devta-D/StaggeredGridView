package devta.staggeredview;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * author @Divyanshu Tayal
 */

public class StaggeredGridView extends LinearLayout {

    private RecyclerView mStaggeredRecyclerView;
    private List<StaggeredData> savedData;

    private int viewMinHeight, viewMaxHeight, viewMaxWidth;

    public StaggeredGridView(Context context) {
        this(context, null);
    }

    public StaggeredGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StaggeredGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setViewDimensions(35, 65);
        initLayout(context);
    }

    private void initLayout(final Context context){

        if(isInEditMode())return;

        mStaggeredRecyclerView = new RecyclerView(context);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mStaggeredRecyclerView.setLayoutManager(layoutManager);

        mStaggeredRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        addView(mStaggeredRecyclerView);
    }

    public void setViewDimensions(int minHeightInPercentage, int maxHeightInPercentage){
        if(minHeightInPercentage!=0)viewMinHeight = minHeightInPercentage;
        if(maxHeightInPercentage!=0)viewMaxHeight = maxHeightInPercentage;
        if(mStaggeredRecyclerView!=null)invalidate();
    }

    public void setViewMaxWidthInDP(int viewMaxWidth) {
        this.viewMaxWidth = viewMaxWidth;
        invalidate();
    }

    public void setData(List<StaggeredData> staggeredData){
        if(!isInEditMode() && mStaggeredRecyclerView!=null)
            mStaggeredRecyclerView.setAdapter(new StaggeredViewAdapter(
                    staggeredData, viewMinHeight, viewMaxHeight, viewMaxWidth));
        this.savedData = staggeredData;
    }

    @Override
    public void invalidate() {
        super.invalidate();
        mStaggeredRecyclerView.setAdapter(new StaggeredViewAdapter(
                savedData, viewMinHeight, viewMaxHeight, viewMaxWidth));
    }

}
