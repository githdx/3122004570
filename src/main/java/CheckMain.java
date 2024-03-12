import com.hankcs.hanlp.HanLP;

import java.util.Arrays;
import java.util.List;

public class CheckMain {
    public static void main(String[] args) {
        String txt1=TxtHandle.txtRead("../../src/resources/orig.txt");
        String txt2=TxtHandle.txtRead("../../src/resources/orig_0.8_add.txt");

        //1.获取SimHash
        int[] weightVectorList1=Calculation.getSiHash(txt1);
        int[] weightVectorList2=Calculation.getSiHash(txt2);

        //2.计算海明距离
        int distance=Calculation.calculateHammingDistance(weightVectorList1,weightVectorList2);
        //3.计算论文相似度
        double similarity=Calculation.calculateHammingSimilarity(distance);
        System.out.println("论文相似度："+similarity+"%");
    }
}
