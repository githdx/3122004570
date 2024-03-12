import cn.hutool.core.io.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class TxtHandle {
    //txt文本读取
    public static String txtRead(String path){
        //文件打开失败错误提示
        try{
            List<String> paragraph = FileUtil.readUtf8Lines(path);
            StringBuilder str= new StringBuilder();
            for(String string:paragraph){
                str.append(string);
            }
            return str.toString();
        }catch(Exception e){
            System.out.println("文件打开失败，请重新选择文件！");
            return null;
        }
    }

    //txt文本写入
    public static void txtWrite(String path,Double similarity){
        List<Double> content=new ArrayList<>();
        content.add(similarity);
        FileUtil.appendUtf8Lines(content,path);
    }
}
