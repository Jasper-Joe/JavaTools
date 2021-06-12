import socket.RuntimeUtil;

public class Main {
    public static void main(String[] args) {
        long res = RuntimeUtil.getFreeMemory();
        p(res);


    }

    public static void p(Object res) {
        System.out.println(res);
    }

}
