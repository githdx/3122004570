import com.hankcs.hanlp.HanLP;

import java.util.Arrays;
import java.util.List;

public class CheckMain {
    public static void main(String[] args) {
        //1.读取txt文件
        String txt1=TxtHandle.txtRead("../../src/resources/orig.txt");
        String txt2=TxtHandle.txtRead("../../src/resources/orig_0.8_add.txt");

        //2.获取SimHash
        int[] weightVectorList1=Calculation.getSiHash(txt1);
        int[] weightVectorList2=Calculation.getSiHash(txt2);

        //3.计算海明距离
        int distance=Calculation.calculateHammingDistance(weightVectorList1,weightVectorList2);
        //4.计算论文相似度
        double similarity=Calculation.calculateHammingSimilarity(distance);
        System.out.println("论文相似度："+similarity+"%");
        //5.将结果写入文件
        TxtHandle.txtWrite("../../src/resources/answer.txt",similarity);
    }
}
