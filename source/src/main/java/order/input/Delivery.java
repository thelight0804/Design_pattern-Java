package order.input;

/**
 * 배달 방식을 구현하는 클래스
 */
public class Delivery implements ServeType{
    public String serving(){
        return "(조리 완료 알람을 보냅니다.)";
    }
}