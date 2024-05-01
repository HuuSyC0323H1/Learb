package com.coreprocess;

import com.coreprocess.model.Category;
import com.coreprocess.model.ShopeeCategory;
import com.coreprocess.model.Subcategory;
import com.coreprocess.repositories.ShopeeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Huu Sy
 * @Date: 01/05/2024 8:35 SA
 */

@Component
public class ShopeeProcess implements ApplicationRunner {

    @Autowired
    private ShopeeCategoryRepository shopeeCategoryRepository;

    private Long subcategoryIdCounter = 1L;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long subCategoryId = 1L;
        List<ShopeeCategory> shopeeCategory = shopeeCategoryRepository.findAll();
        String categoryName = null;
        String subcategoryName = null;
        for (ShopeeCategory s : shopeeCategory) {
            for (Category category : s.getCategories()) {
                for (Subcategory subcategory : category.getSubcategories()) {
                    if (subcategory.getSubCategoryId() == subCategoryId) {
                        categoryName = category.getName();
                        subcategoryName = subcategory.getName();
                        break;
                    }
                }
                if (categoryName != null && subcategoryName != null) {
                    break;
                }
            }

            if (categoryName != null && subcategoryName != null) {
                System.out.println("Category Name: " + categoryName);
                System.out.println("Subcategory Name: " + subcategoryName);
            } else {
                System.out.println("Subcategory with id " + subCategoryId + " not found.");
            }
        }
    }

    private void addShopeeCategories() {
        ShopeeCategory shopeeCategory = new ShopeeCategory();
        shopeeCategory.setCategories(new ArrayList<>());

        Category category = new Category();
        category.setName("Thời trang nam");
        List<Subcategory> subcategories1 = new ArrayList<>();
        subcategories1.add(createSubcategory("Áo Khoác"));
        subcategories1.add(createSubcategory("Áo Vest và Blazer"));
        subcategories1.add(createSubcategory("Áo Hoodie, Áo Len & Áo Nỉ"));
        subcategories1.add(createSubcategory("Quần Jeans"));
        subcategories1.add(createSubcategory("Quần Dài/Quần Âu"));
        subcategories1.add(createSubcategory("Quần Short"));
        subcategories1.add(createSubcategory("Áo"));
        subcategories1.add(createSubcategory("Áo Ba Lỗ"));
        subcategories1.add(createSubcategory("Đồ Lót"));
        subcategories1.add(createSubcategory("Đồ Ngủ"));
        subcategories1.add(createSubcategory("Đồ Bộ"));
        subcategories1.add(createSubcategory("Vớ/Tất"));
        subcategories1.add(createSubcategory("Trang Phục Truyền Thống"));
        subcategories1.add(createSubcategory("Đồ Hóa Trang"));
        subcategories1.add(createSubcategory("Trang Phục Ngành Nghề"));
        subcategories1.add(createSubcategory("Khác"));
        category.setSubcategories(subcategories1);
        shopeeCategory.getCategories().add(category);
        System.out.printf("Category" + category.getName());
        Category category1 = new Category();
        category1.setName("Điện thoại & Phụ kiện");
        List<Subcategory> subcategories2 = new ArrayList<>();
        subcategories2.add(createSubcategory("Máy tính bảng"));
        subcategories2.add(createSubcategory("Pin Dự Phòng"));
        subcategories2.add(createSubcategory("Pin Gắn Trong, Cáp và Bộ Sạc"));
        subcategories2.add(createSubcategory("Ốp lưng, bao da, Miếng dán điện thoại"));
        subcategories2.add(createSubcategory("Bảo vệ màn hình"));
        subcategories2.add(createSubcategory("Đế giữ điện thoại"));
        subcategories2.add(createSubcategory("Thẻ nhớ"));
        subcategories2.add(createSubcategory("Sim"));
        subcategories2.add(createSubcategory("Phụ kiện khác"));
        subcategories2.add(createSubcategory("Thiết bị khác"));
        category1.setSubcategories(subcategories2);
        shopeeCategory.getCategories().add(category1);
        System.out.printf("Category" + category1.getName());
        Category category2 = new Category();
        category2.setName("Thiết bị điện tử");
        List<Subcategory> subcategories3 = new ArrayList<>();
        subcategories3.add(createSubcategory("Tai nghe nhét tai"));
        subcategories3.add(createSubcategory("Thiết bị đeo thông minh"));
        subcategories3.add(createSubcategory("Đĩa game"));
        subcategories3.add(createSubcategory("Phụ kiện tivi"));
        subcategories3.add(createSubcategory("Linh phụ kiện"));
        subcategories3.add(createSubcategory("Máy game console"));
        subcategories3.add(createSubcategory("Phu kiện console"));
        subcategories3.add(createSubcategory("Loa"));
        subcategories3.add(createSubcategory("Tivi"));
        subcategories3.add(createSubcategory("Tivi Box"));
        subcategories3.add(createSubcategory("Headphones"));
        category2.setSubcategories(subcategories3);
        shopeeCategory.getCategories().add(category2);
        System.out.printf("Category" + category2.getName());
        Category category3 = new Category();
        category3.setName("Máy tính & Laptop");
        List<Subcategory> subcategories = new ArrayList<>();
        subcategories.add(createSubcategory("Máy Tính Bàn"));
        subcategories.add(createSubcategory("Màn Hình"));
        subcategories.add(createSubcategory("Linh Kiện Máy Tính"));
        subcategories.add(createSubcategory("Thiết Bị Lưu Trữ"));
        subcategories.add(createSubcategory("Thiết Bị Mạng"));
        subcategories.add(createSubcategory("Máy In, Máy Scan & Máy Chiếu"));
        subcategories.add(createSubcategory("Phụ Kiện Máy Tính"));
        subcategories.add(createSubcategory("Laptop"));
        subcategories.add(createSubcategory("Khác"));
        subcategories.add(createSubcategory("Gaming"));
        category3.setSubcategories(subcategories);
        shopeeCategory.getCategories().add(category3);
        System.out.printf("Category" + category3.getName());
        Category category4 = new Category();
        category4.setName("Máy ảnh & Máy quay phim");
        List<Subcategory> subcategories4 = new ArrayList<>();
        subcategories4.add(createSubcategory("Máy ảnh - Máy quay phim"));
        subcategories4.add(createSubcategory("Camera giám sát & Camera hệ thống"));
        subcategories4.add(createSubcategory("Thẻ nhớ"));
        subcategories4.add(createSubcategory("Ống kính"));
        subcategories4.add(createSubcategory("Phụ kiện máy ảnh"));
        subcategories4.add(createSubcategory("Máy bay camera & Phụ kiện"));
        category4.setSubcategories(subcategories4);
        shopeeCategory.getCategories().add(category4);
        System.out.printf("Category" + category4.getName());
        Category category5 = new Category();
        category5.setName("Đồng hồ");
        List<Subcategory> subcategories5 = new ArrayList<>();
        subcategories4.add(createSubcategory("Đồng Hồ Nam"));
        subcategories4.add(createSubcategory("Đồng Hồ Nữ"));
        subcategories4.add(createSubcategory("Bộ Đồng Hồ & Đồng Hồ Cặp"));
        subcategories4.add(createSubcategory("Đồng Hồ Trẻ Em"));
        subcategories4.add(createSubcategory("Phụ Kiện Đồng Hồ"));
        subcategories4.add(createSubcategory("Khác"));
        category5.setSubcategories(subcategories5);
        shopeeCategory.getCategories().add(category5);
        System.out.printf("Category" + category5.getName());
        Category category6 = new Category();
        category6.setName("Giày dép nam");
        List<Subcategory> subcategories6 = new ArrayList<>();
        subcategories6.add(createSubcategory("Bốt"));
        subcategories6.add(createSubcategory("Giày Thể Thao/ Sneakers"));
        subcategories6.add(createSubcategory("Giày Sục"));
        subcategories6.add(createSubcategory("Giày Tây Lười"));
        subcategories6.add(createSubcategory("Giày Oxfords & Giày Buộc Dây"));
        subcategories6.add(createSubcategory("Xăng-đan và Dép"));
        subcategories6.add(createSubcategory("Phụ kiện giày dép"));
        subcategories6.add(createSubcategory("Khác"));
        category6.setSubcategories(subcategories6);
        shopeeCategory.getCategories().add(category6);
        System.out.printf("Category" + category6.getName());
        Category category7 = new Category();
        category7.setName("Thiết bị điện gia dụng");
        List<Subcategory> subcategories7 = new ArrayList<>();
        subcategories6.add(createSubcategory("Đồ gia dụng nhà bếp"));
        subcategories6.add(createSubcategory("Đồ gia dụng lớn"));
        subcategories6.add(createSubcategory("Máy hút bụi & Thiết bị làm sạch"));
        subcategories6.add(createSubcategory("Quạt & Máy nóng lạnh"));
        subcategories6.add(createSubcategory("Thiết bị chăm sóc quần áo"));
        subcategories6.add(createSubcategory("Khác"));
        subcategories6.add(createSubcategory("Máy xay, ép, máy đánh trứng trộn bột, máy xay thực phẩm"));
        subcategories6.add(createSubcategory("Bếp điện"));
        category7.setSubcategories(subcategories7);
        shopeeCategory.getCategories().add(category7);
        System.out.printf("Category" + category7.getName());
        Category category8 = new Category();
        category8.setName("Thể thao & Du lịch");
        List<Subcategory> subcategories8 = new ArrayList<>();
        subcategories8.add(createSubcategory("Vali"));
        subcategories8.add(createSubcategory("Túi du lịch"));
        subcategories8.add(createSubcategory("Phụ kiện du lịch"));
        subcategories8.add(createSubcategory("Dụng Cụ Thể Thao & Dã Ngoại"));
        subcategories8.add(createSubcategory("Giày Thể Thao"));
        subcategories8.add(createSubcategory("Thời Trang Thể Thao & Dã Ngoại"));
        subcategories8.add(createSubcategory("Phụ Kiện Thể Thao & Dã Ngoại"));
        subcategories8.add(createSubcategory("Khác"));
        category8.setSubcategories(subcategories8);
        shopeeCategory.getCategories().add(category8);
        System.out.printf("Category" + category8.getName());
        Category category9 = new Category();
        category9.setName("Ô tô & Xe Máy & Xe đạp");
        List<Subcategory> subcategories9 = new ArrayList<>();
        subcategories9.add(createSubcategory("Xe đạp, xe điện"));
        subcategories9.add(createSubcategory("Mô tô, xe máy"));
        subcategories9.add(createSubcategory("Xe Ô tô"));
        subcategories9.add(createSubcategory("Mũ bảo hiểm"));
        subcategories9.add(createSubcategory("Phụ kiện xe máy"));
        subcategories9.add(createSubcategory("Phụ kiện xe đạp"));
        subcategories9.add(createSubcategory("Phụ kiện bên trong ô tô"));
        subcategories9.add(createSubcategory("Dầu nhớt & dầu nhờn"));
        subcategories9.add(createSubcategory("Phụ tùng ô tô"));
        subcategories9.add(createSubcategory("Phụ tùng xe máy"));
        subcategories9.add(createSubcategory("Phụ kiện bên ngoài ô tô"));
        subcategories9.add(createSubcategory("Chăm sóc ô tô"));
        subcategories9.add(createSubcategory("Dịch vụ cho xe"));
        category9.setSubcategories(subcategories9);
        shopeeCategory.getCategories().add(category9);
        System.out.printf("Category" + category9.getName());
        Category category10 = new Category();
        category10.setName("Thời trang nữ");
        List<Subcategory> subcategories10 = new ArrayList<>();
        subcategories10.add(createSubcategory("Quần"));
        subcategories10.add(createSubcategory("Quần đùi"));
        subcategories10.add(createSubcategory("Chân váy"));
        subcategories10.add(createSubcategory("Quần jeans"));
        subcategories10.add(createSubcategory("Đầm/Váy"));
        subcategories10.add(createSubcategory("Váy cưới"));
        subcategories10.add(createSubcategory("Đồ liền thân"));
        subcategories10.add(createSubcategory("Áo khoác, Áo choàng & Vest"));
        subcategories10.add(createSubcategory("Áo len & Cardigan"));
        subcategories10.add(createSubcategory("Hoodie và Áo nỉ"));
        subcategories10.add(createSubcategory("Bộ"));
        subcategories10.add(createSubcategory("Đồ lót"));
        subcategories10.add(createSubcategory("Đồ ngủ"));
        subcategories10.add(createSubcategory("Áo"));
        subcategories10.add(createSubcategory("Đồ tập"));
        subcategories10.add(createSubcategory("Đồ Bầu"));
        subcategories10.add(createSubcategory("Đồ truyền thống"));
        subcategories10.add(createSubcategory("Đồ hóa trang"));
        subcategories10.add(createSubcategory("Vải"));
        subcategories10.add(createSubcategory("Vớ/ Tất"));
        subcategories10.add(createSubcategory("Khác"));
        category10.setSubcategories(subcategories10);
        shopeeCategory.getCategories().add(category10);
        System.out.printf("Category" + category10.getName());
        Category category11 = new Category();
        category11.setName("Mẹ & Bé");
        List<Subcategory> subcategories11 = new ArrayList<>();
        subcategories11.add(createSubcategory("Đồ dùng du lịch cho bé"));
        subcategories11.add(createSubcategory("Đồ dùng ăn dặm cho bé"));
        subcategories11.add(createSubcategory("Phụ kiện cho mẹ"));
        subcategories11.add(createSubcategory("Chăm sóc sức khỏe mẹ"));
        subcategories11.add(createSubcategory("Đồ dùng phòng tắm & Chăm sóc cơ thể bé"));
        subcategories11.add(createSubcategory("Đồ dùng phòng ngủ cho bé"));
        subcategories11.add(createSubcategory("An toàn cho bé"));
        subcategories11.add(createSubcategory("Thực phẩm cho bé"));
        subcategories11.add(createSubcategory("Chăm sóc sức khỏe bé"));
        subcategories11.add(createSubcategory("Tã & bô em bé"));
        subcategories11.add(createSubcategory("Đồ chơi"));
        subcategories11.add(createSubcategory("Bộ & Gói quà tặng"));
        subcategories11.add(createSubcategory("Khác"));
        category11.setSubcategories(subcategories11);
        shopeeCategory.getCategories().add(category11);
        System.out.printf("Category" + category11.getName());
        Category category12 = new Category();
        category12.setName("Nhà cửa & Đời sống");
        List<Subcategory> subcategories12 = new ArrayList<>();
        subcategories12.add(createSubcategory("Chăn, Ga, Gối & Nệm"));
        subcategories12.add(createSubcategory("Đồ nội thất"));
        subcategories12.add(createSubcategory("Trang trí nhà cửa"));
        subcategories12.add(createSubcategory("Dụng cụ & Thiết bị tiện ích"));
        subcategories12.add(createSubcategory("Đồ dùng nhà bếp và hộp đựng thực phẩm"));
        subcategories12.add(createSubcategory("Đèn"));
        subcategories12.add(createSubcategory("Ngoài trời & Sân vườn"));
        subcategories12.add(createSubcategory("Đồ dùng phòng tắm"));
        subcategories12.add(createSubcategory("Vật phẩm thờ cúng"));
        subcategories12.add(createSubcategory("Đồ trang trí tiệc"));
        subcategories12.add(createSubcategory("Chăm sóc nhà cửa và giặt ủi"));
        subcategories12.add(createSubcategory("Sắp xếp nhà cửa"));
        subcategories12.add(createSubcategory("Dụng cụ pha chế"));
        subcategories12.add(createSubcategory("Tinh dầu thơm phòng"));
        subcategories12.add(createSubcategory("Đồ dùng phòng ăn"));
        category12.setSubcategories(subcategories12);
        shopeeCategory.getCategories().add(category12);
        System.out.printf("Category" + category12.getName());
        Category category13 = new Category();
        category13.setName("Sắc đẹp");
        List<Subcategory> subcategories13 = new ArrayList<>();
        subcategories13.add(createSubcategory("Chăm sóc da mặt"));
        subcategories13.add(createSubcategory("Tắm & chăm sóc cơ thể"));
        subcategories13.add(createSubcategory("Trang điểm"));
        subcategories13.add(createSubcategory("Chăm sóc tóc"));
        subcategories13.add(createSubcategory("Dụng cụ & Phụ kiện Làm đẹp"));
        subcategories13.add(createSubcategory("Vệ sinh răng miệng"));
        subcategories13.add(createSubcategory("Nước hoa"));
        subcategories13.add(createSubcategory("Chăm sóc nam giới"));
        subcategories13.add(createSubcategory("Khác"));
        category13.setSubcategories(subcategories13);
        shopeeCategory.getCategories().add(category13);
        System.out.printf("Category" + category13.getName());

        Category category14 = new Category();
        category14.setName("Sức khỏe");
        List<Subcategory> subcategories14 = new ArrayList<>();
        subcategories14.add(createSubcategory("Thực phẩm bổ sung"));
        subcategories14.add(createSubcategory("Dụng cụ y tế"));
        subcategories14.add(createSubcategory("Chăm sóc cá nhân"));
        subcategories14.add(createSubcategory("Chăm sóc gia đình"));
        subcategories14.add(createSubcategory("Sức khỏe tinh thần"));
        subcategories14.add(createSubcategory("Thiết bị y tế"));
        subcategories14.add(createSubcategory("Chăm sóc cơ thể"));
        subcategories14.add(createSubcategory("Chăm sóc gia đình"));
        subcategories14.add(createSubcategory("Khác"));
        category14.setSubcategories(subcategories14);
        shopeeCategory.getCategories().add(category14);
        System.out.printf("Category" + category14.getName());

        Category category15 = new Category();
        category15.setName("Giày dép nữ");
        List<Subcategory> subcategories15 = new ArrayList<>();
        subcategories15.add(createSubcategory("Bốt"));
        subcategories15.add(createSubcategory("Giày Thể Thao/ Sneaker"));
        subcategories15.add(createSubcategory("Giày Đế Bằng"));
        subcategories15.add(createSubcategory("Giày Cao Gót"));
        subcategories15.add(createSubcategory("Giày Đế Xuồng"));
        subcategories15.add(createSubcategory("Xăng-đan Và Dép"));
        subcategories15.add(createSubcategory("Phụ Kiện Giày"));
        subcategories15.add(createSubcategory("Giày Khác"));
        category15.setSubcategories(subcategories15);
        shopeeCategory.getCategories().add(category15);
        System.out.printf("Category" + category15.getName());

        Category category16 = new Category();
        category16.setName("Túi ví nữ");
        List<Subcategory> subcategories16 = new ArrayList<>();
        subcategories16.add(createSubcategory("Ba Lô Nữ"));
        subcategories16.add(createSubcategory("Cặp Laptop"));
        subcategories16.add(createSubcategory("Ví Dự Tiệc & Ví Cầm Tay"));
        subcategories16.add(createSubcategory("Túi Đeo Hông & Túi Đeo Ngực"));
        subcategories16.add(createSubcategory("Túi Tote"));
        subcategories16.add(createSubcategory("Túi Quai Xách"));
        subcategories16.add(createSubcategory("Túi Đeo Chéo & Túi Đeo Vai"));
        subcategories16.add(createSubcategory("Ví/Bóp Nữ"));
        subcategories16.add(createSubcategory("Phụ Kiện Túi"));
        subcategories16.add(createSubcategory("Khác"));
        category16.setSubcategories(subcategories16);
        shopeeCategory.getCategories().add(category16);
        System.out.printf("Category" + category16.getName());

        Category category17 = new Category();
        category17.setName("Phụ kiện & Trang sức nữ");
        List<Subcategory> subcategories17 = new ArrayList<>();
        subcategories17.add(createSubcategory("Nhẫn"));
        subcategories17.add(createSubcategory("Bông tai"));
        subcategories17.add(createSubcategory("Khăn choàng"));
        subcategories17.add(createSubcategory("Găng tay"));
        subcategories17.add(createSubcategory("Phụ kiện tóc"));
        subcategories17.add(createSubcategory("Vòng tay & Lắc tay"));
        subcategories17.add(createSubcategory("Lắc chân"));
        subcategories17.add(createSubcategory("Mũ"));
        subcategories17.add(createSubcategory("Dây chuyền"));
        subcategories17.add(createSubcategory("Kính mắt"));
        subcategories17.add(createSubcategory("Kim loại quý"));
        subcategories17.add(createSubcategory("Thắt lưng"));
        subcategories17.add(createSubcategory("Cà vạt & Nơ cổ"));
        subcategories17.add(createSubcategory("Phụ kiện thêm"));
        subcategories17.add(createSubcategory("Bộ phụ kiện"));
        subcategories17.add(createSubcategory("Khác"));
        subcategories17.add(createSubcategory("Vớ/ Tất"));
        subcategories17.add(createSubcategory("Ô/Dù"));
        category17.setSubcategories(subcategories17);
        shopeeCategory.getCategories().add(category17);
        System.out.printf("Category" + category17.getName());
        Category category18 = new Category();
        category18.setName("Bách hóa online");
        List<Subcategory> subcategories18 = new ArrayList<>();
        subcategories18.add(createSubcategory("Đồ ăn vặt"));
        subcategories18.add(createSubcategory("Đồ chế biến sẵn"));
        subcategories18.add(createSubcategory("Nhu yếu phẩm"));
        subcategories18.add(createSubcategory("Nguyên liệu nấu ăn"));
        subcategories18.add(createSubcategory("Đồ làm bánh"));
        subcategories18.add(createSubcategory("Sữa - trứng"));
        subcategories18.add(createSubcategory("Đồ uống"));
        subcategories18.add(createSubcategory("Ngũ cốc & mứt"));
        subcategories18.add(createSubcategory("Các loại bánh"));
        subcategories18.add(createSubcategory("Đồ uống có cồn"));
        subcategories18.add(createSubcategory("Bộ quà tặng"));
        subcategories18.add(createSubcategory("Thực phẩm tươi sống và thực phẩm đông lạnh"));
        subcategories18.add(createSubcategory("Khác"));
        category18.setSubcategories(subcategories18);
        shopeeCategory.getCategories().add(category18);
        System.out.printf("Category" + category18.getName());

        Category category19 = new Category();
        category19.setName("Nhà sách online");
        List<Subcategory> subcategories19 = new ArrayList<>();
        subcategories19.add(createSubcategory("Sách Tiếng Việt"));
        subcategories19.add(createSubcategory("Sách ngoại văn"));
        subcategories19.add(createSubcategory("Gói Quà"));
        subcategories19.add(createSubcategory("Bút viết"));
        subcategories19.add(createSubcategory("Dụng cụ học sinh & văn phòng"));
        subcategories19.add(createSubcategory("Màu, Họa Cụ và Đồ Thủ Công"));
        subcategories19.add(createSubcategory("Sổ và Giấy Các Loại"));
        subcategories19.add(createSubcategory("Quà Lưu Niệm"));
        subcategories19.add(createSubcategory("Nhạc cụ và phụ kiện âm nhạc"));
        category19.setSubcategories(subcategories19);
        shopeeCategory.getCategories().add(category19);
        System.out.printf("Category" + category19.getName());
        shopeeCategoryRepository.save(shopeeCategory);
    }

    private Subcategory createSubcategory(String name) {
        Subcategory subcategory = new Subcategory();
        subcategory.setSubCategoryId(subcategoryIdCounter++);
        subcategory.setName(name);
        return subcategory;
    }
}
