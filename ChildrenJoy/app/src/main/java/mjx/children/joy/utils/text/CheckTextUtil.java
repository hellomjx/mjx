package mjx.children.joy.utils.text;

/**
 * Created by Administrator on 2018/3/27.
 */
public class CheckTextUtil {
    String delStr6 = "&;";
    String delStr7 = "=";
    String delStr8 = "()";
    String delStr9 = "//";
    String delStr10 = "+";
    String delStr11 = "-";
    String delStr12 = ";";
    String delStr13 = ".";
    String delStr14 = "/";
    String delStr15 = "(";
    String delStr16 = ")";
    String delStr17 = "\"\"";
    String delStr18 = "'";
    public String delUnavliableText(String textStr){
        if(textStr == null){
            return "";
        }
        textStr = textStr.replace(delStr6,"");
        textStr = textStr.replace(delStr7,"");
        textStr = textStr.replace(delStr8,"");
        textStr = textStr.replace(delStr9,"");
        textStr = textStr.replace(delStr10,"");
        textStr = textStr.replace(delStr11,"");
        textStr = textStr.replace(delStr12,"");
        textStr = textStr.replace(delStr13,"");
        textStr = textStr.replace(delStr14,"");
        textStr = textStr.replace(delStr15,"");
        textStr = textStr.replace(delStr16,"");
        textStr = textStr.replace(delStr17,"");
        textStr = textStr.replace(delStr18,"");
        return textStr;
    }
}
