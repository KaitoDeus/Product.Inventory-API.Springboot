public class TienthuongThongthuong implements ITienthuong{

    @Override
    public double tinhTienthuong(double luongCB) {
        return luongCB * 0.02;
    }

    @Override
    public String toString() {
        return "TienthuongThongthuong";
    }
    
}
