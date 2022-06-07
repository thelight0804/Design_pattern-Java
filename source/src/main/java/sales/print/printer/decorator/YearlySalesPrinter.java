package sales.print.printer.decorator;

import sales.print.printer.SalesPrint;

public class YearlySalesPrinter extends PrintDecorator {

    /**
     * Constructor.
     * @param printer The SalesPrint object to be decorated.
     */
    public YearlySalesPrinter(SalesPrint printer) {
        this.parentPrinter = printer;
    }

    @Override
    public String print() {
        // TODO: need working source code for printing yearly sales
        return "Yearly Sales \n" + parentPrinter.print();
    }
}
