package algorithms.recursion.password_cracker;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;



class Result {

    static List<Integer> alreadyVisitedIndexes;
    
    public static String passwordCrackerWithValidation(List<String> passwords, String loginAttempt) {
        alreadyVisitedIndexes = new ArrayList<>();
        Map<Character, List<String>> possibleValuesPerCharacter = new HashMap<>();
        
        for(String password : passwords) {
            Character initialCharacter = password.charAt(0);
            possibleValuesPerCharacter.putIfAbsent(initialCharacter, new ArrayList<>());
            possibleValuesPerCharacter.get(initialCharacter).add(password);
        }
        
        return passwordCracker(possibleValuesPerCharacter, loginAttempt, 0);
    }
    
    public static String passwordCracker(Map<Character, List<String>> passwords, String loginAttempt, Integer index) {
        alreadyVisitedIndexes.add(index);
        List<String> possiblePasswords = passwords.get(loginAttempt.charAt(0));
        
        if(possiblePasswords == null || possiblePasswords.isEmpty()) return "WRONG PASSWORD";
        
        for(int i = 0; i < possiblePasswords.size(); i++) {
            String currentPassword = possiblePasswords.get(i);
            
            if(loginAttempt.startsWith(currentPassword)) {
                String newLoggingAttempt = loginAttempt.substring(currentPassword.length()); 
                
                if(newLoggingAttempt.isEmpty()) {
                    return currentPassword;
                } else if(!alreadyVisitedIndexes.contains(index + currentPassword.length())) {            
                    String newReturnable = passwordCracker(passwords, newLoggingAttempt, index + currentPassword.length());
                    if(!newReturnable.equals("WRONG PASSWORD")) {
                        return currentPassword + " " + newReturnable;
                    }
                }
            }
        }

        return "WRONG PASSWORD";
    }

}

public class Solution {
    @SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<String> passwords = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .collect(toList());

                String loginAttempt = bufferedReader.readLine();

                String result = Result.passwordCrackerWithValidation(passwords, loginAttempt);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
