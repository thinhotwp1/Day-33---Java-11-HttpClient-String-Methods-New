//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class NewStringMethod {
    public static void main(String[] args) {
        String s1 = "";
        String s2 = "   "; // Chỉ dấu cách
        String s3 = "Hello";

        // isEmpty()
        System.out.println("s1.isEmpty(): " + s1.isEmpty()); // true
        System.out.println("s2.isEmpty(): " + s2.isEmpty()); // false  <-- Vấn đề là ở đây

        // isBlank()
        System.out.println("s1.isBlank(): " + s1.isBlank()); // true
        System.out.println("s2.isBlank(): " + s2.isBlank()); // true   <-- Đã giải quyết
        System.out.println("s3.isBlank(): " + s3.isBlank()); // false


        String multiLineText = "Dòng 1\nDòng 2\n\nDòng 3";

        multiLineText.lines() // Trả về Stream<String>
                .filter(line -> !line.isBlank()) // Lọc bỏ các dòng trống
                .map(line -> "-> " + line)       // Thêm "->" vào mỗi dòng
                .forEach(System.out::println);


        /*
         * Strip là bản nâng cấp của trim(), trim() chỉ xóa các ký tự whitespace của ASCII (dưới U+0020)
         * Strip() thì nhận diện được tất cả các ký tự whitespace của Unicode (ví dụ: dấu cách không ngắt dòng...).
         */
        String strip = "   0923001270   \n";
        System.out.println("Strip better trim: " + strip
                .stripLeading()             // stripLeading: loại bỏ khoảng trắng đằng trước
                .stripTrailing()           //  stripTrailing: loại bỏ khoảng trắng đằng sau
                .strip() + "!");           //  strip: loại bỏ khoảng trắng cả 2 đầu


        String s = "Ha";
        String repeated = s.repeat(3); // repeat: lặp lại chuỗi n lần
        System.out.println(repeated); // Kết quả: "HaHaHa"
    }
}