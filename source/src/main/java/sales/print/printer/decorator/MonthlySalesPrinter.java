package sales.print.printer.decorator;

import sales.print.printer.SalesPrint;

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
        return "Monthly Sales \n" + parentPrinter.print();
    }

}
