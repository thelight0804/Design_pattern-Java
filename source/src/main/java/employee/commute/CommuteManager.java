package employee.commute;

import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import conf.middleware.Console;
import employee.Employee;
import employee.EmployeeManagement;
import employee.commute.command.CommuteCommand;
import employee.commute.command.OffWorkCommand;
import employee.commute.command.OnWorkCommand;
import employee.commute.receiver.DeliveryEmployee;
import employee.commute.receiver.KitchenEmployee;
import employee.exception.NoSpaceForCommandException;
import menu.MenuManagement;
import repository.EmployeeRepository;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class works as a Invoker in the Command Pattern.
 * Client should depends this class to manage the Commute.
 */
public class CommuteManager implements Manager {
    private final int MAX_EMPLOYEE = 10;
    private final CommuteCommand[] onWorkCommands = new OnWorkCommand[MAX_EMPLOYEE];
    private final CommuteCommand[] offWorkCommands = new OffWorkCommand[MAX_EMPLOYEE];

    // Use singleton pattern
    private static CommuteManager instance = new CommuteManager();
    public static CommuteManager getInstance() {
        if (instance == null) {
            instance = new CommuteManager();
        }
        return instance;
    }

    public void setCommuteCommand(int index, CommuteCommand onWorkCommand, CommuteCommand offWorkCommand) {
        onWorkCommands[index] = onWorkCommand;
        offWorkCommands[index] = offWorkCommand;
    }

    public int findEmptyIndex() throws NoSpaceForCommandException {
        for(int i = 0; i < MAX_EMPLOYEE; i++) {
            if(onWorkCommands[i] == null && offWorkCommands[i] == null) {
                return i;
            }
        }
        throw new NoSpaceForCommandException("Not enough space for commands");
    }

    public CommuteManager() { }

    public void onWork() {
        try {
            int index = Integer.parseInt(Console.getInput("개인 번호를 입력하세요"));
            onWorkCommands[index].execute();
        }
        catch (NumberFormatException e) {
            System.out.println("올바르지 못한 숫자 형식입니다.");
        }
        catch (NullPointerException e) {
            System.out.println("등록되지 않은 직원 개인 번호입니다.");
        }
    }

    public void offWork() {
        try {
            int index = Integer.parseInt(Console.getInput("개인 번호를 입력하세요"));
            offWorkCommands[index].execute();
        }
        catch (NumberFormatException e) {
            System.out.println("올바르지 못한 숫자 형식입니다.");
        }
        catch (NullPointerException e) {
            System.out.println("등록되지 않은 직원 개인 번호입니다.");
        }
    }

    public void run() {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        for (; ; ) {
            System.out.println("등록할 때 부여된 개인 번호를 통해 출근/퇴근 확인을 해주시기 바랍니다.");
            System.out.println("1. 출근 등록");
            System.out.println("2. 퇴근 등록");
            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        System.out.println("출근 등록을 위해 등록할 개인 번호를 입력해주세요.");
                        int index = Integer.parseInt(br.readLine());
                        onWork();
                        break;
                    case 2:
                        System.out.println("퇴근 등록을 위해 등록할 개인 번호를 입력해주세요.");
                        index = Integer.parseInt(br.readLine());
                        offWork();
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다.");
                        return;
                }
            } catch (NumberFormatException e) { // NumberFormatException if input is not integer
                System.out.println("잘못된 입력입니다.");
            } catch (Exception e) {
                System.out.println("에러가 발생했습니다.");
            }
        }
    }

    public static void main(String[] args) {
        CommuteManager commuteManager = new CommuteManager();
        // 정우성, password 배달 직원 등록
        DeliveryEmployee employee0 = new DeliveryEmployee(new Employee("정우성", "password"));
        commuteManager.setCommuteCommand(0,
                new OnWorkCommand(employee0),
                new OffWorkCommand(employee0));
        // 이순신, password 배달 직원 등록
        DeliveryEmployee employee1 = new DeliveryEmployee(new Employee("이순신", "password"));
        commuteManager.setCommuteCommand(1,
                new OnWorkCommand(employee1),
                new OffWorkCommand(employee1));
        // 박봉팔, passwd 주방 직원 등록
        KitchenEmployee employee2 = new KitchenEmployee(new Employee("박봉팔", "passwd"));
        commuteManager.setCommuteCommand(2,
                new OnWorkCommand(employee2),
                new OffWorkCommand(employee2));

        commuteManager.run();
    }

    enum CommuteManagementEndpoint implements EndpointElement {
        ON_WORK{
            @Override public String getName() {
                return "출근";
            }
            @Override public String getDescription() {
                return "출근 시간을 데이터베이스에 주가합니다.";
            }
            @Override public Runnable getRunner() {
                return CommuteManager.getInstance()::onWork;
            }
        },
        OFF_WORK{
            @Override public String getName() {
                return "퇴근";
            }
            @Override public String getDescription() {
                return "퇴근 시간을 데이터베이스에 주가합니다.";
            }
            @Override public Runnable getRunner() {
                return CommuteManager.getInstance()::offWork;
            }
        }


    }





}
