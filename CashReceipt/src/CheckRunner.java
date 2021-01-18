import ru.clevertec.beans.MainOrder;
import ru.clevertec.controllers.MainOrderController;
import ru.clevertec.enums.Arguments;

public class CheckRunner {
    public static void main(String[] args) {

        Arguments.initialize(args);

        MainOrder mainOrder = new MainOrder();


        MainOrderController mainOrderController = new MainOrderController(mainOrder);
        mainOrderController.readProductsFromFile();
        mainOrderController.readCreditCardFromFile();
        mainOrderController.getDiscountCardForOrder();
        mainOrderController.createMainOrder();
        mainOrderController.printCheck();


//        JSong jSong = new JSong();
//        jSong.setPrettyString(true);
//        jSong.setProcessedObject(purchase);
//        String jSonString = jSong.serialize();
//        System.out.println(jSonString);

    }
}


