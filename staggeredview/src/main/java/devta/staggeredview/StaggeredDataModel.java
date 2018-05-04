package devta.staggeredview;

/**
 * author @Divyanshu Tayal
 */
class StaggeredDataModel {

    private ViewDataModel top, bottom;

    public ViewDataModel getTop() {
        return top;
    }

    public void setTop(ViewDataModel top) {
        this.top = top;
    }

    public ViewDataModel getBottom() {
        return bottom;
    }

    public void setBottom(ViewDataModel bottom) {
        this.bottom = bottom;
    }

    public static class ViewDataModel {

        private int heightPercent;
        private StaggeredData staggeredData;

        public int getHeightPercent() {
            return heightPercent;
        }

        public void setHeightPercent(int heightPercent) {
            this.heightPercent = heightPercent;
        }

        public StaggeredData getStaggeredData() {
            return staggeredData;
        }

        public void setStaggeredData(StaggeredData staggeredData) {
            this.staggeredData = staggeredData;
        }
    }
}
