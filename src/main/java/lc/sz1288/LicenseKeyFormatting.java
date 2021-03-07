package lc.sz1288;

public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder newS = new StringBuilder(S.toUpperCase().replaceAll("-", ""));
        int pos = newS.length() - K;
        while (pos > 0) {
            newS.insert(pos, '-');
            pos -= K;
        }
        return newS.toString();
    }

    public static void main(String[] args) {
        LicenseKeyFormatting lk = new LicenseKeyFormatting();
        System.out.println(lk.licenseKeyFormatting("2-5g-3-J", 2));
    }
}
