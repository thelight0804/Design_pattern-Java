package menu.sales.print.printer.decorator;

import menu.sales.print.printer.SalesPrint;

/**
 * This class can decorate a SalesPrint object.
 * @see SalesPrint
 */
public abstract class PrintDecorator extends SalesPrint {
    /**
     * The SalesPrint object to be decorated.
     */
    protected SalesPrint parentPrinter;

    /**
     * @see SalesPrint#print()
     */
    public abstract String print();
}
