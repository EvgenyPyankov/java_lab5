class Point {
    double x;
    double y;
    private double pointR;
    private int isHit;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getPointR() {
        return pointR;
    }

    public void setPointR(double pointR) {
        this.pointR = pointR;
    }

    public int getIsHit(){
        return isHit;
    }

    public void setItHit(int isHit){
        this.isHit=isHit;
    }

}
