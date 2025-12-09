public class ClientTest {
    public static void main(String[] args) {
        INhanvienFactory factory = new NhanvienFactory();

        // Demo tao nhan vien voi day du thong tin
        System.out.println("\n=== Demo tao nhan vien voi day du thong tin ===");

        // Tao nhan vien Lap trinh vien
        Nhanvien nv1 = factory.createNhanvien("LT001", "Nguyen Van A", 5000.0);
        nv1.setPhuongthucTinhThuong(new TienthuongNgoaigio()); // Gan phuong thuc thuong ngoai gio
        System.out.println("Nhan vien 1: " + nv1.toString());
        System.out.println("Tien thuong: " + nv1.phuongthucTinhThuong.tinhTienthuong(nv1.getLuongCB()));

        // Tao nhan vien Ke toan vien
        Nhanvien nv2 = factory.createNhanvien("KT001", "Tran Thi B", 4500.0);
        nv2.setPhuongthucTinhThuong(new TienthuongThongthuong()); // Gan phuong thuc thuong thong thuong
        System.out.println("Nhan vien 2: " + nv2.toString());
        System.out.println("Tien thuong: " + nv2.phuongthucTinhThuong.tinhTienthuong(nv2.getLuongCB()));

        // Tao nhan vien Chuyen vien phan tich
        Nhanvien nv3 = factory.createNhanvien("CV001", "Le Van C", 6000.0);
        nv3.setPhuongthucTinhThuong(new TienthuongNgoaitinh()); // Gan phuong thuc thuong ngoai tinh
        System.out.println("Nhan vien 3: " + nv3.toString());
        System.out.println("Tien thuong: " + nv3.phuongthucTinhThuong.tinhTienthuong(nv3.getLuongCB()));

        // Tao nhan vien Nhan vien kiem thu
        Nhanvien nv4 = factory.createNhanvien("NK001", "Pham Thi D", 5500.0);
        nv4.setPhuongthucTinhThuong(new TienthuongNgoaigio()); // Gan phuong thuc thuong ngoai gio
        System.out.println("Nhan vien 4: " + nv4.toString());
        System.out.println("Tien thuong: " + nv4.phuongthucTinhThuong.tinhTienthuong(nv4.getLuongCB()));

        // Demo tao nhan vien voi chi ma so (se tra ve null theo hien tai, nhung demo interface)
        System.out.println("\n=== Demo tao nhan vien voi chi ma so ===");
        Nhanvien nv5 = factory.createNhanvien("LT002");
        System.out.println(nv5);

        // Demo setters va getters
        System.out.println("\n=== Demo setters va getters ===");
        System.out.println("Truoc khi cap nhat:");
        System.out.println("  getMaso(): " + nv1.getMaso());
        System.out.println("  getHoten(): " + nv1.getHoten());
        System.out.println("  getLuongCB(): " + nv1.getLuongCB());
        System.out.println("  getPhuongthucTinhThuong(): " + nv1.getPhuongthucTinhThuong());

        nv1.setMaso("LT001_UPDATED");
        nv1.setHoten("Nguyen Van Updated");
        nv1.setLuongCB(5500.0);
        nv1.setPhuongthucTinhThuong(new TienthuongThongthuong());

        System.out.println("Sau khi cap nhat:");
        System.out.println("  getMaso(): " + nv1.getMaso());
        System.out.println("  getHoten(): " + nv1.getHoten());
        System.out.println("  getLuongCB(): " + nv1.getLuongCB());
        System.out.println("  getPhuongthucTinhThuong(): " + nv1.getPhuongthucTinhThuong());
        System.out.println("toString(): " + nv1.toString());
        System.out.println("Tien thuong sau cap nhat: " + nv1.phuongthucTinhThuong.tinhTienthuong(nv1.getLuongCB()));

        // Demo getTienThuuong (tra ve luongCB)
        System.out.println("\n=== Demo getTienThuong ===");
        System.out.println("getTienThuong cua nv1: " + nv1.getTienThuong()); // Tra ve luongCB

        // Demo cac phuong thuc thuong truc tiep
        System.out.println("\n=== Demo cac phuong thuc thuong ===");
        ITienthuong thuongThongThuong = new TienthuongThongthuong();
        ITienthuong thuongNgoaiGio = new TienthuongNgoaigio();
        ITienthuong thuongNgoaiTinh = new TienthuongNgoaitinh();

        double luongTest = 5000.0;
        System.out.println("Luong test: " + luongTest);
        System.out.println("TienthuongThongthuong.tinhTienthuong(): " + thuongThongThuong.tinhTienthuong(luongTest));
        System.out.println("TienthuongNgoaigio.tinhTienthuong(): " + thuongNgoaiGio.tinhTienthuong(luongTest));
        System.out.println("TienthuongNgoaitinh.tinhTienthuong(): " + thuongNgoaiTinh.tinhTienthuong(luongTest));

        // Demo constructors cua cac lop con
        System.out.println("\n=== Demo constructors cua cac lop con ===");
        Laptrinhvien lt = new Laptrinhvien("LT003", "Test LT", 4000.0);
        Ketoanvien kt = new Ketoanvien("KT003", "Test KT", 3500.0);
        ChuyenvienPhantich cv = new ChuyenvienPhantich("CV003", "Test CV", 7000.0);
        NhanvienKiemthu nk = new NhanvienKiemthu("NK003", "Test NK", 4800.0);

        System.out.println("Laptrinhvien: " + lt.toString());
        System.out.println("Ketoanvien: " + kt.toString());
        System.out.println("ChuyenvienPhantich: " + cv.toString());
        System.out.println("NhanvienKiemthu: " + nk.toString());

        // Demo default constructors
        Laptrinhvien ltDefault = new Laptrinhvien();
        System.out.println("Laptrinhvien default: " + ltDefault.toString());
    }
}
