public class Object {

    //    these all methods just calling Admin class method for some file updating and all
    public static void getCategory() {
        Admin.letGetCategory();
    }

    public static void getSubCategory(String category) {
        Admin.letGetSubCategory(category);
    }

    public static void getProducts(String subCategory) {
        Admin.letGetProducts(subCategory);
    }

    public static void main(String[] args) {

    }
}