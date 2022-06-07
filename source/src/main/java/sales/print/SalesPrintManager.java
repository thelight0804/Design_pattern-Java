package sales.print;

import conf.interfaces.Manager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import repository.SalesRepository;
import sales.print.printer.CardSalesPrint;
import sales.print.printer.GiftcardSalesPrint;
import sales.print.printer.SalesPrint;
import sales.print.printer.decorator.MonthlySalesPrinter;
import sales.print.printer.decorator.YearlySalesPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;
import java.util.function.Supplier;

public class SalesPrintManager implements Manager {
    private SalesRepository salesRepository = SalesRepository.getInstance();

    private SalesPrint decoratePrint(SalesPrint print) {
        System.out.println("회계 정보를 출력하고 싶은 형식을 선택하세요.");
        int i = 1;
        for (SalesDecoratorType decorator : SalesDecoratorType.values()) {
            System.out.println(i + ": " + decorator.getName());
            i++;
        }
        System.out.println(i + ": 종료");
        System.out.print("선택: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int option = Integer.parseInt(reader.readLine());
            if (option != i) {
                return this.decoratePrint(SalesDecoratorType.values()[option - 1]
                        .getDecorator()
                        .apply(print));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return print;
    }

    private void printSales() {
        System.out.println("출력하고 싶은 회계 정보를 선택하세요");
        int i = 1;
        for (SalesPrintType salesPrintType : SalesPrintType.values()) {
            System.out.println(i + ": " + salesPrintType.getName());
            i++;
        }
        System.out.println((i) + ": 이전 메뉴로 돌아가기");
        System.out.print("선택: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int option = Integer.parseInt(reader.readLine());
            if (option != i) {
                SalesPrint print = this.decoratePrint(
                        SalesPrintType.values()[option - 1]
                                .getConstructor()
                                .get());
                System.out.println(print.print());
            }
            SalesPrintType type = SalesPrintType.values()[option - 1];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("매출 관리 시스템 ---- ");
            System.out.println("1. 회계 정보 출력");
            System.out.println("2. 회계 정보 입력");
            System.out.println("3. 종료");
            System.out.print("선택: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:
                        printSales();
                        break;
                    case 2:
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SalesPrintManager().run();
    }
}

@AllArgsConstructor @Getter
enum SalesPrintType {
    ALL_SALES_PRINT(SalesPrint::new, "전체 회계 정보"),
    CARD_SALES_PRINT(CardSalesPrint::new, "카드 회계 정보"),
    GIFTCARD_SALES_PRINT(GiftcardSalesPrint::new, "상품권 회계 정보"),
    ;

    private final Supplier<SalesPrint> constructor;
    private final String name;
}

@AllArgsConstructor @Getter
enum SalesDecoratorType {
    MONTHLY_SALES_PRINTER(MonthlySalesPrinter::new, "월별 회계 정리본"),
    YEARLY_SALES_PRINTER(YearlySalesPrinter::new, "연별 회계 정리본"),
    ;

    private final Function<SalesPrint, SalesPrint> decorator;
    private final String name;
}