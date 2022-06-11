package menu.sales.print.printer.decorator;

import menu.sales.print.printer.SalesPrint;

public class MonthlySalesPrinter extends PrintDecorator{

    /**
     * Constructor.
     * @param printer The SalesPrint object to be decorated.
     */
    public MonthlySalesPrinter(SalesPrint printer) {
        this.parentPrinter = printer;
    }

    @Override
    public String print() {
        // TODO: need working source code for printing monthly sales
        return "Monthly Sales \n" + parentPrinter.print();
    }

}
