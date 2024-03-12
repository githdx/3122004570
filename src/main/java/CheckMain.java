import com.hankcs.hanlp.HanLP;

import java.util.Arrays;
import java.util.List;

public class CheckMain {
    public static void main(String[] args) {
        String txt1=TxtHandle.txtRead("../../src/resources/orig.txt");
        String txt2=TxtHandle.txtRead("../../src/resources/orig_0.8_add.txt");
        //1.分词
        List<String> featureWordList1= Calculation.participle(txt1);
        List<String> featureWordList2= Calculation.participle(txt2);
        //2.hash
        List<String> hashList1= Calculation.Hash(featureWordList1);
        List<String> hashList2= Calculation.Hash(featureWordList2);
        //3.加权
        int [][]originList1= Calculation.weighted(hashList1);
        int [][]originList2= Calculation.weighted(hashList2);
        //4.合并
        int []weightVectorList1=Calculation.merged(originList1);
        int []weightVectorList2=Calculation.merged(originList2);
        //5.降维
        Calculation.dimensionalityReduction(weightVectorList1);
        Calculation.dimensionalityReduction(weightVectorList2);
        //6.计算海明距离
        int distance=Calculation.calculateHammingDistance(weightVectorList1,weightVectorList2);
        //7.计算论文相似度
        double similarity=Math.round( (100-100.0*distance/256)* 100) / 100.0;
        System.out.println("论文相似度："+similarity+"%");

    }
}
