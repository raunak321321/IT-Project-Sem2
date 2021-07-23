import java.io.IOException;

public class user {
    public static void main(String[] args) throws IOException {
//        Shopping s = new Shopping("anything",789999);
//        Object.getCategory();
//        Object.getSubCategory("stationary");
//        Object.getProducts("books");
//        Product p =new Product(1);
//        p.getRating();
//        p.getReviews();
//        float a = p.getRating();
//        System.out.println(a);
        Business b = new Business("anything1", 78945612);
//        b.setItem("fashion","Bracelet","Beaded King Bracelet","nice item with good reviews","image1.jpg",450,10);
        Product p = new Product(27);
        float a = p.getRating();
        System.out.println(a);
        p.getReviews();
        p.getName();
        p.getDesc();
        p.getImage();

//        Shopping s = new Shopping("Raunak10",12341);
//        System.out.println(s.getName());
//        System.out.println(s.getEmail());
//        Shopping s1 = new Shopping("haa2a",12322133);
//        System.out.println(s1.getEmail());
//        Shopping r = new Shopping("Raunak1",12341);
//        System.out.println(s1.getName());
//        Object.getTitle();
//        Object.getSubTitle("fashion");
//        Object.getProducts("Dress");
//        Shopping r = new Shopping("Raju56",9898589);
//        Shopping r1 = new Shopping("Raju35",989882);
//        Product p = new Product(9);
//        Product p1 = new Product(3);
//        Product p2 = new Product(2);
//        r.buyCartProd();
//        r.buy(p,9);
//        p.setQuantity(50);
//        r.addToCart(p,9);
//        r.addToCart(p1,1);
//        r.addToCart(p,50);
//        r1.addToCart(p1,1);
//        r1.addToCart(p2,6);
//        r1.buyCartProd();
//        r.setRating(p,4);

//        r.buy(p,3);
//        p.getDesc();
//        p.getReviews();
//
//        p.getPrice();
//        p.getRating();
//        p.getImage();
    }
}
