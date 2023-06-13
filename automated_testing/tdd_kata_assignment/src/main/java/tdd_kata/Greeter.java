package tdd_kata;

import java.util.Arrays;

public class Greeter {
    public String greet(String name) {
        if (isNameEmpty(name)) {
            return "Hello, my friend.";
        }
        if (name.equals(name.toUpperCase())) {
            return String.format("HELLO, %s!", name);
        }
        if (stringToArrayWithSanitation(name).length == 2) {
            return twoNamesToGreeting(stringToArrayWithSanitation(name));
        }
        if (getIndexesOfDoubleQuotes(stringToArrayWithSanitation(name))[0]!=null){
            String[] names = new String[2];
            names [0] = stringToArrayWithSanitation(name)[0];
            names [1] = joinNamesThatAreInDoubleQuotes(stringToArrayWithSanitation(name));
            return twoNamesToGreeting(names);
        }
        if (stringToArrayWithSanitation(name).length > 2 && getIndexOfUpperCaseName(stringToArrayWithSanitation(name)).length < 1) {
            return multipleNotShoutedNames(name);
        }
        if ((stringToArrayWithSanitation(name).length > 2) && (getIndexOfUpperCaseName(stringToArrayWithSanitation(name)).length >= 1)) {
            return multipleMixedNames(stringToArrayWithSanitation(name));
        }


        return String.format("Hello, %s.", name);
    }

    private boolean isNameEmpty(String name) {
        return name == null || name.trim().isEmpty();
    }

    public String[] stringToArrayWithSanitation(String name) {
        return (name
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
                .split(",", 0));
    }

    public Integer[] getIndexOfUpperCaseName(String[] name) {
        Integer[] arrayOfShouted = new Integer[name.length];
        int i = 0;
        for (int x = 0; x < name.length; x++) {
            if (name[x].equals(name[x].toUpperCase())) {
                arrayOfShouted[i] = x;
                i++;
            }
        }
        return Arrays.copyOfRange(arrayOfShouted, 0, i);
    }

    private String twoNamesToGreeting(String[] name) {
        return String.format("Hello, %s and %s.", name[0], name[1]);
    }

    private String multipleNotShoutedNames(String name) {
        String output = "Hello,";

        for (int x = 0; x < stringToArrayWithSanitation(name).length; x++) {
            if (x != stringToArrayWithSanitation(name).length - 1) {
                output += " " + stringToArrayWithSanitation(name)[x] + ",";
            } else {
                output += " and " + stringToArrayWithSanitation(name)[x] + ".";
            }
        }
        return output;
    }

    public String multipleMixedNames(String[] names) {
        if (names.length == 3) {
            String helperStringNotShouted = "";
            String shoutedName = "";

            for (int x = 0; x < names.length; x++) {
                if (names[x].equals(names[x].toUpperCase())) {
                    shoutedName = names[x];
                } else {
                    helperStringNotShouted += stringToArrayWithSanitation(names[x])[0] + ",";
                }
            }

            return "Hello, " + String.join(" and ", helperStringNotShouted.split(",")) + ". AND HELLO, " + shoutedName + "!";
        }

        return "yes";
    }

    public Integer[] getIndexesOfDoubleQuotes(String[] names){
        Integer i = 0;
        Integer[] index= new Integer[2];
        for(int x =0;x< names.length;x++){
            if(names[x].startsWith("\"")||names[x].endsWith("\"")){
                index[i]=x;
                i++;
            }
        }
        return index;

    }
    public String joinNamesThatAreInDoubleQuotes (String[] names){
        String output = "";
        for (int x= getIndexesOfDoubleQuotes(names)[0];x<getIndexesOfDoubleQuotes(names)[1]+1;x++){
            if (x==getIndexesOfDoubleQuotes(names)[1]){
                output+=String.format("%s",names[x]);
            }else{
                output+=String.format("%s, ",names[x]);
            }
        }
        return output.replaceAll("\"","");
    }
}
