package study.public_data.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PatternTest {

    private static final String regex = ".*[a-zA-Z].*\\d.*[^a-zA-Z\\d\\s].*";

    @Test
    public void prepareToCompile() {
        List<String> inputs = createStrings(100000);

        // "compile을 뺀 경우"
        long startTime = System.nanoTime();
        Pattern pattern = Pattern.compile(regex);  // 정규식 컴파일을 미리 한 경우
        for (String input : inputs) {
            pattern.matcher(input).matches();
        }
        long endTime = System.nanoTime();
        long runTime = endTime - startTime;
        System.out.println("With compile (미리 호출) 시간: " + runTime / 1_000_000 + " ms");
    }

    @Test
    public void NotPrepareToCompile() {
        List<String> inputs = createStrings(100000);

        // "compile을 안 뺀 경우"
        long startTime = System.nanoTime();
        for (String input : inputs) {
            Pattern pattern = Pattern.compile(regex);  // 매번 Pattern.compile 호출
            pattern.matcher(input).matches();
        }
        long endTime = System.nanoTime();
        long runTime = endTime - startTime;
        System.out.println("Without compile (매번 호출) 시간: " + runTime / 1_000_000 + " ms");
    }

    private List<String> createStrings(int count) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(createString(10));
        }
        return list;
    }

    // 랜덤 문자열 생성 (길이 지정)
    private String createString(int length) {
        StringBuilder sb = new StringBuilder(length);
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=_+[]{}|;:,.<>?";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
