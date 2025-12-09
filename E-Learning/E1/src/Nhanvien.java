public abstract class Nhanvien {
    protected String maso;
    protected String hoten;
    protected double luongCB;
    protected ITienthuong phuongthucTinhThuong;

    public Nhanvien() {
    }

    public Nhanvien(String maso, String hoten, double luongCB) {
        this.maso = maso;
        this.hoten = hoten;
        this.luongCB = luongCB;
    }

    @Override
    public String toString() {
        return "Nhanvien [maso=" + maso + ", hoten=" + hoten + ", luongCB=" + luongCB + "]";
    }

    public double getTienThuong() {
        return luongCB;
    }

    public ITienthuong getPhuongthucTinhThuong() {
        return phuongthucTinhThuong;
    }

    public void setPhuongthucTinhThuong(ITienthuong phuongthucTinhThuong) {
        this.phuongthucTinhThuong = phuongthucTinhThuong;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public double getLuongCB() {
        return luongCB;
    }

    public void setLuongCB(double luongCB) {
        this.luongCB = luongCB;
    }

}
