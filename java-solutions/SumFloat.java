public class SumFloat {
    public static void main(final String args[]) {
        float result = 0;
        for (String arg: args) {
            int left = -1;            
            for (int pos = 0; pos < arg.length(); pos++) {
                if (check(arg, pos)) {
                    if (left == -1) {
                        left = pos;
                    }
                } else if (left != -1) {
                    result += Float.valueOf(arg.substring(left, pos));
                    left = -1;
                }
            }

            if (left != -1) {
                result += Float.valueOf(arg.substring(left, arg.length()));
            }
        }

        System.out.println(result);
    }

    private static boolean check(String arr, int ind) {
        return !Character.isWhitespace(arr.charAt(ind));
    }
}
