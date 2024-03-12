import com.hankcs.hanlp.HanLP;

import java.util.Arrays;
import java.util.List;

public class CheckMain {
    public static void main(String[] args) {
        String txt=TxtHandle.txtRead("../../src/resources/orig.txt");
        //1.分词
        List<String> featureWordList= Calculation.participle(txt);
        //2.hash
        List<String> hashList= Calculation.Hash(featureWordList);
        //3.加权
        int [][]originList= Calculation.weighted(hashList);
        //4.合并
        int []weightVectorList=Calculation.merged(originList);
        //5.降维
        Calculation.dimensionalityReduction(weightVectorList);
        System.out.println(Arrays.toString(weightVectorList));
    }
}
