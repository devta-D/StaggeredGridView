package devta.staggeredview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author @Divyanshu Tayal
 */

class StaggeredViewAdapter extends RecyclerView.Adapter<StaggeredViewAdapter.StaggeredViewHolder> {

    private List<StaggeredDataModel> staggeredDataModelList = new ArrayList<>();
    private int minHeight, maxHeight, maxWidth;
    private StaggeredItemClickListener listener;

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    StaggeredViewAdapter(List<StaggeredData> staggeredData, int minHeight,
                         int maxHeight, int maxWidth, StaggeredItemClickListener listener) {

        if(staggeredData==null||staggeredData.isEmpty())return;

        this.minHeight = minHeight; this.maxHeight = maxHeight; this.maxWidth = maxWidth;
        this.listener = listener;

        int maxPos = staggeredData.size();

        for(int pos=0; pos<maxPos; pos++){

            StaggeredDataModel staggeredDataModel = new StaggeredDataModel();

            StaggeredDataModel.ViewDataModel topViewData = new StaggeredDataModel.ViewDataModel();
            topViewData.setStaggeredData(staggeredData.get(pos));
            topViewData.setHeightPercent(getRandomHeight());
            staggeredDataModel.setTop(topViewData);

            try{
                StaggeredDataModel.ViewDataModel bottomViewData = new StaggeredDataModel.ViewDataModel();
                bottomViewData.setStaggeredData(staggeredData.get(pos+1));
                bottomViewData.setHeightPercent(100-topViewData.getHeightPercent());
                staggeredDataModel.setBottom(bottomViewData);
            }catch (Exception e){
                Log.d("StaggeredViewAdapter", "Bottom none, display top at max height");
            }

            staggeredDataModelList.add(staggeredDataModel);
            pos++;
        }

    }

    private StaggeredDataModel getItemAt(int pos){
        return staggeredDataModelList.get(pos);
    }

    @Override
    @NonNull
    public StaggeredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_view_staggered, parent, false);

        Display display = ((WindowManager) parent.getContext()
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        if(maxWidth==0){
            Point deviceDisplay = new Point();
            display.getSize(deviceDisplay);
            int deviceWidth = deviceDisplay.x;
            maxWidth = pxToDp(deviceWidth/getItemCount());
        }

        if(maxWidth>120){
            view.setLayoutParams(new FrameLayout.LayoutParams(
                    dpToPx(maxWidth), ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return new StaggeredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StaggeredViewHolder holder, int position) {

        final StaggeredDataModel staggeredDataModel = getItemAt(position);

        if(staggeredDataModel.getTop()!=null){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 0,
                    staggeredDataModel.getTop().getHeightPercent());

            holder.topViewContainer.setLayoutParams(params);

            holder.topTitleView.setText(staggeredDataModel.getTop().getStaggeredData().getTitle());

            Glide.with(holder.topImageView.getContext())
                    .load(staggeredDataModel.getTop().getStaggeredData().getImageUri())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder))
                    .into(holder.topImageView);

            holder.topViewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                        listener.onStaggeredItemClick(staggeredDataModel.getTop().getStaggeredData());
                }
            });
        }

        if(staggeredDataModel.getBottom()!=null){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 0,
                    staggeredDataModel.getBottom().getHeightPercent());

            holder.bottomViewContainer.setLayoutParams(params);

            holder.bottomTitleView.setText(staggeredDataModel.getBottom().getStaggeredData().getTitle());

            Glide.with(holder.bottomImageView.getContext())
                    .load(staggeredDataModel.getBottom().getStaggeredData().getImageUri())
                    .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder))
                    .into(holder.bottomImageView);

            holder.bottomViewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                        listener.onStaggeredItemClick(staggeredDataModel.getBottom().getStaggeredData());
                }
            });

        }else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            holder.topViewContainer.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return staggeredDataModelList.size();
    }

    class StaggeredViewHolder extends RecyclerView.ViewHolder {

        TextView topTitleView, bottomTitleView;
        ImageView topImageView, bottomImageView;
        RelativeLayout topViewContainer;
        RelativeLayout bottomViewContainer;

        StaggeredViewHolder(View itemView) {
            super(itemView);
            topTitleView = itemView.findViewById(R.id.topTitleView);
            bottomTitleView = itemView.findViewById(R.id.bottomTitleView);
            topImageView =  itemView.findViewById(R.id.topImageView);
            bottomImageView =  itemView.findViewById(R.id.bottomImageView);
            topViewContainer =  itemView.findViewById(R.id.topViewContainer);
            bottomViewContainer =  itemView.findViewById(R.id.bottomViewContainer);
        }
    }

    private int getRandomHeight(){
        Random r = new Random();
        return r.nextInt( maxHeight - minHeight + 1) + minHeight;
    }

    private int dpToPx(float dp) {
        return Math.round(dp * (((float) Resources.getSystem().getDisplayMetrics().densityDpi) / 160.0f));
    }

    private int pxToDp(float px) {
        return Math.round(px / (((float) Resources.getSystem().getDisplayMetrics().densityDpi) / 160.0f));
    }

}
