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

        private String title;
        private String image;
        private int heightPercent;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getHeightPercent() {
            return heightPercent;
        }

        public void setHeightPercent(int heightPercent) {
            this.heightPercent = heightPercent;
        }
    }
}
