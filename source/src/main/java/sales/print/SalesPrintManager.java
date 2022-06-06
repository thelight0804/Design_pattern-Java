package sales.print;

import conf.interfaces.Manager;
import sales.print.printer.SalesPrint;
import sales.print.printer.decorator.MonthlySalesPrinter;

public class SalesPrintManager implements Manager {


    @Override
    public void run() {

    }

    public static void main(String[] args) {
        SalesPrint salesPrint = new SalesPrint();
        salesPrint = new MonthlySalesPrinter(salesPrint);
        salesPrint = new MonthlySalesPrinter(salesPrint);
        System.out.println(salesPrint.print());
    }
}
