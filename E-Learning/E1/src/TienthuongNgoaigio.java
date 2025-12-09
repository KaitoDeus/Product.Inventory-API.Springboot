public class TienthuongNgoaigio implements ITienthuong{

    @Override
    public double tinhTienthuong(double luongCB) {
        return luongCB * 0.10;
    }

    @Override
    public String toString() {
        return "TienthuongNgoaigio";
    }
    
}
