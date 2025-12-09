public class TienthuongNgoaitinh implements ITienthuong{

    @Override
    public double tinhTienthuong(double luongCB) {
        return luongCB * 0.15;
    }

    @Override
    public String toString() {
        return "TienthuongNgoaitinh";
    }
    
}
