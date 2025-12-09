public class NhanvienFactory implements INhanvienFactory{

    @Override
    public Nhanvien createNhanvien(String maso, String hoten, double luongCB) {
        if (maso.startsWith("LT")) {
            return new Laptrinhvien(maso, hoten, luongCB);
        } else if (maso.startsWith("KT")) {
            return new Ketoanvien(maso, hoten, luongCB);
        } else if (maso.startsWith("CV")) {
            return new ChuyenvienPhantich(maso, hoten, luongCB);
        } else if (maso.startsWith("NK")) {
            return new NhanvienKiemthu(maso, hoten, luongCB);
        } else {
            // Default to Laptrinhvien if prefix not recognized
            return new Laptrinhvien(maso, hoten, luongCB);
        }
    }

    @Override
    public Nhanvien createNhanvien(String maso) {
        // Create with default values, assuming Laptrinhvien as default type
        // return new Laptrinhvien(maso, "Default Name", 0.0);
        return null;
    }
    
}
