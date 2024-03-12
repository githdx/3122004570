import cn.hutool.core.io.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class TxtHandle {
    //txt文本读取
    public static String txtRead(String path){
        List<String> paragraph = FileUtil.readUtf8Lines(path);
        StringBuilder str= new StringBuilder();
        for(String string:paragraph){
            str.append(string);
        }
        return str.toString();
    }

    //txt文本写入
    public static void txtWrite(String path,Double similarity){
        List<Double> content=new ArrayList<>();
        content.add(similarity);
        FileUtil.appendUtf8Lines(content,path);
    }
}
