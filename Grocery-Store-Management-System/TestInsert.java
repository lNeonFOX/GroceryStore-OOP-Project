import database.ProductDAO;
import model.Product;

public class TestInsert {
    public static void main (String[] args) {
        Product product = new Product(111, "Rice", 870.0, 9, "Diary") {
            @Override
            public void handle() {

            }

            @Override
            public String getType() {
                return "";
            }
        };

        ProductDAO dao = new ProductDAO();
        dao.insertProduct(product);
    }
}
