import ru.clevertec.controllers.MainOrderController;
import ru.clevertec.enums.Arguments;

public class CheckRunner {

    public static void main(String[] args) {



//        DBController dbController = new DBController();
//
//        try (Connection connection = dbController.getConnection()){
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//


        Arguments.initialize(args);

        MainOrderController mainOrderController = new MainOrderController();
        mainOrderController.readProductsFromFile();
        mainOrderController.readCreditCardFromFile();
        mainOrderController.getDiscountCardForOrder();
        mainOrderController.createMainOrder();
        mainOrderController.printCheck();


        // test aspect to different domen
//        new TestClass().printTestProduct(new Product(22, "testProduct", 2.2));

    }

}




