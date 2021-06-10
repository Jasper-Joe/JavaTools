public class Main {
    public static void main(String[] args) {
        char a = 'A';
        String A = String.valueOf(a);
        char c = '\ufeff';
        int res = (int)c;
        //boolean res = Character.isWhitespace(' ');
        System.out.println(res);

    }

}
