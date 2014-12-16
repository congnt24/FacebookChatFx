package controller;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Cong on 12/16/2014.
 */
public final class SmileyHandler {
	
    public static final HashMap<String, String> emoticons = new HashMap<String, String>();
    static {
        emoticons.put(":\\(", "emo_im_sad");
        emoticons.put(":-\\(", "emo_im_sad");
        emoticons.put(":D", "emo_im_laughing");
        emoticons.put(":-D", "emo_im_laughing");
        emoticons.put(":P", "emo_im_tongue_sticking_out");
        emoticons.put(":-P", "emo_im_tongue_sticking_out");
        emoticons.put(":p", "emo_im_tongue_sticking_out");
        emoticons.put(":-p", "emo_im_tongue_sticking_out");
        emoticons.put(";\\)", "emo_im_winking");
        emoticons.put(";-\\)", "emo_im_winking");
        emoticons.put("o.O", "emo_im_wtf");
        emoticons.put("O.o", "emo_im_wtf");
        emoticons.put("o.o", "emo_im_wtf");
        emoticons.put(":wtf:", "emo_im_wtf");
        emoticons.put("://", "emo_im_undecided");
        emoticons.put(":-//", "emo_im_undecided");
        emoticons.put(":\\\\", "emo_im_undecided");
        emoticons.put(":-\\\\", "emo_im_undecided");
        emoticons.put(":o", "emo_im_surprised");
        emoticons.put(":-o", "emo_im_surprised");
        emoticons.put(":O", "emo_im_surprised");
        emoticons.put(":-O", "emo_im_surprised");
        emoticons.put("0:-\\)", "emo_im_angel");
        emoticons.put("0:\\)", "emo_im_angel");
        emoticons.put("O:-\\)", "emo_im_angel");
        emoticons.put("O:\\)", "emo_im_angel");
        emoticons.put(":\\)", "emo_im_happy");
        emoticons.put("=\\)", "emo_im_happy");
        emoticons.put(":-\\)", "emo_im_happy");
        emoticons.put("B\\)", "emo_im_cool");
        emoticons.put("B-\\)", "emo_im_cool");
        emoticons.put(":cool:", "emo_im_cool");
        emoticons.put(":'\\(", "emo_im_crying");
        emoticons.put(";\\(", "emo_im_crying");
        emoticons.put(":!", "emo_im_foot_in_mouth");
        emoticons.put(":-!", "emo_im_foot_in_mouth");
        emoticons.put("$\\)", "emo_im_money_mouth");
        emoticons.put("$-\\)", "emo_im_money_mouth");
        emoticons.put(":@", "emo_im_yelling");
        emoticons.put(":-@", "emo_im_yelling");
        emoticons.put(":#", "emo_im_lips_are_sealed");
        emoticons.put(":-#", "emo_im_lips_are_sealed");
        emoticons.put(":\\*", "emo_im_kissing");
        emoticons.put(":-\\*", "emo_im_kissing");
    }
    public static final HashMap<String, String> emoticons2 = new HashMap<String, String>();
    static {
//        emoticons2.put(":\\(", "emo_im_sad");
        emoticons2.put(":-(", "emo_im_sad");
        emoticons2.put(":D", "emo_im_laughing");
//        emoticons2.put(":-D", "emo_im_laughing");
        emoticons2.put(":P", "emo_im_tongue_sticking_out");
//        emoticons2.put(":-P", "emo_im_tongue_sticking_out");
//        emoticons2.put(":p", "emo_im_tongue_sticking_out");
//        emoticons2.put(":-p", "emo_im_tongue_sticking_out");
        emoticons2.put(";)", "emo_im_winking");
//        emoticons2.put(";-\\)", "emo_im_winking");
        emoticons2.put("o.O", "emo_im_wtf");
//        emoticons2.put("O.o", "emo_im_wtf");
//        emoticons2.put("o.o", "emo_im_wtf");
//        emoticons2.put(":wtf:", "emo_im_wtf");
        emoticons2.put(":/", "emo_im_undecided");
//        emoticons2.put(":-//", "emo_im_undecided");
//        emoticons2.put(":\\\\", "emo_im_undecided");
//        emoticons2.put(":-\\\\", "emo_im_undecided");
        emoticons2.put(":o", "emo_im_surprised");
//        emoticons2.put(":-o", "emo_im_surprised");
//        emoticons2.put(":O", "emo_im_surprised");
//        emoticons2.put(":-O", "emo_im_surprised");
        emoticons2.put("0:-)", "emo_im_angel");
//        emoticons2.put("0:\\)", "emo_im_angel");
//        emoticons2.put("O:-\\)", "emo_im_angel");
//        emoticons2.put("O:\\)", "emo_im_angel");
        emoticons2.put(":)", "emo_im_happy");
//        emoticons2.put("=\\)", "emo_im_happy");
//        emoticons2.put(":-\\)", "emo_im_happy");
        emoticons2.put("B)", "emo_im_cool");
//        emoticons.put("B-\\)", "emo_im_cool");
//        emoticons.put(":cool:", "emo_im_cool");
        emoticons2.put(":'(", "emo_im_crying");
//        emoticons.put(";\\(", "emo_im_crying");
        emoticons2.put(":!", "emo_im_foot_in_mouth");
//        emoticons.put(":-!", "emo_im_foot_in_mouth");
//        emoticons.put("$\\)", "emo_im_money_mouth");
//        emoticons.put("$-\\)", "emo_im_money_mouth");
        emoticons2.put(":@", "emo_im_yelling");
//        emoticons.put(":-@", "emo_im_yelling");
        emoticons2.put(":#", "emo_im_lips_are_sealed");
//        emoticons2.put(":-#", "emo_im_lips_are_sealed");
        emoticons2.put(":*", "emo_im_kissing");
//        emoticons2.put(":-\\*", "emo_im_kissing");
    }
 
    public static String getSmiledText(String message) {
    	String result=message;
    	for (final String smiley : getSmileyStrings()) {
    		result=result.replaceAll(smiley, "<img src='"+ClassLoader.getSystemResource("smiley/"+emoticons.get(smiley)+".png")+"'/>");
        }
    	return result;
    }
    public static String[] getSmileyStrings() {
        final Set<String> set = emoticons.keySet();
        return set.toArray(new String[set.size()]);
    }
    public static String[] getSmileyStrings2() {
        final Set<String> set = emoticons2.keySet();
        return set.toArray(new String[set.size()]);
    }
}